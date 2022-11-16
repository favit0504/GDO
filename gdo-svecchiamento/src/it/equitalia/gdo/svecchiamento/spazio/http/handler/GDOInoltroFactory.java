package it.equitalia.gdo.svecchiamento.spazio.http.handler;

import it.equitalia.gdo.svecchiamento.spazio.util.SvecchiamentoConstants;


/**
 * GDOInoltroFactory
 * 
 * @author Valerio Donnarumma
 * 
 */
public class GDOInoltroFactory {

	public static Inoltratore createInoltratore() throws Exception {
		Class<?> clz = Class.forName(GDOInoltroFactory.class.getPackage().getName()
						+"." +SvecchiamentoConstants.GDO_INOLTRATORE);
		
		return (Inoltratore) clz.newInstance();
	}

}
