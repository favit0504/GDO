package it.equitalia.gdo.web.interceptors;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.exception.SystemException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.web.actions.AbstractBaseAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -3416159964166247585L;

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ExceptionInterceptor.class);

	public String intercept(ActionInvocation invocation) throws Exception {

		String result = "errore-generico";
		try {
			result = invocation.invoke();

		} catch (BusinessException e) { // eccezioni applicative
			logger.error(e.getMessage(), e);
			AbstractBaseAction action = (AbstractBaseAction) invocation.getAction();
			action.addActionErrorGDO(e.getMessage());
		} catch (SystemException e) { // eccezioni ws, ecc.
			logger.error(e.getMessage(), e);
			AbstractBaseAction action = (AbstractBaseAction) invocation.getAction();
			action.addActionErrorGDO(e.getMessage());
			result = "errore-generico-senza-menu";
		} catch (Exception e) { 
			//eccezioni non BusinessException e SystemException,
			//le consideriamo non gestite e mostriamo errore generico 
			logger.error("Eccezione non gestita:" + e.getMessage(), e);
			AbstractBaseAction action = (AbstractBaseAction) invocation.getAction();			
			action.addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_GENERICO));
		}

		return result;

	}
}