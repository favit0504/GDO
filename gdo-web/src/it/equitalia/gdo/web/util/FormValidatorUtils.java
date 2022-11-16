package it.equitalia.gdo.web.util;

import it.equitalia.gdo.web.actions.news.NuovaNewsAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class FormValidatorUtils {
	
private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(NuovaNewsAction.class);

	private static SimpleDateFormat dateFormatDdMmYyyy;

	static {
		dateFormatDdMmYyyy = new SimpleDateFormat("dd/MM/yyyy");
		dateFormatDdMmYyyy.setLenient(false);
	}

	/**
	 * Validazione data
	 * @param text
	 * @return
	 */
	public static boolean isDdMmYyyy(String text) {

		boolean result = true;
		try {
			dateFormatDdMmYyyy.parse(text);
		} catch (ParseException e) {
			log.warn("Errore durante il parsing della data: "+text, e);
			result = false;
		}
		return result;
	}
	
	 public static Date fromStringToDate(String data){		
		 Date d = null;
		try {
			d = new Date(dateFormatDdMmYyyy.parse(data).getTime());
		} catch (ParseException e) {			
			 log.warn("Errore durante il parsing della data: "+data, e);
		}				
		 return d;
	 }

}
