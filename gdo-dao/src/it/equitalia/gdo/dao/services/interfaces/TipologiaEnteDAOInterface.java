package it.equitalia.gdo.dao.services.interfaces;

import it.equitalia.gdo.dao.GenericDAOInterface;
import it.equitalia.gdo.dao.model.TipoEnte;

import java.util.List;

public interface TipologiaEnteDAOInterface extends GenericDAOInterface<TipoEnte> {
	List<TipoEnte> getListaTipoEnti();

}
