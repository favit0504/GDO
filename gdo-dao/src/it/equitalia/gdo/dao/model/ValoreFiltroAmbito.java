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
@Table(name = "GDO.FILTRO_AMBITO", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_FILTRO_AMBITO"}))

public class ValoreFiltroAmbito implements java.io.Serializable, GenericType {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_FILTRO_AMBITO", insertable=true, updatable=false)
	private int id;
	
	@Column(name="CODICE_AMBITO") 
	private Integer codiceAmbito;
	
	//La relazione ci serve solo nell'altra direzione, ma in JPA 1.0 non si puo` fare unidirezionale
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_FILTRO", nullable=false)
	private FiltroAmbito filtroAmbito;
	
	public ValoreFiltroAmbito() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodiceAmbito() {
		return codiceAmbito;
	}

	public void setCodiceAmbito(Integer codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}

	public FiltroAmbito getFiltroAmbito() {
		return filtroAmbito;
	}

	public void setFiltroAmbito(FiltroAmbito filtroAmbito) {
		this.filtroAmbito = filtroAmbito;
	}

	public String getSequenceName() {

		return "GDO.SEQ_ID_FILTRO_AMBITO";
	}


}
