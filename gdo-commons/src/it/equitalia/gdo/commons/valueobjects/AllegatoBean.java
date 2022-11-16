package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class AllegatoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String titolo;

	private byte[] contenutoFile;
	
	private String nomeFile;
	
	public AllegatoBean() {	
	}

	public String getTitolo() {
		return titolo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public byte[] getContenutoFile() {
		return contenutoFile;
	}

	public void setContenutoFile(byte[] contenutoFile) {
		this.contenutoFile = contenutoFile;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	
}
