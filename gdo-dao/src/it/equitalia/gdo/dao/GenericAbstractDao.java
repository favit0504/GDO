package it.equitalia.gdo.dao;



import it.equitalia.gdo.dao.model.generic.GenericType;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 * 
 * @author Valerio Donnarumma
 *
 *
 */
public abstract class GenericAbstractDao<T extends GenericType> implements GenericDAOInterface<T> {

	protected Class<T> type;
	private static final String NEXT_VAL_QUERY = "select %s.nextval from dual";
	/**
	 * Logger
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger( GenericAbstractDao.class );
	
	protected Properties properties;
	
	public EntityManager entityManager;
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public GenericAbstractDao() {
		 Type t = getClass().getGenericSuperclass();
	        ParameterizedType pt = (ParameterizedType) t;
	        type = (Class) pt.getActualTypeArguments()[0];
    }			
	
	public T insert(final T t) {
		impostaIdEntitaEFigli(t);
		entityManager.persist(t);	
		
		return t;
    }

	/**
	 * Sembra che l'implementazione delle transazioni gestite da Websphere 
	 * non supporti bene le sequence con relative annotazioni @SequenceGenerator ecc.
	 * Quindi serve questo metodo che non fa altro che chiamare manualmente le sequence
	 * su se stesso e sulle entita' associate.
	 * @param t
	 */
	protected void impostaIdEntitaEFigli(final T t) {
		setChildIds(t);
		int id = getIdValue(t);
		t.setId(id);
	}
/**
 * Imposta gli id di eventuali figli dell'entità, di default implementazione vuota
 * @param t
 */
    protected void setChildIds(T t) {
		//di def. non fa niente
		
	}

	public int getIdValue(GenericType t) {
    	String sequenceName = t.getSequenceName();
    	String sequenceQuery = createSequenceQuery(sequenceName);
    	BigDecimal idValue = (BigDecimal)entityManager.createNativeQuery(sequenceQuery).getSingleResult();
    	return idValue.intValue();				
	}

	


	//protected abstract String getSequenceName();

	public void delete(final Serializable id) {
    	delete(find(id));
    }
    
    public void delete(T t) {
    	t = entityManager.merge(t);
    	entityManager.remove(t);
    	entityManager.flush();
    }

    @SuppressWarnings("unchecked")
	public T find(final Serializable id) {
    	 Object o = entityManager.find(type, id);
         return (T)o;
    }

    public T update(final T t) {
    	return insert(t);
    }
    
    @SuppressWarnings("unchecked")
	public List<T> findAll() {
    	Query q = entityManager.createQuery( "select n from " + type.getName()+" n ");
    	return q.getResultList();
    }
    
    public List<T> findByParameter(Map<String, Object> params) {
		throw new RuntimeException("Metodo findByParameter non implementato in questo DAO");
	}
    

	/**
	 * Statico perche` viene usato anche esternamente dai DAO specifici
	 */
	public static String createSequenceQuery(String sequenceName) {
		return String.format(NEXT_VAL_QUERY, sequenceName);
	}
    
    
}
