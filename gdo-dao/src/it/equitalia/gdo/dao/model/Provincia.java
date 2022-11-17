package it.equitalia.gdo.dao.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIG.SIGT2_PROVINCE")
public class Provincia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5882255526848498004L;
	
	@Id
	@Column(name="s_sigprov")
	private String codiceProvincia; 
	
	@Column(name="c_cod_lingua")	
	private String lingua;
	
	@Column(name="t_provincia")
	private String provincia;
	
	@Column(name="d_val_inf")
	private String dataValidita ;
	
	@Column(name="d_cessazione")
	private String	dataCessazione;
	
	@Column(name="c_regione")
	private String	codiceRegione;
	

	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
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
	public String getCodiceProvincia() {
		return codiceProvincia;
	}
	public void setCodiceProvincia(String codiceProvincia) {
		this.codiceProvincia = codiceProvincia;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCodiceRegione() {
		return codiceRegione;
	}
	public void setCodiceRegione(String codiceRegione) {
		this.codiceRegione = codiceRegione;
	}
	
	
}
