package dad.rubenpablo.root;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Grupo;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class RootModel {

	private StringProperty nombreFichero = new SimpleStringProperty();
	private ObjectProperty<Grupo> grupo = new SimpleObjectProperty<Grupo>();
	private ListProperty<Alumno> alumnos = new SimpleListProperty<Alumno>();
	
	public final ObjectProperty<Grupo> grupoProperty() {
		return this.grupo;
	}
	

	public final Grupo getGrupo() {
		return this.grupoProperty().get();
	}
	

	public final void setGrupo(final Grupo grupo) {
		this.grupoProperty().set(grupo);
	}


	public final StringProperty nombreFicheroProperty() {
		return this.nombreFichero;
	}
	


	public final String getNombreFichero() {
		return this.nombreFicheroProperty().get();
	}
	
	public final void setNombreFichero(final String nombreFichero) {
		this.nombreFicheroProperty().set(nombreFichero);
	}


	public final ListProperty<Alumno> alumnosProperty() {
		return this.alumnos;
	}
	


	public final ObservableList<Alumno> getAlumnos() {
		return this.alumnosProperty().get();
	}
	


	public final void setAlumnos(final ObservableList<Alumno> alumnos) {
		this.alumnosProperty().set(alumnos);
	}
	
	
	
	
}
