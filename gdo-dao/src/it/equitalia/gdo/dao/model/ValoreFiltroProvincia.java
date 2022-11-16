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
@Table(name = "GDO.FILTRO_PROVINCIA", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_FILTRO_PROVINCIA"}))

public class ValoreFiltroProvincia implements java.io.Serializable, GenericType {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_FILTRO_PROVINCIA", insertable=true, updatable=false)
	private int id;
	
	@Column(name="CODICE_PROVINCIA") 
	private String codiceProvincia;
	
	//La relazione ci serve solo nell'altra direzione, ma in JPA 1.0 non si puo` fare unidirezionale
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_FILTRO", nullable=false)
	private FiltroProvincia filtroParent;
	
	public ValoreFiltroProvincia() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodiceProvincia() {
		return codiceProvincia;
	}

	public void setCodiceProvincia(String codice) {
		this.codiceProvincia = codice;
	}
	
	public FiltroProvincia getFiltroProvincia() {
		return filtroParent;
	}

	public void setFiltroProvincia(FiltroProvincia f) {
		this.filtroParent = f;
	}

	public String getSequenceName() {

		return "GDO.SEQ_ID_FILTRO_PROVINCIA";
	}


}
