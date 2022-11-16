package it.equitalia.gdo.commons.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class StringUtils {

	 /**
	     * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
	     *
	     * <pre>
	     * StringUtils.isBlank(null)      = true
	     * StringUtils.isBlank("")        = true
	     * StringUtils.isBlank(" ")       = true
	     * StringUtils.isBlank("bob")     = false
	     * StringUtils.isBlank("  bob  ") = false
         * </pre>
	     *
	     * @param cs  the CharSequence to check, may be null
	     * @return {@code true} if the CharSequence is null, empty or whitespace */
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Ordina per valore la mappa passata in input 
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<String,String>> entriesSortedByValues(Map<String,String> map) {
	    SortedSet<Map.Entry<String,String>> sortedEntries = new TreeSet<Map.Entry<String,String>>(
	        new Comparator<Map.Entry<String,String>>() {
	            public int compare(Map.Entry<String,String> e1, Map.Entry<String,String> e2) {
	                int res = e1.getValue().compareTo(e2.getValue());
	                return res != 0 ? res : 1;
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
	
	/**
	 * Come entriesSortedByValues (ordina per valore la mappa passata in input), ma con Map<Integer,String>
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<Integer,String>> entriesSortedByValues2(Map<Integer,String> map) {
	    SortedSet<Map.Entry<Integer,String>> sortedEntries = new TreeSet<Map.Entry<Integer,String>>(
	        new Comparator<Map.Entry<Integer,String>>() {
	            public int compare(Map.Entry<Integer,String> e1, Map.Entry<Integer,String> e2) {
	                int res = e1.getValue().compareTo(e2.getValue());
	                return res != 0 ? res : 1;
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
	
	
}
