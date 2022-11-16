package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class BlobAllegatoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idBlobAllegato;
	private byte[] contenutoFile;

	public BlobAllegatoBean() {
		
	}

	public Integer getIdBlobAllegato() {
		return idBlobAllegato;
	}

	public void setIdBlobAllegato(Integer idBlobAllegato) {
		this.idBlobAllegato = idBlobAllegato;
	}

	public byte[] getContenutoFile() {
		return contenutoFile;
	}

	public void setContenutoFile(byte[] contenutoFile) {
		this.contenutoFile = contenutoFile;
	}



}
