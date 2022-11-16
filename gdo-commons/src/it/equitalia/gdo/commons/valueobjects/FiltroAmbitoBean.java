package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class FiltroAmbitoBean extends AbstractFiltroBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Integer> valori;

	public List<Integer> getValori() {
		return valori;
	}

	public void setValori(List<Integer> valori) {
		this.valori = valori;
	}


}