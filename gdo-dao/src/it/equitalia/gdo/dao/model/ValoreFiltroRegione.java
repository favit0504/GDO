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
@Table(name = "GDO.FILTRO_REGIONE", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_FILTRO_REGIONE"}))

public class ValoreFiltroRegione implements java.io.Serializable, GenericType {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_FILTRO_REGIONE", insertable=true, updatable=false)
	private int id;
	
	@Column(name="CODICE_REGIONE") 
	private String codiceRegione;
	
	//La relazione ci serve solo nell'altra direzione, ma in JPA 1.0 non si puo` fare unidirezionale
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_FILTRO", nullable=false)
	private FiltroRegione filtroRegione;
	
	public ValoreFiltroRegione() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodiceRegione() {
		return codiceRegione;
	}

	public void setCodiceRegione(String codiceRegione) {
		this.codiceRegione = codiceRegione;
	}

	public FiltroRegione getFiltroRegione() {
		return filtroRegione;
	}

	public void setFiltroRegione(FiltroRegione filtroRegione) {
		this.filtroRegione = filtroRegione;
	}

	public String getSequenceName() {

		return "GDO.SEQ_ID_FILTRO_REGIONE";
	}


}
