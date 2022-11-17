package it.equitalia.gdo.web.actions.documento;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.web.businessdelegate.DocumentoServiceBD;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Preparable;

public class VisualizzaDocumentoAction extends AbstractFormDocumentoAction implements Preparable {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(VisualizzaDocumentoAction.class);
	
	private static String titoloPagina = "Visualizza Documento";
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
		if (documento == null)
		{
			addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_DETTAGLIO) );
			return ERROR;
		}
		else
		{
			DocumentoServiceBD documentoService = new DocumentoServiceBD();
			DocumentoBean documentoTrovato = documentoService.recuperaDocumentoById( documento.getId() );
			
			if (documentoTrovato == null || documentoTrovato.getId() == null) 
			{
				log.warn("Documento con id " + documento.getId() + " non trovato su database");
				addActionErrorGDO(GDOMessaggi.getMessaggio( GDOMessaggi.ERRORE_REPERIMENTO_DETTAGLIO) );
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
