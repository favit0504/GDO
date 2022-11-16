package it.equitalia.gdo.dao.model;

import java.io.Serializable;

public class Ente implements Serializable {

	private static final long serialVersionUID = -6282593514612555331L;
	
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
	
}
