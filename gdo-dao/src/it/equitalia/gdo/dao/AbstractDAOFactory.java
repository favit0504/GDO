package it.equitalia.gdo.dao;

import it.equitalia.gdo.dao.db2.DB2DAOFactory;
import it.equitalia.gdo.dao.oracle.OracleDAOFactory;
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
public abstract class AbstractDAOFactory {
	protected EntityManager em;
	
	protected void setEntityManager(EntityManager em ) {
		this.em = em;
	}
	
	public enum DAOFactoryType { 
		ORACLE_GDO ("ORACLE_GDO"), 
		ORACLE_GEU ("ORACLE_GEU"),
		DB2 ("DB2");

		private String value = null;

		/**
		 * @param value
		 */
		DAOFactoryType(String value) {
			this.value = value;
		}

		/**
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return this.value.toString();
		}

		/**
		 * @return
		 */
		public String getValue() {
			return this.value;
		}
	}
	
	
	/**
	 * Ritorna il DAO Factory corrispondente al tipo indicato in input.
	 * @param entityManager 
	 * 
	 * @return
	 */
	public static final AbstractDAOFactory getDAOFactory(DAOFactoryType whichFactory, EntityManager entityManager){
		if( whichFactory == null )
			throw new IllegalArgumentException("Invalid DAO Factory Type");
		
		switch (whichFactory) {
			case ORACLE_GDO: return OracleDAOFactory.getInstance(entityManager);
			case ORACLE_GEU: return OracleDAOFactory.getInstance(entityManager);
			case DB2: return DB2DAOFactory.getInstance(entityManager);
			default: throw new IllegalArgumentException("Invalid DAO Factory Type");
		}
	}
	

	/**
	 * Restituisce il componente responsabile della gestione degli allegati.
	 * 
	 */
	public abstract AllegatoDAOInterface getAllegatoDAO();
	
	/**
	 * Restituisce il componente responsabile della gestione dei documenti.
	 * 
	 */
	public abstract DocumentoDAOInterface getDocumentoDAO();
	
	/**
	 * Restituisce il componente responsabile della gestione delle news.
	 * 
	 */
	public abstract NewsDAOInterface getNewsDAO();
	
	/**
	 * Restituisce il componente responsabile della gestione delle sezioni.
	 * 
	 */
	public abstract SezioneDAOInterface getSezioneDAO();
	
	/**
	 * Restituisce il componente responsabile della gestione delle regioni.
	 * 
	 */
	public abstract RegioneDAOInterface getRegioneDAO();

	/**
	 * Restituisce il componente responsabile della gestione delle province.
	 * 
	 */
	public abstract ProvinciaDAOInterface getProvinciaDAO();
	
	/**
	 * Restituisce il componente responsabile della gestione dei servizi.
	 * 
	 */
	public abstract ServizioDAOInterface getServizioDAO();
	
	/**
	 * Restituisce il componente responsabile della gestione della tipologia enti.
	 * 
	 */
	public abstract TipologiaEnteDAOInterface getTipoEnteDAO();
	
	public abstract AmbitoDAOInterface getAmbitoDAO();
	
}
