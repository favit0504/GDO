package it.equitalia.gdo.web.actions.sezione;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.businessdelegate.SezioneServiceBD;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Preparable;

public class ModificaSezioneAction extends AbstractFormSezioneAction implements Preparable{
	
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ModificaSezioneAction.class);
	
	private static String titoloPagina = "Modifica sezione";
	private static String formAction = "sezione!salva.action";
	
	
	
	public String getTitoloPagina() {
		return titoloPagina;
	}
	
	public String getFormAction() {
		return formAction;
	}
	

	/**
	 * Metodo di salvataggio
	 */
	public String salva() throws Exception {
		
		try {
			
			if (sezione == null) {
				throw new BusinessException("Nessuna sezione selezionata per l'aggiornamento");
			}
			else {
				// form inviato - salva il risultato che è gia stato validato da validate()
				
				// Invocazione BD
				SezioneServiceBD sezioneService = new SezioneServiceBD();
				
				//completo il bean da salvare
				sezione.setDataModifica(new Date());
				sezione.setOwnerModifica(getUtente());
				sezione.setDocumenti((sezioneService.recuperaSezioneById(sezione.getId())).getDocumenti());
				sezione.setUtenti(utenze_abilitate);
				
				// Salvataggio entità
				SezioneBean saved = sezioneService.aggiornaSezione(sezione);
				
				if(saved != null && saved.getId() != null) {
					addActionMessage(GDOMessaggi.getMessaggio(GDOMessaggi.SEZIONE_SALVATA_CORRETTAMENTE));					
					//setto l'id nel bean per il link post salvataggio
					sezione.setId(saved.getId());
					
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
		if (sezione == null) {
			log.warn("Nessun parametro passato per la modifica");
			addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_DETTAGLIO) );
			return ERROR;
		}
		else
		{
			SezioneServiceBD sezioneService = new SezioneServiceBD();
			SezioneBean sezioneTrovata = sezioneService.recuperaSezioneById( sezione.getId() );
			
			if (sezioneTrovata == null || sezioneTrovata.getId() == null) 
			{
				log.warn("Sezione con id " + sezione.getId() + " non trovata su database");
				addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_DETTAGLIO) );
				return ERROR;
			}
			else if (!sezioneTrovata.getValida()) //le notizie dello storico non sono modificabili
			{
				addActionErrorGDO( GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_SEZIONE_NON_MODIFICABILE) );
				return ERROR;
			}			
			else
			{												
				/* Visualizzazione del form di modifica */
				sezione = sezioneTrovata;
				
				controllaAbilitazioneInserimentoDocumento(getUtente(), sezione);
				
				if(sezione.getUtenti() != null && sezione.getUtenti().size() > 0)
					utenze_abilitate = sezione.getUtenti();

				getSession().remove(LISTA_DOCUMENTI_VALIDI);
				getSession().put(LISTA_DOCUMENTI_VALIDI, sezione.getDocumentiValidi());
				
				return INPUT;
			}
			
		}
	
	}
	
	
	@Override
	public Boolean isModalitaModifica() {
		return true;
	}

	@Override
	public Boolean isModalitaVisualizza() {
		//Se non sono l'owner della sezione trovata 
		//non posso modificare i dati di testata
		if(!sezione.getOwner().equals(getUtente()))
			return true;
		else 
			return false;
	}

	@Override
	public Boolean isModalitaCreazione() {
		return false;
	}
	
	

}
