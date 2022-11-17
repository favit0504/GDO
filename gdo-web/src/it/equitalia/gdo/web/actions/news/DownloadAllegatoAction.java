package it.equitalia.gdo.web.actions.news;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.AllegatoBean;
import it.equitalia.gdo.web.actions.AbstractBaseAction;
import it.equitalia.gdo.web.businessdelegate.AllegatoServiceBD;
import it.equitalia.gdo.web.security.SecurityHelper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Preparable;

public class DownloadAllegatoAction extends AbstractBaseAction  implements Preparable {		

	private static final long serialVersionUID = 1L;	
	private static Logger log = Logger.getLogger(DownloadAllegatoAction.class);

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
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_NESSUN_ALLEGATO_SPECIFICATO));
			return ERROR;
		}
		else
		{
					
			try {
				Integer idAllegato = Integer.parseInt(this.id);
				
				//qua basta controllare che l'utente corrente abbia la funzionalita 
				//di gestione delle news... [metodo prepare]
				AllegatoServiceBD service = new AllegatoServiceBD();
				
				AllegatoBean allegato = service.recuperaAllegato(idAllegato);
				if (allegato != null) {
					
					filename = allegato.getNomeFile();
					fileInputStream = new ByteArrayInputStream(allegato.getContenutoFile());				
					return SUCCESS;
				}					
				else 
				{
					addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_ALLEGATO_NON_TROVATO));
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
				log.error("Errore durante il reperimento dell'allegato id="+id, e);
				addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_ALLEGATO));
				return ERROR;
			}
		}
	}

	public void prepare() throws Exception {
		//controllo nell'oggetto in sessione se l'utente e' abilitato alla gestione delle news
		if ( !SecurityHelper.isAbilitatoGestioneNews(ServletActionContext.getRequest()) ) {
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.MSG_NO_PRIVILEGI));
		}

	}

}
