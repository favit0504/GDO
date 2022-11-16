package it.equitalia.gdo.commons.utils;


import it.equitalia.gdo.commons.exception.SystemException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class ServiceLocatorConfig {
	
	/**
	 * Il file da configurazione
	 */
	private final String FILE_CONFIG_JNDI = "gdo_jndi.properties";

	public final static String KEY_INITIAL_CONTEXT_FACTORY 	= "java.naming.factory.initial";
	public final static String KEY_PROVIDER_URL 			= "java.naming.provider.url";

	private static final ServiceLocatorConfig INSTANCE = new ServiceLocatorConfig();
	
	private Properties _descriptor = null;

	private ServiceLocatorConfig() {
		
//		InputStream is = getClass().getClassLoader().getResourceAsStream("/"+ FILE_CONFIG_JNDI);
		InputStream is = ServiceLocatorConfig.class.getClassLoader().getResourceAsStream(FILE_CONFIG_JNDI);
		
		if (is == null){
			throw new SystemException(FILE_CONFIG_JNDI + ": File not found");
		}

		_descriptor = new Properties();
		try {
			_descriptor.load(is);
			
			System.out.println(_descriptor);

		}
		catch (IOException ioexc) {
			ioexc.printStackTrace();
			throw new SystemException("ERROR\t" + FILE_CONFIG_JNDI + ", failed to read file");
		}
		finally {
			
			try {
				if( is != null){
					is.close();
				}
			}
			catch (IOException ioexc) {
				ioexc.printStackTrace();
				throw new SystemException("ERROR\t" + FILE_CONFIG_JNDI + ", failed to close file");
			} 
		}
	}

	public static final ServiceLocatorConfig getInstance() {
		return INSTANCE;
	}

	public String getProperty(String key) {
		String value = (String) _descriptor.get(key);
		if (value == null)
			throw new SystemException(key + ": invalid property");

		return value;
	}
}