package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class SezioneBean extends BeanVersionabile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String titolo;
	private String descrizione;
	
	private Integer stato;
	private String descrizioneStato;
	
	private List<String> utenti;
	
	private List<DocumentoBean> documenti;
	private List<DocumentoBean> documentiValidi;
	
	
	public SezioneBean() {
		
	}

	
	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getStato() {
		return stato;
	}

	public void setStato(Integer stato) {
		this.stato = stato;
	}

	public List<DocumentoBean> getDocumenti() {
		return documenti;
	}

	public void setDocumenti(List<DocumentoBean> documenti) {
		this.documenti = documenti;
	}


	public List<String> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<String> utenti) {
		this.utenti = utenti;
	}

	public String getDescrizioneStato() {
		return descrizioneStato;
	}

	public void setDescrizioneStato(String descrizioneStato) {
		this.descrizioneStato = descrizioneStato;
	}

	public List<DocumentoBean> getDocumentiValidi() {
		return documentiValidi;
	}

	public void setDocumentiValidi(List<DocumentoBean> documentiValidi) {
		this.documentiValidi = documentiValidi;
	}


}
