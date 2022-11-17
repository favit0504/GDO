package it.equitalia.gdo.web.actions.documento;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.web.businessdelegate.DocumentoServiceBD;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Preparable;

public class ModificaDocumentoAction extends AbstractFormDocumentoAction implements Preparable {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ModificaDocumentoAction.class);
	
	private static String titoloPagina = "Modifica documento";
	private static String formAction = "documento!salva.action";
	
	
	
	/**
	 * Metodo di visualizzazione
	 */
	@SkipValidation
	public String execute() throws BusinessException {
		
		//parametro non passato
		if (documento == null)
		{
			
			log.warn("Nessun parametro passato per la modifica");
			addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_DETTAGLIO) );
			return ERROR;
		}
		else
		{
			DocumentoServiceBD documentoService = new DocumentoServiceBD();
			DocumentoBean documentoTrovato = documentoService.recuperaDocumentoById(documento.getId());
			
			if (documentoTrovato == null || documentoTrovato.getId() == null) 
			{
				log.warn("Documento con id " + documento.getId() + " non trovato su database");
				addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_DETTAGLIO) );
				return ERROR;
			}
			else if (!documentoTrovato.getValida()) //le notizie dello storico non sono modificabili
			{
				addActionErrorGDO( GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_DOCUMENTO_NON_MODIFICABILE) );
				return ERROR;
			}			
			else
			{												
				/* Visualizzazione del form di modifica */
				documento = documentoTrovato;
				
				try {
					tipologiaUtenteCheck = new ArrayList<Integer>();
					if (documento.getEnte())
						tipologiaUtenteCheck.add(OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO.SOLO_ENTE.getValue());
					if (documento.getAgente())
						tipologiaUtenteCheck.add(OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO.SOLO_AGENTE.getValue());
					
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
	 * Metodo di salvataggio
	 */
	public String salva() throws Exception {
		
		try {
			
			if (documento == null)
			{
				throw new BusinessException("Nessun documento selezionato per l'aggiornamento");
			}
			else
			{
				// form inviato - salva il risultato che è gia stato validato da validate()
				
				//Invocazione BD
				DocumentoServiceBD documentoService = new DocumentoServiceBD();
				
				//completo il bean da salvare
				documento.setDataModifica(new Date());
				documento.setOwnerModifica(getUtente());
				DocumentoBean d = documentoService.recuperaDocumentoById(documento.getId());
				documento.setSezione(d.getSezione());
				impostaTipologiaUtente();
				
				//caricamento nuovo documento
				inserisciDocumentoNelBean();
				
				if (mantieniDocumento == null)
					mantieniDocumento = false;
				
				//Salvataggio entità
				DocumentoBean updated = documentoService.aggiornaDocumento(documento, documento.getSezione(), mantieniDocumento);
				
				if(updated != null && updated.getId() != null) {
					addActionMessage(GDOMessaggi.getMessaggio(GDOMessaggi.DOCUMENTO_SALVATO_CORRETTAMENTE));					
					//setto l'id nel bean per il link post salvataggio
					documento.setId(updated.getId());
					
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
	
	@Override
	public Boolean isModalitaCreazione() {
		return false;
	}

	@Override
	public Boolean isModalitaModifica() {
		return true;
	}

	@Override
	public Boolean isModalitaVisualizza() {
		return false;
	}
	
	public String getTitoloPagina() {
		return titoloPagina;
	}
	
	public String getFormAction() {
		return formAction;
	}
	

}
