package it.equitalia.gdo.webservices;


import it.equitalia.gdo.commons.ejb.impl.DocumentoServiceRemote;
import it.equitalia.gdo.commons.ejb.impl.NewsServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;


public class SvecchiamentoWebService {
	
	
	private static final Logger logger = Logger.getLogger(SvecchiamentoWebService.class);
	
	@Resource
	private Context ctx;

	
	public boolean invocaSvecchiamento() throws BusinessException{
		
		try {
			ctx = new InitialContext();
			DocumentoServiceRemote documentoService = getDocumentiServiceRemote();
			boolean resultDocumenti = documentoService.svecchiaDocumenti();			
			
			NewsServiceRemote newsService = getNewsServiceRemote();
			boolean resultNews = newsService.svecchiaNews();
			
			if(resultDocumenti && resultNews)
				return true;
			else
				return false;
			
		}
		catch(NamingException e) {
			logger.error("Errore in fase di lookup dei servizi ",e);
			throw new BusinessException("Si è verificato un errore interno");
		}
		catch(BusinessException be) {
			logger.error(be, be.getCause());
			throw new BusinessException("Si è verificato un errore interno: " +be.getCause());
		}
		catch(Exception ex)
		 {
			logger.error(ex, ex.getCause());
			throw new BusinessException("Si è verificato un errore interno: " +ex.getCause());
		}
		
		
	}

	public boolean invocaSvecchiamentoDocumenti() throws BusinessException{
		
		boolean result;
		
		try {
			ctx = new InitialContext();
			DocumentoServiceRemote documentoService = getDocumentiServiceRemote();
			result = documentoService.svecchiaDocumenti();			
			
			
		}
		catch(NamingException e) {
			logger.error("Errore in fase di lookup dei servizi ",e);
			throw new BusinessException("Si è verificato un errore interno");
		}
		catch(BusinessException be) {
			logger.error(be, be.getCause());
			throw new BusinessException("Si è verificato un errore interno: " +be.getCause());
		}
		
		return result;
		
	}
	
	public boolean invocaSvecchiamentoNews() throws BusinessException{
		
		boolean result;
		
		try {
			ctx = new InitialContext();
			NewsServiceRemote newsService = getNewsServiceRemote();
			result = newsService.svecchiaNews();
			
		}
		catch(NamingException e) {
			logger.error("Errore in fase di lookup dei servizi ",e);
			throw new BusinessException("Si è verificato un errore interno");
		}
		catch(BusinessException be) {
			logger.error(be, be.getCause());
			throw new BusinessException("Si è verificato un errore interno: " +be.getCause());
		}
		
		return result;
		
	}
	
	private DocumentoServiceRemote getDocumentiServiceRemote() throws NamingException {
			
		String jndi_documenti = GDOConfig.getInstance().getProperty(GDOConfig.WS_CALL_EJB_JNDI_DOCUMENTO);
		DocumentoServiceRemote obj = (DocumentoServiceRemote) PortableRemoteObject.narrow(ctx.lookup(jndi_documenti),DocumentoServiceRemote.class);
		
		return obj;
	}
	
	private NewsServiceRemote getNewsServiceRemote() throws NamingException {
		
		String jndi_news = GDOConfig.getInstance().getProperty(GDOConfig.WS_CALL_EJB_JNDI_NEWS);
		NewsServiceRemote obj = (NewsServiceRemote)PortableRemoteObject.narrow(ctx.lookup(jndi_news),NewsServiceRemote.class);
		
		return obj;
	}
	
	
}
