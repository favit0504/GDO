package it.equitalia.gdo.svecchiamento.spazio.util;

import it.equitalia.gdo.commons.exception.SystemException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Questa classe permette di accedere alle proprietà del file di configurazione dell'integratore
 * con Spazio.
 *
 */
public class ConfigurazioneSpazio{
	public enum Property{
		//Property per gestione con coda JMS
		GDO_FLUSSI_QUEUE_MANAGER("gdo.flussi.spazio.queue-manager"),
		GDO_FLUSSI_QUEUE("gdo.flussi.spazio.queue-name"),
		GDO_FLUSSI_USERCLASS("gdo.flussi.spazio.userclass"),
		GDO_FLUSSI_SPAZIO_USER("gdo.flussi.spazio.user"),
		
		//Property per gestione con chiamata HTTP
		SPAZIO_HTTPACTION("spazio.httpAction"),
		SPAZIO_CMD("spazio.cmd"),
		SPAZIO_QM("spazio.qm"),
		SPAZIO_UCLASS("spazio.uclass"),
		SPAZIO_PASS("spazio.pass"),
		SPAZIO_QUEUE("spazio.queue"),
		SPAZIO_CODING("spazio.coding"),
		SPAZIO_HOST_GDO("spazio.host.gdo")
		;


		private String value;
		Property(String value){
			this.value=value;
		}

		public String getTextProperty(){
			return this.toString();
		}

		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value.toString();
		}


	}


	/**
	 * Il file da configurazione
	 */
	private final String fileDiConfigurazione = "gdo_svecchiamento.properties";


	/**
	 * L'unica istanza della classe
	 */
	private static ConfigurazioneSpazio INSTANCE = new ConfigurazioneSpazio(); 

	/**
	 * Variabile d'appoggio per caricare le proprietà
	 */
	private Properties properties = null;

	/**
	 * Si occupa di aprire il file di configurazione e caricare le proprietà in esso contenute
	 *
	 */
	private ConfigurazioneSpazio() {	
		InputStream is = ConfigurazioneSpazio.class.getClassLoader().getResourceAsStream(fileDiConfigurazione);
		if (is == null) {
			throw new SystemException(fileDiConfigurazione+": File not found");
		}
		properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException ioexc) {
			ioexc.printStackTrace();
			throw new SystemException("ERROR "+fileDiConfigurazione+", failed to read file");
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException ioexc) {
				ioexc.printStackTrace();

				throw new SystemException("ERROR "+ fileDiConfigurazione +", failed to close file");
			}
		}
	}

	public static final ConfigurazioneSpazio getInstance() {
		return INSTANCE;
	}

	public String getProperty(Property property){
		return getProperty(property.getTextProperty());
	}


	/**
	 * Restiuisce la proprietà indentificata dalla parola chiave passata come parametro
	 * 
	 * @param key la chiave della proprietà che si desidera avere 
	 * @return la proprietà corrispondente al chiave passata come parametro
	 */
	private String getProperty(String key) {
		String value = (String) properties.get(key);
		if (value == null) {
			throw new SystemException(key + ": invalid property");
		}
		return value;
	}



}
