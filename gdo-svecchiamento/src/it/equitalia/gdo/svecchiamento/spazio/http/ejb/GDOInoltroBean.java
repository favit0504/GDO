package it.equitalia.gdo.svecchiamento.spazio.http.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.svecchiamento.spazio.http.handler.GDOInoltroHandler;
import it.equitalia.gdo.svecchiamento.spazio.util.ConfigurazioneSpazio;
import it.equitalia.gdo.svecchiamento.spazio.util.GdoInoltroException;

import org.apache.log4j.Logger;

/**
 * Bean implementation class for Enterprise Bean: GDOInoltro
 */
public class GDOInoltroBean implements javax.ejb.SessionBean {
	
	private static Logger logger = Logger.getLogger(GDOInoltroBean.class);

	static final long serialVersionUID = 3206093459760846163L;
	private javax.ejb.SessionContext mySessionCtx;

	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}

	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws BusinessException {
		ConfigurazioneSpazio.getInstance();
	}

	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}

	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}

	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}

	public int inoltroFile(byte[] contentTxt, String nomeFile) throws GdoInoltroException {

		logger.debug("SERVICE.inoltroFile");

		logger.debug("input.param=" + contentTxt);

		long begTime = System.currentTimeMillis();
		int risultato;

		try {
			GDOInoltroHandler handler = new GDOInoltroHandler();
			risultato = handler.inoltra(contentTxt, nomeFile);

			long endTime = System.currentTimeMillis() - begTime;
			logger.info("SERVICE.inoltroFile\t" + endTime);
			
			return risultato;
			
		} catch (GdoInoltroException gdo) {
			logger.error(gdo.toString().replaceAll("\t", " "));
			throw new GdoInoltroException(gdo.getCodice(), gdo.getMessage());
		} catch (Throwable e) {
			logger.error(e.toString().replaceAll("\t", " "));
			throw new GdoInoltroException(""+e, e.getCause());
		}
	}

	
}
