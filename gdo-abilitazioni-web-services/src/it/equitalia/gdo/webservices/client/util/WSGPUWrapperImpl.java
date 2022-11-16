package it.equitalia.gdo.webservices.client.util;

import java.util.List;

import it.equitalia.gdo.commons.exception.SystemException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.webservices.client.GeuGetUserProfileDelegate;
import it.equitalia.gdo.webservices.client.GeuGetUserProfileService;
//import it.equitalia.gdo.webservices.client.Ruolo;
import it.equitalia.gdo.webservices.client.Funzione;
import it.equitalia.gdo.webservices.client.UserProfile;
import it.equitalia.gdo.webservices.client.UserProfileRequest;

import javax.xml.ws.BindingProvider;

//I metodi commentati sono funzionanti ma al momento non necessari
public class WSGPUWrapperImpl implements WSGPUWrapper {

	/* 
	 * endpoint serve a permettermi di forzare un endpoint diverso da quello di default nei test
	 */
	private String endpoint = null;
	
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	/**
	 * Chiamata al servizio
	 * 
	 * @param chiaveUtente
	 * @return
	 */
	private UserProfile callWS(String chiaveUtente) throws SystemException {
		
		if (endpoint == null)
			endpoint = GDOConfig.getInstance().getProperty(WSGPUCostanti.WS_GPU_ENDPOINT_PROPERTY);
		
		UserProfile response = null;
		try {
			GeuGetUserProfileService service = new GeuGetUserProfileService();

			GeuGetUserProfileDelegate delegate = service
					.getGeuGetUserProfilePort();

			((BindingProvider) delegate)
					.getRequestContext()
					.put( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint );

			
			UserProfileRequest req = new UserProfileRequest();
			req.setCodiceProgetto(WSGPUCostanti.CODICE_PROGETTO);
			req.setTipoCliente(WSGPUCostanti.TIPO_CLIENTE);
			req.setIdUtente(chiaveUtente);

			response = delegate.getUserProfile(req);
			
		} catch (Exception e) {
			throw new SystemException("Si &egrave; verificato un errore in fase di comunicazione col servizio GPU", e);
		}

		return response;
	}

	public List<Funzione> fetchListaFunzioni(String chiaveUtente) {
		return callWS(chiaveUtente).getListaFunzioni();
	}
	
//	public UserProfile fetchUserProfile(String chiaveUtente) {
//		return callWS(chiaveUtente);
//	}

//	public Ruolo fetchRuolo(String chiaveUtente) {
//		return callWS(chiaveUtente).getRuolo();
//	}
//	
//	public boolean isAutorizzatoGestioneNews(String utente) {
//		
//		WSGPUWrapper ws = new WSGPUWrapperImpl();		
//		List<Funzione> funzioni = ws.fetchListaFunzioni(utente);
//		
//		boolean autorizzato = false;
//		
//		for (int i = 0; i < funzioni.size(); i++) {
//			String codice = funzioni.get(i).getCodice();
//			if (WSGPUCostanti.FUNZIONE_GESTIONE_NEWS.equals(codice) ||  WSGPUCostanti.FUNZIONE_GESTIONE_TUTTO.equals(codice) ) 
//				autorizzato = true;					
//		}
//		
//		return autorizzato;
//		
//	}
//
//	public boolean isAutorizzatoGestioneDocumenti(String utente) {
//		
//		WSGPUWrapper ws = new WSGPUWrapperImpl();		
//		List<Funzione> funzioni = ws.fetchListaFunzioni(utente);
//		
//		boolean autorizzato = false;
//		
//		for (int i = 0; i < funzioni.size(); i++) {
//			String codice = funzioni.get(i).getCodice();
//			if (WSGPUCostanti.FUNZIONE_GESTIONE_DOCUMENTI.equals(codice) ||  WSGPUCostanti.FUNZIONE_GESTIONE_TUTTO.equals(codice) ) 
//				autorizzato = true;					
//		}
//		
//		return autorizzato;
//		
//	}
//
//	public boolean isAutorizzatoGestioneSezioni(String utente) {
//		
//		WSGPUWrapper ws = new WSGPUWrapperImpl();		
//		List<Funzione> funzioni = ws.fetchListaFunzioni(utente);
//		
//		boolean autorizzato = false;
//		
//		for (int i = 0; i < funzioni.size(); i++) {
//			String codice = funzioni.get(i).getCodice();
//			if (WSGPUCostanti.FUNZIONE_GESTIONE_TUTTO.equals(codice) ) 
//				autorizzato = true;					
//		}
//		
//		return autorizzato;
//	}
}
