package it.equitalia.gdo.ejb.services;


import it.equitalia.gdo.commons.ejb.impl.AllegatoServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.AllegatoServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.AllegatoBean;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.converters.ModelToBean;
import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Allegato;
import it.equitalia.gdo.dao.services.interfaces.AllegatoDAOInterface;
import it.equitalia.gdo.ejb.aspects.ProfilingInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;


@Stateless(name="ejb/AllegatoService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AllegatoServiceBean extends ServiceWithDAOFactory<AllegatoDAOInterface> implements AllegatoServiceLocal, AllegatoServiceRemote {
	

	private static final Logger logger = Logger.getLogger(AllegatoServiceBean.class);
	@Resource
	private SessionContext ctx;
	

	
	/**
	 * Recupera allegato con tanto di contenuto del file
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Interceptors({ProfilingInterceptor.class})
		public AllegatoBean recuperaAllegato(Integer idAllegato)
			throws BusinessException {
	
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			if(idAllegato != null){
				
				params.put(AllegatoDAOInterface.PARAM_ID_ALLEGATO, idAllegato);					
			}
			
			AllegatoDAOInterface dao = getDAO();
			dao.setEntityManager(this.entityManager);
			List<Allegato> results = dao.findByParameter(params);
			if (results != null && results.size() != 1) {
				logger.warn("Allegato id=[" + idAllegato +"] non trovato");
				throw new DataAccessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_ALLEGATO_NON_TROVATO) );
			}
			
			AllegatoBean beanRestituitoDalServizio = ModelToBean.execute(results.get(0), true);
			
			if (beanRestituitoDalServizio.getNomeFile() == null || beanRestituitoDalServizio.getContenutoFile() == null)
			{
				logger.error("Errore nel recupero dell'allegato id=[" + idAllegato +"], nome o contenuto mancanti");				
				throw new DataAccessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_ALLEGATO_RECORD_NON_VALIDO)); 
			}
			return beanRestituitoDalServizio;
			
				
		} catch (DataAccessException dae) {
		    ctx.setRollbackOnly();
		    logger.error(dae.getMessage(), dae);
		    throw new BusinessException(dae.getMessage());
		}
		catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException("error.service.recupera.news.generic", t);
		}
	}

	@Override
	protected AllegatoDAOInterface getSpecificDAO(AbstractDAOFactory adf) {
		return adf.getAllegatoDAO();
	}






  
}