package it.equitalia.gdo.ejb.services;


import it.equitalia.gdo.commons.ejb.impl.SezioneServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.SezioneServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.StringUtils;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.converters.BeanToModel;
import it.equitalia.gdo.dao.converters.ModelToBean;
import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Documento;
import it.equitalia.gdo.dao.model.Sezione;
import it.equitalia.gdo.dao.oracle.DocumentoOracleDAO;
import it.equitalia.gdo.dao.services.interfaces.DocumentoDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.SezioneDAOInterface;
import it.equitalia.gdo.ejb.aspects.ProfilingInterceptor;

import java.util.ArrayList;
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

/**
 * 
 * @author Valerio Donnarumma
 *
 */
@Stateless(name="ejb/SezioneService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SezioneServiceBean extends ServiceWithDAOFactory<SezioneDAOInterface> implements SezioneServiceLocal, SezioneServiceRemote {
	
	private static final Logger logger = Logger.getLogger(SezioneServiceBean.class);
	@Resource
	private SessionContext ctx;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public SezioneBean aggiornaSezione(SezioneBean sezioneBean) throws BusinessException {
		SezioneBean updated = null;
		try{
			List<Documento> listaDocumenti = null;
			if(sezioneBean.getDocumenti() != null && sezioneBean.getDocumenti().size() > 0){
				listaDocumenti = new ArrayList<Documento>();
				for(DocumentoBean d : sezioneBean.getDocumenti()){
					listaDocumenti.add(BeanToModel.execute(d));
				}
			}
			
			sezioneBean.setDocumenti(null);
			
			//trasformo bean in input in entita
			Sezione sezione = BeanToModel.execute(sezioneBean);
			
			//eseguo operazione update
			sezione = (Sezione)getDAO().update(sezione);
			
			if(listaDocumenti != null){
				DocumentoDAOInterface documentoDAO = new DocumentoOracleDAO(this.entityManager);
				for(Documento d : listaDocumenti){
					d.setSezione(sezione);
					documentoDAO.updateSenzaVersionare(d);
				}
			}
			
			//restituisco il bean senza i documenti associati
			updated = ModelToBean.execute(sezione, false);
			
			
		} catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
            ctx.setRollbackOnly();
            throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_UPDATE_SEZIONE), e);
        }
		
		return updated;
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public SezioneBean creaSezione(SezioneBean sezioneBean) throws BusinessException {
		SezioneBean saved = null;
		
		try{
			SezioneDAOInterface sezioneDao = getDAO();
			sezioneDao.setEntityManager(this.entityManager);
			Sezione sezioneDaInserire = BeanToModel.execute(sezioneBean);
			Sezione sezioneSalvata = sezioneDao.insert(sezioneDaInserire);
			saved = ModelToBean.execute(sezioneSalvata, false);

		} catch (DataAccessException dae){
			ctx.setRollbackOnly();
            logger.error(dae.getMessage(), dae);
			//throw new BusinessException(dae.getMessage());
            throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_INSERT_SEZIONE), dae);
		}
		return saved;
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Interceptors({ProfilingInterceptor.class})
	public List<SezioneBean> recuperaSezione(SezioneBean sezioneBean) throws BusinessException {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			if(sezioneBean != null){
				if(sezioneBean.getId() != null){
					params.put(SezioneDAOInterface.PARAM_ID_SEZIONE, sezioneBean.getId());	
				}
				if(sezioneBean.getCodice() != null){
					params.put(SezioneDAOInterface.PARAM_CODICE_SEZIONE, sezioneBean.getCodice());	
				}
				if(sezioneBean.getValida() != null){
					params.put(SezioneDAOInterface.PARAM_VALIDA, sezioneBean.getValida());	
				}
				if(sezioneBean.getTitolo() != null && !StringUtils.isBlank(sezioneBean.getTitolo())){
					params.put(SezioneDAOInterface.PARAM_TITOLO, sezioneBean.getTitolo());	
				}
				if(sezioneBean.getDescrizione() != null && !StringUtils.isBlank(sezioneBean.getDescrizione())){
					params.put(SezioneDAOInterface.PARAM_DESCRIZIONE, sezioneBean.getDescrizione());	
				}
				if(sezioneBean.getStato() != null){
					params.put(SezioneDAOInterface.PARAM_STATO, sezioneBean.getStato());	
				}
				if(sezioneBean.getOwner() != null && !StringUtils.isBlank(sezioneBean.getOwner())){
					params.put(SezioneDAOInterface.PARAM_OWNER, sezioneBean.getOwner());	
				}
				if(sezioneBean.getDataCreazione() != null){
					params.put(SezioneDAOInterface.PARAM_DATA_CREAZIONE, sezioneBean.getDataCreazione());	
				}
				if(sezioneBean.getOwnerModifica() != null && !StringUtils.isBlank(sezioneBean.getOwnerModifica())){
					params.put(SezioneDAOInterface.PARAM_OWNER_MODIFICA, sezioneBean.getOwnerModifica());	
				}
				if(sezioneBean.getDataModifica() != null){
					params.put(SezioneDAOInterface.PARAM_DATA_MODIFICA, sezioneBean.getDataModifica());	
				}

			
			}

			List<Sezione> sezioni = getDAO().findByParameter(params);
			List<SezioneBean> sezioniBeanList = new ArrayList<SezioneBean>();

			if(sezioni.size() > 0){
				for(Sezione s: sezioni)
					sezioniBeanList.add(ModelToBean.execute(s, false));	
			}

			return sezioniBeanList;

		} catch (DataAccessException dae) {
		    ctx.setRollbackOnly();
		    logger.error(dae.getMessage(), dae);
		    throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_SEZIONE), t);
		}
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Interceptors({ProfilingInterceptor.class})
	public SezioneBean recuperaSezioneById(Integer id) throws BusinessException {
		
		try{
			return ModelToBean.execute(getDAO().find(id),true);
			

		} catch (DataAccessException dae) {
		    ctx.setRollbackOnly();
		    logger.error(dae.getMessage(), dae);
		    throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_SEZIONE), t);
		}
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Interceptors({ProfilingInterceptor.class})
	public List<SezioneBean> recuperaStoricoSezioneByCodice(Integer codiceSezione) throws BusinessException {
		
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			if(codiceSezione != null) {
				params.put(SezioneDAOInterface.PARAM_CODICE_SEZIONE, codiceSezione);
			}
			SezioneDAOInterface sezioneDao = getDAO();
			
			List<Sezione> sezione = sezioneDao.findStoricoSezione(params);
			List<SezioneBean> sezioneBeanList = new ArrayList<SezioneBean>();
			
			if(sezione.size() > 0){
				for(Sezione s: sezione)
					sezioneBeanList.add(ModelToBean.execute(s, false));	
			}
			
			return sezioneBeanList;
	
		} catch (DataAccessException dae) {
		    ctx.setRollbackOnly();
		    logger.error(dae.getMessage(), dae);
		    throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_SEZIONE), t);
		}
		
	}

	@Override
	protected SezioneDAOInterface getSpecificDAO(AbstractDAOFactory adf) {
		return adf.getSezioneDAO();
	}
	
  
}