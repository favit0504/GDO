package it.equitalia.gdo.svecchiamento.spazio.jms;


import javax.jms.Queue;

import org.apache.log4j.Logger;

import com.primeur.javax.jms.SpazioQueueConnection;
import com.primeur.javax.jms.SpazioQueueReceiver;
import com.primeur.javax.jms.SpazioQueueSender;
import com.primeur.javax.jms.SpazioQueueSession;
import com.primeur.javax.jms.SpazioTextRecordFile;
import com.primeur.spazio.jms.SPQueueConnection;
import com.primeur.spazio.jms.SPQueueConnectionFactory;
import com.primeur.spazio.jms.SPQueueSession;

/*
 * 
 * ATTENZIONE NON CAMBIARE QUESTO FILE PER ESIGENZE DI UN SINGOLO PROGETTO.
 *          SE NECESSARIO ESTENDERE LA CLASSE E FARE OVERLOADING.
 * 
 * 
 * 
 */
/**
 * Questa classe aiuta la comunicazione con Spazio Primeur tramite
 * JMS rendendo molte attività trasparenti alle altre classi.
 * 
 * Normalmente l'ordine per inviare un file a Spazio è:
 * //Creo il factory della connection 
 * SPQueueConnectionFactory  fact = SpazioJMSHelper.createQueueConnectionFactory(jndiName);
 * 
 * //creo la connessione
 * conn =  SpazioJMSHelper.createQueueConnection(fact);
 * 
 * //creo la sessione
 * sess = SpazioJMSHelper.createQueueSession(conn);
 * 
 * //creo la coda
 * Queue queue = SpazioJMSHelper.createQueue(jndiName);
 * 
 * //creo il sender per la queue da me interessata
 * sender = SpazioJMSHelper.createSender(sess,queue);
 * 
 *  //creo il file a seconda del tipo
 * Spazio****File fileSpazio = SpazioJMSHelper.create*****File(sess);
 * 
 * //setto le proprietà
 * SpazioJMSHelper.set*****OnFile(fileSpazio);
 * 
 * //scrivo
 * //e qui che viene fatto il clearbody o impsotate alcune proprietà proprie dei diversi tipi di file
 * SpazioJMSHelper.writeByteInFile(fileSpazio, file, sender);
 * 
 * //invio a spazio
 * SpazioJMSHelper.sendFile(sender, fileSpazio);
 * 
 * //Mettere la close nel finally * 
 * SpazioJMSHelper.close(sender,sess,conn);
 * 
 * 
 */
public class SpazioJMSHelper {
	private static Logger _logger = Logger.getLogger(SpazioJMSHelper.class);
	
	/**
	 * Crea il Factory per la creazione del QueueConnection.
	 * @param l'jndi name del factory
	 * @return il factory del QueueConnection  
	 * @throws ComunicazioneSpazioException se vi sono problemi a creare il factory
	 */	
	public static SPQueueConnectionFactory createQueueConnectionFactory(String jndiName) throws ComunicazioneSpazioException {
		_logger.debug("Recupero del queueConnectionFactory in corso...");
		return ConnessioneSpazioHelper.createQueueConnectionFactory(jndiName); 
	}	
	

	/**
	 * Crea la queue per spazio relativa all'operazione per cui verrà utilizzata.
	 * @param jndiName il jndi della queue 
	 * @return la queue 
	 * @throws ComunicazioneSpazioException in caso si presentano errori con la creazione della queue 
	 */
	public static Queue createQueue(String jndiName) throws ComunicazioneSpazioException{
	    	_logger.debug("Recupero della queue in corso...");
	    	Queue coda=ConnessioneSpazioHelper.createQueue(jndiName);
		_logger.debug("Queue recuperata correttamente per invio a db di produzione");
		
		return coda;
	}
	
//	/**
//	 * Crea il Receiver sulla coda passata come parametro
//	 * @return SpazioQueueReceiver 
//	 * @throws ComunicazioneSpazioException
//	 */
//	public static SpazioQueueReceiver getReceiver(SpazioQueueSession sessione,Queue coda)throws ComunicazioneSpazioException{
//		_logger.debug("Creazione del receiver in corso ...");	
//		return ConnessioneSpazioHelper.getReceiver(sessione, coda);
//	}
//	
//	public static SpazioQueueReceiver getReceiver(SpazioQueueSession sessione,Queue coda, String selector)throws ComunicazioneSpazioException{
//		
//	_logger.debug("Creazione del receiver in corso con selector = "+selector);
//
//		return ConnessioneSpazioHelper.getReceiver(sessione, coda, selector);
//		    
//	}

	
	/**
	 * Crea il QueueConnection sfruttando il factory passato come parametro
	 * @param factory il factory che verrà usato per la creazione della queue connection
	 * @return la queue connection
	 * @throws ComunicazioneSpazioException in caso ci siano problemi a momento di creazione della queue connection
	 */
	public static SPQueueConnection createQueueConnection(SPQueueConnectionFactory factory) throws ComunicazioneSpazioException {
		_logger.debug("Creazione del queue connection in corso..");
		return ConnessioneSpazioHelper.createQueueConnection(factory);
		
	}
	
