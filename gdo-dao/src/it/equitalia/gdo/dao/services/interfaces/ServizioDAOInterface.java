package it.equitalia.gdo.dao.services.interfaces;

import it.equitalia.gdo.dao.GenericDAOInterface;
import it.equitalia.gdo.dao.model.Servizio;

import java.util.List;

public interface ServizioDAOInterface extends GenericDAOInterface<Servizio> {
	List<Servizio> getListaServiziEnte();
	List<Servizio> getListaServiziAgente();
	List<Servizio> getListaServiziAltriUtenti();
	List<String> getListaServiziAttiviUtenteEnteoAgente(String chiaveUtente);
	List<String> getListaServiziAttiviAltriUtenti(String chiaveUtente);
	List<Servizio> getListaServiziUtenteEsterno();
	List<String> getListaServiziAttiviUtenteEsterno(String usd);

}
