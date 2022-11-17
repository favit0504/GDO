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
@Table(name = "GDO.FILTRO_ENTE", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_FILTRO_ENTE"}))
public class ValoreFiltroEnte implements java.io.Serializable, GenericType {

	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="ID_FILTRO_ENTE", insertable=true, updatable=false)
	private Integer id;
	
	@Column(name="CODICE_ENTE")
	private Integer codiceEnte;
	
	@Column(name="TIPO_UFFICIO_ENTE")
	private String tipoUfficioEnte;
	
	@Column(name="CODICE_UFFICIO_ENTE")
	private String codiceUfficioEnte;
	

	//La relazione ci serve solo nell'altra direzione, ma in JPA 1.0 non si puo` fare unidirezionale
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_FILTRO", nullable=false)
	private FiltroEnte filtroParent;
	
	public ValoreFiltroEnte() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(Integer codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getTipoUfficioEnte() {
		return tipoUfficioEnte;
	}

	public void setTipoUfficioEnte(String tipoUfficioEnte) {
		this.tipoUfficioEnte = tipoUfficioEnte;
	}

	public String getCodiceUfficioEnte() {
		return codiceUfficioEnte;
	}

	public void setCodiceUfficioEnte(String codiceUfficioEnte) {
		this.codiceUfficioEnte = codiceUfficioEnte;
	}

	public FiltroEnte getFiltroEnte() {
		return filtroParent;
	}

	public void setFiltroEnte(FiltroEnte f) {
		this.filtroParent = f;
	}

	public String getSequenceName() {

		return "GDO.SEQ_ID_FILTRO_ENTE";
	}


}
