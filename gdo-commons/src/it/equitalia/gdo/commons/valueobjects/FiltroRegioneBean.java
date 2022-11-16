package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;
import java.util.List;

public class FiltroRegioneBean extends AbstractFiltroBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected List<String> valori;

	public List<String> getValori() {
		return valori;
	}

	public void setValori(List<String> valoriFiltroRegione) {
		this.valori = valoriFiltroRegione;
	}


}
