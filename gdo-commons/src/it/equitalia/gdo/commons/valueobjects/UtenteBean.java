package it.equitalia.gdo.commons.valueobjects;

import it.equitalia.gdo.commons.utils.Costanti.RaggruppamentoSocietario;

import java.io.Serializable;
import java.util.List;
import it.equitalia.gdo.commons.utils.Costanti.TipologiaUtente;

/**
 * Bean per gestione valutazione filtri utente.
 *  
 * @author Valerio Donnarumma
 *
 */
public class UtenteBean implements Serializable {

	private static final long serialVersionUID = 8448155000359716296L;
	
	private String usd;
	private List<String> servizi;
	private List<String> province;
	private List<Integer> ambiti;
	public List<Integer> getAmbiti() {
		return ambiti;
	}
	public void setAmbiti(List<Integer> ambiti) {
		this.ambiti = ambiti;
	}
	private EnteBean ente;
	private String tipologiaEnte;
	private TipologiaUtente tipologiaUtente;
	private List<RaggruppamentoSocietario> societa;
	private List<String> regioni;
	
	
	public String getUsd() {
		return usd;
	}
	public void setUsd(String usd) {
		this.usd = usd;
	}
	public List<String> getServizi() {
		return servizi;
	}
	public void setServizi(List<String> servizi) {
		this.servizi = servizi;
	}
	public List<String> getProvince() {
		return province;
	}
	public void setProvince(List<String> province) {
		this.province = province;
	}
	public EnteBean getEnte() {
		return ente;
	}
	public void setEnte(EnteBean ente) {
		this.ente = ente;
	}
	public String getTipologiaEnte() {
		return tipologiaEnte;
	}
	public void setTipologiaEnte(String tipologiaEnte) {
		this.tipologiaEnte = tipologiaEnte;
	}
	public TipologiaUtente getTipologiaUtente() {
		return tipologiaUtente;
	}
	public void setTipologiaUtente(TipologiaUtente tipologiaUtente) {
		this.tipologiaUtente = tipologiaUtente;
	}
	public List<RaggruppamentoSocietario> getSocieta() {
		return societa;
	}
	public void setSocieta(List<RaggruppamentoSocietario> societa) {
		this.societa = societa;
	}
	public List<String> getRegioni() {
		return regioni;
	}
	public void setRegioni(List<String> regioni) {
		this.regioni = regioni;
	}
	
	
	
	
}
