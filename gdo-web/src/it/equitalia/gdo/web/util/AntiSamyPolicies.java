package it.equitalia.gdo.web.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;


/**
 * Helper per utilizzo della libreria antisamy
 * [https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project]
 * 
 * Utilizzato per filtrare testo indesiderato immesso dagli utenti
 */
public class AntiSamyPolicies {
	
	private static Logger log = Logger.getLogger(AntiSamyPolicies.class);
	
	private static final AntiSamyPolicies INSTANCE = new AntiSamyPolicies();
	
	/**
	 * Costruttore privato
	 */
	private AntiSamyPolicies() {		
		policySoloTesto = leggiPolicy("/antisamy-plain-text.xml");
		policyTextarea = leggiPolicy("/antisamy-policy-textarea.xml");
		
	}	
	
	public static AntiSamyPolicies getInstance() {
		return INSTANCE;
	}
	
	
	
	/** INIZIO POLICIES **/
	
	//l'oggetto Policy per il metodo scan che sarà utilizzato è threadsafe:
	//https://lists.owasp.org/pipermail/owasp-antisamy/2009-October/000278.html
	//ATTENZIONE nel caso si utilizzi un metodo diverso da scan!
	
	//Policy che ammette solo testo e nient'altro
	private Policy policySoloTesto;		
	//Policy che ammette solo i tag previsti dalla toolbar incorporatanella textarea (es. testo della news)
	private Policy policyTextarea;
	
	/** FINE POLICIES **/
	
	public Policy getPolicySoloTesto() {
		return policySoloTesto;
	}

	public Policy getPolicyTextarea() {
		return policyTextarea;
	}

	/**
	 * Metodo inizializzazione policies
	 * @param policyFileLocation
	 * @return
	 */
	private static Policy leggiPolicy(String policyFileLocation) {
		
		InputStream inputstreamPolicy = null;
		try {
			
			inputstreamPolicy = AntiSamyPolicies.class.getResourceAsStream(policyFileLocation);
			return Policy.getInstance(inputstreamPolicy);
			
		} catch (PolicyException e) {
			log.error("Errore nella lettura della policy antisamy " + policyFileLocation + " policySoloTesto da file di property", e);
			//Lancio una eccezione bloccante per impedire il servizio venga deployato
			//senza antisamy funzionante
			throw new RuntimeException("Impossibile inizializzare correttamente la pagina",e);
		} 
		finally {
			try {
				if (inputstreamPolicy != null) 
					inputstreamPolicy.close();				
			}
			catch (IOException e) {
				log.error("Errore nella chiusura della policy antisamy da file di property", e);
			}
		}
	}
	
}
