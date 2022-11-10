package it.equitalia.gdo.web.businessdelegate;

import java.util.Map;

import it.equitalia.gdo.commons.ejb.impl.DocumentoFrontendServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.ServiceLocator;
import it.equitalia.gdo.web.util.GDOCostantiWeb.EJB_JNDI;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;

public class DocumentoFrontendServiceBD {

	private DocumentoFrontendServiceRemote service;
	
	public DocumentoFrontendServiceBD(){
		final String jndi = GDOConfig.getInstance().getProperty(EJB_JNDI.JNDI_DOCUMENTALE_FRONTEND.getJndi()); 
		service = ServiceLocator.getInstance().getRemoteObject(jndi, DocumentoFrontendServiceRemote.class);
		
	}
	
	public Map<Integer, SezioneBean> recuperaDocumentiAttiviDestinatiAdUtente(String chiaveUtente) throws BusinessException{
		return service.recuperaDocumentiAttiviDestinatiAdUtente(chiaveUtente);		
	}
	
	public Boolean popolaEValutaUtente(DocumentoBean documentoBean, UtenteBean utenteBean) throws BusinessException{
		return service.popolaEValutaUtente(utenteBean, documentoBean);
	}
}
