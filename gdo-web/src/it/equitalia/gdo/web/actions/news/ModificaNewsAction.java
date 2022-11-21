package it.equitalia.gdo.web.actions.news;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.web.businessdelegate.NewsServiceBD;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Preparable;

public class ModificaNewsAction extends AbstractFormNewsAction implements Preparable {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ModificaNewsAction.class);
	
	private static String titoloPagina = "Modifica news";
	private static String formAction = "news!salva.action";	
		
	private Boolean mantieniAllegato;
	
	public String getTitoloPagina() {
		return titoloPagina;
	}
	
	public String getFormAction() {
		return formAction;
	}
	
	
	public Boolean getMantieniAllegato() {
		return mantieniAllegato;
	}

	public void setMantieniAllegato(Boolean bool) {
		mantieniAllegato = bool;
	}
	
	/**
	 * Metodo di salvataggio
	 */
	public String salva() throws Exception {
		
		try {
			
			if (news == null)
			{
				throw new BusinessException("Nessuna notizia selezionata per l'aggiornamento");
			}
			else
			{
				// form inviato - salva il risultato che è gia stato validato da validate()
										
				//completo il bean da salvare
				news.setDataModifica(new Date());				
				news.setOwnerModifica(getUtente());												
				impostaTipologiaUtente();		
				
				//caricamento nuovo allegato
				inserisciAllegatoNelBean();				
				
				if (mantieniAllegato == null)
					mantieniAllegato = false;
				
				// Invocazione BD e salvataggio entità
				NewsServiceBD newsService = new NewsServiceBD();
				
				NewsBean saved = newsService.aggiornaNews(news,mantieniAllegato);
				
				if(saved != null && saved.getId() != null)
				{
					addActionMessage(GDOMessaggi.getMessaggio(GDOMessaggi.NEWS_SALVATA_CORRETTAMENTE));					
					//setto l'id nel bean per il link post salvataggio
					news.setId(saved.getId());
					
					return SUCCESS;				
				} else {
					return ERROR;				
				}
			}			
		}
		catch(Exception e) {
			throw new BusinessException("Impossibile completare l'operazione di aggiornamento. Si prega di riprovare",e);
		}

	}
	
	/**
	 * Metodo di visualizzazione
	 */
	@SkipValidation
	public String execute() throws BusinessException {
		
		//parametro non passato
		if (news == null)
		{
			log.warn("Nessun parametro passato per la modifica");
			addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_DETTAGLIO) );
			return ERROR;
		}
		else
		{
			NewsServiceBD newsService = new NewsServiceBD();
			NewsBean newsTrovata = newsService.recuperaNewsById( news.getId() );
			
			if (newsTrovata == null || newsTrovata.getId() == null) 
			{
				log.warn("Notizia con id " + news.getId() + " non trovata su database");
				addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_DETTAGLIO) );
				return ERROR;
			}
			else if (!newsTrovata.getValida()) //le notizie dello storico non sono modificabili
			{
				addActionErrorGDO( GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_NEWS_NON_MODIFICABILE) );
				return ERROR;
			}			
			else
			{												
				/* Visualizzazione del form di modifica */
				valorizzaNewsVisualizzateComePopUp();
				news = newsTrovata;		
				if(newsPopUp!=null && news!=null){
					if(newsPopUp.getId().equals(news.getId())){
						newsPopUp = null;
					}
				}
							
				try {
									
					tipologiaUtenteCheck = new ArrayList<Integer>();
					if(news.getAltriUtenti()==null)
						news.setAltriUtenti(false);
					if (news.getEnte())
						tipologiaUtenteCheck.add(OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getValue());
					if (news.getAgente())
						tipologiaUtenteCheck.add(OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getValue());
					if (news.getAltriUtenti())
						tipologiaUtenteCheck.add(OPZIONI_TIPOLOGIA_UTENTE.SOLO_ALTRO_UTENTE.getValue());
					if (news.getUtenteEsterno())
						tipologiaUtenteCheck.add(OPZIONI_TIPOLOGIA_UTENTE.SOLO_UTENTE_ESTERNO.getValue());
					
					popolaOptionsConValoriSelezionati();
					
				} catch (Exception e) {
					log.error("Errore nel reperimento dei campi per i filtri nel form di modifica",e);
					addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_ELENCHI) );
					return ERROR;
				}
				
				return INPUT;
			}
			
		}
	
	}
	
	/**
	 * I campi sono gia validati nella superclasse, qua gestisco solo il caso particolare dell'allegato
	 */
	@Override
	protected void validaForm() {
		super.validaForm();	
		
		//Messaggio all'utente invitandolo a ricaricare l'allegato in caso di:
		if (news != null) {
			
			//A) validazione fallita e mantenimento del vecchio allegato
			if (mantieniAllegato != null && mantieniAllegato == true && getFieldErrors().size() > 0) 
			{
				addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.NECESSARIO_RICARICARE_ALLEGATO));
			}
			//B) tentativo di caricamento nuovo allegato con errore di validazione
			else if (allegato_0 != null && getFieldErrors().size() > 0)
			{
				addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.NECESSARIO_RICARICARE_ALLEGATO));
			}
		}
			
	}

	@Override
	public Boolean isModalitaModifica() {
		return true;
	}

	@Override
	public Boolean isModalitaVisualizza() {
		return false;
	}

	@Override
	public Boolean isModalitaCreazione() {
		return false;
	}
	


}
