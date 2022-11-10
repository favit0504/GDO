package it.equitalia.gdo.web.security;

import it.equitalia.gdo.webservices.client.Funzione;
import it.equitalia.gdo.webservices.client.util.WSGPUCostanti;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class SecurityContext implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userId;
	private List<Funzione> listaFunzioni;

	boolean abilitatoGestioneNews = false;
	boolean abilitatoGestioneDocumenti = false;
	boolean abilitatoGestioneSezioni = false;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Funzione> getListaFunzioni() {
		return listaFunzioni;
	}
	public void setListaFunzioni(List<Funzione> listaFunzioni) {
		this.listaFunzioni = listaFunzioni;
	}
	
	public boolean isAbilitatoGestioneNews() {
		return abilitatoGestioneNews;
	}
	public boolean isAbilitatoGestioneDocumenti() {
		return abilitatoGestioneDocumenti;
	}
	public boolean isAbilitatoGestioneSezioni() {
		return abilitatoGestioneSezioni;
	}
	
	/**
	 * Partendo dalla lista delle funzioni chiamata dal web service
	 * riempo il bean con le autorizzazioni presenti
	 */
	public void elaboraAutorizzazioni() {
		
		for (int i = 0; i < listaFunzioni.size(); i++) {
			String codice = listaFunzioni.get(i).getCodice();
			if ( WSGPUCostanti.FUNZIONE_GESTIONE_TUTTO.equals(codice)) { 
				abilitatoGestioneDocumenti = abilitatoGestioneNews = abilitatoGestioneSezioni = true;
			}
			else if (WSGPUCostanti.FUNZIONE_GESTIONE_DOCUMENTI.equals(codice) ) {
				abilitatoGestioneDocumenti = true;
			}
			else if (WSGPUCostanti.FUNZIONE_GESTIONE_NEWS.equals(codice) ) {
				abilitatoGestioneNews = true;
			}
						
		}		
		
	}

	
}
