package it.equitalia.gdo.web.actions.sezione;

import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.businessdelegate.SezioneServiceBD;

import com.opensymphony.xwork2.Preparable;

public class NuovaSezioneAction extends AbstractFormSezioneAction implements Preparable {

	private static final long serialVersionUID = 8062533703601186086L;
	
	private static String titoloPagina = "Nuova sezione";
	private static String formAction = "sezione!nuova.action";
	

	public String getTitoloPagina() {
		return titoloPagina;
	}
	
	public String getFormAction() {
		return formAction;
	}				

	public String execute() throws Exception {
		
		if(sezione!=null){
			// form non inviato: salvo il risultato validato 
			
			if(utenze_abilitate!= null && utenze_abilitate.size()>0){
				sezione.setUtenti(utenze_abilitate);
			}
			sezione.setOwner(getUtente());			
			SezioneServiceBD sezioneService = new SezioneServiceBD();
			SezioneBean saved = sezioneService.creaSezione(sezione);
			if(saved != null){
				sezione.setId(saved.getId());
				addActionMessage(GDOMessaggi.getMessaggio(GDOMessaggi.SEZIONE_SALVATA_CORRETTAMENTE));
				return SUCCESS;
			}
			else
				return ERROR;
		}
		else{
			// Form non inviato: visualizzazione
			// stato default = 1
			
			getSession().remove(LISTA_DOCUMENTI_VALIDI);
			sezione = new SezioneBean();
			sezione.setStato(1);
			
			return INPUT;
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
