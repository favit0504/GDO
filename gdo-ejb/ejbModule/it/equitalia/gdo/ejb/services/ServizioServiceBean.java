package it.equitalia.gdo.ejb.services;

import it.equitalia.gdo.commons.ejb.impl.ServizioServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.ServizioServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.ServizioBean;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.AbstractDAOFactory.DAOFactoryType;
import it.equitalia.gdo.dao.converters.ModelToBean;
import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Servizio;
import it.equitalia.gdo.dao.services.interfaces.ServizioDAOInterface;
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

@Stateless(name="ejb/ServizioService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ServizioServiceBean extends ServiceWithDAOFactory<ServizioDAOInterface> implements ServizioServiceLocal, ServizioServiceRemote {

	private static final Logger logger = Logger.getLogger(ServizioServiceBean.class);
	@Resource
	private SessionContext ctx;


	protected DAOFactoryType getFactoryType() {return DAOFactoryType.DB2;}
	
	@Override
	protected ServizioDAOInterface getSpecificDAO(AbstractDAOFactory adf)  {

		return adf.getServizioDAO();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<ServizioBean> recuperaServiziEnte() throws BusinessException {
		try{
			List<Servizio> servizi = getDAO().getListaServiziEnte();
			List<ServizioBean> bean = new ArrayList<ServizioBean>();
			popolaListaBean(servizi, bean);
			return bean;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_SERVIZIO_ENTE), t);
		}

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<ServizioBean> recuperaServiziAgente() throws BusinessException {
		try{
			List<Servizio> servizi = getDAO().getListaServiziAgente();
			List<ServizioBean> bean = new ArrayList<ServizioBean>();
			popolaListaBean(servizi, bean);
			return bean;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_SERVIZIO_AGENTE), t);
		}

	}
	
	private void popolaListaBean(List<Servizio> servizi, List<ServizioBean> bean) {
		if(servizi.size()>0)
			for (Servizio s: servizi)
				bean.add(ModelToBean.execute(s));
	}
	
}


