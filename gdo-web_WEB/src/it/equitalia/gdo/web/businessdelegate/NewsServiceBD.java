package it.equitalia.gdo.web.businessdelegate;


import it.equitalia.gdo.commons.ejb.impl.NewsServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.ServiceLocator;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.web.util.GDOCostantiWeb.EJB_JNDI;

import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class NewsServiceBD {

	private NewsServiceRemote service = null;
	
	
	public NewsServiceBD(){
		final String jndi = GDOConfig.getInstance().getProperty(EJB_JNDI.JNDI_NEWS.getJndi()); 
		service = ServiceLocator.getInstance().getRemoteObject(jndi, NewsServiceRemote.class);
	}
	
	public NewsBean creaNews(NewsBean newsBean) throws BusinessException {
	    return service.creaNews(newsBean);
	}
	
	public List<NewsBean> recuperaNewsPopUp() throws BusinessException {
	    return service.recuperaNewsPopUp();
	}
	
	public NewsBean aggiornaNews(NewsBean newsBean, boolean mantieniAllegato) throws BusinessException {
	    return service.aggiornaNews(newsBean,mantieniAllegato);
	}
	
	
	public List<NewsBean> recuperaNews(NewsBean newsBean) throws BusinessException {
		return service.recuperaNews(newsBean);
	}
	
	public NewsBean recuperaNewsById(Integer id) throws BusinessException {
		return service.recuperaNewsById(id);
	}
	
	public List<NewsBean> recuperaStoricoNewsByCodice(Integer codiceNews) throws BusinessException {
		return service.recuperaStoricoNewsByCodice(codiceNews);
	}

}
