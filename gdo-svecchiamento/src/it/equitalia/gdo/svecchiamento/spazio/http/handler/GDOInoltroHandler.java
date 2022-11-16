package it.equitalia.gdo.svecchiamento.spazio.http.handler;

import org.apache.log4j.Logger;


public class GDOInoltroHandler {
	private static Logger logger = Logger.getLogger(GDOInoltroHandler.class);

	public int inoltra(byte[] file, String nomeFile) throws Exception {
		logger.debug("METHOD.inoltra");
		logger.debug("input.param= file length byte: " + file.length);

		Inoltratore inoltratore = GDOInoltroFactory.createInoltratore();
		return inoltratore.inoltra(file, nomeFile);
	}

}