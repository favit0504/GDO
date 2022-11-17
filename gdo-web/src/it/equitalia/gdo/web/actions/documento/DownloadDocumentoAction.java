package it.equitalia.gdo.web.actions.documento;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.StringUtils;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.actions.AbstractBaseAction;
import it.equitalia.gdo.web.businessdelegate.DocumentoServiceBD;
import it.equitalia.gdo.web.security.SecurityHelper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Preparable;

public class DownloadDocumentoAction extends AbstractBaseAction implements Preparable {		

	private static final long serialVersionUID = 1L;	
	private static Logger log = Logger.getLogger(DownloadDocumentoAction.class);

	private String id;
	private String filename;
	private InputStream fileInputStream;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}	

	

	public String execute() throws Exception {
		
		HttpServletRequest request =  ServletActionContext.getRequest();
		log.info(request.getAttributeNames());
		log.info(request.getParameterMap());
		
		if (id == null)
		{			
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_NESSUN_DOCUMENTO_SPECIFICATO));
			return ERROR;
		}
		else
		{
					
			try {
				Integer idDocumento = Integer.parseInt(this.id);
				
				//controllo che l'utente corrente abbia la funzionalita 
				//di gestione dei documenti... [metodo prepare]
				
				DocumentoServiceBD service = new DocumentoServiceBD();
				DocumentoBean documentoBean = service.recuperaFileByIdDocumento(idDocumento);
				
				if (documentoBean != null && documentoBean.getBlob() != null) {
					
					filename = documentoBean.getNomeFile();
					fileInputStream = new ByteArrayInputStream(documentoBean.getBlob().getContenutoFile());
					return SUCCESS;
				}					
				else 
				{
					addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_DOCUMENTO_NON_TROVATO));
					return ERROR;
				}
			}
			catch (BusinessException e) {
				//qua non loggo l'errore perche` e` stato gia loggato in precedenza
				//e mostro a video un messaggio piu` specifico di quello generico sotto
				addActionErrorGDO(e.getMessage());
				return ERROR;
			}
			catch (Exception e) {
				log.error("Errore durante il reperimento del documento con id="+id, e);
				addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_DOCUMENTO));
				return ERROR;
			}
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
		
		if(!isAbilitatoSezione || !isOwnerSezione){
			return false;
		}
		else
			return true;
		
		
	}

	public void prepare() throws Exception {
		//controllo nell'oggetto in sessione se l'utente e' abilitato alla gestione dei documenti
		if ( !SecurityHelper.isAbilitatoGestioneDocumenti(ServletActionContext.getRequest()) ) {
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.MSG_NO_PRIVILEGI));
		}
		
		if(id != null && !StringUtils.isBlank(id)){
			DocumentoServiceBD documentoService = new DocumentoServiceBD();
			DocumentoBean d = documentoService.recuperaDocumentoById(Integer.parseInt(id));
			
			if(d.getSezione() != null){
				if(controllaAbilitazioneInserimentoDocumento(getUtente(), d.getSezione())){
					addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.MSG_NO_PRIVILEGI));
				}	
			}
		}
	}

}
