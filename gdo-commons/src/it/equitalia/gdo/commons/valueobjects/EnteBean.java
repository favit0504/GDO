package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;

public class EnteBean implements Serializable {

	private static final long serialVersionUID = -1654995833055014963L;
	
	private String codiceUfficio;
	private int codiceEnte;
	private String tipoUfficio;
	
	public String getTipoUfficio() {
		return tipoUfficio;
	}
	public void setTipoUfficio(String tipoEnte) {
		this.tipoUfficio = tipoEnte;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public int getCodiceEnte() {
		return codiceEnte;
	}
	public void setCodiceEnte(int codiceEnte) {
		this.codiceEnte = codiceEnte;
	}
	
	@Override
	public String toString() {
		return "[" + codiceEnte + "," + tipoUfficio + "," + codiceUfficio + "]";
	}
}
