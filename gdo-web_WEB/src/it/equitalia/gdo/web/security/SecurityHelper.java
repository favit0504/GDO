package it.equitalia.gdo.web.security;

import it.equitalia.gdo.commons.exception.SystemException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.web.util.GDOCostantiWeb;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class SecurityHelper {

	private static Logger log = Logger.getLogger(SecurityHelper.class);

	/**
	 * Metodo utilita' richiamato dalle funzioni esposte sotto
	 */
	private static SecurityContext getSecurityContext(HttpServletRequest req) {

		SecurityContext sc = null;
		try {
			sc = (SecurityContext) req.getSession().getAttribute(GDOCostantiWeb.KEY_SECURITY_CONTEXT);		
		}
		catch (Exception e) {
			
			log.error(">>> Errore in fase di recupero SecurityContext dalla sessione", e);
			throw new SystemException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_SESSIONE));
		}

		if (sc == null) {
			log.error(">>> SecurityContext == null");
			throw new SystemException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_SESSIONE));
					
		}

		return sc;

	}
	
	
	public static boolean isAbilitatoGestioneNews(HttpServletRequest request) throws SystemException {
		log.debug("Verifica abilitazione gestione news");
		boolean auth = false; 
		auth = SecurityHelper.getSecurityContext(request).isAbilitatoGestioneNews();
		return auth;
	}	
	
	
	public static boolean isAbilitatoGestioneSezioni(HttpServletRequest request) throws SystemException {
		log.debug("Verifica abilitazione gestione sezioni");
		boolean auth = false; 
		auth = SecurityHelper.getSecurityContext(request).isAbilitatoGestioneSezioni();
		return auth;
	}
	
	
	public static boolean isAbilitatoGestioneDocumenti(HttpServletRequest request) throws SystemException {
		log.debug("Verifica abilitazione gestione documenti");
		boolean auth = false; 
		auth = SecurityHelper.getSecurityContext(request).isAbilitatoGestioneDocumenti();
		return auth;
	}
	
	/**
	 * Metodo chiamato dentro la JSP: non deve dare errore perche` tiles la gestisce male
	 */
	public static boolean isAbilitatoGestioneNewsMenu(HttpServletRequest request)  {
		
		boolean abilitato = false;
		try {
			abilitato = isAbilitatoGestioneNews(request);
		}
		catch(Exception e) {
			log.warn("Eccezione non gestita verifica abilitazione dentro JSP:", e);
		}
		return abilitato;
	}	
	
	/**
	 * Metodo chiamato dentro la JSP: non deve dare errore perche` tiles la gestisce male
	 */
	public static boolean isAbilitatoGestioneSezioniMenu(HttpServletRequest request)  {
		boolean abilitato = false;
		try {
			abilitato = isAbilitatoGestioneSezioni(request);
		}
		catch(Exception e) {
			log.warn("Eccezione non gestita verifica abilitazione dentro JSP:", e);
		}
		return abilitato;
	}
	
	/**
	 * Metodo chiamato dentro la JSP: non deve dare errore perche` tiles la gestisce male
	 */
	public static boolean isAbilitatoGestioneDocumentiMenu(HttpServletRequest request) {
		boolean abilitato = false;
		try {
			abilitato = isAbilitatoGestioneDocumenti(request);
		}
		catch(Exception e) {
			log.warn("Eccezione non gestita verifica abilitazione dentro JSP:", e);
		}
		return abilitato;
	}
	

}
