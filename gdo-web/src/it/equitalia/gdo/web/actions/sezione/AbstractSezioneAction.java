package it.equitalia.gdo.web.actions.sezione;



import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.actions.AbstractBaseAction;
import it.equitalia.gdo.web.security.SecurityHelper;
import it.equitalia.gdo.web.util.PopolaCampiSezione;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;



public abstract class AbstractSezioneAction extends AbstractBaseAction {

	private static Logger log = Logger.getLogger(AbstractSezioneAction.class);
	private static final long serialVersionUID = 1L;

	protected SezioneBean sezione;
	
	protected PopolaCampiSezione p = null;
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
	
	
	/* Metodi get e set */

	public SezioneBean getSezione() {
		return sezione;
	}

	public void setSezione(SezioneBean sezioneBean) {
		sezione = sezioneBean;
	}
	

	@SuppressWarnings("unchecked")
	public  Map<Integer, String> getOpzioniStato() {
		if(getSession().containsKey("opzioniStato")){
			opzioniStato = (Map<Integer, String>) getSession().get("opzioniStato");
		}
		return opzioniStato;
		
	}
	
	
	/**
	 * Valido sia per ricerca che per form modifica/inserimento
	 */
	public void prepare() throws Exception {
		// controllo nell'oggetto in sessione se sono abilitato alla gestione delle sezioni
		if (!SecurityHelper.isAbilitatoGestioneSezioni(ServletActionContext.getRequest())) {
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.MSG_NO_PRIVILEGI));
		}		
	}
	
	
	public void validate() {
		if(sezione!=null){
			validaForm();
		}
	}
	
	protected void validaForm() {		
	}

	
}


