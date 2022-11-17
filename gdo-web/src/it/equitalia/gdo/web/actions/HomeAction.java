package it.equitalia.gdo.web.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class HomeAction extends AbstractBaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(HomeAction.class);

	public String execute() throws Exception {

		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();


		logger.info("test");
		
		addActionErrorGDO("CIAO");
		addActionMessage("CIAO___");
		
		
		return SUCCESS;
	}

}
