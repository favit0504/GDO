package it.equitalia.gdo.svecchiamento.spazio.jms;

import it.equitalia.gdo.commons.exception.BusinessException;



public class ComunicazioneSpazioException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ComunicazioneSpazioException() {
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ComunicazioneSpazioException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ComunicazioneSpazioException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ComunicazioneSpazioException(Throwable cause) {
		super(cause);
	}

}
