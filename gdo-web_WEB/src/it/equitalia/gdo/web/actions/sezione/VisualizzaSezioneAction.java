package it.equitalia.gdo.web.actions.sezione;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.actions.news.VisualizzaNewsAction;
import it.equitalia.gdo.web.businessdelegate.SezioneServiceBD;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Preparable;

public class VisualizzaSezioneAction extends AbstractFormSezioneAction implements Preparable{
	
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(VisualizzaNewsAction.class);
	
	private static String titoloPagina = "Visualizza Sezione";
	private static String formAction = "#";
	
	public String getTitoloPagina() {
		return titoloPagina;
	}
	
	public String getFormAction() {
		return formAction;
	}
	
	/**
	 * Metodo di visualizzazione
	 */
	@SkipValidation
	public String execute() throws BusinessException {
		
		//parametro non passato
		if (sezione == null)
		{
			addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_DETTAGLIO) );
			return ERROR;
		}
		else
		{
			SezioneServiceBD sezioneService = new SezioneServiceBD();
			SezioneBean sezioneTrovata = sezioneService.recuperaSezioneById(sezione.getId());
			
			if (sezioneTrovata == null || sezioneTrovata.getId() == null) 
			{
				log.warn("Sezione con id " + sezione.getId() + " non trovata su database");
				addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_DETTAGLIO) );
				return ERROR;
			}				
			else
			{
				/* Visualizzazione del form di modifica */
				sezione = sezioneTrovata;
				if(sezione.getUtenti() != null && sezione.getUtenti().size() > 0)
					utenze_abilitate = sezione.getUtenti();
				
				getSession().remove(LISTA_DOCUMENTI_VALIDI);
				getSession().put(LISTA_DOCUMENTI_VALIDI, sezione.getDocumentiValidi());
				
				return SUCCESS;
			}
			
		}
	
	}

	@Override
	public Boolean isModalitaModifica() {
		return false;
	}

	@Override
	public Boolean isModalitaVisualizza() {
		return true;
	}

	@Override
	public Boolean isModalitaCreazione() {
		return false;
	}	
	
	@Override
	public String solaLettura() {
		return " readonly=\"readonly\" ";
	}

}