	/**
	 * Crea il QueueSession sfruttando il QueueConnection passato come parametro
	 * @param connessione la connessione su cui verrà istanziata la queue session
	 * @return la queue session
	 * @throws ComunicazioneSpazioException in caso ci siano problemi a momento di creazione della queue session
	 */
	public static SPQueueSession createQueueSession(SpazioQueueConnection connessione) throws ComunicazioneSpazioException {
		_logger.debug("Avvio della creazione della spazioQueueConnection");
		SPQueueSession sessione=null;
		sessione= ConnessioneSpazioHelper.createQueueSession(connessione);
		_logger.debug("Sessione istanziata"); 
		return sessione;
	}

	/**
	 * Crea un opportuno sender in base alla sessione e alla queue passate come parametri
	 * @param sessione la sessione in cui il sender andrà ad operare
	 * @param queue la coda in cui il sender andrà a inviare i file
	 * @return il sender valido per la queue e la sessione passati come parametro
	 * @throws ComunicazioneSpazioException in caso ci siano problemi a momento di creazione del sender
	 */
	public static SpazioQueueSender createSender(SpazioQueueSession sessione, Queue queue) throws ComunicazioneSpazioException {
		_logger.debug("Creazione del sender per l'invio su spazio in corso...");
		SpazioQueueSender sender=ConnessioneSpazioHelper.createSender(sessione, queue);
		return sender;
	}

	/**
	 * Metodo di supporto per la corretta istanziazione di un file testuale di record per spazio jms.
	 * @param sessione la sessione sulla queue dove si vuole inviare il file
	 * @return il file inserito correttamente all'interno della classe SpazioTextRecordFile
	 * @throws ComunicazioneSpazioException in caso che ci siano problemi nella creazione del file
	 */
	public static SpazioTextRecordFile createTextRecordFile(SpazioQueueSession sessione) throws ComunicazioneSpazioException {
		_logger.debug("Creazione del file di tipo record per spazio in corso..");
		//	inizializzo il contenitore del file da inviare specificando anche con quale sender sarà inviato
		SpazioTextRecordFile fileSpazio=IOSpazioHelper.createTextRecordFile(sessione);
	
		_logger.debug("File creato correttamente.");
		return fileSpazio;
	}
	
//	/**
//	 * Metodo di supporto per la corretta istanziazione di un file testuale per spazio jms.
//	 * @param sessione la sessione sulla queue dove si vuole inviare il file
//	 * @return il file inserito correttamente all'interno della classe SpazioTextFile
//	 * @throws ComunicazioneSpazioException in caso che ci siano problemi nella creazione del file
//	 */
//	public static SpazioTextFile createTextFile(SpazioQueueSession sessione) throws ComunicazioneSpazioException {
//		_logger.debug("Creazione del file di tipo testuale per spazio in corso..");
//		//	inizializzo il contenitore del file da inviare specificando anche con quale sender sarà inviato
//		SpazioTextFile fileSpazio=IOSpazioHelper.createTextFile(sessione);
//			
//		_logger.debug("File creato correttamente.");
//		return fileSpazio;
//	}
//	
//	/**
//	 * Metodo di supporto per la corretta istanziazione di un file binario per spazio jms.
//	 * @param sessione la sessione sulla queue dove si vuole inviare il file
//	 * @return il file inserito correttamente all'interno della classe SpazioBytesFile
//	 * @throws ComunicazioneSpazioException in caso che ci siano problemi nella creazione del file
//	 */
//	public static SpazioBytesFile createBinaryFile(SpazioQueueSession sessione) throws ComunicazioneSpazioException {
//		_logger.debug("Creazione del file di tipo binario per spazio in corso..");
//		//	inizializzo il contenitore del file da inviare specificando anche con quale sender sarà inviato
//		SpazioBytesFile fileSpazio=IOSpazioHelper.createBinaryFile(sessione);
//		_logger.debug("File creato correttamente.");
//		return fileSpazio;
//	}
//	
//	/**
//	 * Scrive il file di testo, passato come parametro, nel file per spazio dopo aver effettuato il clear del body
//	 * @param fileSpazio il file per spazio in cui va scritto il file
//	 * @param file il file che si vuole inviare
//	 * @param sender il sender che si occuperò di inviare il file
//	 * @throws ComunicazioneSpazioException
//	 */
//	public static void writeInFile(SpazioTextRecordFile fileSpazio, String file, SpazioQueueSender sender) throws ComunicazioneSpazioException{
//	    _logger.debug("Scrittura dei byte nel file di tipo record per spazio in corso..");
//	    IOSpazioHelper.writeInFile(fileSpazio, file, sender);
//	    _logger.debug("File scritto correttamente.");
//	    
//	}
//	
//	/**
//	 * Scrive il file di testo, passato come parametro, nel file per spazio dopo aver effettuato il clear del body
//	 * @param fileSpazio il file per spazio in cui va scritto il file 
//	 * @param file il file che si vuole inviare
//	 * @param sender il sender che si occuperò di inviare il file
//	 * @throws ComunicazioneSpazioException
//	 */
//	public static void writeInFile(SpazioTextFile fileSpazio, String file, SpazioQueueSender sender) throws ComunicazioneSpazioException{
//	    _logger.debug("Scrittura dei byte nel file di tipo testuale per spazio in corso..");
//	    IOSpazioHelper.writeInFile(fileSpazio, file, sender);
//	    _logger.debug("File scritto correttamente.");
//	    
//	}
//	
//	/**
//	 * Scrive il file di binario, passato come parametro, nel file per spazio dopo aver effettuato il clear del body
//	 * @param fileSpazio il file per spazio in cui va scritto il file binario
//	 * @param file il file che si vuole inviare
//	 * @param sender il sender che si occuperò di inviare il file
//	 * @throws ComunicazioneSpazioException
//	 */
//	public static void writeInFile(SpazioBytesFile fileSpazio, byte[] fileBinario, SpazioQueueSender sender) throws ComunicazioneSpazioException{
//	    _logger.debug("Scrittura dei byte nel file di tipo binario per spazio in corso..");
//	    IOSpazioHelper.writeInFile(fileSpazio, fileBinario, sender);
//	    _logger.debug("File scritto correttamente.");
//	    
//	}
//	
//	/**
//	 * Legge il file di record testuali e ne restituisce un array binario
//	 * @param fileSpazio
//	 * @return un array binario contentente in forma binaria i record testuali
//	 * @throws ComunicazioneSpazioException in caso si verificano errori durante la lettura
//	 */
//	public static byte[] readFromFile(SpazioRecordFile fileSpazio) throws ComunicazioneSpazioException{
//	    _logger.debug("Lettura dei byte nel file di tipo text record per spazio in corso..");
//	    byte[] risposta=IOSpazioHelper.readFromFile(fileSpazio);
//	    return risposta;
//	}
//
//	
	/**
	 * Si occupa di gestire opportunamente la chiusura di un queue sender 
	 * @param sender il sender che si vuole chiudere
	 * @throws ComunicazioneSpazioException in caso di problemi durante la chiusura
	 */
	public static void close(SpazioQueueSender sender) throws ComunicazioneSpazioException {
		_logger.debug("Chiusura del queue sender in corso");
		ConnessioneSpazioHelper.close(sender);
		_logger.debug("Chiusura del queue sender riuscita");
	}
	
