package it.equitalia.gdo.webservices;


import it.equitalia.gdo.commons.ejb.impl.NewsFrontendServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.Costanti.TipologiaUtente;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.webservices.valueobjects.ListaNews;

import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


public class NewsFrontendWebService {
	
	
	private static final Logger logger = Logger.getLogger(NewsFrontendWebService.class);
	
	@Resource
	private Context ctx;


	public ListaNews recuperaNewsAttiveDestinateAdUtenteEnte(String chiaveUtente) throws BusinessException {
	
		ListaNews listaNewsObject = new ListaNews();
		
		try {
			ctx = new InitialContext();
			NewsFrontendServiceRemote newsService = getNewsFrontendServiceRemote();
			List<NewsBean> newsRestituiteEJB = newsService.recuperaNewsAttiveDestinateAdUtenteEnte(chiaveUtente, TipologiaUtente.ENTE);
						
			listaNewsObject.setListaNews(newsRestituiteEJB);
	
		}
		catch(NamingException e) {
			logger.error("Errore in fase di lookup dei servizi ",e);
			throw new BusinessException("Si è verificato un errore interno");
		}
		return listaNewsObject;

	}
	
	public ListaNews recuperaNewsAttiveDestinateAdUtenteAgente(String chiaveUtente) throws BusinessException{
		
		ListaNews listaNewsObject = new ListaNews();
		
		try {
			ctx = new InitialContext();
			NewsFrontendServiceRemote newsService = getNewsFrontendServiceRemote();
			List<NewsBean> newsRestituiteEJB = newsService.recuperaNewsAttiveDestinateAdUtenteAgente(chiaveUtente, TipologiaUtente.AGENTE);			
			listaNewsObject.setListaNews(newsRestituiteEJB);
		}
		catch(NamingException e) {
			logger.error("Errore in fase di lookup dei servizi ",e);
			throw new BusinessException("Si è verificato un errore interno");
		}
		return listaNewsObject;

	}
	
	public ListaNews recuperaNewsAttivePerAltriUtenti(String chiaveUtente) throws BusinessException{
		
		ListaNews listaNewsObject = new ListaNews();
		
		try {
			ctx = new InitialContext();
			NewsFrontendServiceRemote newsService = getNewsFrontendServiceRemote();
			List<NewsBean> newsRestituiteEJB = newsService.recuperaNewsAttivePerAltriUtenti(chiaveUtente, TipologiaUtente.ALTRIUTENTI);			
			listaNewsObject.setListaNews(newsRestituiteEJB);
		}
		catch(NamingException e) {
			logger.error("Errore in fase di lookup dei servizi ",e);
			throw new BusinessException("Si è verificato un errore interno");
		}
		return listaNewsObject;

	}
	private NewsFrontendServiceRemote getNewsFrontendServiceRemote() throws NamingException {
			
		String jndi_news = GDOConfig.getInstance().getProperty(GDOConfig.WS_CALL_EJB_JNDI_NEWS_FRONTEND);
		Object obj = ctx.lookup(jndi_news);
		
		return (NewsFrontendServiceRemote) obj;
	}
}
