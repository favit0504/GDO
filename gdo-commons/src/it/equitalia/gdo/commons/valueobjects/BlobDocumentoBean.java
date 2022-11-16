package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class BlobDocumentoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idBlobDocumento;
	private byte[] contenutoFile;

	public BlobDocumentoBean() {
		
	}

	public Integer getIdBlobDocumento() {
		return idBlobDocumento;
	}

	public void setIdBlobDocumento(Integer idBlobDocumento) {
		this.idBlobDocumento = idBlobDocumento;
	}

	public byte[] getContenutoFile() {
		return contenutoFile;
	}

	public void setContenutoFile(byte[] contenutoFile) {
		this.contenutoFile = contenutoFile;
	}



}
