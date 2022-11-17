package it.equitalia.gdo.web.actions;

import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.web.security.SecurityContext;
import it.equitalia.gdo.web.util.AntiSamyPolicies;
import it.equitalia.gdo.web.util.GDOCostantiWeb;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractBaseAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = -3415927931061936921L;
	
	private SessionMap<String, Object> session;
	
	static {
		//Inizializzo singleton alla chiamata della prima Action.
		//cosi se qualcosa non va in fase di configurazione viene subito notato
		AntiSamyPolicies.getInstance();
	}
	
	protected static final String USER = "userId";

	public String getUtente() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession s = request.getSession();
			SecurityContext sc = (SecurityContext) s.getAttribute(GDOCostantiWeb.KEY_SECURITY_CONTEXT);
			String user = sc.getUserId();
			//Normalizziamo tutto maiuscolo
			return user.toUpperCase();
			//return (String)session.get(USER);
		}catch (Exception e) {
			return null;
		}
	}

	public void setSession(Map<String, Object> arg0) {
		session = (SessionMap<String, Object>)arg0;
		
	}

	public SessionMap<String, Object> getSession() {
		return session;
	}
	
	public void addActionErrorGDO(String message) {
		
		if (message == null)
			message = GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_GENERICO);
		addActionError(message);
	}
	
	public void addActionMessageGDO(String message) {
		
		if (message == null)
			message = "Messaggio";
		addActionMessage(message);
	}
	
	
}
