package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class FiltroEnteBean extends AbstractFiltroBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer codiceEnte;
	private String tipoUfficioEnte;
	private String codiceUfficioEnte;
	


	public Integer getCodiceEnte() {
		return codiceEnte;
	}


	public void setCodiceEnte(Integer codiceEnte) {
		this.codiceEnte = codiceEnte;
	}


	public String getTipoUfficioEnte() {
		return tipoUfficioEnte;
	}


	public void setTipoUfficioEnte(String tipoUfficioEnte) {
		this.tipoUfficioEnte = tipoUfficioEnte;
	}


	public String getCodiceUfficioEnte() {
		return codiceUfficioEnte;
	}


	public void setCodiceUfficioEnte(String codiceUfficioEnte) {
		this.codiceUfficioEnte = codiceUfficioEnte;
	}




}
