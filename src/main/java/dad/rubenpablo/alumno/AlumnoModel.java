package dad.rubenpablo.alumno;

import dad.gesaula.ui.model.Alumno;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class AlumnoModel {
	
	private ObjectProperty<Alumno> alumnoSeleccionado = new SimpleObjectProperty<Alumno>();
	private IntegerProperty indiceSeleccionado = new SimpleIntegerProperty(-1);
	private ListProperty<Alumno> alumnos = new SimpleListProperty<Alumno>();
	private ObjectProperty<Alumno> alumnoEditar = new SimpleObjectProperty<Alumno>();
	
	public final ObjectProperty<Alumno> alumnoSeleccionadoProperty() {
		return this.alumnoSeleccionado;
	}
	
	public final Alumno getAlumnoSeleccionado() {
		return this.alumnoSeleccionadoProperty().get();
	}
	
	public final void setAlumnoSeleccionado(final Alumno alumnoSeleccionado) {
		this.alumnoSeleccionadoProperty().set(alumnoSeleccionado);
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

	public final IntegerProperty indiceSeleccionadoProperty() {
		return this.indiceSeleccionado;
	}
	

	public final int getIndiceSeleccionado() {
		return this.indiceSeleccionadoProperty().get();
	}
	

	public final void setIndiceSeleccionado(final int indiceSeleccionado) {
		this.indiceSeleccionadoProperty().set(indiceSeleccionado);
	}

	public final ObjectProperty<Alumno> alumnoEditarProperty() {
		return this.alumnoEditar;
	}
	

	public final Alumno getAlumnoEditar() {
		return this.alumnoEditarProperty().get();
	}
	

	public final void setAlumnoEditar(final Alumno alumnoEditar) {
		this.alumnoEditarProperty().set(alumnoEditar);
	}
	
	
	
	
	
	
	
}
