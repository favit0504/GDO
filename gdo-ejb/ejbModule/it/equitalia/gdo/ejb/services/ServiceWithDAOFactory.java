package it.equitalia.gdo.ejb.services;

import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.AbstractDAOFactory.DAOFactoryType;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class ServiceWithDAOFactory<T> {

	protected EntityManagerFactory emfGDO = null;
	protected EntityManagerFactory emfGEU = null;
	protected EntityManagerFactory emfDB2 = null;

	protected EntityManager entityManager = null;
	protected EntityManager entityManagerGEU = null;
	protected EntityManager entityManagerDB2 = null;

	protected DAOFactoryType factoryType = DAOFactoryType.ORACLE_GDO;

	protected DAOFactoryType getFactoryType() {
		return factoryType;
	}

	protected Map<Object, Object> props = null;

	protected AbstractDAOFactory getFactory() {
		EntityManager em = getEntityManager();
		AbstractDAOFactory df = AbstractDAOFactory.getDAOFactory(
				getFactoryType(), em);
		return df;
	}

	protected EntityManager getEntityManager() {
		switch (getFactoryType()) {

		case ORACLE_GDO:
			if (emfGDO == null) {
				props = new HashMap<Object, Object>();
				props.put("javax.persistence.jtaDataSource", GDOConfig.getInstance().getProperty(GDOConfig.GDO_ORACLE));
				emfGDO = Persistence.createEntityManagerFactory("gdo-unit", props);
			}
			entityManager = emfGDO.createEntityManager();

			return entityManager;

		case ORACLE_GEU:
			if (emfGEU == null) {
				props = new HashMap<Object, Object>();
				props.put("javax.persistence.jtaDataSource", GDOConfig.getInstance().getProperty(GDOConfig.GDO_ORACLE));
				emfGEU = Persistence.createEntityManagerFactory("geu-unit", props);
			}
			entityManagerGEU = emfGEU.createEntityManager();

			return entityManagerGEU;

		case DB2:
			if (emfDB2 == null) {
				props = new HashMap<Object, Object>();
				props.put("javax.persistence.jtaDataSource", GDOConfig.getInstance().getProperty(GDOConfig.GDO_DB2));
				emfDB2 = Persistence.createEntityManagerFactory("gdo-db2-unit", props);
			}
			entityManagerDB2 = emfDB2.createEntityManager();

			return entityManagerDB2;

		default:
			break;
		}
		throw new IllegalArgumentException("Invalid DAO Factory Type");
	}

	protected T getDAO() {
		AbstractDAOFactory df = getFactory();
		return getSpecificDAO(df);

	}

	protected abstract T getSpecificDAO(AbstractDAOFactory adf);
}
