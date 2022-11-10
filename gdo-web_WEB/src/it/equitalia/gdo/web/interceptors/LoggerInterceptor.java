package it.equitalia.gdo.web.interceptors;

//import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoggerInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoggerInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		log(invocation);		
		return invocation.invoke();
	}

	//Gestione logica del logging
	private void log(ActionInvocation invocation) {
		StringBuffer message = new StringBuffer("/");
		
		/*
		 TODO: questa cosa del namespace serve per path complessi?
		 Verificare una volta costruite diverse pagine nell'applicazione
		 
		String namespace = invocation.getProxy().getNamespace();

		if ((namespace != null) && (namespace.trim().length() > 0)) {
			message.append(namespace).append("/");
		}*/

		message.append(invocation.getProxy().getActionName());
		message.append(" -> ");
		message.append(invocation.getAction().getClass().getSimpleName());
		message.append(":");
		message.append(invocation.getProxy().getMethod());

		//TODO: level info?
		logger.info(message.toString());

	}
}
