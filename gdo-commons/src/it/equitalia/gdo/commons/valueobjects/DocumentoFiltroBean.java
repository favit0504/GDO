package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class DocumentoFiltroBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idDocumento;
	private Integer idFiltro;
	
	public DocumentoFiltroBean() {
		
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Integer getIdFiltro() {
		return idFiltro;
	}

	public void setIdFiltro(Integer idFiltro) {
		this.idFiltro = idFiltro;
	}

	

}
