package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.AbstractFiltro;
import it.equitalia.gdo.dao.model.generic.EntitaVersionabile;
import it.equitalia.gdo.dao.model.generic.GenericType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
@Entity
@Table(name = "GDO.NEWS")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class News extends EntitaVersionabile implements Serializable, GenericType {
	private final static String SEQUENCE_NAME = "GDO.SEQ_ID_NEWS";
	private static final long serialVersionUID = 1L;
	
	@Column(name="TITOLO") private String titolo;
	@Column(name="TESTO") private String testo;
	@Column(name="STATO") private Integer stato;
	
	@Column(name="DATA_INIZIO_PUBBLICAZIONE") @Temporal(TemporalType.DATE) private Date dataInizioPubblicazione;
	@Column(name="DATA_FINE_PUBBLICAZIONE") @Temporal(TemporalType.DATE) private Date dataFinePubblicazione;
	
	@Column(name="ENTE")
	private Boolean ente;
	
	@Column(name="AGENTE")
	private Boolean agente;
	
	@Column(name="SVECCHIATA")
	private Boolean svecchiata;
	
	@Column(name="FLG_POP_UP")
	private Boolean visualizzaPopUp;

	@OneToOne(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="news")
	private Allegato allegato;	

	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinTable(	name = "GDO.NEWS_FILTRO" , joinColumns = @JoinColumn(name = "ID_NEWS") ,
				inverseJoinColumns = @JoinColumn(name= "ID_FILTRO",  referencedColumnName = "ID_FILTRO")	)
	private List<AbstractFiltro> filtri;
	
	
	
	public News() {
	}


	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Integer getStato() {
		return stato;
	}

	public void setStato(Integer stato) {
		this.stato = stato;
	}

	public Date getDataInizioPubblicazione() {
		return dataInizioPubblicazione;
	}

	public void setDataInizioPubblicazione(Date dataInizioPubblicazione) {
		this.dataInizioPubblicazione = dataInizioPubblicazione;
	}

	public Date getDataFinePubblicazione() {
		return dataFinePubblicazione;
	}

	public void setDataFinePubblicazione(Date dataFinePubblicazione) {
		this.dataFinePubblicazione = dataFinePubblicazione;
	}
	
	public Boolean getEnte() {
		return ente;
	}
	
	public void setEnte(Boolean ente) {
		this.ente = ente;
	}

	public Boolean getAgente() {
		return agente;
	}

	public void setAgente(Boolean agente) {
		this.agente = agente;
	}
	
	public void setFiltri(List<AbstractFiltro> filtri) {
		this.filtri = filtri;
	}

	public List<AbstractFiltro> getFiltri() {
		return filtri;
	}

	public Allegato getAllegato() {
		return allegato;
	}

	public void setAllegato(Allegato allegato) {
		allegato.setNews(this);
		this.allegato = allegato;
	}

	public Boolean getSvecchiata() {
		return svecchiata;
	}

	public void setSvecchiata(Boolean svecchiata) {
		this.svecchiata = svecchiata;
	}

	public String getSequenceName() {
		
		return  SEQUENCE_NAME;
	}


	public void setVisualizzaPopUp(Boolean visualizzaPopUp) {
		this.visualizzaPopUp = visualizzaPopUp;
	}


	public Boolean getVisualizzaPopUp() {
		return visualizzaPopUp;
	}
	
}
