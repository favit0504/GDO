package it.equitalia.gdo.commons.utils;

import it.equitalia.gdo.commons.exception.SystemException;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class ServiceLocator {
	
	/**
	 * InitialContext.
	 */
	private InitialContext _context = null;
	
	private static Logger logger = Logger.getLogger(ServiceLocator.class);
	
	
	/**
	 * Costruttore della classe.
	 * 
	 * @see #_context
	 */
	private ServiceLocator() {
		
		try {

			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY	, ServiceLocatorConfig.getInstance().getProperty(ServiceLocatorConfig.KEY_INITIAL_CONTEXT_FACTORY));
			env.put(Context.PROVIDER_URL			, ServiceLocatorConfig.getInstance().getProperty(ServiceLocatorConfig.KEY_PROVIDER_URL));

			_context = new InitialContext( env );
			
		} catch (NamingException nexc) {
	
			logger.error("Errore in fase di creazione istanza ServiceLocator");
			throw new SystemException("ERROR\t failed to initialize context", nexc );
		}
		
	}
	
	private static class Holder {
		private static final ServiceLocator INSTANCE = new ServiceLocator();
		
	}
	
	public static final ServiceLocator getInstance(){
		return Holder.INSTANCE;
	}	

	/**
	 * 
	 * @param jndiName
	 * @return
	 */
	public Object getLocalObject( String jndiName ) {
		try{

			return _context.lookup( "java:comp/env/" + jndiName );
			
		} catch (NamingException nexc) {
			
			logger.error("Impossibile risolvere " + jndiName, nexc);			
			throw new SystemException("Si &egrave; verificato un errore di comunicazione con i servizi interni. Si prega di riprovare in seguito");			
			//throw new ResourceLookupFailureException( nexc.getMessage() );
		}
	}
	

	/**
	 * 
	 * @param <T>
	 * @param jndiName
	 * @param remoteInterfaceClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T>T getRemoteObject(String jndiName, Class<T> remoteInterfaceClass) {
		try {
			
			return (T)PortableRemoteObject.narrow( _context.lookup(jndiName), remoteInterfaceClass);
			
		} catch (NamingException nexc) {
		
			logger.error("Impossibile risolvere " + jndiName, nexc);			
			throw new SystemException("Si &egrave; verificato un errore di comunicazione con i servizi interni. Si prega di riprovare in seguito");			
			//nexc.printStackTrace();			
			//throw new ResourceLookupFailureException( nexc.getMessage() );
		
		}
	}
	
}
