package it.equitalia.gdo.web.actions.news;



import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.web.actions.AbstractBaseAction;
import it.equitalia.gdo.web.security.SecurityHelper;
import it.equitalia.gdo.web.util.FormValidatorUtils;
import it.equitalia.gdo.web.util.PopolaFiltriNews;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;



public abstract class AbstractNewsAction extends AbstractBaseAction {

	private static Logger log = Logger.getLogger(AbstractNewsAction.class);
	private static final long serialVersionUID = 1L;

	private static final String DATA_FINE_PUBBLICAZIONE = "Data Fine Pubblicazione";
	private static final String DATA_INIZIO_PUBBLICAZIONE = "Data Inizio Pubblicazione";
	
	protected NewsBean news;
	
	protected PopolaFiltriNews p = null;
	private static int risultatiPerPagina;
	
	

	public int getRisultatiPerPagina() {
		return risultatiPerPagina;
	}
	
	static {
	
		try {
		
			risultatiPerPagina = Integer.parseInt( GDOConfig.getInstance().getProperty(GDOConfig.RIS_PER_PAGINA) );
		} catch(java.lang.NumberFormatException e) {
			log.error("Errore nella lettura del numero risultati per pagina da file di property", e);
			
		}
	

	}

	//liste comuni a form/pagina di dettaglio e form/pagina di ricerca
	protected static Map<Integer,String> opzioniStato = new HashMap<Integer,String>();
	protected static Map<Integer,String> opzioniTipologiaUtente = new HashMap<Integer,String>();
	protected List<Integer> tipologiaUtenteCheck;
	protected NewsBean newsPopUp;
	
	
	/* Metodi get e set */
	

	public NewsBean getNews() {
		return news;
	}

	public void setNews(NewsBean newsBean) {
		news = newsBean;
	}
	
	public NewsBean getNewsPopUp() {
		return newsPopUp;
	}

	public void setNewsPopUp(NewsBean newsPopUp) {
		this.newsPopUp = newsPopUp;
	}

	@SuppressWarnings("unchecked")
	public Map<Integer,String> getOpzioniTipologiaUtente() {
		if(getSession().containsKey("opzioniTipologiaUtente")){
			opzioniTipologiaUtente = (Map<Integer, String>) getSession().get("opzioniTipologiaUtente");
		}
		return opzioniTipologiaUtente;
		
	}

	@SuppressWarnings("unchecked")
	
	public  Map<Integer, String> getOpzioniStato() {
		if(getSession().containsKey("opzioniStato")){
			opzioniStato = (Map<Integer, String>) getSession().get("opzioniStato");
		}
		return opzioniStato;
		
	}
	

	public List<Integer> getTipologiaUtenteCheck() {
		return tipologiaUtenteCheck;
	}
	public void setTipologiaUtenteCheck(List<Integer> tipologiaUtente) {
		this.tipologiaUtenteCheck = tipologiaUtente;
	}
	
	
	/**
	 * Valido sia per ricerca che per form modifica/inserimento
	 */
	public void prepare() throws Exception {
		// controllo nell'oggetto in sessione se sono abilitato alla gestione
		// delle news
		if (!SecurityHelper.isAbilitatoGestioneNews(ServletActionContext.getRequest())) {
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.MSG_NO_PRIVILEGI));
		}		
	}
	
	
	public void validate() {
		if(news!=null){
			validaDate();
			validaForm();
		}
	}
	
	protected void validaForm() {		
	}

	protected void validaDate() {
		Date dataInizio = null;
		Date dataFine = null;

		if (!StringUtils.isBlank(news.getDataInizioPubblicazione()) ){
			if(FormValidatorUtils.isDdMmYyyy(news.getDataInizioPubblicazione())){
				dataInizio= FormValidatorUtils.fromStringToDate(news.getDataInizioPubblicazione());				
			} else{
				news.setDataInizioPubblicazione(null); //Fix per la sicurezza
				addFieldError("errori-form",(String.format(GDOMessaggi.getMessaggio(GDOMessaggi.FORMATO_DATA_ERRATO), DATA_INIZIO_PUBBLICAZIONE)));
			}	
		}

		if (!StringUtils.isBlank(news.getDataFinePubblicazione()) ){
			if(FormValidatorUtils.isDdMmYyyy(news.getDataFinePubblicazione())){
				dataFine= FormValidatorUtils.fromStringToDate(news.getDataFinePubblicazione());					
			} else{
				news.setDataFinePubblicazione(null); //Fix per la sicurezza
				addFieldError("errori-form",(String.format(GDOMessaggi.getMessaggio(GDOMessaggi.FORMATO_DATA_ERRATO), DATA_FINE_PUBBLICAZIONE)));
			}	
		}

		if (dataInizio!=null && dataFine!=null ){
			if(dataInizio.after(dataFine))
				addFieldError("errori-form",(String.format(GDOMessaggi.getMessaggio(GDOMessaggi.ORDINE_DATE_ERRATO), DATA_INIZIO_PUBBLICAZIONE, DATA_FINE_PUBBLICAZIONE)));
		}
	}
	


	
	protected void impostaTipologiaUtente() {
		news.setEnte(false);
		news.setAgente(false);
		news.setAltriUtenti(false);
		news.setUtenteEsterno(false);
		if(tipologiaUtenteCheck != null && tipologiaUtenteCheck.size() > 0){
		
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getValue()))
					news.setAgente(true);
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getValue()))
					news.setEnte(true);	
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE.SOLO_ALTRO_UTENTE.getValue()))
				    news.setAltriUtenti(true);
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE.SOLO_UTENTE_ESTERNO.getValue()))
				news.setUtenteEsterno(true);
		}
	}

}


