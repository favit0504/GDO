package it.equitalia.gdo.dao.services.interfaces;

import it.equitalia.gdo.dao.GenericDAOInterface;
import it.equitalia.gdo.dao.model.Ambito;


import java.util.List;

public interface AmbitoDAOInterface extends GenericDAOInterface<Ambito> {
	List<Ambito> getListaAmbitiAttivi();
	
}
