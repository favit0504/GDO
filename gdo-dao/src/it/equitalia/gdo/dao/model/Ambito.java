package it.equitalia.gdo.dao.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIG.SIGT2_CONCESSIONE")
public class Ambito implements Serializable {

	
	private static final long serialVersionUID = -6740090707467187160L;

	
	
	@Id
	@Column(name="C_CONCESSIONE")
	private Integer codiceAmbito; 
	
	public Integer getCodiceAmbito() {
		return codiceAmbito;
	}

	public void setCodiceAmbito(Integer codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Column(name="S_DENOM_CONC")	
	private String descrizione;
	

	
}
