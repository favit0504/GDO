package it.equitalia.gdo.web.businessdelegate;


import it.equitalia.gdo.commons.ejb.impl.SezioneServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.ServiceLocator;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.util.GDOCostantiWeb.EJB_JNDI;

import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class SezioneServiceBD {

	private SezioneServiceRemote service = null;
	
	public SezioneServiceBD(){
		final String jndi = GDOConfig.getInstance().getProperty(EJB_JNDI.JNDI_SEZIONE.getJndi()); 
		service = ServiceLocator.getInstance().getRemoteObject(jndi, SezioneServiceRemote.class);
	}
	
	public SezioneBean creaSezione(SezioneBean sezioneBean) throws BusinessException {
	    return service.creaSezione(sezioneBean);
	}
	
	public SezioneBean aggiornaSezione(SezioneBean sezioneBean) throws BusinessException {
	    return service.aggiornaSezione(sezioneBean);
	}
	
	public List<SezioneBean> recuperaSezione(SezioneBean sezioneBean) throws BusinessException {
		return service.recuperaSezione(sezioneBean);
	}
	
	public SezioneBean recuperaSezioneById(Integer id) throws BusinessException {
		return service.recuperaSezioneById(id);
	}
	
	public List<SezioneBean> recuperaStroricoByCodice(Integer codiceSezione) throws BusinessException {
		return service.recuperaStoricoSezioneByCodice(codiceSezione);
	}

}
