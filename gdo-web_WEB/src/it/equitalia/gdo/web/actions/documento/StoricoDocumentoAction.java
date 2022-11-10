package it.equitalia.gdo.web.actions.documento;

import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.web.businessdelegate.DocumentoServiceBD;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Preparable;

public class StoricoDocumentoAction extends AbstractDocumentoAction implements Preparable {		

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(StoricoDocumentoAction.class);

	private List<DocumentoBean> elencoDocumenti;

	public String execute() throws Exception {
		
		if(documento != null && documento.getCodice() != null){
			DocumentoServiceBD documentoService = new DocumentoServiceBD();
			elencoDocumenti = documentoService.recuperaStoricoDocumentoByCodice(documento.getCodice());
			
			if(elencoDocumenti.size()==0){
				addActionMessageGDO(GDOMessaggi.getMessaggio(GDOMessaggi.RICERCA_NESSUN_RISULTATO));
				
			}
			return SUCCESS;

		} else {
			//messaggio non correttissimo, ma non si presenta mai se l'utente non si inventa qualcosa
			addActionMessageGDO(GDOMessaggi.getMessaggio(GDOMessaggi.RICERCA_NESSUN_RISULTATO));
			return ERROR;
			
		}
		
	}

	public List<DocumentoBean> getElencoDocumenti() {
		return elencoDocumenti;
	}

	public void setElencoDocumenti(List<DocumentoBean> elencoDocumenti) {
		this.elencoDocumenti = elencoDocumenti;
	}
	


}
