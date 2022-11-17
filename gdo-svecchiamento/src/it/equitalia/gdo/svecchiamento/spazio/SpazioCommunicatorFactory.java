package it.equitalia.gdo.svecchiamento.spazio;

import it.equitalia.gdo.svecchiamento.spazio.jms.MySender;

import org.apache.log4j.Logger;

public abstract class SpazioCommunicatorFactory {

	private static final Logger _logger = Logger.getLogger(SpazioCommunicatorFactory.class);

	/**
	 * Definisce i ComunicatoreFactory di cui è possibile richiedere un'istanza. 
	 * 
	 *
	 */
	public enum TipoComunicatore { SPAZIOJMS, DEFAULT, TEST_SPAZIOJMS }

	/**
	 * Restituisce il ComunicatoreFactory della tipologia passata come parametro.
	 * 
	 * @param tipo il tipo di ComunicatoreFactory di cui si vuole avere un'istanza
	 * @return l'instanza del ComunicatoreFactory del tipo richiesto
	 */
	public static final SpazioCommunicatorFactory getFactory(TipoComunicatore tipo){
		_logger.debug("Creazione del comunicatore "+tipo+" in corso.");
		//se non si specifica il tipo ritorna un errore
		if( tipo == null ){
			_logger.error("Tipo di comunicatore nullo");
			throw new IllegalArgumentException("Tipo di Factory richiesta errato");
		}
		//altrimenti si fa una selezione sul tipo.
		switch (tipo) {
			case SPAZIOJMS:
				_logger.debug("Creazione del comunicatore di tipo spazio jms terminata.");
				return SpazioJmsCommunicatorFactory.getInstance();
				
			case TEST_SPAZIOJMS:
				_logger.debug("Creazione del comunicatore di TEST.");
				return SpazioJmsCommunicatorFactory.getInstance();
				
				//di default viene creato un comunicatore di tipo spazio        
			default:
				_logger.debug("Creazione del comunicatore di tipo spazio jms terminata.");
			return SpazioJmsCommunicatorFactory.getInstance();

		}

	}



	/**
	 * Ritorna una classe strategy per inviare al DB Test sul Mainframe
	 * @return una classe strategy che sa comunicare correttamente il tipo di richiesta al mainframe
	 */
	abstract public HostSender getSender();
	
	/**
	 * Sender utilizzato per i test.
	 * @return una classe che eredita HostSender che sa comunicare correttamente il tipo di richiesta al mainframe
	 */
	abstract public MySender getTestSender();
}
