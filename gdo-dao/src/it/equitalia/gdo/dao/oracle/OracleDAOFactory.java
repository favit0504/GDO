package it.equitalia.gdo.dao.oracle;

import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.services.interfaces.AllegatoDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.AmbitoDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.DocumentoDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.TipologiaEnteDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.NewsDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.ProvinciaDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.RegioneDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.ServizioDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.SezioneDAOInterface;

import javax.persistence.EntityManager;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public final class OracleDAOFactory extends AbstractDAOFactory {
	private OracleDAOFactory(){}
	
	private static class Holder {
		private static final OracleDAOFactory INSTANCE = new OracleDAOFactory();
	}
	
	/**
	 * 
	 * @param entityManager
	 * @return
	 */
	public static final AbstractDAOFactory getInstance(EntityManager entityManager){
		Holder.INSTANCE.setEntityManager(entityManager);
		return Holder.INSTANCE;
	}

	@Override
	public AllegatoDAOInterface getAllegatoDAO() {
		return new AllegatoOracleDAO(em);
	}

	@Override
	public DocumentoDAOInterface getDocumentoDAO() {
		return new DocumentoOracleDAO(em);
	}	

	@Override
	public NewsDAOInterface getNewsDAO() {
		return new NewsOracleDAO(em);
	}

	@Override
	public SezioneDAOInterface getSezioneDAO() {
		return new SezioneOracleDAO(em);
	}

	@Override
	public RegioneDAOInterface getRegioneDAO() {
		return null;
	}
	
	@Override
	public ProvinciaDAOInterface getProvinciaDAO() {
		return null;
	}

	@Override
	public ServizioDAOInterface getServizioDAO() {
		return null;
	}

	@Override
	public TipologiaEnteDAOInterface getTipoEnteDAO() {
		return null;
	}

	@Override
	public AmbitoDAOInterface getAmbitoDAO() {
		return null;
	}

}	
