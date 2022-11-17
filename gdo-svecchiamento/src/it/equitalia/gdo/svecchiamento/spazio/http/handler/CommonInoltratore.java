package it.equitalia.gdo.svecchiamento.spazio.http.handler;

import it.equitalia.gdo.svecchiamento.spazio.http.SpazioUpload;
import it.equitalia.gdo.svecchiamento.spazio.util.GdoException;
import it.equitalia.gdo.svecchiamento.spazio.util.GdoInoltroException;


public abstract class CommonInoltratore implements Inoltratore {
	
	private byte[] file;

	
	public int inoltra(byte[] contentTXT, String nomeFile) throws Exception {

		// Invio il file alla coda Spazio
		int risultatoUpload = SpazioUpload.doUpload(contentTXT, nomeFile);
		if (risultatoUpload != 200) {
			throw new GdoInoltroException(GdoException.CODICE_ERRORE_KO, GdoException.MESSAGGIO_ERRORE_KO);
			
		}
		
		return risultatoUpload;

	}


	public byte[] getFile() {
		return file;
	}
	
	public void setFile(byte[] file) {
		this.file = file;
	}

}
