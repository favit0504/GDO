package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;


public abstract class AbstractFiltroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idFiltro;
	//private String tipoFiltro;

	
	public AbstractFiltroBean() {
		
	}

	public Integer getIdFiltro() {
		return idFiltro;
	}


	public void setIdFiltro(Integer idFiltro) {
		this.idFiltro = idFiltro;
	}


//	public String getTipoFiltro() {
//		return tipoFiltro;
//	}
//
//
//	public void setTipoFiltro(String t) {
//		this.tipoFiltro = t;
//	}
	
}
