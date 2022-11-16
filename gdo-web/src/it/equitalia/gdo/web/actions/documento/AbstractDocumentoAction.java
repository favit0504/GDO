package it.equitalia.gdo.web.actions.documento;



import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.actions.AbstractBaseAction;
import it.equitalia.gdo.web.security.SecurityHelper;
import it.equitalia.gdo.web.util.PopolaFiltriDocumento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;



public abstract class AbstractDocumentoAction extends AbstractBaseAction {

	private static Logger log = Logger.getLogger(AbstractDocumentoAction.class);
	private static final long serialVersionUID = 1L;

	protected DocumentoBean documento;
	protected SezioneBean sezione;
	
	protected PopolaFiltriDocumento p = null;
	
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
	
	/* Metodi get e set */
	public DocumentoBean getDocumento() {
		return documento;
	}
	
	public void setDocumento(DocumentoBean documento) {
		this.documento = documento;
	}

	public SezioneBean getSezione() {
		return sezione;
	}

	public void setSezione(SezioneBean sezione) {
		this.sezione = sezione;
	}
	
	public List<Integer> getTipologiaUtenteCheck() {
		return tipologiaUtenteCheck;
	}

	public void setTipologiaUtenteCheck(List<Integer> tipologiaUtenteCheck) {
		this.tipologiaUtenteCheck = tipologiaUtenteCheck;
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
		if(getSession().containsKey("opzioniStatoDocumenti")){
			opzioniStato = (Map<Integer, String>) getSession().get("opzioniStatoDocumenti");
		}
		return opzioniStato;
		
	}


	/**
	 * Valido sia per ricerca che per form modifica/inserimento
	 */
	public void prepare() throws Exception {
		// controllo nell'oggetto in sessione se sono abilitato alla gestione delle sezioni
		if (!SecurityHelper.isAbilitatoGestioneDocumenti(ServletActionContext.getRequest())) {
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.MSG_NO_PRIVILEGI));
		}		
	}
	
	
	public void validate() {
		if(documento!=null){
			validaForm();
		}
	}
	
	protected void validaForm() {		
	}
	
	protected void impostaTipologiaUtente() {
		documento.setEnte(false);
		documento.setAgente(false);
		if(tipologiaUtenteCheck != null && tipologiaUtenteCheck.size() > 0){
			
		//	FiltroTipologiaBean tipologiaUtente = new FiltroTipologiaBean();
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getValue()))
				documento.setAgente(true);
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getValue()))
				documento.setEnte(true);								
		}
	}

	
}


