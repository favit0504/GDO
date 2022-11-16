package it.equitalia.gdo.dao.services.interfaces;

import java.util.List;

import it.equitalia.gdo.commons.valueobjects.UtenteBean;
import it.equitalia.gdo.dao.GenericDAOInterface;
import it.equitalia.gdo.dao.model.Ente;
import it.equitalia.gdo.dao.model.Regione;

public interface RegioneDAOInterface extends GenericDAOInterface<Regione> {
	List<Regione> getListaRegioniAttive();
	List<Regione> getListaRegioniPerEnte(Ente e);
	List<Regione> getListaRegioniPerAgente(UtenteBean a);

}
