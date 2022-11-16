package it.equitalia.gdo.web.actions.news;

import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.web.businessdelegate.NewsServiceBD;
import java.util.Date;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Preparable;

public class NuovaNewsAction extends AbstractFormNewsAction implements Preparable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static org.apache.log4j.Logger log = Logger.getLogger(NuovaNewsAction.class);
		
	private static String titoloPagina = "Nuova News";
	private static String formAction = "news!nuova.action";
	

	public String getTitoloPagina() {
		return titoloPagina;
	}
	
	public String getFormAction() {
		return formAction;
	}
	

	
	public String execute() throws Exception {
		
		if (news != null) {
			// form inviato - salva il risultato che è gia stato validato da validate()		
			
			news.setDataCreazione(new Date());
			news.setOwner(getUtente());
			
			//il codice 1111 sara' sovrascritto all'interno di EntitaVersionabileDAO
			//con lo stesso valore dato all'id,  autogenerato dalla sequence per il record
			news.setCodice(11111);												
			
			impostaTipologiaUtente();	
			inserisciAllegatoNelBean();
									
			// Invocazione BD e salvataggio entità
			NewsServiceBD newsService = new NewsServiceBD();
			NewsBean saved = newsService.creaNews(news);
			
			if(saved != null && saved.getId() != null){
				
				//setto l'id nel bean per il link post salvataggio
				news.setId(saved.getId());
				addActionMessage(GDOMessaggi.getMessaggio(GDOMessaggi.NEWS_SALVATA_CORRETTAMENTE));								
				return SUCCESS;				
			} else {
				return ERROR;				
			}
		}

		else {
			
			valorizzaNewsVisualizzateComePopUp();
			
			//default stato = 1
			news = new NewsBean();
			news.setStato(1);
			
			return INPUT;
		}

	}
	

	/**
	 * I campi sono gia validati nella superclasse, qua gestisco solo il caso particolare dell'allegato
	 */
	@Override
	protected void validaForm() {
		super.validaForm();
		
		if (news != null && allegato_0 != null && getFieldErrors().size() > 0)
		{
			addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.NECESSARIO_RICARICARE_ALLEGATO));
		}
	}

	@Override
	public Boolean isModalitaModifica() {
		return false;
	}

	@Override
	public Boolean isModalitaVisualizza() {
		return false;
	}

	@Override
	public Boolean isModalitaCreazione() {
		return true;
	}
}
