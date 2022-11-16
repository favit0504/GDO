package it.equitalia.gdo.commons.utils;

import java.util.Properties;

import org.apache.log4j.Logger;

public class GDOMessaggi {

	private final static Logger log = Logger.getLogger(GDOMessaggi.class);
	
	private final static String NOME_FILE_PROPERTIES ="gdo_messaggi.properties";

	
	
	private static Properties props;

	/* Chiavi i cui valori vanno letti da file */
	
	//ERRORI GENERICI
	public final static String ERRORE_GENERICO = "error.generico";
	public final static String ERRORE_SESSIONE = "error.sessione";
	public final static String MSG_NO_PRIVILEGI = "error.noauth";
	
	public final static String STATO_OBBLIGATORIO = "error.stato.obbligatorio";
	public final static String FORMATO_DATA_ERRATO = "error.data.formato";
	public final static String ORDINE_DATE_ERRATO = "error.data.ordinamento";
	
	public final static String FILTRI_NON_INSERITI = "error.no.parametri.ricerca";
	
	public final static String RICERCA_NESSUN_RISULTATO = "msg.no.risultati";
	public final static String ERRORE_REPERIMENTO_ELENCHI = "error.reperimento.elenchi";
	
	
	//ERRORI GESTIONE NEWS
	public final static String TITOLO_NEWS_OBBLIGATORIO = "error.news.titolo.obbligatorio";
	public final static String TESTO_NEWS_OBBLIGATORIO = "error.news.testo.obbligatorio";
	public final static String TIPOLOGIA_NEWS_OBBLIGATORIO = "error.news.tipologia.obbligatoria";
	public final static String ERRORE_INSERT_NEWS = "error.service.insert.news";	
	public final static String ERRORE_UPDATE_NEWS = "error.service.update.news";
	public final static String ERRORE_RECUPERA_NEWS = "error.service.recupera.news.generic";
	public final static String ERRORE_REPERIMENTO_DETTAGLIO = "error.news.reperimento.dettaglio";
	public final static String NEWS_SALVATA_CORRETTAMENTE = "news.salvata.ok";
	public final static String NECESSARIO_RICARICARE_ALLEGATO = "error.allegato.ricaricare";
	public final static String ALLEGATO_TROPPO_GRANDE = "error.allegato.dimensione";
	public final static String ERRORE_CARICAMENTO_ALLEGATO = "error.caricamento.allegato";
	public final static String ERRORE_NEWS_NON_MODIFICABILE = "error.news.non.modificabile";
	public final static String NEWS_NON_VSUALIZZABILE = "error.news.non.visualizzabile";
	public final static String CODICE_ENTE_NUMERICO = "error.codice.ente.numerico";
	public static final String DATA_INIZIO_OBBLIGATORIA = "error.news.data_inizio.obbligatoria";
	
	public static final String ERRORE_NEWS_TESTO_NON_VALIDO = "error.news.testo.non_valido";
	public static final String ERRORE_NEWS_TITOLO_NON_VALIDO = "error.news.titolo.non_valido";
	public static final String ERRORE_NEWS_TESTO_TROPPO_LUNGO = "error.news.testo.troppo_lungo";	
	

	//ERRORI GESTIONE DOCUMENTO
	public final static String DOCUMENTO_SALVATO_CORRETTAMENTE = "documento.salvato.ok";
	public final static String ERRORE_INSERT_DOCUMENTO = "error.service.insert.documento";
	public final static String ERRORE_UPDATE_DOCUMENTO = "error.service.update.documento";
	public final static String ERRORE_DELETE_DOCUMENTO = "error.service.delete.documento";
	public final static String ERRORE_RECUPERA_DOCUMENTO = "error.service.recupera.documento.generic";
	public final static String ERRORE_CARICAMENTO_DOCUMENTO = "error.caricamento.documento";
	public final static String TITOLO_DOCUMENTO_OBBLIGATORIO = "error.documento.titolo.obbligatorio";
	public final static String TIPOLOGIA_DOCUMENTO_OBBLIGATORIA = "error.documento.tipologia.obbligatoria";
	public final static String DESCRIZIONE_DOCUMENTO_OBBLIGATORIA = "error.documento.descrizione.obbligatoria";
	public final static String STATO_DOCUMENTO_OBBLIGATORIO = "error.documento.stato.obbligatorio";
	public final static String FILE_DOCUMENTO_OBBLIGATORIO = "error.documento.file.obbligatorio";
	public final static String FILE_ALLEGATO_VUOTO = "error.news.file.vuoto";
	public final static String FILE_DOCUMENTO_VUOTO = "error.documento.file.vuoto";
	public final static String FILE_DOCUMENTO_TROPPO_GRANDE = "error.file.dimensione";
	public static final String ID_SEZIONE_NON_PRESENTE = "error.idsezione.mancante";
	public final static String ERRORE_DOCUMENTO_NON_MODIFICABILE = "error.documento.non.modificabile";
	public final static String ERRORE_DOCUMENTO_NON_TROVATO = "error.service.recupera.documento.nontrovato";
	public final static String NECESSARIO_RICARICARE_FILE = "error.file.ricaricare";
	public final static String ERRORE_NESSUN_DOCUMENTO_SPECIFICATO = "error.service.recupera.documento.noallegato";
	public final static String ERRORE_RECUPERA_FILE = "error.service.recupera.file.generic";
	public final static String ERRORE_FILE_NON_TROVATO = "error.service.recupera.file.nontrovato";
	
	public static final String ERRORE_DOCUMENTO_TITOLO_NON_VALIDO = "error.documento.titolo.non_valido";
	public static final String ERRORE_DOCUMENTO_DESCRIZIONE_NON_VALIDA = "error.documento.descrizione.non_valido";
	
