package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;

public class AmbitoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2628626500920022646L;
	public Integer getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(Integer codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	private Integer codiceAmbito;
	private String descrizione;
}
