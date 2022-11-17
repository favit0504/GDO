package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.GenericType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
@Entity
@Table(name = "GDO.ALLEGATO", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_ALLEGATO"}))
public class Allegato implements java.io.Serializable, GenericType {
	private final static String SEQUENCE_NAME = "GDO.SEQ_ID_ALLEGATO";
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name="ID_ALLEGATO", insertable=true, updatable=false)
	private Integer id;
	
	@Column(name="TITOLO")
	private String titolo;
	
	@Column(name="NOME_FILE")
	private String nomeFile;
	
	
	

	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="ID_BLOB", nullable=false)
	private BlobAllegato blob;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_NEWS", nullable=false)
	private News news;

	
	public Allegato() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idAllegato) {
		this.id = idAllegato;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public BlobAllegato getBlob() {
		return blob;
	}

	public void setBlob(BlobAllegato blob) {		
		blob.setAllegato(this);
		this.blob = blob;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getSequenceName() {
		return SEQUENCE_NAME;
	}

}
