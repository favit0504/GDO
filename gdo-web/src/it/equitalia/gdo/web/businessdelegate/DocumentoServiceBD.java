package it.equitalia.gdo.web.businessdelegate;


import it.equitalia.gdo.commons.ejb.impl.DocumentoServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.ServiceLocator;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.util.GDOCostantiWeb.EJB_JNDI;

import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class DocumentoServiceBD {

	private DocumentoServiceRemote service = null;
	
	public DocumentoServiceBD(){
		final String jndi = GDOConfig.getInstance().getProperty(EJB_JNDI.JNDI_DOCUMENTO.getJndi()); 
		service = ServiceLocator.getInstance().getRemoteObject(jndi, DocumentoServiceRemote.class);
	}
	
	public DocumentoBean creaDocumento(DocumentoBean documentoBean, SezioneBean sezioneBean) throws BusinessException {
	    return service.creaDocumento(documentoBean, sezioneBean);
	}
	
	public DocumentoBean aggiornaDocumento(DocumentoBean documentoBean, SezioneBean sezioneBean, boolean mantieniDocumento) throws BusinessException {
	    return service.aggiornaDocumento(documentoBean, sezioneBean, mantieniDocumento);
	}
	
	public List<DocumentoBean> recuperaDocumento(DocumentoBean documentoBean) throws BusinessException {
		return service.recuperaDocumento(documentoBean);
	}
	
	public DocumentoBean recuperaDocumentoById(Integer id) throws BusinessException {
		return service.recuperaDocumentoById(id);
	}
	
	public DocumentoBean recuperaFileByIdDocumento(Integer id) throws BusinessException {
		return service.recuperaFileByIdDocumento(id);
	}
	
	public List<DocumentoBean> recuperaStoricoDocumentoByCodice(Integer codiceDocumento) throws BusinessException {
		return service.recuperaStoricoDocumentoByCodice(codiceDocumento);
	}

}
