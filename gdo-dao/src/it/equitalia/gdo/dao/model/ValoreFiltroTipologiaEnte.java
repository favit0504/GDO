package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.GenericType;

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
@Table(name = "GDO.FILTRO_TIPOLOGIA_ENTE", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_FILTRO_TIPOLOGIA_ENTE"}))
public class ValoreFiltroTipologiaEnte implements java.io.Serializable, GenericType {

	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="ID_FILTRO_TIPOLOGIA_ENTE", insertable=true, updatable=false)
	private Integer id;
	
	@Column(name="TIPOLOGIA_ENTE")
	private String tipologiaEnte;
	
	

	//La relazione ci serve solo nell'altra direzione, ma in JPA 1.0 non si puo` fare unidirezionale
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_FILTRO", nullable=false)
	private FiltroTipologiaEnte filtroParent;
	
	public ValoreFiltroTipologiaEnte() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getTipologiaEnte() {
		return tipologiaEnte;
	}

	public void setTipologiaEnte(String tipologiaEnte) {
		this.tipologiaEnte = tipologiaEnte;
	}

	public FiltroTipologiaEnte getFiltroTipologiaEnte() {
		return filtroParent;
	}

	public void setFiltroTipologiaEnte(FiltroTipologiaEnte f) {
		this.filtroParent = f;
	}

	public String getSequenceName() {

		return "GDO.SEQ_ID_FILTRO_TIPOLOGIA_ENTE";
	}


}
