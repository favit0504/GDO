package it.equitalia.gdo.commons.exception;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class ResourceLookupFailureException extends SystemException {

	private static final long serialVersionUID = 1L;

	public ResourceLookupFailureException() {
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ResourceLookupFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ResourceLookupFailureException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ResourceLookupFailureException(Throwable cause) {
		super(cause);
	}

}
