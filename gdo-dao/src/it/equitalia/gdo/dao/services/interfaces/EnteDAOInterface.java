package it.equitalia.gdo.dao.services.interfaces;

import it.equitalia.gdo.dao.GenericDAOInterface;
import it.equitalia.gdo.dao.model.Ente;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface EnteDAOInterface extends GenericDAOInterface<Ente> {
	
	Ente getTriplettaEnte(String chiaveUtente);
	String getTipologiaEnte(Ente ente);
	
}
