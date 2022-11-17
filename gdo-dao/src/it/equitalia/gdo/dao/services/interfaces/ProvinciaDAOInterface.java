package it.equitalia.gdo.dao.services.interfaces;

import it.equitalia.gdo.dao.GenericDAOInterface;
import it.equitalia.gdo.dao.model.Ente;
import it.equitalia.gdo.dao.model.Provincia;

import java.util.List;

public interface ProvinciaDAOInterface extends GenericDAOInterface<Provincia> {
	List<Provincia> getListaProvinceAttive();
	List<Provincia> getListaProvincePerEnte(Ente e);
	List<Integer> getListaProvincePerAgente(String a);
}
