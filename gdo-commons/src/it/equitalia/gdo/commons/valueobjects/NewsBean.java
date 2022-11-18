package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class NewsBean extends BeanVersionabile implements Serializable, BeanConFiltriInterface {

	private static final long serialVersionUID = 1L;
	

	private String titolo;
	private String testo;
	private Integer stato;
	private String descrizioneStato;	
	private String dataInizioPubblicazione;
	private String dataFinePubblicazione;
	//private List<FiltroBean> filtroBean;	
	private String descrizioneTipoUtente;	
	private Boolean visualizzaPopUp;
	private String visualizzaPopUpDescr;
	private Boolean svecchiata;		
	private Boolean agente;
	private Boolean altriUtenti;
	private FiltroEnteBean filtroEnte;
	private FiltroSocietaBean filtroSocieta;
	private FiltroTipologiaEnteBean filtroTipologiaEnte;
	//private FiltroTipologiaBean filtroTipologia;
	private FiltroServizioEnteBean filtroServizioEnte;
	private FiltroServizioAgenteBean filtroServizioAgente;
	private FiltroServizioAltriUtentiBean filtroServizioAltriUtenti;
	private FiltroServizioUtenteEsternoBean filtroServizioUtenteEsterno;
	private FiltroRegioneAgenteBean filtroRegioneAgente;
	private FiltroRegioneEnteBean filtroRegioneEnte;	
	private FiltroProvinciaEnteBean filtroProvinciaEnte;
	private FiltroAmbitoBean filtroAmbito;	
	private AllegatoBean allegato;
	
	public NewsBean() {		
	}
	
	private Boolean ente;
	public Boolean getEnte() {
		return ente;
	}

	public void setEnte(Boolean ente) {
		this.ente = ente;
	}

	public Boolean getAgente() {
		return agente;
	}

	public void setAgente(Boolean agente) {
		this.agente = agente;
	}

	public Boolean getAltriUtenti() {
		return altriUtenti;
	}

	public void setAltriUtenti(Boolean altriUtenti) {
		this.altriUtenti = altriUtenti;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Integer getStato() {
		return stato;
	}

	public void setStato(Integer stato) {
		this.stato = stato;
	}

	public String getDescrizioneStato() {
		return descrizioneStato;
	}

	public void setDescrizioneStato(String descrizioneStato) {
		this.descrizioneStato = descrizioneStato;
	}

	public String getDataInizioPubblicazione() {
		return dataInizioPubblicazione;
	}

	public void setDataInizioPubblicazione(String dataInizioPubblicazione) {
		this.dataInizioPubblicazione = dataInizioPubblicazione;
	}

	public String getDataFinePubblicazione() {
		return dataFinePubblicazione;
	}

	public void setDataFinePubblicazione(String dataFinePubblicazione) {
		this.dataFinePubblicazione = dataFinePubblicazione;
	}

	public FiltroEnteBean getFiltroEnte() {
		return filtroEnte;
	}

	public void setFiltroEnte(FiltroEnteBean filtroEnte) {
		this.filtroEnte = filtroEnte;
	}

	public FiltroSocietaBean getFiltroSocieta() {
		return filtroSocieta;
	}

	public void setFiltroSocieta(FiltroSocietaBean filtroSocieta) {
		this.filtroSocieta = filtroSocieta;
	}

	public FiltroTipologiaEnteBean getFiltroTipologiaEnte() {
		return filtroTipologiaEnte;
	}

	public void setFiltroTipologiaEnte(FiltroTipologiaEnteBean filtroTipologiaEnte) {
		this.filtroTipologiaEnte = filtroTipologiaEnte;
	}

	public FiltroRegioneAgenteBean getFiltroRegioneAgente() {
		return filtroRegioneAgente;
	}

	public void setFiltroRegioneAgente(FiltroRegioneAgenteBean filtroRegioneAgente) {
		this.filtroRegioneAgente = filtroRegioneAgente;
	}

	public FiltroRegioneEnteBean getFiltroRegioneEnte() {
		return filtroRegioneEnte;
	}

	public void setFiltroRegioneEnte(FiltroRegioneEnteBean filtroRegioneEnte) {
		this.filtroRegioneEnte = filtroRegioneEnte;
	}


	public FiltroProvinciaEnteBean getFiltroProvinciaEnte() {
		return filtroProvinciaEnte;
	}

	public void setFiltroProvinciaEnte(FiltroProvinciaEnteBean filtroProvinciaEnte) {
		this.filtroProvinciaEnte = filtroProvinciaEnte;
	}

	public FiltroServizioEnteBean getFiltroServizioEnte() {
		return filtroServizioEnte;
	}

	public void setFiltroServizioEnte(FiltroServizioEnteBean filtroServizioEnte) {
		this.filtroServizioEnte = filtroServizioEnte;
	}

	public FiltroServizioAgenteBean getFiltroServizioAgente() {
		return filtroServizioAgente;
	}

	public void setFiltroServizioAgente(
			FiltroServizioAgenteBean filtroServizioAgente) {
		this.filtroServizioAgente = filtroServizioAgente;
	}
	

	public FiltroServizioAltriUtentiBean getFiltroServizioAltriUtenti() {
		return filtroServizioAltriUtenti;
	}	

	public void setFiltroServizioAltriUtenti(
			FiltroServizioAltriUtentiBean filtroServizioAltriUtenti) {
		this.filtroServizioAltriUtenti = filtroServizioAltriUtenti;
	}

	public AllegatoBean getAllegato() {
		return allegato;
	}

	public void setAllegato(AllegatoBean allegato) {		
		this.allegato = allegato;
	}

	public String getDescrizioneTipoUtente() {
		return descrizioneTipoUtente;
	}

	public void setDescrizioneTipoUtente(String descrizioneTipoUtente) {
		this.descrizioneTipoUtente = descrizioneTipoUtente;
	}
	
	public Boolean getSvecchiata() {
		return svecchiata;
	}

	public void setSvecchiata(Boolean svecchiata) {
		this.svecchiata = svecchiata;
	}


	public FiltroAmbitoBean getFiltroAmbito() {
		return filtroAmbito;
	}

	public void setFiltroAmbito(FiltroAmbitoBean filtroAmbito) {
		this.filtroAmbito = filtroAmbito;
	}
	
	public void setVisualizzaPopUp(Boolean visualizzaPopUp) {
		this.visualizzaPopUp = visualizzaPopUp;
	}

	public Boolean isVisualizzaPopUp() {
		return visualizzaPopUp;
	}		
	
	@Override
	public String toString() {
		return "NEWS ID:" + id;
	}

	public String getVisualizzaPopUpDescr() {
		return visualizzaPopUpDescr;
	}

	public void setVisualizzaPopUpDescr(String visualizzaPopUpDescr) {
		this.visualizzaPopUpDescr = visualizzaPopUpDescr;
	}

	public FiltroServizioUtenteEsternoBean getFiltroServizioUtenteEsterno() {
		return filtroServizioUtenteEsterno;

	}
	public void setFiltroServizioUtenteEsterno(FiltroServizioUtenteEsternoBean filtroServizio) {
		this.filtroServizioUtenteEsterno = filtroServizio;
	}
	
}
