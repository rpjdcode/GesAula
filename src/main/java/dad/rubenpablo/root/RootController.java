package dad.rubenpablo.root;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Grupo;
import dad.rubenpablo.App;
import dad.rubenpablo.alumno.AlumnoController;
import dad.rubenpablo.grupo.GrupoController;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RootController implements Initializable {
	
	// Model
	private RootModel model = new RootModel();
	
	// Controllers
	private GrupoController grupoController = new GrupoController();
	private AlumnoController alumnoController = new AlumnoController();
	
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Tab alumnosTab;

    @FXML
    private Tab grupoTab;

    @FXML
    private ToolBar toolBar;

    @FXML
    private TextField toolFileNameText;

    @FXML
    private Button toolNewButton;

    @FXML
    private Button toolSaveFileButton;

    @FXML
    private BorderPane view;

    public RootController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Root.fxml"));
    	loader.setController(this);
    	loader.load();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model.setGrupo(new Grupo()); // Inicialmente, existirá un grupo vacío
		Bindings.bindBidirectional(model.nombreFicheroProperty(), toolFileNameText.textProperty());
		Bindings.bindBidirectional(model.grupoProperty(), grupoController.getModel().grupoActualProperty());
		
		model.alumnosProperty().bindBidirectional(grupoController.getModel().getGrupoActual().alumnosProperty());
		Bindings.bindBidirectional(alumnoController.getModel().alumnosProperty(), model.alumnosProperty());
		view.setCenter(grupoController.getView()); // Inicialmente, aparecerá la vista de formulario de grupo

		tabPane.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			if (nv == alumnosTab) {
				// En el centro de la vista raíz aparecerá la vista del controlador de alumnos
				view.setCenter(alumnoController.getView());
			}
			if (nv == grupoTab) {
				view.setCenter(grupoController.getView());
			}
			
		});
		
	}
	
    @FXML
    void onNewButtonAction(ActionEvent event) {
    	model.setGrupo(new Grupo());
    }

    @FXML
    void onSaveFileAction(ActionEvent event) {
    	try {
    		if (model.getNombreFichero().equals("")) {
    			App.error("Error", "Nombre de fichero inválido", "Debe especificar un nombre de fichero");
    		} else {
    			model.getGrupo().save(new File(model.getNombreFichero()));
    			App.info("Éxito", "Fichero guardado con éxito", "");
    		}

		} catch (Exception e) {
			App.error("Error", "Error guardado", "Error guardando el fichero. Causa: " + e.getMessage());
			e.printStackTrace();
		}
    }

    public BorderPane getView() {
		return view;
	}
}
