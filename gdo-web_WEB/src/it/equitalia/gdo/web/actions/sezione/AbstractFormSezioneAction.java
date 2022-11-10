package it.equitalia.gdo.web.actions.sezione;

import static it.equitalia.gdo.commons.utils.GDOMessaggi.ERRORE_SEZIONE_TITOLO_NON_VALIDO;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.ERRORE_SEZIONE_DESCRIZIONE_NON_VALIDA;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.getMessaggio;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.actions.news.AbstractFormNewsAction;
import it.equitalia.gdo.web.util.AntiSamyPolicies;
import it.equitalia.gdo.web.util.PopolaCampiSezione;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

public abstract class AbstractFormSezioneAction extends AbstractSezioneAction  {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(AbstractFormNewsAction.class);
	
	protected Boolean abilitaNuovoDocumento;
	protected List<String> utenze_abilitate= new ArrayList<String>();
	
	public static String LISTA_DOCUMENTI_VALIDI = "documentiValidi";
	
	//liste comuni a form/pagina di dettaglio e form/pagina di ricerca
	protected static Map<Integer,String> opzioniStato = new HashMap<Integer,String>();
	@SuppressWarnings("unchecked")	
	public  Map<Integer, String> getOpzioniStato() {
		if(getSession().containsKey("opzioniStato")){
			opzioniStato = (Map<Integer, String>) getSession().get("opzioniStato");
		}
		return opzioniStato;
		
	}
	
	public List<String> getUtenze_abilitate() {	
		if(sezione.getUtenti()!= null)
			return sezione.getUtenti();
		else
			return utenze_abilitate;
	}

	public void setUtenze_abilitate(List<String> utenze_abilitate) {
		this.utenze_abilitate = utenze_abilitate;
	}
	
	public Boolean getAbilitaNuovoDocumento() {
		return abilitaNuovoDocumento;
	}

	public void setAbilitaNuovoDocumento(Boolean abilitaNuovoDocumento) {
		this.abilitaNuovoDocumento = abilitaNuovoDocumento;
	}

	public void prepare() throws Exception{
		super.prepare();
		PopolaCampiSezione p = new PopolaCampiSezione(getSession());
		p.popolaStatoSezione();
	}
	
	@Override
	protected void validaForm(){
		
		//Validazione Titolo
		if (StringUtils.isBlank(sezione.getTitolo()))
			addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.TITOLO_SEZIONE_OBBLIGATORIO));
		else {
			AntiSamy as = new AntiSamy();
			try {
				CleanResults cr = as.scan(sezione.getTitolo(), AntiSamyPolicies.getInstance().getPolicySoloTesto());
				if (cr.getNumberOfErrors() > 0) {
					addFieldError("errori-form", getMessaggio(ERRORE_SEZIONE_TITOLO_NON_VALIDO));
				}
			} catch (ScanException e) {
				log.error("Errore durante l'esecuzione funzione di antisamy [scansione titolo sezione per ricercare codice malevolo o input non ammesso ],input="+sezione.getTitolo(),e);
				addFieldError("errori-form", getMessaggio(ERRORE_SEZIONE_TITOLO_NON_VALIDO));
			} catch (PolicyException e) {
				log.error("Errore durante l'esecuzione funzione di antisamy [scansione titolo sezione per ricercare codice malevolo o input non ammesso ],input="+sezione.getTitolo(),e);
				addFieldError("errori-form", getMessaggio(ERRORE_SEZIONE_TITOLO_NON_VALIDO));
			} 
			
		}
		
		//Validazione Descrizione
		if (StringUtils.isBlank(sezione.getDescrizione()))
			addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.DESCRIZIONE_SEZIONE_OBBLIGATORIO));
		else {
			AntiSamy as = new AntiSamy();
			try {
				CleanResults cr = as.scan(sezione.getDescrizione(), AntiSamyPolicies.getInstance().getPolicySoloTesto());
				if (cr.getNumberOfErrors() > 0) {
					addFieldError("errori-form", getMessaggio(ERRORE_SEZIONE_DESCRIZIONE_NON_VALIDA));
				}
			} catch (ScanException e) {
				log.error("Errore durante l'esecuzione funzione di antisamy [scansione descrizione sezione per ricercare codice malevolo o input non ammesso ],input="+sezione.getDescrizione(),e);
				addFieldError("errori-form", getMessaggio(ERRORE_SEZIONE_DESCRIZIONE_NON_VALIDA));
			} catch (PolicyException e) {
				log.error("Errore durante l'esecuzione funzione di antisamy [scansione descrizione sezione per ricercare codice malevolo o input non ammesso ],input="+sezione.getDescrizione(),e);
				addFieldError("errori-form", getMessaggio(ERRORE_SEZIONE_DESCRIZIONE_NON_VALIDA));
			} 
		}
		
		//Validazione Stato
		if (sezione.getStato() == null)
			addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.STATO_SEZIONE_OBBLIGATORIO));
		
		//Validazione Abilitazione
		controllaAbilitazioneInserimentoDocumento(getUtente(), sezione);
		
	}
	
	/**
	 * Verifica se, per la sezione e l'utente specificati in input, 
	 * si ha l'abilitazione alla creazione di un nuovo documento.
	 * 
	 * Condizioni:
	 * 		1. l'utente è presente nell'elenco degli utenti abilitati;
	 * 		2. l'utente è l'owner della sezione.
	 * 
	 * Viene impostata la proprietà abilitaNuovoDocumento a:
	 * 		1. true, se almeno una delle condizioni è soddisfatta
	 * 		2. false, se entrambe le condizioni non sono soddisfatte.
	 * 
	 * @param utente
	 * @param sezioneBean
	 */
	protected void controllaAbilitazioneInserimentoDocumento(String utente, SezioneBean sezioneBean){
		Boolean isAbilitatoSezione = false;
		Boolean isOwnerSezione = false;
		
		//Controllo se sono tra gli utenti abilitati alla sezione
		List<String> utentiAbilitati = sezioneBean.getUtenti();
		if(utentiAbilitati != null && utentiAbilitati.size() > 0){
			for(String u : utentiAbilitati){
				//Controllo non case sensitive
				if(u.toUpperCase().equals(getUtente()))
					isAbilitatoSezione = true;
			}
		}
		//Controllo se sono l'owner della sezione di appartenenza				
		if(sezioneBean.getOwner().equals(getUtente())){
			isOwnerSezione = true;
		}
		
		if(!isAbilitatoSezione && !isOwnerSezione)
			setAbilitaNuovoDocumento(false);
		else
			setAbilitaNuovoDocumento(true);
		
	}
	
	public abstract Boolean isModalitaVisualizza();
	public abstract Boolean isModalitaModifica();
	public abstract Boolean isModalitaCreazione();	
	public String solaLettura() {
		return "";
	}
	

}
