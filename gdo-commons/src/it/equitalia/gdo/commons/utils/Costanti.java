package it.equitalia.gdo.commons.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Costanti {
	
	private final static String ENTE_VALORE = "E";
	private final static String AGENTE_VALORE = "C";
	private final static String EQUITALIA_VALORE = "X";
	public enum TipologiaUtente {
	    ENTE("Ente", ENTE_VALORE),
	    AGENTE("Agente", AGENTE_VALORE),
	    EQUITALIA("Equitalia", EQUITALIA_VALORE);
	    
	    
	    private final String descrizione;
	    private final String valore;

	    private TipologiaUtente(String descrizione, String valore) {
	        this.descrizione = descrizione;
	        this.valore=valore;
	    }

	    public String getValue() {
	        return valore;
	    }
	    public String getDescrizione() {
	    	
	    	return descrizione;
	    }

		
	}
	
	private final static int SI_VAL = 1;
	private final static int NO_VAL = 0; 
	public enum OPZIONI_VALIDA {
	    SI("Si",SI_VAL),
	    NO("No",NO_VAL);
	    
	    
	    private final String descrizione;
	    private final int valore;

	    private OPZIONI_VALIDA(String descrizione, int valore) {
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
	
	private final static int ATTIVO_VAL = 1;
	private final static int NON_ATTIVO_VAL = 0; 
	public enum OPZIONI_STATO_DOCUMENTI {
	    ATTIVO("Attivo",ATTIVO_VAL),
	    NON_ATTIVO("Non Attivo",NON_ATTIVO_VAL);
	    
	    
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

	private final static int SOLO_ENTE_VAL =0;
	private final static int SOLO_AGENTE_VAL =1;
	public enum OPZIONI_TIPOLOGIA_UTENTE {
		
		SOLO_ENTE("Enti", SOLO_ENTE_VAL),		
		SOLO_AGENTE("Agenti", SOLO_AGENTE_VAL);


		private final String descrizione;
		private final int valore;	    

		private OPZIONI_TIPOLOGIA_UTENTE(String descrizione, int valore) {
			this.descrizione = descrizione;
			this.valore=valore;	      	        	        
		}

		public int getValue() {
			return valore;
		}

		public String getDescrizione() {	    	
			return descrizione;
		}

		public static String getDescrizioneByValore(int value) { 
			for(OPZIONI_TIPOLOGIA_UTENTE e: OPZIONI_TIPOLOGIA_UTENTE.values()) { 
				if(e.getValue() == value) 
				{ return e.getDescrizione(); 
				} 
			} return null;
		}
	}

	
	private static final Integer CODICE_NORD =  Integer.parseInt( GDOConfig.getInstance().getProperty(GDOConfig.CODICE_NORD) ); 
	private static final Integer CODICE_CENTRO = Integer.parseInt( GDOConfig.getInstance().getProperty(GDOConfig.CODICE_CENTRO) );	
	private static final Integer CODICE_SUD = Integer.parseInt( GDOConfig.getInstance().getProperty(GDOConfig.CODICE_SUD) );
	private static final Integer CODICE_SICILIA = Integer.parseInt( GDOConfig.getInstance().getProperty(GDOConfig.CODICE_SICILIA) );
	
	public static enum RaggruppamentoSocietario {


		NORD(1, "Equitalia Nord",CODICE_NORD),
		CENTRO(2, "Equitalia Centro", CODICE_CENTRO),
		SUD(3, "Equitalia Sud",CODICE_SUD),
		SICILIA(4, "Riscossione Sicilia", CODICE_SICILIA);


		private final int position;
		private final int codice;
		private final String descrizione;

		RaggruppamentoSocietario(int position, String descrizione, int codice) {
			this.position=position;
			this.descrizione=descrizione;
			this.codice = codice;
		}

		public String getDescrizione() {
			return descrizione;
		}

		public int getCodice() {
			return codice;
		}
		
		public int getPosition() {
			return position;
		}

		public String toString() {
			return descrizione;
		}

		public static List<RaggruppamentoSocietario> getOrderedValues() {
			List<RaggruppamentoSocietario> list = Arrays.asList(RaggruppamentoSocietario.values());
	        Collections.sort(list, new Comparator<RaggruppamentoSocietario>() {
	            public int compare(RaggruppamentoSocietario o1, RaggruppamentoSocietario o2) {
	                return o1.getPosition() - o2.getPosition();
	            }
	        });
			return list;
		}
		
		public static RaggruppamentoSocietario getByCodice(int codice) {
			if(codice == CODICE_NORD)
				return RaggruppamentoSocietario.NORD;
			else if(codice == CODICE_CENTRO)
				return RaggruppamentoSocietario.CENTRO;
			else if(codice == CODICE_SUD)
				return RaggruppamentoSocietario.SUD;
			else if(codice == CODICE_SICILIA)
				return RaggruppamentoSocietario.SICILIA;
			return null;
		}
		
	}


	
}
