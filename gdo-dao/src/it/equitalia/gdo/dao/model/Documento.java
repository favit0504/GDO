package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.AbstractFiltro;
import it.equitalia.gdo.dao.model.generic.EntitaVersionabile;
import it.equitalia.gdo.dao.model.generic.GenericType;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
@Entity
@Table(name = "GDO.DOCUMENTO", uniqueConstraints = @UniqueConstraint(columnNames = {"ID"}))
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Documento extends EntitaVersionabile implements Serializable, GenericType {
	
	private static final String SEQUENCE_NAME = "GDO.SEQ_ID_DOCUMENTO";
	private static final long serialVersionUID = 1L;
	
	@Column(name="TITOLO") private String titolo;
	@Column(name="DESCRIZIONE") private String descrizione;
	@Column(name="STATO") private Integer stato;
	
	@Column(name="ENTE")
	private Boolean ente;
	
	@Column(name="AGENTE")
	private Boolean agente;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="ID_BLOB", nullable=false)
	private BlobDocumento blob;
	
	@Column(name="NOME_FILE")
	private String nomeFile;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_SEZIONE", nullable=false)
	private Sezione sezione;
	
	@Column(name="SVECCHIATO")
	private Boolean svecchiato;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinTable(	name = "GDO.DOCUMENTO_FILTRO" , joinColumns = @JoinColumn(name = "ID_DOCUMENTO") ,
				inverseJoinColumns = @JoinColumn(name= "ID_FILTRO",  referencedColumnName = "ID_FILTRO")	)
	private List<AbstractFiltro> filtri;

	
	
	public Documento() {
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

//	public Date getDataInizioValidita() {
//		return dataInizioValidita;
//	}
//
//	public void setDataInizioValidita(Date dataInizioValidita) {
//		this.dataInizioValidita = dataInizioValidita;
//	}
//
//	public Date getDataFineValidita() {
//		return dataFineValidita;
//	}
//
//	public void setDataFineValidita(Date dataFineValidita) {
//		this.dataFineValidita = dataFineValidita;
//	}
	
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
	
	public BlobDocumento getBlob() {
		return blob;
	}

	public void setBlob(BlobDocumento blob) {
		this.blob = blob;
	}
	
	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public Sezione getSezione() {
		return sezione;
	}

	public void setSezione(Sezione sezione) {
		this.sezione = sezione;
	}

	public List<AbstractFiltro> getFiltri() {
		return filtri;
	}

	public void setFiltri(List<AbstractFiltro> filtri) {
		this.filtri = filtri;
	}

	public Boolean getSvecchiato() {
		return svecchiato;
	}

	public void setSvecchiato(Boolean svecchiato) {
		this.svecchiato = svecchiato;
	}

	public String getSequenceName() {
		return SEQUENCE_NAME;
	}
	

}
