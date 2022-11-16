package it.equitalia.gdo.svecchiamento.spazio.jms;


import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

import it.equitalia.gdo.commons.utils.ServiceLocator;

import org.apache.log4j.Logger;

import com.primeur.javax.jms.SpazioQueueConnection;
import com.primeur.javax.jms.SpazioQueueReceiver;
import com.primeur.javax.jms.SpazioQueueSender;
import com.primeur.javax.jms.SpazioQueueSession;
import com.primeur.spazio.jms.SPQueueConnection;
import com.primeur.spazio.jms.SPQueueConnectionFactory;
import com.primeur.spazio.jms.SPQueueSession;

public class ConnessioneSpazioHelper {
    private static Logger _logger = Logger.getLogger(ConnessioneSpazioHelper.class);
    
	/**
	 * Crea il Factory per la creazione del QueueConnection.
	 * @param l'jndi name del factory
	 * @return il factory del QueueConnection  
	 * @throws ComunicazioneSpazioException se vi sono problemi a creare il factory
	 */	
	public static SPQueueConnectionFactory createQueueConnectionFactory(String jndiName) throws ComunicazioneSpazioException {
		_logger.debug("Recupero del queueConnectionFactory in corso...");
		SPQueueConnectionFactory conFact=null;
		conFact= ServiceLocator.getInstance().getRemoteObject(jndiName, SPQueueConnectionFactory.class);
		return conFact;
	}	
	

	/**
	 * Crea la queue per spazio relativa all'operazione per cui verrà utilizzata.
	 * @param jndiName il jndi della queue 
	 * @return la queue 
	 * @throws ComunicazioneSpazioException in caso si presentano errori con la creazione della queue 
	 */
	public static Queue createQueue(String jndiName) throws ComunicazioneSpazioException{
	    	_logger.debug("Recupero della queue in corso...");
	    	Queue coda=null;
		coda= ServiceLocator.getInstance().getRemoteObject( jndiName, Queue.class);
		_logger.debug("Queue recuperata correttamente per invio a db di produzione");
		
		return coda;
	}
	
	/**
	 * Crea il Receiver sulla coda passata come parametro
	 * @return SpazioQueueReceiver 
	 * @throws ComunicazioneSpazioException
	 */
	public static SpazioQueueReceiver getReceiver(SpazioQueueSession sessione,Queue coda)throws ComunicazioneSpazioException{
		
		_logger.debug("Creazione del receiver in corso ...");
		
		try{
			
		    return (SpazioQueueReceiver) sessione.createReceiver(coda);
		    
		}catch (JMSException e) {
		    	e.printStackTrace();
			_logger.error("Errore nella creazione del Receiver "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella creazione del Receiver",e);
		}
		
	}
	
	public static SpazioQueueReceiver getReceiver(SpazioQueueSession sessione,Queue coda, String selector)throws ComunicazioneSpazioException{
		
	_logger.debug("Creazione del receiver in corso con selector = "+selector);
		try{
			
		    return (SpazioQueueReceiver) sessione.createReceiver(coda,selector);
		    
		}catch (JMSException e) {
		    	e.printStackTrace();
			_logger.error("Errore nella creazione del Receiver con selector="+selector+" Eccezione: "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella creazione del Receiver",e);
		}
	}

	
	/**
	 * Crea il QueueConnection sfruttando il factory passato come parametro
	 * @param factory il factory che verrà usato per la creazioen della queue connection
	 * @return la queue connection
	 * @throws ComunicazioneSpazioException in caso ci siano problemi a momento di creazione della queue connection
	 */
	public static SPQueueConnection createQueueConnection(SPQueueConnectionFactory factory) throws ComunicazioneSpazioException {
		_logger.debug("Creazione del queue connection in corso..");
		try {
			SPQueueConnection conn= (SPQueueConnection)factory.createQueueConnection();
			_logger.debug("Connessione creata");
			conn.start();
			_logger.debug("Connessione sulla coda avviata");    
			return conn;
		} catch (JMSException e) {
		    	e.printStackTrace();
			_logger.error("Errore nella creazione della queue "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella creazione della queue connecition",e);
		}
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
		try {
			sessione= (SPQueueSession)connessione.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
			_logger.debug("Sessione istanziata"); 
		} catch (JMSException e) {
		    		e.printStackTrace();
				_logger.error("Errore nella creazione della queue session "+e.getMessage());
				throw new ComunicazioneSpazioException("Errore nella creazione della queue session",e);
		}
	 
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
		SpazioQueueSender sender=null;
		try {
			sender= (SpazioQueueSender)sessione.createSender(queue);
			_logger.debug("Sender creato correttamente");
		} catch (JMSException e) {
		    	e.printStackTrace();
			_logger.error("Errore nella creazione del sender "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella creazione del sender",e);
		}
		return sender;
	}
	
	/**
	 * Si occupa di gestire opportunamente la chiusura di un queue sender 
	 * @param sender il sender che si vuole chiudere
	 * @throws ComunicazioneSpazioException in caso di problemi durante la chiusura
	 */
	public static void close(SpazioQueueSender sender) throws ComunicazioneSpazioException {
		_logger.debug("Chiusura del queue sender in corso");
		try {
			if(sender!=null)
				sender.close();
			
		} catch (JMSException e) {
		    	e.printStackTrace();
			_logger.error("Chiusura del queue sender fallita "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella chiusura del sender",e);
		}
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
		try {
			if(session!=null){
				close(sender);
				session.close();
			}
		} catch (JMSException e) {
		    	e.printStackTrace();
			_logger.error("Errore nella chiusura del queue sender: "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella chiusura della session",e);
		}
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
		try {
			if(connection!=null){
				close(sender,session);
				connection.stop();
				connection.close();
			}
		} catch (JMSException e) {
		    e.printStackTrace();
			_logger.error("errore nella chiusura della queue connection: "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella chiusura della  queue connessione",e);
		}
		_logger.debug("Chiusura del queue connection terminata con successo.");
	}
	
	
	/**
	 * Si occupa di gestire opportunamente la chiusura di un queue receiver 
	 * @param receiver il receiver che si vuole chiudere
	 * @throws ComunicazioneSpazioException in caso di problemi durante la chiusura
	 */
	public static void close(SpazioQueueReceiver receiver) throws ComunicazioneSpazioException {
		_logger.debug("Chiusura del queue receiver in corso");
		try {
			if(receiver!=null)
				receiver.close();
			
		} catch (JMSException e) {
		    	e.printStackTrace();
			_logger.error("Chiusura del queue receiver fallita "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella chiusura del receiver",e);
		}
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
		try {
			if(session!=null){
				close(receiver);
				session.close();
			}
		} catch (JMSException e) {
		    	e.printStackTrace();
			_logger.error("Errore nella chiusura del queue receiver: "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella chiusura della session",e);
		}
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
		try {
			if(connection!=null){
				close(receiver,session);
				connection.stop();
				connection.close();
			}
		} catch (JMSException e) {
		    e.printStackTrace();
			_logger.error("errore nella chiusura della queue connection: "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella chiusura della  queue connessione",e);
		}
		_logger.debug("Chiusura del queue connection terminata con successo.");
	}
	
	
}
