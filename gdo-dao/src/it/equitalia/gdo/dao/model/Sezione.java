package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.EntitaVersionabile;
import it.equitalia.gdo.dao.model.generic.GenericType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
@Entity
@Table(name = "GDO.SEZIONE")
public class Sezione extends EntitaVersionabile implements Serializable, GenericType {
	private final static String SEQUENCE_NAME = "GDO.SEQ_ID_SEZIONE";
	private static final long serialVersionUID = 1L;
		
	@Column(name="TITOLO") private String titolo;
	@Column(name="DESCRIZIONE") private String descrizione;
	@Column(name="STATO") private Integer stato;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="sezione")
	private List<SezioneUtenza> valori;
	
    @OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="sezione")
	private List<Documento> documenti;
	

	public Sezione() {
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

	public List<Documento> getDocumenti() {
		return documenti;
	}

	public void setDocumenti(List<Documento> documenti) {
		this.documenti = documenti;
	}
	
	public List<SezioneUtenza> getValoriSezioneUtenza() {
		return valori;
	}

	public void setValoriSezioneUtenza(List<SezioneUtenza> valori) {
		this.valori = valori;
	}
	
	/**
	 * Se non si aggiungono i valori tramite questo metodo
	 *  non prende la FK quando salva su database su FILTRO_PROVINCIA
	 *  il valore di ID_FILTRO, "filtro padre" 
	 */
	public void aggiungiValoreUtenzaConRiferimentoAllaSezione(SezioneUtenza entitaValore) {

		if (valori == null)
			valori = new ArrayList<SezioneUtenza>();
		
		entitaValore.setSezione(this);
		valori.add(entitaValore);
		
	}

	public String getSequenceName() {
		
		return SEQUENCE_NAME;
	}
	

}
