package it.equitalia.gdo.web.util;

public class GDOCostantiWeb {

	public final static String KEY_SECURITY_CONTEXT = "SECURITY_CONTEXT";
	
	public enum EJB_JNDI{
		JNDI_NEWS					("service.jndi_news"),
		JNDI_ALLEGATO				("service.jndi_allegato"),
		JNDI_FILTRO_SERVIZIO		("service.jndi_filtro_servizio"),
		JNDI_FILTRO_PROVINCIA		("service.jndi_filtro_provincia"),
		JNDI_FILTRO_REGIONE			("service.jndi_filtro_regione"),
		JNDI_TIPOLOGIA_ENTE			("service.jndi_tipologia_ente"),
		JNDI_DOCUMENTO				("service.jndi_documento"),
		JNDI_SEZIONE				("service.jndi_sezione"),		
		JNDI_NEWS_FRONTEND			("service.jndi_news_frontend"),
		JNDI_DOCUMENTALE_FRONTEND	("service.jndi_documentale_frontend"),
		JNDI_AMBITO ("service.jndi_filtro_ambito");
		
		
		private String key;
		
		private EJB_JNDI(String key){
			this.key = key;
		}
		
		public String getJndi(){
			return this.key;
		}
	}
	private final static int ATTIVA_VAL = 1;
	private final static int NON_ATTIVA_VAL = 0; 
	public enum OPZIONI_STATO {
	    ATTIVA("Attiva",ATTIVA_VAL),
	    NON_ATTIVA("Non Attiva",NON_ATTIVA_VAL);
	    
	    
	    private final String descrizione;
	    private final int valore;

	    private OPZIONI_STATO(String descrizione, int valore) {
	        this.descrizione = descrizione;
	        this.valore=valore;
	    }

	    public int getValue() {
	        return valore;
	    }
	    public String getDescrizione() {
	    	
	    	return descrizione;
	    }

		
	}
	
	private final static Boolean VISUALIZZAZIONE_POP_UP_SI_VAL = true;
	private final static Boolean VISUALIZZAZIONE_POP_UP_NO_VAL = false; 
	public enum OPZIONI_VISUALIZZAZIONE_POP_UP {
		VISUALIZZAZIONE_POP_UP_SI("Si",VISUALIZZAZIONE_POP_UP_SI_VAL),
		VISUALIZZAZIONE_POP_UP_NO("No",VISUALIZZAZIONE_POP_UP_NO_VAL);
	    
	    
		 private final String descrizione;
		    private final Boolean valore;

		    private OPZIONI_VISUALIZZAZIONE_POP_UP(String descrizione, Boolean valore) {
		        this.descrizione = descrizione;
		        this.valore=valore;
		    }

	    public Boolean getValue() {
	        return valore;
	    }
	    public String getDescrizione() {
	    	
	    	return descrizione;
	    }

		
	}
	
	private final static int DOC_ATTIVO_VAL = 1;
	private final static int DOC_NON_ATTIVO_VAL = 0; 
	public enum OPZIONI_STATO_DOCUMENTI {
	    ATTIVO("Attivo",DOC_ATTIVO_VAL),
	    NON_ATTIVO("Non Attivo",DOC_NON_ATTIVO_VAL);
	    
	    
	    private final String descrizione;
	    private final int valore;

	    private OPZIONI_STATO_DOCUMENTI(String descrizione, int valore) {
	        this.descrizione = descrizione;
	        this.valore=valore;
	    }

	    public int getValue() {
	        return valore;
	    }
	    public String getDescrizione() {
	    	
	    	return descrizione;
	    }

		
	}
	
	
}
