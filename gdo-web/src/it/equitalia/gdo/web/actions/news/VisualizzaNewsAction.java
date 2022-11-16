package it.equitalia.gdo.web.actions.news;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.web.businessdelegate.NewsServiceBD;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Preparable;

public class VisualizzaNewsAction extends AbstractFormNewsAction implements Preparable {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(VisualizzaNewsAction.class);
	
	private static String titoloPagina = "Visualizza News";
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
		if (news == null)
		{
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
			else
			{												
				/* Visualizzazione del form di modifica */
				valorizzaNewsVisualizzateComePopUp();
				news = newsTrovata;				
				
				try {
									
					tipologiaUtenteCheck = new ArrayList<Integer>();
					if (news.getEnte())
						tipologiaUtenteCheck.add(OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getValue());
					if (news.getAgente())
						tipologiaUtenteCheck.add(OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getValue());
					
					popolaOptionsConValoriSelezionati();
					
				} catch (Exception e) {
					log.error("Errore nel reperimento dei campi per i filtri nel form di modifica",e);
					addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_ELENCHI) );
					return ERROR;
				}
				
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
