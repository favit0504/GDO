package it.equitalia.gdo.web.interceptors;


import it.equitalia.gdo.commons.exception.SystemException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.web.security.SecurityContext;
import it.equitalia.gdo.web.util.GDOCostantiWeb;
import it.equitalia.gdo.webservices.client.Funzione;
import it.equitalia.gdo.webservices.client.util.WSGPUWrapper;
import it.equitalia.gdo.webservices.client.util.WSGPUWrapperImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class AuthenticationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 2682634425154550229L;
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AuthenticationInterceptor.class);
	
	@Override
	public String intercept (ActionInvocation invocation) throws Exception {
	    final ActionContext context = invocation.getInvocationContext();
	    HttpServletRequest request = (HttpServletRequest) context.get(StrutsStatics.HTTP_REQUEST);
	    SecurityContext sc = null;
	    
	    try {	    
	    	sc = (SecurityContext) request.getSession().getAttribute(GDOCostantiWeb.KEY_SECURITY_CONTEXT);
	    }
	    catch(ClassCastException e) {
	    	//Si e` verificato una volta, non ben chiaro il perche`
	    	//nel dubbio, lo gestiamo
	    	log.error("Per qualche motivo la variabili di sessione SECURITY_CONTEXT e` del tipo sbagliato(" + request.getSession().getAttribute(GDOCostantiWeb.KEY_SECURITY_CONTEXT).getClass().getSimpleName() + ")" ,e);
	    	request.getSession().removeAttribute(GDOCostantiWeb.KEY_SECURITY_CONTEXT);
	    	throw new SystemException("Si &egrave; verificato un errore di sessione.",e);
	    }
	    
	    try{
            if(sc == null){
                String userId = null;
                
                //ambiente sviluppo locale
                if ( GDOConfig.getInstance().getProperty(GDOConfig.AMBIENTE_SVILUPPO).equals("1") ) {
                	userId = "X0DR040";
                }
                else { //ambiente test/collaudo/produzione
                	userId = request.getUserPrincipal().getName();
                }
                
                if(userId != null){
                	log.debug("Primo accesso per " + userId + ", popolo SecurityContext");
                	sc = new SecurityContext();
                    sc.setUserId(userId);
                    
                    //fix per le jsp
                    request.getSession().setAttribute("userId", userId);
                    
                	//chiamo WS
                    WSGPUWrapper wsGpu = new WSGPUWrapperImpl();
                    List<Funzione> funzioni = null;
                    
                    //blocco per evitare la lettura funzionalita` da GPU
                    
                    if (GDOConfig.getInstance().getProperty(GDOConfig.SALTA_GPU).equals("1")) {
                    	funzioni = new ArrayList<Funzione>();
                    	Funzione funzione = new Funzione();
                    	funzione.setCodice("FGDOV003");
                    	funzioni.add(funzione);
                    }
                    else {
                    	funzioni = wsGpu.fetchListaFunzioni(userId);
                    }
                    
                    sc.setListaFunzioni(funzioni);
                    sc.elaboraAutorizzazioni();
                    
                    log.debug("Security Context popolato correttamente");
                    
                    request.getSession().setAttribute(GDOCostantiWeb.KEY_SECURITY_CONTEXT, sc);
                    
                    return invocation.invoke();
                    
                } else {
                	return "login";
                	//throw new SecurityException("utente non autenticato");
                }
                
            } else {
            	return invocation.invoke();
            }
            
        //TODO: questo a che serve? Se il login fallisce mi reindirizza correttamente? - Eventualmente farei un log.error qualcosa e return "Login"
        } catch (SecurityException e) {
        	log.error("Errore generico in fase di autenticazione.", e);
            return "login";
        }
        
        
	}

}
