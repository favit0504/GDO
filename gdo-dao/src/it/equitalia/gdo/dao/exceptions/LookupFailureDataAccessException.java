package it.equitalia.gdo.dao.exceptions;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class LookupFailureDataAccessException extends DataAccessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public LookupFailureDataAccessException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LookupFailureDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public LookupFailureDataAccessException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public LookupFailureDataAccessException(Throwable cause) {
		super(cause);
	}
}
