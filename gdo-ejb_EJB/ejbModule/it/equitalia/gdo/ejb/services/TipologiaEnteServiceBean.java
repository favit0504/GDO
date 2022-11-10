package it.equitalia.gdo.ejb.services;

import it.equitalia.gdo.commons.ejb.impl.TipoEnteServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.TipoEnteServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.TipoEnteBean;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.AbstractDAOFactory.DAOFactoryType;
import it.equitalia.gdo.dao.converters.ModelToBean;
import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.TipoEnte;
import it.equitalia.gdo.dao.services.interfaces.TipologiaEnteDAOInterface;
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

@Stateless(name="ejb/TipoEnteService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TipologiaEnteServiceBean extends ServiceWithDAOFactory<TipologiaEnteDAOInterface> implements TipoEnteServiceLocal, TipoEnteServiceRemote {

	private static final Logger logger = Logger.getLogger(TipologiaEnteServiceBean.class);
	@Resource
	private SessionContext ctx;


	protected DAOFactoryType getFactoryType() {return DAOFactoryType.DB2;}
	
	@Override
	protected TipologiaEnteDAOInterface getSpecificDAO(AbstractDAOFactory adf)  {

		return adf.getTipoEnteDAO();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<TipoEnteBean> recuperaTipoEnti() throws BusinessException {
		try{
			List<TipoEnte> tipoEnti = getDAO().getListaTipoEnti();
			List<TipoEnteBean> bean = new ArrayList<TipoEnteBean>();
			popolaListaBean(tipoEnti, bean);
			return bean;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_ENTE), t);
		}

	}
	private void popolaListaBean(List<TipoEnte> tipoEnti, List<TipoEnteBean> bean) {
		if(tipoEnti.size()>0)
			for (TipoEnte te: tipoEnti)
				bean.add(ModelToBean.execute(te));
	}
	
}


