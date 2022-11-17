package it.equitalia.gdo.web.actions.news;

import static it.equitalia.gdo.commons.utils.GDOMessaggi.CODICE_ENTE_NUMERICO;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.DATA_INIZIO_OBBLIGATORIA;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.ERRORE_NEWS_TESTO_NON_VALIDO;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.ERRORE_NEWS_TESTO_TROPPO_LUNGO;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.ERRORE_NEWS_TITOLO_NON_VALIDO;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.STATO_OBBLIGATORIO;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.TESTO_NEWS_OBBLIGATORIO;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.TIPOLOGIA_NEWS_OBBLIGATORIO;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.TITOLO_NEWS_OBBLIGATORIO;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.getMessaggio;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.getMessaggioConVariabili;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.StringUtils;
import it.equitalia.gdo.commons.valueobjects.AllegatoBean;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.web.businessdelegate.NewsServiceBD;
import it.equitalia.gdo.web.util.AntiSamyPolicies;
import it.equitalia.gdo.web.util.PopolaFiltriNews;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;



public abstract class AbstractFormNewsAction extends AbstractNewsAction {
	
	

	private static Logger log = Logger.getLogger(AbstractFormNewsAction.class);

	private static final long serialVersionUID = 1L;		

	protected static List<Integer> numeroAllegati = new LinkedList<Integer>();	

	/*Opzioni per i campi prepopolati del form */
	protected static Map<Integer,String> opzioniRaggruppamentoSocietario = new HashMap<Integer,String>();
	protected static Map<String,String> opzioniProvincia = new LinkedHashMap<String, String>();
	protected static Map<Integer,String> opzioniRegione = new LinkedHashMap<Integer,String>();
	protected static Map<String,String> opzioniServizioEnte = new LinkedHashMap<String,String>();
	protected static Map<String,String> opzioniServizioAgente = new LinkedHashMap<String,String>();
	protected static Map<String,String> opzioniServizioAltriUtenti = new LinkedHashMap<String,String>();
	protected static Map<String,String> opzioniTipoEnte = new LinkedHashMap<String,String>();
	protected static Map<Integer,String> opzioniAmbito = new LinkedHashMap<Integer,String>();
	
	/* Opzioni preselezionate per i filtri a scelta multipla */	
	protected Map<Integer,String> opzioniScelteRaggruppamentoSocietario = new HashMap<Integer,String>();
	
	protected Map<Integer,String> opzioniScelteAmbito = new LinkedHashMap<Integer, String>();
	protected Map<String,String> opzioniScelteProvinciaEnte = new LinkedHashMap<String, String>();
	
	//qua e' regione ha una mappa <String,String> perche' sul nostro database la regione inviata
	//dal form viene salvata come stringa.
	//sopra sulle opzioni lette da DB2 ho invece <Integer,String>, perche' su Db2
	//la regione e' stringa.
	protected Map<String,String> opzioniScelteRegioneAgente = new TreeMap<String,String>();
	protected Map<String,String> opzioniScelteRegioneEnte = new LinkedHashMap<String,String>();
	
	protected Map<String,String> opzioniScelteServizioAgente = new LinkedHashMap<String,String>();
	protected Map<String,String> opzioniScelteServizioEnte = new LinkedHashMap<String,String>();
	protected Map<String,String> opzioniScelteServizioAltriUtenti = new LinkedHashMap<String,String>();
	protected Map<String,String> opzioniScelteTipoEnte = new LinkedHashMap<String,String>();
	
		
	/*Campi non mappati direttamente nel bean */
	protected File allegato_0;	
	protected String allegato_0FileName;
	protected String titolo_allegato_0;

	protected static String tooltipNews;
	
	
	
	
	public String solaLettura() {
		return "";
	}
	
	
	

