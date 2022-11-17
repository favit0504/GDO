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
@Table(name = "GDO.FILTRO_SERVIZIO", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_FILTRO_SERVIZIO"}))

public class ValoreFiltroServizio implements java.io.Serializable, GenericType {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_FILTRO_SERVIZIO", insertable=true, updatable=false)
	private int id;
	
	@Column(name="CODICE_SERVIZIO") 
	private String codiceServizio;
	
	//La relazione ci serve solo nell'altra direzione, ma in JPA 1.0 non si puo` fare unidirezionale
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_FILTRO", nullable=false)
	private FiltroServizio filtroParent;
	
	public ValoreFiltroServizio() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codice) {
		this.codiceServizio = codice;
	}

	public FiltroServizio getFiltroServizio() {
		return filtroParent;
	}

	public void setFiltroServizio(FiltroServizio f) {
		this.filtroParent = f;
	}

	public String getSequenceName() {

		return "GDO.SEQ_ID_FILTRO_SERVIZIO";
	}


}
