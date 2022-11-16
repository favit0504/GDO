package it.equitalia.gdo.svecchiamento.spazio.util;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class GdoInoltroException extends GdoException {

	private static final long serialVersionUID = -1411427873166889768L;
	
	
	public GdoInoltroException(String codice) {
		super(codice);
	}

	public GdoInoltroException(String codice, String messaggio) {
		super(codice, messaggio);
	}

	public GdoInoltroException(String codice, Throwable eccezione) {
		super(codice, eccezione);
	}
}
