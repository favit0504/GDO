package it.equitalia.gdo.web.util;

import it.equitalia.gdo.web.util.GDOCostantiWeb.OPZIONI_STATO;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class PopolaCampiSezione {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(PopolaCampiSezione.class);
	
	SessionMap<String, Object> sessionMap;

	private static String OPZIONI_STATO_KEY = "opzioniStato";
	
	protected  Map<Integer,String> opzioniStato = new HashMap<Integer,String>();

	
	public PopolaCampiSezione(SessionMap<String, Object> s) {		
		sessionMap = s;
	}
	
	/**
	 * Chiamato dalla pagina del form per la ricerca delle sezioni
	 * @param sessionMap
	 * @throws Exception
	 */
	public void popolaStatoRicercaSezione() throws Exception {

		if (!sessionMap.containsKey(OPZIONI_STATO_KEY)){
			popolaStato();
		}
	}
	

	public void popolaStatoSezione() throws Exception {

		if(!sessionMap.containsKey(OPZIONI_STATO_KEY)){
			popolaStato();
		}
	}
	

	private void popolaStato() {
		for(OPZIONI_STATO o : OPZIONI_STATO.values())						
			opzioniStato.put(o.getValue(), o.getDescrizione());

		sessionMap.put(OPZIONI_STATO_KEY, opzioniStato);
	}
	

}
