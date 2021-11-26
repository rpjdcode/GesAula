package dad.rubenpablo.grupo;

import dad.gesaula.ui.model.Grupo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class GrupoModel {
	
	private ObjectProperty<Grupo> grupoActual = new SimpleObjectProperty<Grupo>();

	public final ObjectProperty<Grupo> grupoActualProperty() {
		return this.grupoActual;
	}
	

	public final Grupo getGrupoActual() {
		return this.grupoActualProperty().get();
	}
	

	public final void setGrupoActual(final Grupo grupoActual) {
		this.grupoActualProperty().set(grupoActual);
	}
	
	
	
	
	
}
