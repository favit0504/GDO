package it.equitalia.gdo.commons.exception;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class BusinessException extends ApplicationException {
	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}
}
