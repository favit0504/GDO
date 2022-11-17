package it.equitalia.gdo.svecchiamento.spazio.jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.jms.JMSException;


import org.apache.log4j.Logger;

import com.primeur.javax.jms.SpazioBytesFile;
import com.primeur.javax.jms.SpazioCharSet;
import com.primeur.javax.jms.SpazioEorEofType;
import com.primeur.javax.jms.SpazioFile;
import com.primeur.javax.jms.SpazioProperty;
import com.primeur.javax.jms.SpazioQueueSender;
import com.primeur.javax.jms.SpazioQueueSession;
import com.primeur.javax.jms.SpazioRecordFile;
import com.primeur.javax.jms.SpazioTextFile;
import com.primeur.javax.jms.SpazioTextRecordFile;

public class IOSpazioHelper {
    private static Logger _logger = Logger.getLogger(IOSpazioHelper.class);
    
    
    /**
	 * Metodo di supporto per la corretta istanziazione di un file testuale di record per spazio jms.
	 * @param sessione la sessione sulla queue dove si vuole inviare il file
	 * @return il file inserito correttamente all'interno della classe SpazioTextRecordFile
	 * @throws ComunicazioneSpazioException in caso che ci siano problemi nella creazione del file
	 */
	public static SpazioTextRecordFile createTextRecordFile(SpazioQueueSession sessione) throws ComunicazioneSpazioException {
		_logger.debug("Creazione del file di tipo record per spazio in corso..");
		//	inizializzo il contenitore del file da inviare specificando anche con quale sender sarà inviato
		SpazioTextRecordFile fileSpazio;
		try {
		   fileSpazio = sessione.createVarTextRecordFile(SpazioEorEofType.EOR_CRLF);
			
		} catch (JMSException e) {
		    	e.printStackTrace();
			_logger.error("Errore nella creazione del file di record "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella creazione del file di recordo",e);
		}	
		_logger.debug("File creato correttamente.");
		return fileSpazio;
	}
	
	/**
	 * Metodo di supporto per la corretta istanziazione di un file testuale per spazio jms.
	 * @param sessione la sessione sulla queue dove si vuole inviare il file
	 * @return il file inserito correttamente all'interno della classe SpazioTextFile
	 * @throws ComunicazioneSpazioException in caso che ci siano problemi nella creazione del file
	 */
	public static SpazioTextFile createTextFile(SpazioQueueSession sessione) throws ComunicazioneSpazioException {
		_logger.debug("Creazione del file di tipo testuale per spazio in corso..");
		//	inizializzo il contenitore del file da inviare specificando anche con quale sender sarà inviato
		SpazioTextFile fileSpazio;
		try {
		   fileSpazio = sessione.createTextFile();
		   //IMPOSTAZIONE DEl TERMINATORE DI FINE RIGA
		  
			
		} catch (JMSException e) {
		    	e.printStackTrace();
			_logger.error("Errore nella creazione del file di testo "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella creazione del file di testo",e);
		}	
		_logger.debug("File creato correttamente.");
		return fileSpazio;
	}
	
	/**
	 * Metodo di supporto per la corretta istanziazione di un file binario per spazio jms.
	 * @param sessione la sessione sulla queue dove si vuole inviare il file
	 * @return il file inserito correttamente all'interno della classe SpazioBytesFile
	 * @throws ComunicazioneSpazioException in caso che ci siano problemi nella creazione del file
	 */
	public static SpazioBytesFile createBinaryFile(SpazioQueueSession sessione) throws ComunicazioneSpazioException {
		_logger.debug("Creazione del file di tipo binario per spazio in corso..");
		//	inizializzo il contenitore del file da inviare specificando anche con quale sender sarà inviato
		SpazioBytesFile fileSpazio;
		try {
		   fileSpazio = sessione.createBytesFile();
			
		} catch (JMSException e) {
		    	e.printStackTrace();
			_logger.error("Errore nella creazione del file binario "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nella creazione del file binario",e);
		}	
		_logger.debug("File creato correttamente.");
		return fileSpazio;
	}
	
	/**
	 * Scrive il file di testo, passato come parametro, nel file per spazio dopo aver effettuato il clear del body
	 * @param fileSpazio il file per spazio in cui va scritto il file
	 * @param file il file che si vuole inviare
	 * @param sender il sender che si occuperò di inviare il file
	 * @throws ComunicazioneSpazioException
	 */
	public static void writeInFile(SpazioTextRecordFile fileSpazio, String file, SpazioQueueSender sender) throws ComunicazioneSpazioException{
	    _logger.debug("Scrittura dei byte nel file di tipo record per spazio in corso..");
	    try {
		fileSpazio.setIntProperty(SpazioProperty.CODED_CHAR_SET_ID, SpazioCharSet.ASCII);
		fileSpazio.clearBody(sender);
		BufferedReader br=new BufferedReader(new StringReader(file));
		String line=null;
		while((line=br.readLine())!=null){
		    fileSpazio.writeLine(line);
		}
	   	fileSpazio.flush();
	   	br.close();
	   	
	    } catch (JMSException e) {
	    	e.printStackTrace();
		_logger.error("Errore nella scrittura del file di record "+e.getMessage());
		throw new ComunicazioneSpazioException("Errore nella scrittura del file di record",e);
	    } catch (IOException e) {
		e.printStackTrace();
		_logger.error("Errore nella scrittura del file binario "+e.getMessage());
		throw new ComunicazioneSpazioException("Errore nella scrittura del file di record",e);
	    }	
	    _logger.debug("File scritto correttamente.");
	    
	}
	
	/**
	 * Scrive il file di testo, passato come parametro, nel file per spazio dopo aver effettuato il clear del body
	 * @param fileSpazio il file per spazio in cui va scritto il file 
	 * @param file il file che si vuole inviare
	 * @param sender il sender che si occuperò di inviare il file
	 * @throws ComunicazioneSpazioException
	 */
	public static void writeInFile(SpazioTextFile fileSpazio, String file, SpazioQueueSender sender) throws ComunicazioneSpazioException{
	    _logger.debug("Scrittura dei byte nel file di tipo testuale per spazio in corso..");
	    try {
		fileSpazio.setIntProperty(SpazioProperty.EOR_EOF_TYPE, SpazioEorEofType.EOR_CRLF);
		fileSpazio.clearBody(sender);
		BufferedReader br=new BufferedReader(new StringReader(file));
		String line=null;
		while((line=br.readLine())!=null){
		    fileSpazio.writeLine(line);
		}
	   	fileSpazio.flush();
	   	br.close();
	   	
	    } catch (JMSException e) {
	    	e.printStackTrace();
		_logger.error("Errore nella scrittura del file testuale "+e.getMessage());
		throw new ComunicazioneSpazioException("Errore nella scrittura del file testuale",e);
	    } catch (IOException e) {
		e.printStackTrace();
		_logger.error("Errore nella scrittura del file binario "+e.getMessage());
		throw new ComunicazioneSpazioException("Errore nella scrittura del file testuale",e);
	    }	
	    _logger.debug("File scritto correttamente.");
	    
	}
	
	/**
	 * Scrive il file di binario, passato come parametro, nel file per spazio dopo aver effettuato il clear del body
	 * @param fileSpazio il file per spazio in cui va scritto il file binario
	 * @param file il file che si vuole inviare
	 * @param sender il sender che si occuperò di inviare il file
	 * @throws ComunicazioneSpazioException
	 */
	public static void writeInFile(SpazioBytesFile fileSpazio, byte[] fileBinario, SpazioQueueSender sender) throws ComunicazioneSpazioException{
	    _logger.debug("Scrittura dei byte nel file di tipo binario per spazio in corso..");
	    try {
		fileSpazio.clearBody(sender);
		fileSpazio.writeBytes(fileBinario);
	   	fileSpazio.flush();	
	   	
	    } catch (JMSException e) {
	    	e.printStackTrace();
		_logger.error("Errore nella scrittura del file binario "+e.getMessage());
		throw new ComunicazioneSpazioException("Errore nella scrittura del file binario",e);
	    } 
	    _logger.debug("File scritto correttamente.");
	    
	}
	
	/**
	 * Legge il file di record testuali e ne restituisce un array binario
	 * @param fileSpazio
	 * @return un array binario contentente in forma binaria i record testuali
	 * @throws ComunicazioneSpazioException in caso si verificano errori durante la lettura
	 */
	public static byte[] readFromFile(SpazioRecordFile fileSpazio) throws ComunicazioneSpazioException{
	    _logger.debug("Lettura dei byte nel file di tipo text record per spazio in corso..");
	    byte[] risposta;
	    try {
		  fileSpazio.reset();
		  risposta=fileSpazio.readRecord();
	    } catch (JMSException e) {
	    	e.printStackTrace();
		_logger.error("Errore nella lettura del file "+e.getMessage());
		throw new ComunicazioneSpazioException("Errore nella lettura del file ",e);
	    } 
	    _logger.debug("File letto correttamente.");
	    
	    return risposta;
	}
	
	/**
	 * Si occupa di inviare correttamente, gestendo anche gli eventuali errori,il file tramite il sender 
	 * entrambi passati come parametri.
	 *  
	 * @param sender il sender che verrà usato per inviare il file
	 * @param fileSpazio il file da inviare
	 * @throws ComunicazioneSpazioException in caso vi presentano problemi a inviare file a spazio
	 */
	public static void sendFile(SpazioQueueSender sender, SpazioFile fileSpazio) throws ComunicazioneSpazioException {
		_logger.debug("Invio del file a spazio jms in corso..." );
		try {
			sender.send(fileSpazio);
		} catch (JMSException e) {
		    e.printStackTrace();
			_logger.error("Errore nell'invio del file a spazio jms: "+e.getMessage());
			throw new ComunicazioneSpazioException("Errore nell'invio del file a spazio jms",e);
		}
		_logger.debug("File inviato correttamente.");
	}
	
	/**
	 * Metodo che setta correttamente la descrizione all'interno del file da inviare a spazio
	 * @param file il file da inviare a spazio
	 * @param descrizione la descrizione che si vuole inserire.
	 * @throws ComunicazioneSpazioException in caso si verificano problemi con l'inserimento della descrizione
	 */
	public static void setDescriptionOnFile(SpazioFile file, String descrizione) throws ComunicazioneSpazioException{
		_logger.debug("Inserimento della descrizione nel file di spazio in corso...");
		try{
			file.setStringProperty(SpazioProperty.DESCRIPTION,descrizione);
		} catch (JMSException e) {
		    e.printStackTrace();
			_logger.error("Errore nell'inserimento della descrizione nel file");
			throw new ComunicazioneSpazioException("Errore nel settare la descrizione nel file per spazio",e);
		}
		_logger.debug("Inserimento della descrizione nel file di spazio terminata con successo.");
	}
	
	/**
	 * Metodo che setta correttamente la userclass all'interno del file da inviare a spazio
	 * @param file il file da inviare a spazio
	 * @param userclass la classe da usare per fare il dispatch
	 * @throws ComunicazioneSpazioException in caso si verificano problemi con l'inserimento della userclass
	 */
	public static void setUserClassOnFile(SpazioFile file, String userclass) throws ComunicazioneSpazioException{
		_logger.debug("Inserimento della UserClass nel file di spazio in corso...");
		try{
			file.setStringProperty(SpazioProperty.U_CLASS,userclass);
		} catch (JMSException e) {
		    e.printStackTrace();
			_logger.error("Errore nell'inserimento della UserClass nel file di spazio ");
			throw new ComunicazioneSpazioException("Errore nel settare la userclass nel file per spazio",e);
		}
		_logger.debug("Inserimento della UserClass nel file di spazio terminata con successo.");
	}
	
	
	/**
	 * Metodo che setta correttamente la descrizione all'interno del file da inviare a spazio
	 * @param file il file da inviare a spazio
	 * @param descrizione la descrizione che si vuole inserire.
	 * @throws ComunicazioneSpazioException in caso si verificano problemi con l'inserimento della descrizione
	 */
	public static void setCorrelationIdOnFile(SpazioFile file, String CorrelationID) throws ComunicazioneSpazioException{
		_logger.debug("Inserimento del CorrelationId nel file di spazio in corso...");
		try{
			file.setJMSCorrelationID(CorrelationID);
		} catch (JMSException e) {
		    e.printStackTrace();
			_logger.error("Errore nell'inserimento del CorrelationId nel file di spazio");
			throw new ComunicazioneSpazioException("Errore nel settare il CorrelationId nel file per spazio",e);
		}
		_logger.debug("Inserimento del CorrelationId nel file di spazio terminato con successo.");
	}
	
	/**
	 * Metodo che setta correttamente il sender all'interno del file da inviare a spazio
	 * @param file il file da inviare a spazio
	 * @param sender il sender  che si vuole inserire.
	 * @throws ComunicazioneSpazioException in caso si verificano problemi con l'inserimento della descrizione
	 */
	public static void setSender(SpazioFile file, String sender) throws ComunicazioneSpazioException{
		_logger.debug("Inserimento del Sender nel file di spazio in corso...");
		try{
			file.setStringProperty(SpazioProperty.SENDER,sender);
		} catch (JMSException e) {
		    e.printStackTrace();
			_logger.error("Errore nell'inserimento del sender nel file di spazio");
			throw new ComunicazioneSpazioException("Errore nel settare il sender nel file per spazio",e);
		}
		_logger.debug("Inserimento del sender nel file di spazio terminato con successo.");
	}
	
	
	
	
}
