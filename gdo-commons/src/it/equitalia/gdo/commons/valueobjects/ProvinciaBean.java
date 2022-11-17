package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;

public class ProvinciaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7321969573014441419L;
	private String codice;

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	private String descrizione;

}
