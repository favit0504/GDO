package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class DocumentoBean extends BeanVersionabile implements Serializable, BeanConFiltriInterface {

	private static final long serialVersionUID = 1L;

	private String titolo;
	private String descrizione;
	
	private Integer stato;
	private String descrizioneStato;

	private Boolean ente;
	private Boolean agente;
	private String descrizioneTipoUtente;

	private String titoloSezione;

	private SezioneBean sezione;
	
	private Boolean svecchiato;

	private BlobDocumentoBean blob;
	private String nomeFile;

	private FiltroEnteBean filtroEnte;
	private FiltroSocietaBean filtroSocieta;
	private FiltroTipologiaEnteBean filtroTipologiaEnte;
	private FiltroServizioEnteBean filtroServizioEnte;
	private FiltroServizioAgenteBean filtroServizioAgente;
	private FiltroRegioneAgenteBean filtroRegioneAgente;
	private FiltroRegioneEnteBean filtroRegioneEnte;

	private FiltroProvinciaEnteBean filtroProvinciaEnte;
	private FiltroAmbitoBean filtroAmbito;


	public DocumentoBean() {

	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public Integer getStato() {
		return stato;
	}

	public void setStato(Integer stato) {
		this.stato = stato;
	}

	public BlobDocumentoBean getBlob() {
		return blob;
	}

	public void setBlob(BlobDocumentoBean blob) {
		this.blob = blob;
	}
	
	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

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

//	public String getDataInizioValidita() {
//		return dataInizioValidita;
//	}
//
//	public void setDataInizioValidita(String dataInizioValidita) {
//		this.dataInizioValidita = dataInizioValidita;
//	}
//
//	public String getDataFineValidita() {
//		return dataFineValidita;
//	}
//
//	public void setDataFineValidita(String dataFineValidita) {
//		this.dataFineValidita = dataFineValidita;
//	}

	public String getTitoloSezione() {
		return titoloSezione;
	}

	public void setTitoloSezione(String titoloSezione) {
		this.titoloSezione = titoloSezione;
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

//	public AllegatoBean getAllegato() {
//		return allegato;
//	}
//
//	public void setAllegato(AllegatoBean allegato) {
//		this.allegato = allegato;
//	}

	public SezioneBean getSezione() {
		return sezione;
	}

	public void setSezione(SezioneBean sezione) {
		this.sezione = sezione;
	}

	public String getDescrizioneTipoUtente() {
		return descrizioneTipoUtente;
	}

	public void setDescrizioneTipoUtente(String descrizioneTipoUtente) {
		this.descrizioneTipoUtente = descrizioneTipoUtente;
	}

	public String getDescrizioneStato() {
		return descrizioneStato;
	}

	public void setDescrizioneStato(String descrizioneStato) {
		this.descrizioneStato = descrizioneStato;
	}

	public Boolean getSvecchiato() {
		return svecchiato;
	}

	public void setSvecchiato(Boolean svecchiato) {
		this.svecchiato = svecchiato;
	}

	public FiltroAmbitoBean getFiltroAmbito() {
		return filtroAmbito;
	}

	public void setFiltroAmbito(FiltroAmbitoBean filtroAmbito) {
		this.filtroAmbito=filtroAmbito;
		
	}

	public FiltroServizioAltriUtentiBean getFiltroServizioAltriUtenti() {
		// TODO Stub di metodo generato automaticamente
		return null;
	}

	public void setFiltroServizioAltriUtenti(
			FiltroServizioAltriUtentiBean filtroServizio) {
		// TODO Stub di metodo generato automaticamente		
	}

	@Override
	public FiltroServizioUtenteEsternoBean getFiltroServizioUtenteEsterno() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFiltroServizioUtenteEsterno(FiltroServizioUtenteEsternoBean filtroServizio) {
		// TODO Auto-generated method stub
		
	}


}
