package it.equitalia.gdo.dao.exceptions;

import it.equitalia.gdo.commons.exception.SystemException;

public class DataAccessException extends SystemException {

	
	private static final long serialVersionUID = 1L;

	public DataAccessException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
	}

}
