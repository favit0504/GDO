package it.equitalia.gdo.svecchiamento.spazio.jms;

import it.equitalia.gdo.svecchiamento.spazio.util.ConfigurazioneSpazio;
import it.equitalia.gdo.svecchiamento.spazio.util.ConfigurazioneSpazio.Property;

import javax.jms.Queue;

import org.apache.log4j.Logger;

import com.primeur.spazio.jms.SPQueueConnectionFactory;

public class SpazioJMSHelperGmeExtention extends SpazioJMSHelper {
	

	    /**
	     * Permette di idetificare il tipo di invio per cui si richiede helper 
	     *
	     */
	    public enum TIPO_OPERAZIONE_HELPER{GDO_FLUSSI};
		
	    private static Logger _logger = Logger.getLogger(SpazioJMSHelperGmeExtention.class);
	    	/*
		 * DA SPOSTARE SU UNA CLASSE CHE EXSTENDE APPOSITA PER GEF
		 * 
		 */
		/**
		 * Crea l'opportuno Factory per la creazione del QueueConnection a seconda del tipo di operazione.
		 * @param tipo il tipo di operazione per cui deve essere costruito il factory
		 * @return il factory del QueueConnection collegato al tipo di connessione  
		 * @throws ComunicazioneSpazioException se vi sono problemi a creare il factory
		 */	
		public static SPQueueConnectionFactory createQueueConnectionFactory(TIPO_OPERAZIONE_HELPER tipo) throws ComunicazioneSpazioException {
			_logger.debug("Recupero del queueConnectionFactory in corso...");
			SPQueueConnectionFactory conFact=null;
			switch (tipo) {
				case GDO_FLUSSI:
    					conFact= createQueueConnectionFactory(ConfigurazioneSpazio.getInstance().getProperty(Property.GDO_FLUSSI_QUEUE_MANAGER));
    					_logger.debug("Recupero factory per l'invio ad host effettuato correttamente");
                		break;
					
				default:
					_logger.error("Errore è stato richiesto il factory per l'operazione di tipo:"+tipo);
					throw new ComunicazioneSpazioException("Tipo '"+tipo+"' non correttamente gestito."); 
			}
			
			return conFact;
		}
		
		/**
		 * Crea la queue per spazio relativa all'operazione per cui verrà utilizzata.
		 * @param tipo il tipo di operazione in cui la queue verrà usata
		 * @param parametro serve per quelle operazioni che necessitano per la corretta creazione del factory di maggiori informazioni.
		 * @return 
		 * @throws ComunicazioneSpazioException in caso si presentano errori con la creazione della queue 
		 */
		public static Queue createQueue(TIPO_OPERAZIONE_HELPER tipo) throws ComunicazioneSpazioException{
			_logger.debug("Recupero della queue in corso...");
			Queue coda=null;
			switch (tipo) {
				case GDO_FLUSSI:
					 coda= createQueue(ConfigurazioneSpazio.getInstance().getProperty(Property.GDO_FLUSSI_QUEUE));
					_logger.debug("Queue recuperata correttamente per invio a db di test");
					break;
		
				default:
					_logger.error("Richiesto un tipo ("+tipo+") non valido");
					throw new ComunicazioneSpazioException("Tipo '"+tipo+"' non correttamente gestito."); 
			}
			return coda;
		}
		
	
		
	}
