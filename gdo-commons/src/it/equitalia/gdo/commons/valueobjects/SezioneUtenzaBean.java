package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class SezioneUtenzaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<String> valori;

	public List<String> getValori() {
		return valori;
	}

	public void setValori(List<String> valori) {
		this.valori = valori;
	}


}
