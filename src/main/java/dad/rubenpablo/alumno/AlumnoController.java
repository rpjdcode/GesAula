package dad.rubenpablo.alumno;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import dad.rubenpablo.App;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
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
		model.setAlumnoSeleccionado(new Alumno());
		splitRightSide.setTop(null);
		splitRightSide.setCenter(infoLabel);
		
		sexoCombo.getItems().add(Sexo.HOMBRE);
		sexoCombo.getItems().add(Sexo.MUJER);
		
		model.indiceSeleccionadoProperty().bind(alumnosTable.getSelectionModel().selectedIndexProperty());
		model.indiceSeleccionadoProperty().addListener((o, ov, nv) -> {
			if (nv.intValue() > -1) {
				splitRightSide.setTop(alumnoForm);
				splitRightSide.setCenter(null);
				model.setAlumnoSeleccionado(alumnosTable.getItems().get(nv.intValue()));
			}
			if (nv.intValue() < 0) {
				splitRightSide.setTop(null);
				splitRightSide.setCenter(infoLabel);
				model.setAlumnoSeleccionado(new Alumno());
			}
			
		});
		
		

		// Bindings.createBooleanBinding(() -> model.getIndiceSeleccionado() >= 0, model.indiceSeleccionadoProperty());
		
		// Tabla
		alumnosTable.itemsProperty().bindBidirectional(model.alumnosProperty());
		alumnosTable.setRowFactory(tv -> {
			TableRow<Alumno> row = new TableRow<Alumno>();
			row.setOnMouseClicked((e -> {
				if (row.isEmpty()) {
					nombreText.textProperty().unbind();
					alumnosTable.getSelectionModel().clearSelection();
				} else {
					
					model.setAlumnoSeleccionado(row.getItem());
					nombreText.textProperty().bindBidirectional(model.getAlumnoSeleccionado().nombreProperty());
				}
			}));
			return row;
		});
		// Cell Values
		nombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		apellidosColumn.setCellValueFactory(v -> v.getValue().apellidosProperty());
		fnacColumn.setCellValueFactory(v -> v.getValue().fechaNacimientoProperty());
		sexoColumn.setCellValueFactory(v -> v.getValue().sexoProperty());
		repiteColumn.setCellValueFactory(v -> v.getValue().repiteProperty());
		
		// Cell Factories
		repiteColumn.setCellFactory(CheckBoxTableCell.forTableColumn(repiteColumn));
		
		// Bindings para vincular el formulario de edición de alumno
		model.alumnoEditarProperty().bindBidirectional(model.alumnoSeleccionadoProperty());
		model.alumnoEditarProperty().addListener((o, ov, nv) ->{
			System.out.println("Antes valía " + ov + " y ahora valgo " + nv);
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

//    @FXML
//    void onTableClick(MouseEvent event) {
//    	PickResult resultado = event.getPickResult();
//    	Node nodo = resultado.getIntersectedNode();
//    	System.out.println(nodo);
//    	System.out.println("Type Selector: " + nodo.getTypeSelector());
//
//    	if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() >= 1) {
//        	if (model.getIndiceSeleccionado() >= 0 && !nodo.getTypeSelector().equals("LabeledText")) {
//        		alumnosTable.getSelectionModel().clearSelection();
//        	}
//    	}
//    	
//    }

    public GridPane getView() {
		return view;
	}
    
    public AlumnoModel getModel() {
		return model;
	}


}