	//ERRORI GESTIONE ALLEGATO
	public final static String ERRORE_RECUPERA_ALLEGATO = "error.service.recupera.allegato.generic";
	public final static String ERRORE_NESSUN_ALLEGATO_SPECIFICATO = "error.service.recupera.allegato.noallegato";
	public final static String ERRORE_ALLEGATO_NON_TROVATO = "error.service.recupera.allegato.nontrovato";
	public final static String ERRORE_ALLEGATO_RECORD_NON_VALIDO = "error.service.recupera.allegato.corrotto";

	//ERRORI GESTIONE SEZIONE
	public final static String ERRORE_INSERT_SEZIONE = "error.service.insert.sezione";
	public final static String ERRORE_UPDATE_SEZIONE = "error.service.update.sezione";
	public final static String ERRORE_DELETE_SEZIONE = "error.service.delete.sezione";
	public final static String ERRORE_RECUPERA_SEZIONE = "error.service.recupera.sezione.generic";
	public final static String TITOLO_SEZIONE_OBBLIGATORIO = "error.sezione.titolo.obbligatorio";
	public final static String DESCRIZIONE_SEZIONE_OBBLIGATORIO = "error.sezione.descrizione.obbligatorio";
	public final static String STATO_SEZIONE_OBBLIGATORIO = "error.sezione.stato.obbligatorio";
	public final static String SEZIONE_SALVATA_CORRETTAMENTE = "sezione.salvata.ok";
	public final static String ERRORE_SEZIONE_NON_MODIFICABILE = "error.sezione.non.modificabile";
	public final static String ERRORE_SEZIONE_NO_PERMESSI = "error.sezione.no.permessi";
	public final static String ERRORE_SEZIONE_NO_ABILITAZIONE = "error.sezione.no.abilitazione";
	
	public static final String ERRORE_SEZIONE_TITOLO_NON_VALIDO = "error.sezione.titolo.non_valido";
	public static final String ERRORE_SEZIONE_DESCRIZIONE_NON_VALIDA = "error.sezione.descrizione.non_valido";
	
	//ERRORI GESTIONE SVECCHIAMENTO
	public final static String ERRORE_DOC_FILE_SVECCHIAMENTO = "error.service.recupera.file.documento.svecchiamento";
	public final static String ERRORE_NEWS_FILE_SVECCHIAMENTO = "error.service.recupera.file.news.svecchiamento";

	//ERRORI GESTIONE REGIONI
	public final static String ERRORE_RECUPERA_REGIONE = "error.service.recupera.regione";

	//ERRORI GESTIONE SERVIZI
	public final static String ERRORE_RECUPERA_SERVIZIO = "error.service.recupera.servizio";
	public final static String ERRORE_RECUPERA_SERVIZIO_ENTE = "error.service.recupera.servizio.ente";
	public final static String ERRORE_RECUPERA_SERVIZIO_AGENTE = "error.service.recupera.servizio.agente";

	//ERRORI GESTIONE ENTI
	public final static String ERRORE_RECUPERA_ENTE = "error.service.recupera.ente";

	//ERRORI GESTIONE PROVINCE
	public final static String ERRORE_RECUPERA_PROVINCIA = "error.service.recupera.provincia";
	
	//ERRORI GESTIONE TRIPLETTA ENTE
	public static final String ERRORE_RECUPERA_TRIPLETTA_ENTE = "error.service.recupera.tripletta.ente";
	
	//ERRORI GESTIONE TIPOLOGIA ENTE
	public static final String ERRORE_RECUPERA_TIPOLOGIA_ENTE = "error.service.recupera.tipologia.ente";

	//ERRORI GESTIONE POPOLAMENTO TIPOLOGIA UTENTE
	public static final String ERRORE_POPOLAMENTO_TIPOLOGIA_UTENTE = "error.service.popola.tipologia.utente";

	//ERRORI GESTIONE SOCIETA
	public static String ERRORE_RECUPERA_SOCIETA_AGENTE = "error.service.recupera.societa.agente";

	//ERRORI GESTIONE POPOLAMENTO UTENTE
	public static String ERRORE_POPOLAMENTO_UTENTE = "error.service.popolamento.utente";
	
	//ERRORI GESTIONE AMBITI
	public final static String ERRORE_RECUPERA_AMBITO = "error.service.recupera.ambito";
	
	// ERRORE MANUALE UTENTE NON TROVATO
	public final static String MANUALE_NOT_FOUND = "error.manuale.utente.not.found";
	
	public static final String TOOLTIP_TESTO_NEWS = "tooltip.news.testo";
	
	static {
		
		try {
			
		props = new Properties();
		props.load(GDOMessaggi.class.getClassLoader().getResourceAsStream(NOME_FILE_PROPERTIES));
		log.debug("Inizializzati messaggi gdo_messaggi.properties");
		}
		catch(Exception e) {
			log.error("Errore in fase di inializzazione gdo_gessaggi.properties", e);
		}
	}
	
	public static String getMessaggio(String key) {
		
	String propValue = "";
		
		if (key != null && !key.trim().equals(""))
		{
			propValue = props.getProperty(key);
		}
		
		return propValue;
	}
	
	/**
	 * 
	 * @param key
	 * @param vars Varargs, numero variabile di parametri da sostituire nel messaggio
	 * 						in corrispondenza di %0, %1, %2, ecc.
	 * @return
	 */
	public static String getMessaggioConVariabili(String key, String... vars) {
		
		String msg = getMessaggio(key) ;
		for (int i =0; i < vars.length;i++) {
			msg= msg.replace("%"+i, vars[i]);
		}
		return msg;
	}
	
	
}
