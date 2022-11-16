package it.equitalia.gdo.svecchiamento.spazio.util;

import it.equitalia.gdo.commons.utils.StringUtils;


public class GdoException extends Exception {

	private static final long serialVersionUID = 2538873922663032535L;

	public static final String TIPO_INOLTRATORE_NON_VALIDO = "TIPO_INOLTRATORE_NON_VALIDO";
	
	public static final String CODICE_ERRORE_KO = "-1";
	public static final String MESSAGGIO_ERRORE_KO = "Caricamento file non avvenuto.";
	

	private String codice;
	private String messaggio = "";
	private String[] params;

	public GdoException(String codice) {
		super();
		this.codice = codice;
	}

	/**
	 * 
	 * @param codice
	 * @param params
	 */
	public GdoException(String codice, String[] params) {
		this(codice, params, null);
	}

	public GdoException(String codice, String[] params, Throwable eccezione) {
		this(codice, eccezione);
		this.params = params;
	}

	/**
	 * @roseuid 41EE326602D5
	 */
	public GdoException(String codice, String messaggio) {
		super(messaggio);
		this.codice = codice;
	}

	public GdoException(String codice, Throwable eccezione) {
		super(eccezione);
		this.codice = codice;
	}

	/**
	 * @return java.lang.String
	 * @roseuid 41C8560F0057
	 */
	public String getCodice() {
		return codice;
	}

	@Override
	public String getMessage() {
		if (StringUtils.isBlank(messaggio)) {
			messaggio = super.getMessage();
		}

		return messaggio;
	}

	public String[] getParams() {
		return params;
	}

	/**
	 * @param codice
	 * @roseuid 41C8561D0210
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setMessage(String message) {
		messaggio = message;
	}

}
