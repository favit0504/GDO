package it.equitalia.gdo.ejb.services;

import java.util.ArrayList;
import java.util.List;


import it.equitalia.gdo.commons.ejb.impl.AmbitoServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.AmbitoServiceRemote;

import it.equitalia.gdo.commons.exception.BusinessException;

import it.equitalia.gdo.commons.utils.GDOMessaggi;

import it.equitalia.gdo.commons.valueobjects.AmbitoBean;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.AbstractDAOFactory.DAOFactoryType;
import it.equitalia.gdo.dao.converters.ModelToBean;
import it.equitalia.gdo.dao.exceptions.DataAccessException;

import it.equitalia.gdo.dao.model.Ambito;


import it.equitalia.gdo.dao.services.interfaces.AmbitoDAOInterface;

import it.equitalia.gdo.ejb.aspects.ProfilingInterceptor;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

@Stateless(name="ejb/AmbitoService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AmbitoServiceBean extends ServiceWithDAOFactory<AmbitoDAOInterface> implements AmbitoServiceLocal,AmbitoServiceRemote {

	private static final Logger logger = Logger.getLogger(AmbitoServiceBean.class);
	@Resource
	private SessionContext ctx;
	

	protected DAOFactoryType getFactoryType() {return DAOFactoryType.DB2;}
	@Override
	protected AmbitoDAOInterface getSpecificDAO(AbstractDAOFactory adf)  {

		return adf.getAmbitoDAO();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Interceptors({ProfilingInterceptor.class})
	public List<AmbitoBean> recuperaAmbiti()  throws BusinessException {
		try{
	
			List<Ambito> ambiti= getDAO().getListaAmbitiAttivi();
			List<AmbitoBean> beanAmbiti = new ArrayList<AmbitoBean>();
			popolaListaBean(ambiti, beanAmbiti);
			return beanAmbiti;
			
		} catch (DataAccessException dae) {
		    ctx.setRollbackOnly();
		    logger.error(dae.getMessage(), dae);
		    throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_AMBITO), t);
		}
		
	}
	private void popolaListaBean(List<Ambito> ambiti, List<AmbitoBean> ambitiBean) {
		if(ambiti.size()>0)
			for (Ambito r: ambiti)
				ambitiBean.add(ModelToBean.execute(r));
	}
	
	
	
	
	
}