	static {
		
		//Blocco per inizializzazione 
		//per filtrare testo malevolo dalla textarea
		
		
		Integer numAllegatiInt;
		try {
			numAllegatiInt = Integer.parseInt( GDOConfig.getInstance().getProperty(GDOConfig.NUMERO_ALLEGATI) );
		} catch(java.lang.NumberFormatException e) {
			log.error("Errore nella lettura del numero allegati da file di property", e);
			numAllegatiInt = 1;
		}
		
		for (int i =0; i < numAllegatiInt; i++) {
			numeroAllegati.add(i);
		}

		tooltipNews = GDOMessaggi.getMessaggio(GDOMessaggi.TOOLTIP_TESTO_NEWS).replace("'","&rsquo;");		
		
	}

	
	
	/* Metodi get e set */

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}



	@SuppressWarnings("unchecked")
	public Map<String, String> getOpzioniProvincia() {
		if(getSession().containsKey("opzioniProvincia")){
			opzioniProvincia = (Map<String, String>) getSession().get("opzioniProvincia");
		}
		return opzioniProvincia;
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, String> getOpzioniRegione() {
		if(getSession().containsKey("opzioniRegione")){
			opzioniRegione = (Map<Integer, String>) getSession().get("opzioniRegione");
		}
		return opzioniRegione;
	}	

	@SuppressWarnings("unchecked")
	public Map<String,String> getOpzioniServizioEnte() {
		if(getSession().containsKey("opzioniServizioEnte")){
			opzioniServizioEnte = (Map<String, String>) getSession().get("opzioniServizioEnte");
		}
		return opzioniServizioEnte;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,String> getOpzioniServizioAgente() {
		if(getSession().containsKey("opzioniServizioAgente")){
			opzioniServizioAgente = (Map<String, String>) getSession().get("opzioniServizioAgente");
		}
		return opzioniServizioAgente;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,String> getOpzioniServizioAltriUtenti() {
		if(getSession().containsKey("opzioniServizioAltriUtenti")){
			opzioniServizioAltriUtenti = (Map<String, String>) getSession().get("opzioniServizioAltriUtenti");
		}
		return opzioniServizioAltriUtenti;
	}

	@SuppressWarnings("unchecked")
	public  Map<Integer, String> getOpzioniRaggruppamentoSocietario() {
		if(getSession().containsKey("opzioniRaggruppamentoSocietario")){
			opzioniRaggruppamentoSocietario = (Map<Integer, String>) getSession().get("opzioniRaggruppamentoSocietario");
		}
		return opzioniRaggruppamentoSocietario;
		
	}

	@SuppressWarnings("unchecked")
	public  Map<String, String> getOpzioniTipoEnte() {
		if(getSession().containsKey("opzioniTipoEnte")){
			opzioniTipoEnte = (Map<String, String>) getSession().get("opzioniTipoEnte");
		}
		return opzioniTipoEnte;
	}
	
	public Map<Integer, String> getOpzioniScelteRaggruppamentoSocietario() {
		return opzioniScelteRaggruppamentoSocietario;
	}

	public Map<Integer, String> getOpzioniScelteAmbito() {
		return opzioniScelteAmbito;
	}


	public Map<String, String> getOpzioniScelteProvinciaEnte() {
		return opzioniScelteProvinciaEnte;
	}

	public Map<String, String> getOpzioniScelteRegioneAgente() {
		return opzioniScelteRegioneAgente;
	}

	public Map<String, String> getOpzioniScelteRegioneEnte() {
		return opzioniScelteRegioneEnte;
	}

	public Map<String, String> getOpzioniScelteServizioAgente() {
		return opzioniScelteServizioAgente;
	}
	
	public Map<String, String> getOpzioniScelteServizioAltriUtenti() {
		return opzioniScelteServizioAltriUtenti;
	}
	public Map<String, String> getOpzioniScelteServizioEnte() {
		return opzioniScelteServizioEnte;
	}

	public Map<String, String> getOpzioniScelteTipoEnte() {
		return opzioniScelteTipoEnte;
	}

	public List<Integer> getNumeroAllegati() {
		return numeroAllegati;
	}

	public File getAllegato_0() {
		return allegato_0;
	}
	public void setAllegato_0(File allegato_0) {
		this.allegato_0 = allegato_0;
	}		
	
	public String getAllegato_0FileName() {
		return allegato_0FileName;
	}

	public void setAllegato_0FileName(String allegato_0FileName) {
		this.allegato_0FileName = allegato_0FileName;
	}

	public String getTitolo_allegato_0() {
		return titolo_allegato_0;
	}

	public void setTitolo_allegato_0(String titolo_allegato_0) {
		this.titolo_allegato_0 = titolo_allegato_0;
	}
	
	public String getTooltipNews() {
		return tooltipNews;
	}

	/**
	 * FINE GET/SET
	 */
	
	public abstract Boolean isModalitaVisualizza();
	public abstract Boolean isModalitaModifica();
	public abstract Boolean isModalitaCreazione();	
	
	public void prepare() throws Exception {
		super.prepare();		
		
		//popolamento lista sx doublelist
		p = new PopolaFiltriNews(getSession());
		p.popolaFiltriNews();
		
		if (news != null) {
			popolaOptionsConValoriSelezionati();
		}
		
	}
	

	
	/**
	 * Validazione del form crea/modifica news
	 * 
	 */
	@Override
	protected void validaForm() {
		if (news != null) {			
			
			log.debug("Validazione form di inserimento inviato");
						
			//Validazione Titolo
			if (StringUtils.isBlank(news.getTitolo()))
				addFieldError("errori-form", getMessaggio(TITOLO_NEWS_OBBLIGATORIO));
			else {
				AntiSamy as = new AntiSamy();
				try {
					CleanResults cr = as.scan(news.getTitolo(), AntiSamyPolicies.getInstance().getPolicySoloTesto());
					if (cr.getNumberOfErrors() > 0) {
						addFieldError("errori-form", getMessaggio(ERRORE_NEWS_TITOLO_NON_VALIDO));
					}
				} catch (ScanException e) {
					log.error("Errore durante l'esecuzione funzione di antisamy [scansione titolo news per ricercare codice malevolo],input="+news.getTitolo(),e);
					addFieldError("errori-form", getMessaggio(ERRORE_NEWS_TITOLO_NON_VALIDO));
				} catch (PolicyException e) {
					log.error("Errore durante l'esecuzione funzione di antisamy [scansione titolo news per ricercare codice malevolo],input="+news.getTitolo(),e);
					addFieldError("errori-form", getMessaggio(ERRORE_NEWS_TITOLO_NON_VALIDO));
				} 
				
			}

			//Validazione Testo della News
			String testo = news.getTesto();
			if (StringUtils.isBlank(testo.replaceAll("&nbsp;", ""))){
				news.setTesto("");
				addFieldError("errori-form", getMessaggio(TESTO_NEWS_OBBLIGATORIO));
			}
			else {				
				AntiSamy as = new AntiSamy();
				try {
					//antiSamyPolicyObject è thread safe vedi inizializzazione per riferimenti
					CleanResults cr = as.scan(news.getTesto(),AntiSamyPolicies.getInstance().getPolicyTextarea());
					String testoDaSalvareSuDB = cr.getCleanHTML();
					news.setTesto(testoDaSalvareSuDB);
					
					int diff = testoDaSalvareSuDB.length() - GDOConfig.getInstance().getLunghezzaMassimaTestoNews(); 
					if (diff > 0)
					{
						String msg = getMessaggioConVariabili(ERRORE_NEWS_TESTO_TROPPO_LUNGO,""+diff);
						addFieldError("errori-form",msg	);
					}
					
				} catch (ScanException e) {
					log.error("Errore durante l'esecuzione funzione di antisamy [scansione textarea per ricercare codice malevolo],input="+news.getTesto(),e);
					addFieldError("errori-form", getMessaggio(ERRORE_NEWS_TESTO_NON_VALIDO));
				} catch (PolicyException e) {
					log.error("Errore durante l'esecuzione funzione di antisamy [scansione textarea per ricercare codice malevolo],input="+news.getTesto(),e);
					addFieldError("errori-form", getMessaggio(ERRORE_NEWS_TESTO_NON_VALIDO));
				} 
			}
			
			//Validazione Data Inizio Pubblicazione Obblig.
			if (StringUtils.isBlank(news.getDataInizioPubblicazione()))
				addFieldError("errori-form", getMessaggio(DATA_INIZIO_OBBLIGATORIA));
			
			//Validazione Stato
			if (news.getStato() == null)
				addFieldError("errori-form", getMessaggio(STATO_OBBLIGATORIO));
			
			//Validazione Filtro Cod.Ente
			if (getFieldErrors().containsKey("news.filtroEnte.codiceEnte"))
				addFieldError("errori-form", getMessaggio(CODICE_ENTE_NUMERICO));
			
			//Validazione Tipologia Utente Obblig.
			if (tipologiaUtenteCheck == null || tipologiaUtenteCheck.size() == 0)
				addFieldError("errori-form", getMessaggio(TIPOLOGIA_NEWS_OBBLIGATORIO));
		

			//Validazione dimensione allegato
			String dimMaxStr = GDOConfig.getInstance().getProperty(GDOConfig.DIM_MAX_ALLEGATI_KB);
			Integer dimMaxInt = 500; //default in caso di errore =500KB
			try {
				 dimMaxInt = Integer.parseInt(dimMaxStr);
			}
			catch(Exception e) {
				log.error("Dimensione massima allegato non inserita correttamente in gdo.properties (" +dimMaxStr +")",e);
			}
			//passo da KB a Bytes
			dimMaxInt = 1024 * dimMaxInt;
			if(allegato_0 != null && allegato_0.length() == 0){
				addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.FILE_ALLEGATO_VUOTO));
			}
			if (allegato_0 != null && allegato_0.length() > dimMaxInt ) {				
				String msg = GDOMessaggi.getMessaggio(GDOMessaggi.ALLEGATO_TROPPO_GRANDE).replace("%size",dimMaxStr);
				addFieldError("errori-form", msg);
			}
			
		}
	}

	
	protected void popolaOptionsConValoriSelezionati() throws BusinessException {
	
			
			if (news.getFiltroTipologiaEnte() != null) {
				for (String campo : news.getFiltroTipologiaEnte().getValori())
					opzioniScelteTipoEnte.put(campo, p.getMappaTipiEnte().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteTipoEnte);
				opzioniScelteTipoEnte = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteTipoEnte.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (news.getFiltroProvinciaEnte() != null) {
				for (String campo : news.getFiltroProvinciaEnte().getValori())
					opzioniScelteProvinciaEnte.put(campo, p.getMappaProvince().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteProvinciaEnte);
				opzioniScelteProvinciaEnte = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteProvinciaEnte.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (news.getFiltroRegioneEnte() != null) {
				for (String campo : news.getFiltroRegioneEnte().getValori())			
					opzioniScelteRegioneEnte.put(campo, p.getMappaRegioni().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteRegioneEnte);
				opzioniScelteRegioneEnte = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteRegioneEnte.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (news.getFiltroServizioEnte() != null) {
				for (String campo : news.getFiltroServizioEnte().getValori())
					opzioniScelteServizioEnte.put(campo, p.getMappaServiziEnte().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteServizioEnte);
				opzioniScelteServizioEnte = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteServizioEnte.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (news.getFiltroAmbito() != null) {
				for (Integer campo : news.getFiltroAmbito().getValori())
					opzioniScelteAmbito.put(campo, p.getMappaAmbiti().get(campo));
				
				SortedSet<Map.Entry<Integer,String>> sortedEntries = StringUtils.entriesSortedByValues2(opzioniScelteAmbito);
				opzioniScelteAmbito = new LinkedHashMap<Integer, String>();
				for (Entry<Integer, String> entry : sortedEntries) {
					opzioniScelteAmbito.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (news.getFiltroRegioneAgente() != null) {
				for (String campo : news.getFiltroRegioneAgente().getValori())
					opzioniScelteRegioneAgente.put(campo, p.getMappaRegioni().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteRegioneAgente);
				opzioniScelteRegioneAgente = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteRegioneAgente.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (news.getFiltroServizioAgente() != null) {
				for (String campo : news.getFiltroServizioAgente().getValori())
					opzioniScelteServizioAgente.put(campo, p.getMappaServiziAgente().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteServizioAgente);
				opzioniScelteServizioAgente = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteServizioAgente.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (news.getFiltroSocieta() != null) {
				for (Integer campo : news.getFiltroSocieta().getValori())					
					opzioniScelteRaggruppamentoSocietario.put(campo, p.getMappaSocieta().get(campo));
				
				SortedSet<Map.Entry<Integer,String>> sortedEntries = StringUtils.entriesSortedByValues2(opzioniScelteRaggruppamentoSocietario);
				opzioniScelteRaggruppamentoSocietario = new LinkedHashMap<Integer, String>();
				for (Entry<Integer, String> entry : sortedEntries) {
					opzioniScelteRaggruppamentoSocietario.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (news.getFiltroServizioAltriUtenti() != null) {
				for (String campo : news.getFiltroServizioAltriUtenti().getValori())
					opzioniScelteServizioAltriUtenti.put(campo, p.getMappaServiziAltriUtenti().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteServizioAltriUtenti);
				opzioniScelteServizioAltriUtenti = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteServizioAltriUtenti.put(entry.getKey(), entry.getValue());
	
				}
			}
		
	}

	
	protected void inserisciAllegatoNelBean() throws BusinessException {
		if (allegato_0 != null)
		{
			FileInputStream fis;
			byte[] contenutoFile;
			try {
				fis = new FileInputStream(allegato_0);
				contenutoFile = IOUtils.toByteArray(fis);
			} 
			catch(FileNotFoundException fnfe) {
				//Dipende dal comportamento dell'utente anomalo sul suo pc, non e` un errore del software
				log.warn("Errore durante la lettura dell'allegato da disco",fnfe);
				throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_CARICAMENTO_ALLEGATO));
			}
			catch (Exception e) {
				log.warn("Errore durante la lettura dell'allegato da disco",e); 
				throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_CARICAMENTO_ALLEGATO));
			}
			
			AllegatoBean bean = new AllegatoBean();
			bean.setTitolo(titolo_allegato_0);
			String nomeFile = allegato_0FileName;
			
			//se il nome del file e` troppo lungo, lo sistemo prima di inserirlo nel bean
			if (nomeFile.length() > 50)
			{
				int posizionePunto = nomeFile.lastIndexOf(".");
				if (posizionePunto == -1) {
					nomeFile = nomeFile.substring(0, 50);
				}
				else {
					String estensione = nomeFile.substring(posizionePunto + 1);
					String nome = nomeFile.substring(0, 50 - estensione.length() - 1);
					nomeFile = nome + "." + estensione;
				}
			}
			
			bean.setNomeFile(nomeFile);
			bean.setContenutoFile(contenutoFile);
			
			news.setAllegato(bean);
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer, String> getOpzioniAmbito() {
		if(getSession().containsKey("opzioniAmbito")){
			opzioniAmbito = (Map<Integer, String>) getSession().get("opzioniAmbito");
		}
		return opzioniAmbito;
	}	
	
	protected void valorizzaNewsVisualizzateComePopUp()
	throws BusinessException {
		// In fase di inserimento o modifica di una news è possibile impostare il flag "Visualizza come pop up"
		// solo se non ci sono altre news che possono essere visualizzate da pop up
		NewsServiceBD newsService = new NewsServiceBD();
		List<NewsBean> newsVisualizzateConPopUp = newsService.recuperaNewsPopUp();		
		if(newsVisualizzateConPopUp != null && newsVisualizzateConPopUp.size()>0)
			setNewsPopUp(newsVisualizzateConPopUp.get(0));		
		}
	
}