	/**
	 * Si occupa di gestire opportunamente la chiusura di una session e del sender 
	 * @param sender il sender che si vuole chiudere
	 * @param session la session che si vuole chiudere
	 * @throws ComunicazioneSpazioException in caso di problemi durante la chiusura
	 */
	public static void close(SpazioQueueSender sender, SpazioQueueSession session) throws ComunicazioneSpazioException {
		_logger.debug("Chiusura del queue session in corso");
		ConnessioneSpazioHelper.close(sender,session);
		_logger.debug("Chiusura del queue session termianta con successo.");
	}
	
	/**
	 * Si occupa di gestire opportunamente la chiusura di un queue sender,session e della conessione 
	 * @param sender il sender che si vuole chiudere
	 * @param session la sessione che si vuole chiudere
	 * @param connection la connessione che si vuole chiudere
	 * @throws ComunicazioneSpazioException in caso di problemi durante la chiusura
	 */
	public static void close(SpazioQueueSender sender, SpazioQueueSession session,SpazioQueueConnection connection) throws ComunicazioneSpazioException {
		_logger.debug("Chiusura del queue connection in corso");
		ConnessioneSpazioHelper.close(sender,session,connection);
		_logger.debug("Chiusura del queue connection terminata con successo.");
	}
	
	
	/**
	 * Si occupa di gestire opportunamente la chiusura di un queue receiver 
	 * @param receiver il receiver che si vuole chiudere
	 * @throws ComunicazioneSpazioException in caso di problemi durante la chiusura
	 */
	public static void close(SpazioQueueReceiver receiver) throws ComunicazioneSpazioException {
		_logger.debug("Chiusura del queue receiver in corso");
		ConnessioneSpazioHelper.close(receiver);
		_logger.debug("Chiusura del queue receiver riuscita");
	}
	
	
	/**
	 * Si occupa di gestire opportunamente la chiusura di una session e del receiver 
	 * @param receiver il receiver che si vuole chiudere
	 * @param session la session che si vuole chiudere
	 * @throws ComunicazioneSpazioException in caso di problemi durante la chiusura
	 */
	public static void close(SpazioQueueReceiver receiver, SpazioQueueSession session) throws ComunicazioneSpazioException {
		_logger.debug("Chiusura del queue session in corso");
		ConnessioneSpazioHelper.close(receiver,session);
		_logger.debug("Chiusura del queue session termianta con successo.");
	}

	
	
