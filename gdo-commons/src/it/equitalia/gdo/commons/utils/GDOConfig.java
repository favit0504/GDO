package it.equitalia.gdo.commons.utils;

import it.equitalia.gdo.commons.exception.SystemException;

import java.util.Properties;

import org.apache.log4j.Logger;

public class GDOConfig {

	private final static String NOME_FILE_PROPERTIES = "gdo.properties";
	public final static String AMBIENTE_SVILUPPO = "AMBIENTE_SVILUPPO";
	public final static String SALTA_GPU = "SALTA_GPU";
	public final static String SALTA_CODA = "SALTA_CODA";
	
	public final static String TIPO_CODA_SPAZIO = "TIPO_CODA_SPAZIO";

	// LOGGER
	private final static Logger log = Logger.getLogger(GDOConfig.class);
	public static final String NUMERO_ALLEGATI = "NUMERO_ALLEGATI";
	public static final String DIM_MAX_ALLEGATI_KB = "DIM_MAX_ALLEGATI_KB";
	public static final String DIM_MAX_FILE_DOCUMENTO_KB = "DIM_MAX_FILE_DOCUMENTO_KB";
	public static final String RIS_PER_PAGINA = "RIS_PER_PAGINA";
	public static final String NUMERO_FILE_DOCUMENTO = "NUMERO_FILE_DOCUMENTO";
	
	// AMBITI SOCIETARI
	public static final String CODICE_NORD = "CODICE_NORD";
	public static final String CODICE_CENTRO = "CODICE_CENTRO";
	public static final String CODICE_SUD = "CODICE_SUD";
	public static final String CODICE_SICILIA = "CODICE_SICILIA";
	
	public static final String WS_CALL_EJB_JNDI_NEWS_FRONTEND = "service.jndi_news_frontend";
	public static final String WS_CALL_EJB_JNDI_DOCUMENTO = "service.jndi_documento";
	public static final String WS_CALL_EJB_JNDI_NEWS = "service.jndi_news";
	
	//DATASOURCE GDO
	public static final String GDO_ORACLE = "GDO_ORACLE";
	public static final String GDO_DB2 = "GDO_DB2";
	
	// PROPERTY FILE DI CONFIGURAZIONE GDO
	private Properties gdoProperties = null;
	private int lunghezzaMassimaTestoNews = 2048; //default

	// SINGLETON
	private static GDOConfig instance = null;
	
	public static final String MANUALE_UTENTE = "MANUALE_UTENTE";
	
	public static final String LUNGHEZZA_MASSIMA_TESTO_NEWS = "LUNGHEZZA_MASSIMA_TESTO_NEWS";
	
	
	// PREFISSI DEL CORRELATION ID
	public static final String NEWS_CORRELATION_ID = "N";
	public static final String DOC_CORRELATION_ID = "D";
	

	private GDOConfig() {

		try {

			// CARICO LA LISTA DELLE PROPRIETA' DI CONFIGURAZIONE DI GDO
			gdoProperties = new Properties();
			gdoProperties.load(this.getClass().getClassLoader().getResourceAsStream(NOME_FILE_PROPERTIES));
			
			log.info("File di properties [" + GDOConfig.NOME_FILE_PROPERTIES + "] caricato con successo");
			
			String lunghMaxNewsStr = gdoProperties.getProperty(LUNGHEZZA_MASSIMA_TESTO_NEWS);
			lunghezzaMassimaTestoNews = Integer.parseInt(lunghMaxNewsStr);
			
		} catch (Exception e) {

			// annullo l'instanza che stavo creando
			instance = null;

			// salvo un errore chiaro
			log.error("Errore in fase di lettura del file " + GDOConfig.NOME_FILE_PROPERTIES);

			// mostro all'utente a video un messaggio non troppo specifico
			throw new SystemException("Errore in fase di inizializzazione del servizio", e);

		}

	}

	/**
	 * Inizializzazione singleton
	 * 
	 * @return
	 */
	public static synchronized GDOConfig getInstance() {
		if (instance == null) {
			instance = new GDOConfig();
		}

		return instance;
	}

	/**
	 * Lettura property
	 * 
	 * @param propKey
	 * @return
	 */
	public String getProperty(String propKey) {
		String propValue = "";

		if (propKey != null && !propKey.trim().equals("")) {
			propValue = gdoProperties.getProperty(propKey);
		}
		else {
			throw new SystemException("Richiesta una proprietà di configurazione non impostata");
		}

		return propValue;
	}
	
	
	public int getLunghezzaMassimaTestoNews() {
		return lunghezzaMassimaTestoNews;
	}
}
