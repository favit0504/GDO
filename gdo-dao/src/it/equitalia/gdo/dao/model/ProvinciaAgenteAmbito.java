package it.equitalia.gdo.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WE1_AGENTEAMBITO")
public class ProvinciaAgenteAmbito implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="C_PROV")
	private String codiceProvincia; 

	public String getCodiceProvincia() {
		return codiceProvincia;
	}

	public void setCodiceProvincia(String codiceProvincia) {
		this.codiceProvincia = codiceProvincia;
	}

}
