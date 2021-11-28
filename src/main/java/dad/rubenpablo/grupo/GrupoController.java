package dad.rubenpablo.grupo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Grupo;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GrupoController implements Initializable{

	private GrupoModel model = new GrupoModel();
	
    @FXML
    private HBox actitudProgressBox;

    @FXML
    private Label actitudProgressLabel;

    @FXML
    private Slider actitudSlider;

    @FXML
    private TextField denominacionText;

    @FXML
    private HBox examenesProgressBox;

    @FXML
    private Label examenesProgressLabel;

    @FXML
    private Slider examenesSlider;

    @FXML
    private DatePicker finCursoDP;

    @FXML
    private DatePicker inicioCursoDP;

    @FXML
    private HBox practicasProgressBox;

    @FXML
    private Label practicasProgressLabel;

    @FXML
    private Slider practicasSlider;

    @FXML
    private GridPane view;

    public GrupoController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grupo.fxml"));
    	loader.setController(this);
    	loader.load();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model.setGrupoActual(new Grupo());
		
		actitudProgressLabel.textProperty().bind(actitudSlider.valueProperty().asString("%.2f").concat(" %"));
		examenesProgressLabel.textProperty().bind(examenesSlider.valueProperty().asString("%.2f").concat(" %"));
		practicasProgressLabel.textProperty().bind(practicasSlider.valueProperty().asString("%.2f").concat(" %"));
		
		bindAll();
		
		model.grupoActualProperty().addListener((o, ov, nv) -> {
			unbindAll();
			System.out.println("GrupoController: Cambio en grupo actual. Antes " + ov + " ahora " + nv);
			bindAll();
		});
		
		
	}
    
    @FXML
    void onDragActitudAction(MouseEvent event) {

    }

    @FXML
    void onDragExamenesAction(MouseEvent event) {

    }

    @FXML
    void onDragPracticasAction(MouseEvent event) {

    }
    
    public GrupoModel getModel() {
		return model;
	}
    
    public GridPane getView() {
		return view;
	}


    private void unbindAll() {
    	denominacionText.textProperty().unbindBidirectional(model.getGrupoActual().denominacionProperty());
    	inicioCursoDP.valueProperty().unbindBidirectional(model.getGrupoActual().iniCursoProperty());
    	finCursoDP.valueProperty().unbindBidirectional(model.getGrupoActual().finCursoProperty());
    	examenesSlider.valueProperty().unbindBidirectional(model.getGrupoActual().getPesos().examenesProperty());
    	actitudSlider.valueProperty().unbindBidirectional(model.getGrupoActual().getPesos().actitudProperty());
    	practicasSlider.valueProperty().unbindBidirectional(model.getGrupoActual().getPesos().practicasProperty());

    }
    
    private void bindAll() {
		Bindings.bindBidirectional(denominacionText.textProperty(), model.getGrupoActual().denominacionProperty());
		Bindings.bindBidirectional(inicioCursoDP.valueProperty(), model.getGrupoActual().iniCursoProperty());
		Bindings.bindBidirectional(finCursoDP.valueProperty(), model.getGrupoActual().finCursoProperty());
		Bindings.bindBidirectional(examenesSlider.valueProperty(), model.getGrupoActual().getPesos().examenesProperty());
		Bindings.bindBidirectional(actitudSlider.valueProperty(), model.getGrupoActual().getPesos().actitudProperty());
		Bindings.bindBidirectional(practicasSlider.valueProperty(), model.getGrupoActual().getPesos().practicasProperty());
    }

}