	/**
	 * Si occupa di gestire opportunamente la chiusura di un queue receiver,session e della conessione 
	 * @param receiver il sender che si vuole chiudere
	 * @param session la sessione che si vuole chiudere
	 * @param connection la connessione che si vuole chiudere
	 * @throws ComunicazioneSpazioException in caso di problemi durante la chiusura
	 */
	public static void close(SpazioQueueReceiver receiver, SpazioQueueSession session,SpazioQueueConnection connection) throws ComunicazioneSpazioException {
		_logger.debug("Chiusura del queue connection in corso");
		ConnessioneSpazioHelper.close(receiver,session,connection);
		_logger.debug("Chiusura del queue connection terminata con successo.");
	}
	

//	/**
//	 * Si occupa di inviare correttamente, gestendo anche gli eventuali errori,il file tramite il sender 
//	 * entrambi passati come parametri.
//	 *  
//	 * @param sender il sender che verrà usato per inviare il file
//	 * @param fileSpazio il file da inviare
//	 * @throws ComunicazioneSpazioException in caso vi presentano problemi a inviare file a spazio
//	 */
//	public static void sendFile(SpazioQueueSender sender, SpazioFile fileSpazio) throws ComunicazioneSpazioException {
//		_logger.debug("Invio del file a spazio jms in corso..." );
//		IOSpazioHelper.sendFile(sender, fileSpazio);
//		_logger.debug("File inviato correttamente.");
//	}
//	
//	/**
//	 * Metodo che setta correttamente la descrizione all'interno del file da inviare a spazio
//	 * @param file il file da inviare a spazio
//	 * @param descrizione la descrizione che si vuole inserire.
//	 * @throws ComunicazioneSpazioException in caso si verificano problemi con l'inserimento della descrizione
//	 */
//	public static void setDescriptionOnFile(SpazioFile file, String descrizione) throws ComunicazioneSpazioException{
//		_logger.debug("Inserimento della descrizione nel file di spazio in corso...");
//		IOSpazioHelper.setDescriptionOnFile(file, descrizione);
//		_logger.debug("Inserimento della descrizione nel file di spazio terminata con successo.");
//	}
//	
//	/**
//	 * Metodo che setta correttamente la userclass all'interno del file da inviare a spazio
//	 * @param file il file da inviare a spazio
//	 * @param userclass la classe da usare per fare il dispatch
//	 * @throws ComunicazioneSpazioException in caso si verificano problemi con l'inserimento della userclass
//	 */
//	public static void setUserClassOnFile(SpazioFile file, String userclass) throws ComunicazioneSpazioException{
//		_logger.debug("Inserimento della UserClass nel file di spazio in corso...");
//		IOSpazioHelper.setUserClassOnFile(file, userclass);
//		_logger.debug("Inserimento della UserClass nel file di spazio terminata con successo.");
//	}
//	
//	
//	/**
//	 * Metodo che setta correttamente la descrizione all'interno del file da inviare a spazio
//	 * @param file il file da inviare a spazio
//	 * @param descrizione la descrizione che si vuole inserire.
//	 * @throws ComunicazioneSpazioException in caso si verificano problemi con l'inserimento della descrizione
//	 */
//	public static void setCorrelationIdOnFile(SpazioFile file, String CorrelationID) throws ComunicazioneSpazioException{
//		_logger.debug("Inserimento del CorrelationId nel file di spazio in corso...");
//		IOSpazioHelper.setCorrelationIdOnFile(file, CorrelationID);
//		_logger.debug("Inserimento del CorrelationId nel file di spazio terminato con successo.");
//	}
//	
//	/**
//	 * Metodo che setta correttamente il sender all'interno del file da inviare a spazio
//	 * @param file il file da inviare a spazio
//	 * @param sender il sender  che si vuole inserire.
//	 * @throws ComunicazioneSpazioException in caso si verificano problemi con l'inserimento della descrizione
//	 */
//	public static void setSender(SpazioFile file, String sender) throws ComunicazioneSpazioException{
//		_logger.debug("Inserimento del Sender nel file di spazio in corso...");
//		
//		IOSpazioHelper.setSender(file, sender);
//		
//		_logger.debug("Inserimento del sender nel file di spazio terminato con successo.");
//	}
//	
	
	
	
	
	
	
}
