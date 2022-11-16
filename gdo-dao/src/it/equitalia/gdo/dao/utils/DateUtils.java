package it.equitalia.gdo.dao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtils {
	
	private static final Logger log = Logger.getLogger(DateUtils.class);
	
	private static SimpleDateFormat dateFormatDdMmYyyy;

	static {
		dateFormatDdMmYyyy = new SimpleDateFormat("dd/MM/yyyy");
		dateFormatDdMmYyyy.setLenient(false);
	}
	
	public static Date fromStringToDate(String data){			 
		Date d = null;
		if(data != null){
			try {
				d = new Date(dateFormatDdMmYyyy.parse(data).getTime());
			} catch (ParseException e) {			
				log.error("Errore, impossibile convertire la data formato stringa " + data,e);
			}	
		}
		return d;
	 }
	
	public static String fromDateToString(Date date){
		return dateFormatDdMmYyyy.format(date );
	}

}
