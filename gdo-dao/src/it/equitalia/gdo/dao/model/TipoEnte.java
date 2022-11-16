package it.equitalia.gdo.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIG.SIGT2_TIPO_ENTE")
public class TipoEnte implements Serializable {

	private static final long serialVersionUID = -5882255526848498004L;
	
	@Id
	@Column(name="c_tipo_ente")
	private String codiceTipoEnte; 
	
	@Column(name="s_des_tipo_ente")
	private String ente;
	
	@Column(name="c_cod_lingua")
	private String codiceLingua;
	
	@Column(name="d_val_inf")
	private String dataValidita;

	public String getCodiceTipoEnte() {
		return codiceTipoEnte;
	}

	public void setCodiceTipoEnte(String codiceTipoEnte) {
		this.codiceTipoEnte = codiceTipoEnte;
	}

	public String getEnte() {
		return ente;
	}

	public void setEnte(String ente) {
		this.ente = ente;
	}

	public String getCodiceLingua() {
		return codiceLingua;
	}

	public void setCodiceLingua(String codiceLingua) {
		this.codiceLingua = codiceLingua;
	}

	public String getDataValidita() {
		return dataValidita;
	}

	public void setDataValidita(String dataValidita) {
		this.dataValidita = dataValidita;
	}

	
}
