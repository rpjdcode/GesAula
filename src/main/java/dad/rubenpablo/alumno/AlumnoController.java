package dad.rubenpablo.alumno;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import dad.rubenpablo.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AlumnoController implements Initializable{

	// Model
	private AlumnoModel model = new AlumnoModel();
	
    @FXML
    private HBox alumnoBotonera;

    @FXML
    private GridPane alumnoForm;

    @FXML
    private TableView<Alumno> alumnosTable;

    @FXML
    private TableColumn<Alumno, String> apellidosColumn;

    @FXML
    private TextField apellidosText;

    @FXML
    private Button eliminarButton;

    @FXML
    private TableColumn<Alumno, LocalDate> fnacColumn;

    @FXML
    private DatePicker fnacPicker;

    @FXML
    private TableColumn<Alumno, String> nombreColumn;

    @FXML
    private TextField nombreText;

    @FXML
    private Button nuevoButton;

    @FXML
    private CheckBox repiteCheck;

    @FXML
    private TableColumn<Alumno, Boolean> repiteColumn;

    @FXML
    private TableColumn<Alumno, Sexo> sexoColumn;

    @FXML
    private ComboBox<Sexo> sexoCombo;

    @FXML
    private VBox splitLeftSide;

    @FXML
    private SplitPane splitPane;

    @FXML
    private BorderPane splitRightSide;

    @FXML
    private GridPane view;
    
    private Label infoLabel = new Label("Seleccione un alumno en la tabla de la izquierda");

    public AlumnoController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Alumno.fxml"));
    	loader.setController(this);
    	loader.load();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Inicializaciones
		splitRightSide.setTop(null);
		splitRightSide.setCenter(infoLabel);
		
		sexoCombo.getItems().add(Sexo.HOMBRE);
		sexoCombo.getItems().add(Sexo.MUJER);
		

		// Bindings.createBooleanBinding(() -> model.getIndiceSeleccionado() >= 0, model.indiceSeleccionadoProperty());
		
		// Tabla
		alumnosTable.itemsProperty().bindBidirectional(model.alumnosProperty());
		
		alumnosTable.setRowFactory(this::createTableRow);
		// Cell Values
		nombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		apellidosColumn.setCellValueFactory(v -> v.getValue().apellidosProperty());
		fnacColumn.setCellValueFactory(v -> v.getValue().fechaNacimientoProperty());
		sexoColumn.setCellValueFactory(v -> v.getValue().sexoProperty());
		repiteColumn.setCellValueFactory(v -> v.getValue().repiteProperty());
		
		// Cell Factories
		repiteColumn.setCellFactory(CheckBoxTableCell.forTableColumn(repiteColumn));
		
		model.alumnoSeleccionadoProperty().bind(alumnosTable.getSelectionModel().selectedItemProperty());
		
		alumnosTable.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
			if (nv.intValue() > -1) {
				System.out.println("índice seleccionado");
				mostrarAluForm();
			}
			if (nv.intValue() < 0) {
				System.out.println("Índice no seleccionado");
				cerrarAluForm();
			}
		});
		
		
	}
    
    @FXML
    void onEliminarButtonAction(ActionEvent event) {
    	if (model.getIndiceSeleccionado() == -1) {
    		App.error("Error", "Alumno no seleccionado", "Debe seleccionar un alumno para poder eliminarlo");
    	} else {
    		model.alumnosProperty().remove(model.getAlumnos().get(model.getIndiceSeleccionado()));
    	}
    }

    @FXML
    void onNuevoButtonAction(ActionEvent event) {
    	Alumno nuevo = new Alumno();
    	nuevo.setNombre("Sin nombre");
    	nuevo.setApellidos("Sin apellidos");
    	model.alumnosProperty().add(nuevo);
    }

    public GridPane getView() {
		return view;
	}
    
    public AlumnoModel getModel() {
		return model;
	}

    
    private TableRow<Alumno> createTableRow(TableView<Alumno> tv) {
    	TableRow<Alumno> row = new TableRow<Alumno>();
		row.setOnMouseClicked((e -> {
			
			// Si la fila clickeada está vacía y no existe alumno a editar
			if (row.isEmpty() && model.getAlumnoEditar() == null) {
				cerrarAluForm();
				return ;
			}
			// Si la fila clickeada está vacía y existe un alumno a editar ...
			if (row.isEmpty() && model.getAlumnoEditar() != null) {
				guardarCambios(model.getIndiceSeleccionado());
				desvincularForm();
				model.setAlumnoEditar(null);
				return;
				
			}
			
			// Si se clickea en una fila que no está vacía y no existe un alumno a editar...
			if (!row.isEmpty() && model.getAlumnoEditar() == null) {
				model.setAlumnoEditar(row.getItem()); // Asignamos el alumno a editar
				model.setIndiceSeleccionado(row.getIndex());
				vincularForm();
				return;
			}
			
			// Si se clickea en una fila que no está vacía y existe un alumno a editar...
			if (!row.isEmpty() && model.getAlumnoEditar() != null) {
				guardarCambios(model.getIndiceSeleccionado());
				desvincularForm();
				model.setAlumnoEditar(row.getItem());
				model.setIndiceSeleccionado(row.getIndex());
				vincularForm();
				return;
			}
			
			
			
		}));
		return row;
    }
    
    private void mostrarAluForm() {
		splitRightSide.setTop(alumnoForm);
		splitRightSide.setCenter(null);
    }
    
    private void cerrarAluForm() {
    	alumnosTable.getSelectionModel().clearSelection();
		splitRightSide.setTop(null);
		splitRightSide.setCenter(infoLabel);
    }
    
    private void vincularForm() {
    	nombreText.textProperty().bindBidirectional(model.getAlumnoEditar().nombreProperty());
    	apellidosText.textProperty().bindBidirectional(model.getAlumnoEditar().apellidosProperty());
    	fnacPicker.valueProperty().bindBidirectional(model.getAlumnoEditar().fechaNacimientoProperty());
    	sexoCombo.valueProperty().bindBidirectional(model.getAlumnoEditar().sexoProperty());
    	repiteCheck.selectedProperty().bindBidirectional(model.getAlumnoEditar().repiteProperty());
    }
    
    private void desvincularForm() {
    	nombreText.textProperty().unbindBidirectional(model.getAlumnoEditar().nombreProperty());
    	apellidosText.textProperty().unbindBidirectional(model.getAlumnoEditar().apellidosProperty());
    	fnacPicker.valueProperty().unbindBidirectional(model.getAlumnoEditar().fechaNacimientoProperty());
    	sexoCombo.valueProperty().unbindBidirectional(model.getAlumnoEditar().sexoProperty());
    	repiteCheck.selectedProperty().unbindBidirectional(model.getAlumnoEditar().repiteProperty());
    }
    
    private void guardarCambios(int indice) {
    	model.getAlumnos().get(indice).setNombre(nombreText.getText());
    	model.getAlumnos().get(indice).setApellidos(apellidosText.getText());
    	model.getAlumnos().get(indice).setFechaNacimiento(fnacPicker.getValue());
    	model.getAlumnos().get(indice).setSexo(sexoCombo.getValue());
    	model.getAlumnos().get(indice).setRepite(repiteCheck.isSelected());
    }

}

