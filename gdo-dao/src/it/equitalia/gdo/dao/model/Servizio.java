package it.equitalia.gdo.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WG0.WG0T1_SERVIZIO")
public class Servizio implements Serializable {

	private static final long serialVersionUID = -5882255526848498004L;
	
	@Id
	@Column(name="c_servizio")
	private String codiceServizio; 
	
	@Column(name="t_servizio_new")
	private String servizio;
	
	@Column(name="c_servizio_new")
	private String codiceServizioNew;

	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	public String getServizio() {
		return servizio;
	}

	public void setServizio(String servizio) {
		this.servizio = servizio;
	}

	public String getCodiceServizioNew() {
		return codiceServizioNew;
	}

	public void setCodiceServizioNew(String codiceServizioNew) {
		this.codiceServizioNew = codiceServizioNew;
	}

	
}
