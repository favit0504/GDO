package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.GenericType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "GDO.FILTRO_SOCIETA", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_FILTRO_SOCIETA"}))
public class ValoreFiltroSocieta implements java.io.Serializable, GenericType {

	private static final String SEQ_ID_FILTRO_SOCIETA = "GDO.SEQ_ID_FILTRO_SOCIETA";

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_FILTRO_SOCIETA", insertable=true, updatable=false)
	private int id;
	
	@Column(name="CODICE_SOCIETA") 
	private Integer codiceSocieta;
	
	//La relazione ci serve solo nell'altra direzione, ma in JPA 1.0 non si puo` fare unidirezionale
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_FILTRO", nullable=false)
	private FiltroSocieta filtroParent;
	
	public ValoreFiltroSocieta() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(Integer codice) {
		this.codiceSocieta = codice;
	}
	
	public FiltroSocieta getFiltroSocieta() {
		return filtroParent;
	}

	public void setFiltroSocieta(FiltroSocieta f) {
		this.filtroParent = f;
	}

	public String getSequenceName() {

		return SEQ_ID_FILTRO_SOCIETA;
	}


}
