package it.equitalia.gdo.dao.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIG.SIGT2_REGIONI")
public class Regione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5882255526848498004L;
	@Id
	@Column(name="c_regione")
	private String codiceRegione; 
	@Column(name="c_cod_lingua")	
	private String lingua;
	@Column(name="t_regione")
	private String	regione;
	@Column(name="d_val_inf")
	private String dataValidita ;
	@Column(name="d_cessazione")
	private String	dataCessazione;
	public String getCodiceRegione() {
		return codiceRegione;
	}
	public void setCodiceRegione(String codiceRegione) {
		this.codiceRegione = codiceRegione;
	}
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getDataValidita() {
		return dataValidita;
	}
	public void setDataValidita(String dataValidita) {
		this.dataValidita = dataValidita;
	}
	public String getDataCessazione() {
		return dataCessazione;
	}
	public void setDataCessazione(String dataCessazione) {
		this.dataCessazione = dataCessazione;
	}
	
}
