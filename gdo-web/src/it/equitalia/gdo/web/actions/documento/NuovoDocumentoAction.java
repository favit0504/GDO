package it.equitalia.gdo.web.actions.documento;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.businessdelegate.DocumentoServiceBD;
import it.equitalia.gdo.web.businessdelegate.SezioneServiceBD;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Preparable;

public class NuovoDocumentoAction extends AbstractFormDocumentoAction implements Preparable {
	
	private static final long serialVersionUID = 1L;

	private static org.apache.log4j.Logger log = Logger.getLogger(NuovoDocumentoAction.class);
	
	private static String titoloPagina = "Nuovo documento";
	private static String formAction = "documento!nuovo.action";
	
	private Integer idSezione;
	
	public String getTitoloPagina() {
		return titoloPagina;
	}
	
	public String getFormAction() {
		return formAction;
	}

	public Integer getIdSezione() {
		return idSezione;
	}

	public void setIdSezione(Integer idSezione) {
		this.idSezione = idSezione;
	}

	@SkipValidation
	public String execute() throws BusinessException {
		
		if(idSezione != null){
			SezioneServiceBD sezioneService = new SezioneServiceBD();
			SezioneBean sezione = sezioneService.recuperaSezioneById(idSezione);
			
			if(controllaAbilitazioneInserimentoDocumento(getUtente(), sezione)){
				documento = new DocumentoBean();
				documento.setStato(1);
				documento.setSezione(sezione);
				return INPUT;
				
			} else {
				addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.MSG_NO_PRIVILEGI));
				return ERROR;
				
			}
			
		} else{
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ID_SEZIONE_NON_PRESENTE));
			return ERROR;
		}
	}
	
	private boolean controllaAbilitazioneInserimentoDocumento(String utente, SezioneBean sezioneBean){
		Boolean isAbilitatoSezione = false;
		Boolean isOwnerSezione = false;
		
		//Controllo se sono tra gli utenti abilitati alla sezione
		List<String> utentiAbilitati = sezioneBean.getUtenti();
		if(utentiAbilitati != null && utentiAbilitati.size() > 0){
			for(String u : utentiAbilitati){
				if(u.equals(getUtente()))
					isAbilitatoSezione = true;
			}
		}
		//Controllo se sono l'owner della sezione di appartenenza				
		if(sezioneBean.getOwner().equals(getUtente())){
			isOwnerSezione = true;
		}
		
		if(isAbilitatoSezione || isOwnerSezione){
			return true;
		}
		else
			return false;
		
		
	}
	
	public String creaDocumento(){
		if(documento!=null){
			try{
				if(documento.getSezione()!= null && documento.getSezione().getId()!= null){
					impostaTipologiaUtente();
					inserisciDocumentoNelBean();
					documento.setDataCreazione(new Date());
					documento.setOwner(getUtente());
					
					// Invocazione BD e salvataggio entità
					DocumentoServiceBD documentoService = new DocumentoServiceBD();
					DocumentoBean saved = documentoService.creaDocumento(documento, documento.getSezione());
					
					if(saved != null){
						documento.setId(saved.getId());
						addActionMessage(GDOMessaggi.getMessaggio(GDOMessaggi.DOCUMENTO_SALVATO_CORRETTAMENTE));
						return SUCCESS;
					}
					else{
						addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_INSERT_DOCUMENTO));
						return ERROR;
					}
					
				}
				else{
					addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ID_SEZIONE_NON_PRESENTE));
					return ERROR;
				}	
			} catch (BusinessException be){
				log.error(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_INSERT_DOCUMENTO), be);
				addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_INSERT_DOCUMENTO));
				return ERROR;
			}
		}
		else{
			return INPUT;
		}
	
	}
	@Override
	public Boolean isModalitaCreazione() {		
		return true;
	}
	@Override
	public Boolean isModalitaModifica() {
		return false;
	}
	@Override
	public Boolean isModalitaVisualizza() {
		return false;
	}
	
	/**
	 * Si gestisce solo il caso in cui sia già stato caricato il file
	 */
	@Override
	protected void validaForm() {
		super.validaForm();
		
		if (documento != null && documento_0 != null && getFieldErrors().size() > 0)
		{
			addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.NECESSARIO_RICARICARE_ALLEGATO));
		}
	}

}
