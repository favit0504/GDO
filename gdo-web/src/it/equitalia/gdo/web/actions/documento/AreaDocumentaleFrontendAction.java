package it.equitalia.gdo.web.actions.documento;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;
import it.equitalia.gdo.web.actions.AbstractBaseAction;
import it.equitalia.gdo.web.actions.news.NuovaNewsAction;
import it.equitalia.gdo.web.businessdelegate.DocumentoFrontendServiceBD;
import it.equitalia.gdo.web.businessdelegate.DocumentoServiceBD;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Preparable;

public class AreaDocumentaleFrontendAction extends AbstractBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;

	private static org.apache.log4j.Logger log = Logger.getLogger(NuovaNewsAction.class);
	
	private static String titoloPagina;
	private String titoloDocumentale = "Documentazione tecnica e manuali";
	private static String formAction = "documentale!frontend.action";


	protected static List<SezioneBean> sezioniSx = new ArrayList<SezioneBean>();
	protected static List<SezioneBean> sezioniDx = new ArrayList<SezioneBean>();
	protected DocumentoBean documento = new DocumentoBean();
	protected Integer idDocumento;

	private String filename;
	private ByteArrayInputStream fileInputStream;
	

	public String getTitoloDocumentale() {
		return titoloDocumentale;
	}

	public String getTitoloPagina() {
		return titoloPagina;
	}
	
	public String getFormAction() {
		return formAction;
	}
	
	public List<SezioneBean> getSezioniSx() {
		return sezioniSx;
	}

	public List<SezioneBean> getSezioniDx() {
		return sezioniDx;
	}
	
	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public DocumentoBean getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoBean documento) {
		this.documento = documento;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ByteArrayInputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(ByteArrayInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String execute (){
		try {
			DocumentoFrontendServiceBD documentoFrontendService = new DocumentoFrontendServiceBD();		
			Map<Integer, SezioneBean> mappaSezioni  = documentoFrontendService.recuperaDocumentiAttiviDestinatiAdUtente(getUtente());

			costruisciSottolistePerLeSezioni(mappaSezioni);		
			
		} catch (BusinessException e) {			
			log.error("Errore durante il reperimento delle sezioni", e);
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_SEZIONE));
			return ERROR;
		}
		return INPUT;
	}
	
	public String visualizza(){
		
		try {
			
			if (idDocumento == null){
				addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_DOCUMENTO));
				return ERROR;
			}
			
			DocumentoServiceBD documentoService = new DocumentoServiceBD();
		
			documento = documentoService.recuperaDocumentoById(idDocumento);
			if(documento != null){
		//		titoloPagina = documento.getTitolo();
			}
			else{
				addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_DOCUMENTO_NON_TROVATO));
				return ERROR;
			}
			
		} catch (BusinessException e) {
			log.error("Errore durante il reperimento del documento", e);
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_DOCUMENTO));
			return ERROR;
		}
		
		return INPUT;
	}
	
	public String download(){	
		if(idDocumento != null){
			try {
				log.info("Download file documento, parametro id=" + Arrays.asList( idDocumento ));
				boolean abilitato = valutaAbilitazioneUtente(idDocumento);
					
				if(abilitato){
					DocumentoServiceBD documentoService = new DocumentoServiceBD();
					DocumentoBean doc = documentoService.recuperaFileByIdDocumento(idDocumento);
					
					if (doc.getBlob() != null) {
						filename = doc.getNomeFile();
						fileInputStream = new ByteArrayInputStream(doc.getBlob().getContenutoFile());				
						return SUCCESS;
					}					
					else 
					{
						addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_FILE_NON_TROVATO));
						return ERROR;
					}
				}
			} catch (BusinessException e) {
				log.error("Errore nel recupero del file del documento",e);
				e.printStackTrace();
			}
		
		return INPUT;
		} else {
			log.error("Errore durante il reperimento del file del documento");
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_FILE));
			return ERROR;
		}
	}

	private void costruisciSottolistePerLeSezioni(Map<Integer, SezioneBean> mappaSezioni) {
		sezioniSx.clear();
		sezioniDx.clear();
		
		List<SezioneBean> sezioni = estraiListaSezioni(mappaSezioni);
		
		int numDocumentiDx = 0;
		int numDocumentiSx = 0;
		
		for (SezioneBean sez : sezioni){
			int numDoc = sez.getDocumenti().size();
			if(numDocumentiSx <= numDocumentiDx){
				sezioniSx.add(sez);
				numDocumentiSx += numDoc;
			} 
			else {
				sezioniDx.add(sez);
				numDocumentiDx += numDoc;
			}
		}		
		
	}

	private List<SezioneBean> estraiListaSezioni(
			Map<Integer, SezioneBean> mappaSezioni) {
		List <SezioneBean> sezioni = new ArrayList<SezioneBean>();
				
		Set<Integer> chiaviSezione = mappaSezioni.keySet();
		Iterator<Integer> it = chiaviSezione.iterator();
		while (it.hasNext()){
			Integer chiave = (Integer) it.next();
			SezioneBean sez = mappaSezioni.get(chiave);
			sezioni.add(sez);
			
		}
		return sezioni;
	}
	
	private boolean valutaAbilitazioneUtente(int idNews) throws BusinessException {		
		
		DocumentoServiceBD documentoBD = new DocumentoServiceBD();
		documento = documentoBD.recuperaDocumentoById(idDocumento);
					
		String usd = getUtente();
		UtenteBean utenteBean = new UtenteBean();
		utenteBean.setUsd(usd);
		DocumentoFrontendServiceBD documentoFrontendServiceBD = new DocumentoFrontendServiceBD();
		boolean abilitato = documentoFrontendServiceBD.popolaEValutaUtente(documento, utenteBean);
		return abilitato;
		
	}
	public void prepare() throws Exception {
		
	}
	
	
		

}
