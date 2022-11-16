package it.equitalia.gdo.dao.db2;

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

public class DB2DAOFactory extends AbstractDAOFactory {
	private DB2DAOFactory(){}
	
	private static class Holder {
		private static final DB2DAOFactory INSTANCE = new DB2DAOFactory();
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
		//sta su Oracle, non DB2
		return null;
	}

	@Override
	public DocumentoDAOInterface getDocumentoDAO() {
		//sta su Oracle, non DB2
		return null;
	}

	@Override
	public NewsDAOInterface getNewsDAO() {
		//sta su Oracle, non DB2
		return null;
	}

	@Override
	public SezioneDAOInterface getSezioneDAO() {
		//sta su Oracle, non DB2
		return null;
	}

	@Override
	public RegioneDAOInterface getRegioneDAO() {

		RegioneDB2DAO dao= new RegioneDB2DAO();
		dao.setEntityManager(em);
		return dao;
	}

	@Override
	public ProvinciaDAOInterface getProvinciaDAO() {

		ProvinciaDB2DAO dao= new ProvinciaDB2DAO();
		dao.setEntityManager(em);
		return dao;
	}

	@Override
	public ServizioDAOInterface getServizioDAO() {
		ServizioDB2DAO dao = new ServizioDB2DAO();
		dao.setEntityManager(em);
		return dao;
	}
	
	@Override
	public TipologiaEnteDAOInterface getTipoEnteDAO() {
		TipoEnteDB2DAO dao = new TipoEnteDB2DAO();
		dao.setEntityManager(em);
		return dao;
	}

	@Override
	public AmbitoDAOInterface getAmbitoDAO() {
		AmbitoDB2DAO dao = new AmbitoDB2DAO();
		dao.setEntityManager(em);
		return dao;
	}
}
