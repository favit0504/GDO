package it.equitalia.gdo.ejb.services;

import it.equitalia.gdo.commons.ejb.impl.ProvinciaServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.ProvinciaServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.EnteBean;
import it.equitalia.gdo.commons.valueobjects.ProvinciaBean;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.AbstractDAOFactory.DAOFactoryType;
import it.equitalia.gdo.dao.converters.BeanToModel;
import it.equitalia.gdo.dao.converters.ModelToBean;
import it.equitalia.gdo.dao.db2.ProvinciaDB2DAO;
import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Ente;
import it.equitalia.gdo.dao.model.Provincia;
import it.equitalia.gdo.dao.services.interfaces.ProvinciaDAOInterface;
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

@Stateless(name="ejb/ProvinciaService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProvinciaServiceBean extends ServiceWithDAOFactory<ProvinciaDAOInterface> implements ProvinciaServiceLocal, ProvinciaServiceRemote {

	private static final Logger logger = Logger.getLogger(ProvinciaServiceBean.class);
	@Resource
	private SessionContext ctx;

	@Override
	protected ProvinciaDAOInterface getSpecificDAO(AbstractDAOFactory adf)  {

		return adf.getProvinciaDAO();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<ProvinciaBean> recuperaProvinceAttive() throws BusinessException {

		try{
			ProvinciaDAOInterface db2DAO = new ProvinciaDB2DAO();
			factoryType = DAOFactoryType.DB2;
			db2DAO.setEntityManager(getEntityManager());
			
			List<Provincia> province = db2DAO.getListaProvinceAttive();
			List<ProvinciaBean> bean = new ArrayList<ProvinciaBean>();
			popolaListaBean(province, bean);
			return bean;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_PROVINCIA), t);
		}

	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<ProvinciaBean> recuperaProvinceAttivePerEnte(EnteBean enteBean) throws BusinessException {

		try{
			Ente ente = BeanToModel.execute(enteBean);
			ProvinciaDAOInterface db2DAO = new ProvinciaDB2DAO();
			factoryType = DAOFactoryType.DB2;
			db2DAO.setEntityManager(getEntityManager());
			
			List<Provincia> province = db2DAO.getListaProvincePerEnte(ente);
			List<ProvinciaBean> bean = new ArrayList<ProvinciaBean>();
			popolaListaBean(province, bean);

			return bean;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_PROVINCIA), t);
		}
	}



	private void popolaListaBean(List<Provincia> province, List<ProvinciaBean> bean) {
		if(province.size()>0)
			for (Provincia r: province)
				bean.add(ModelToBean.execute(r));
	}



}


