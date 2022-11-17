package it.equitalia.gdo.webservices.client.util;

import it.equitalia.gdo.webservices.client.Funzione;
//import it.equitalia.gdo.webservices.client.Ruolo;
//import it.equitalia.gdo.webservices.client.UserProfile;

import java.util.List;

//I metodi commentati sono funzionanti ma al momento non necessari
public interface WSGPUWrapper {

	public List<Funzione> fetchListaFunzioni(String chiaveUtente);

	public void setEndpoint(String endpoint);
	
//	public Ruolo fetchRuolo(String chiaveUtente);
//
//	public UserProfile fetchUserProfile(String chiaveUtente);
//	
//	public boolean isAutorizzatoGestioneNews(String utente);
//
//	public boolean isAutorizzatoGestioneDocumenti(String utente);
//
//	public boolean isAutorizzatoGestioneSezioni(String utente);
	
}
