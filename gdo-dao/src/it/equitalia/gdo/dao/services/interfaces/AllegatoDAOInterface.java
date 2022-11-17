package it.equitalia.gdo.dao.services.interfaces;

import it.equitalia.gdo.dao.GenericDAOInterface;
import it.equitalia.gdo.dao.model.Allegato;

import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface AllegatoDAOInterface extends GenericDAOInterface<Allegato> {
	
	public static final String PARAM_ID_ALLEGATO = "ID_ALLEGATO";
	public static final String PARAM_TITOLO = "TITOLO";
	public static final String PARAM_NOME_FILE = "NOME_FILE";
	public static final String PARAM_BLOB = "FILE_BLOB";
	public static final String PARAM_ID_NEWS = "ID_NEWS";
	
	public int mantieniVecchioAllegato(int idVecchiaNews, int idNuovaNews);
	
	public List<Integer> findByIdNews(int idNews);
}
