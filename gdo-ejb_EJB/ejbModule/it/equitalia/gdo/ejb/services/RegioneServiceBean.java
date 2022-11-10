package it.equitalia.gdo.ejb.services;

import it.equitalia.gdo.commons.ejb.impl.RegioneServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.RegioneServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.EnteBean;
import it.equitalia.gdo.commons.valueobjects.RegioneBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.AbstractDAOFactory.DAOFactoryType;
import it.equitalia.gdo.dao.converters.BeanToModel;
import it.equitalia.gdo.dao.converters.ModelToBean;
import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Ente;
import it.equitalia.gdo.dao.model.Regione;
import it.equitalia.gdo.dao.services.interfaces.RegioneDAOInterface;
import it.equitalia.gdo.ejb.aspects.ProfilingInterceptor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

@Stateless(name="ejb/RegioneService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RegioneServiceBean extends ServiceWithDAOFactory<RegioneDAOInterface> implements RegioneServiceLocal, RegioneServiceRemote {

	private static final Logger logger = Logger.getLogger(RegioneServiceBean.class);
	@Resource
	private SessionContext ctx;
	

	protected DAOFactoryType getFactoryType() {return DAOFactoryType.DB2;}
	@Override
	protected RegioneDAOInterface getSpecificDAO(AbstractDAOFactory adf)  {

		return adf.getRegioneDAO();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Interceptors({ProfilingInterceptor.class})
	public List<RegioneBean> recuperaRegioniAttive()  throws BusinessException {
		try{
	
			List<Regione> regioni= getDAO().getListaRegioniAttive();
			List<RegioneBean> bean = new ArrayList<RegioneBean>();
			popolaListaBean(regioni, bean);
			return bean;
			
		} catch (DataAccessException dae) {
		    ctx.setRollbackOnly();
		    logger.error(dae.getMessage(), dae);
		    throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_REGIONE), t);
		}
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Interceptors({ProfilingInterceptor.class})
	public List<RegioneBean> recuperaRegioniAttivePerEnte(EnteBean enteBean)
			throws BusinessException {
		try{
				Ente ente =BeanToModel.execute(enteBean);
				List<Regione> regioni= getDAO().getListaRegioniPerEnte(ente);
				List<RegioneBean> bean = new ArrayList<RegioneBean>();
				popolaListaBean(regioni, bean);
				return bean;
				
			} catch (DataAccessException dae) {
			    ctx.setRollbackOnly();
			    logger.error(dae.getMessage(), dae);
			    throw new BusinessException(dae.getMessage());
			} catch(Throwable t){
				logger.error(t, t);
				throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_REGIONE), t);
			}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Interceptors({ProfilingInterceptor.class})
	public List<RegioneBean> recuperaRegioniAttivePerAgente(UtenteBean utente) throws BusinessException {
		try{
			
			List<Regione> regioni= getDAO().getListaRegioniPerAgente(utente);
			List<RegioneBean> bean = new ArrayList<RegioneBean>();
			popolaListaBean(regioni, bean);
			return bean;
			
		} catch (DataAccessException dae) {
		    ctx.setRollbackOnly();
		    logger.error(dae.getMessage(), dae);
		    throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_REGIONE), t);
		}
	}
	
	
	private void popolaListaBean(List<Regione> regioni, List<RegioneBean> bean) {
		if(regioni.size()>0)
			for (Regione r: regioni)
				bean.add(ModelToBean.execute(r));
	}

}