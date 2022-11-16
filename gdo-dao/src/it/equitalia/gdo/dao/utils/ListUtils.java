package it.equitalia.gdo.dao.utils;

import java.util.Collections;


public class ListUtils {
	
	/**
	 * Per evitare null pointer exception quando si itera una collection iterable
	 * 
	 * Source:
	 * (http://stackoverflow.com/questions/2250031/null-check-in-an-enhanced-for-loop)
	 */
	public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
	    return iterable == null ? Collections.<T>emptyList() : iterable;
	}
}
