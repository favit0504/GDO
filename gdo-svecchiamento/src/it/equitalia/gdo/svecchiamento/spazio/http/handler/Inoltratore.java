package it.equitalia.gdo.svecchiamento.spazio.http.handler;


/**
 * Inoltratore
 * 
 * @author Valerio Donnarumma
 *
 */
public interface Inoltratore {

	public int inoltra(byte[] file, String nomeFile) throws Exception;

}