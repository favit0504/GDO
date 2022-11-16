package it.equitalia.gdo.dao;

import it.equitalia.gdo.dao.exceptions.DataAccessException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

/**
 * 
 * @author Valerio Donnarumma
 *
 * 
 */
public interface GenericDAOInterface<T> {

	/**
	 * Memorizza le informazioni contenute nell'entità indicata in input.
	 * 
	 * @param entity entità da salvare
	 * 
	 * @return entità salvata 
	 */
	T insert(T t) throws DataAccessException;

	
    void delete(Serializable id);
    
    /**
	 * Elimina le informazioni presenti nella base dati informativa
	 * dell'entità indicata in input.
	 * 	
	 * @param entity
	 */
    void delete(T t);

    /**
	 * Cerca nella base dati informativa l'entità corrispondente 
	 * all'identificativo indicato in input.
	 * 
	 * @param id identificativo entità
	 * 
	 * @return l'entità corrispondente all'identificativo indicato in input.
	 */
    T find(Serializable id);

    /**
	 * Aggiorna le informazioni dell'entità indicata in input.
	 * 
	 * @param entity aggiornamenti entità 
	 * 
	 * @return entità aggiornata
	 */
    T update(T t);
    
    /**
	 * Cerca tutte le entità presenti nella base dati informativa.
	 * 
	 * @return l'elenco delle entità presenti nella base dati informativa
	 */
    List<T> findAll();
    
    /**
	 * Cerca le entità presenti nella base dati informativa che
	 * corrispondono ai criteri specifcati.
	 * 
	 * @return l'elenco delle entità che soddisfano le condizioni indicate.
	 */
    List<T> findByParameter(Map<String, Object> params);
    
    
    void setEntityManager(EntityManager entityManager);
    
}