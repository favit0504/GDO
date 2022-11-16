package it.equitalia.gdo.dao.model.generic;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name = "GDO.FILTRO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_FILTRO", discriminatorType=DiscriminatorType.STRING)
public class AbstractFiltro implements java.io.Serializable, GenericType {
private static final String SEQUENCE = "SEQ_ID_FILTRO";
	private static final long serialVersionUID = 1L;
	public String getSequenceName() {
		return SEQUENCE;
		
	}
	public static enum TIPO_FILTRO {
		FiltroEnte, FiltroServizioEnte, FiltroServizioAgente, FiltroSocieta, 
		//FiltroTipologia,
		FiltroTipologiaEnte,FiltroRegioneAgente, FiltroRegioneEnte,FiltroProvinciaEnte,
		FiltroAmbito
		
		; 
	}
	
	@Id
	@Column(name="ID_FILTRO", insertable=true, updatable=false)
	private Integer id;
	
	@Column(name="TIPO_FILTRO") 
	protected String tipoFiltro;

	public AbstractFiltro() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(String tipo) {
		this.tipoFiltro = tipo;
	}



}
