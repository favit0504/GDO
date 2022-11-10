package it.equitalia.gdo.web.actions.documento;

import static it.equitalia.gdo.commons.utils.GDOMessaggi.ERRORE_DOCUMENTO_DESCRIZIONE_NON_VALIDA;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.ERRORE_DOCUMENTO_TITOLO_NON_VALIDO;
import static it.equitalia.gdo.commons.utils.GDOMessaggi.getMessaggio;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.StringUtils;
import it.equitalia.gdo.commons.valueobjects.BlobDocumentoBean;
import it.equitalia.gdo.web.actions.news.AbstractFormNewsAction;
import it.equitalia.gdo.web.util.AntiSamyPolicies;
import it.equitalia.gdo.web.util.PopolaFiltriDocumento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

public abstract class AbstractFormDocumentoAction extends AbstractDocumentoAction{
	
	private static Logger log = Logger.getLogger(AbstractFormNewsAction.class);

	private static final long serialVersionUID = 1L;		

	protected static List<Integer> numeroFileDocumento = new LinkedList<Integer>();	

	/*Opzioni per i campi prepopolati del form */
	protected static Map<Integer,String> opzioniRaggruppamentoSocietario = new HashMap<Integer,String>();
	protected static Map<String,String> opzioniProvincia = new LinkedHashMap<String, String>();
	protected static Map<Integer,String> opzioniRegione = new LinkedHashMap<Integer,String>();
	protected static Map<String,String> opzioniServizioEnte = new LinkedHashMap<String,String>();
	protected static Map<String,String> opzioniServizioAgente = new LinkedHashMap<String,String>();
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
	protected Map<String,String> opzioniScelteRegioneAgente = new LinkedHashMap<String,String>();
	protected Map<String,String> opzioniScelteRegioneEnte = new LinkedHashMap<String,String>();
	
	protected Map<String,String> opzioniScelteServizioAgente = new LinkedHashMap<String,String>();
	protected Map<String,String> opzioniScelteServizioEnte = new LinkedHashMap<String,String>();
	protected Map<String,String> opzioniScelteTipoEnte = new LinkedHashMap<String,String>();
		
	/*Campi non mappati direttamente nel bean */
	protected File documento_0;	
	protected String documento_0FileName;
	protected String titolo_documento_0;
	
	protected Boolean mantieniDocumento;
	
	public String solaLettura() {
		return "";
	}
	
	
	

	static {
		Integer numFileInt;
		try {
			numFileInt = Integer.parseInt( GDOConfig.getInstance().getProperty(GDOConfig.NUMERO_FILE_DOCUMENTO) );
		} catch(java.lang.NumberFormatException e) {
			log.error("Errore nella lettura del numero di file di property", e);
			numFileInt = 1;
		}
		for (int i =0; i < numFileInt; i++) {
			numeroFileDocumento.add(i);
		}

	}

	
	/* Metodi get e set */

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getMantieniDocumento() {
		return mantieniDocumento;
	}

	public void setMantieniDocumento(Boolean mantieniDocumento) {
		this.mantieniDocumento = mantieniDocumento;
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
	public Map<Integer, String> getOpzioniAmbito() {
		if(getSession().containsKey("opzioniAmbito")){
			opzioniAmbito = (Map<Integer, String>) getSession().get("opzioniAmbito");
		}
		return opzioniAmbito;
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

	public Map<String, String> getOpzioniScelteServizioEnte() {
		return opzioniScelteServizioEnte;
	}

	public Map<String, String> getOpzioniScelteTipoEnte() {
		return opzioniScelteTipoEnte;
	}

	public List<Integer> getNumeroFileDocumento() {
		return numeroFileDocumento;
	}

	public File getDocumento_0() {
		return documento_0;
	}
	
	public void setDocumento_0(File documento_0) {
		this.documento_0 = documento_0;
	}
	
	public String getDocumento_0FileName() {
		return documento_0FileName;
	}

	public void setDocumento_0FileName(String documento_0FileName) {
		this.documento_0FileName = documento_0FileName;
	}

	public String getTitolo_documento_0() {
		return titolo_documento_0;
	}

	public void setTitolo_documento_0(String titolo_documento_0) {
		this.titolo_documento_0 = titolo_documento_0;
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
		p = new PopolaFiltriDocumento(getSession());
		p.popolaFiltriDocumento();
		
		if (documento != null) {
			popolaOptionsConValoriSelezionati();
		}
		
	}

	
	/**
	 * Validazione del form crea/modifica documento
	 * 
	 */
	@Override
	protected void validaForm() {
		if (documento != null) {
			
			log.debug("Validazione form di inserimento inviato");
			
			//Validazione Sezione
			if(documento.getSezione() == null || documento.getSezione().getId() == null )
				addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.ID_SEZIONE_NON_PRESENTE));
								
			//validazione Titolo
			if (StringUtils.isBlank(documento.getTitolo()))
				addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.TITOLO_DOCUMENTO_OBBLIGATORIO));
			else {
				AntiSamy as = new AntiSamy();
				try {
					CleanResults cr = as.scan(documento.getTitolo(), AntiSamyPolicies.getInstance().getPolicySoloTesto());
					if (cr.getNumberOfErrors() > 0) {
						addFieldError("errori-form", getMessaggio(ERRORE_DOCUMENTO_TITOLO_NON_VALIDO));
					}
				} catch (ScanException e) {
					log.error("Errore durante l'esecuzione funzione di antisamy [scansione titolo documento per ricercare codice malevolo o input non ammesso ],input="+documento.getTitolo(),e);
					addFieldError("errori-form", getMessaggio(ERRORE_DOCUMENTO_TITOLO_NON_VALIDO));
				} catch (PolicyException e) {
					log.error("Errore durante l'esecuzione funzione di antisamy [scansione titolo documento per ricercare codice malevolo o input non ammesso ],input="+documento.getTitolo(),e);
					addFieldError("errori-form", getMessaggio(ERRORE_DOCUMENTO_TITOLO_NON_VALIDO));
				} 
			}
			
			//Validazione Descrizione
			if (StringUtils.isBlank(documento.getDescrizione()))
				addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.DESCRIZIONE_DOCUMENTO_OBBLIGATORIA));
			else  {
				AntiSamy as = new AntiSamy();
				try {
					CleanResults cr = as.scan(documento.getDescrizione(), AntiSamyPolicies.getInstance().getPolicyTextarea());
					if (cr.getNumberOfErrors() > 0) {
						addFieldError("errori-form", getMessaggio(ERRORE_DOCUMENTO_DESCRIZIONE_NON_VALIDA));
					}
				} catch (ScanException e) {
					log.error("Errore durante l'esecuzione funzione di antisamy [scansione descrizione documento per ricercare codice malevolo o input non ammesso ],input="+documento.getDescrizione(),e);
					addFieldError("errori-form", getMessaggio(ERRORE_DOCUMENTO_DESCRIZIONE_NON_VALIDA));
				} catch (PolicyException e) {
					log.error("Errore durante l'esecuzione funzione di antisamy [scansione descrizione documento per ricercare codice malevolo o input non ammesso ],input="+documento.getDescrizione(),e);
					addFieldError("errori-form", getMessaggio(ERRORE_DOCUMENTO_DESCRIZIONE_NON_VALIDA));
				}
				
			}
			
			//Validazione Stato
			if(documento.getStato() == null)
				addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.STATO_DOCUMENTO_OBBLIGATORIO));

			//Validazione Almeno 1 Tipologia Utente
			if(tipologiaUtenteCheck == null || tipologiaUtenteCheck.size() == 0){
				addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.TIPOLOGIA_DOCUMENTO_OBBLIGATORIA));
			}
			
			//Validazione Filtro Cod.Ente
			if (getFieldErrors().containsKey("documento.filtroEnte.codiceEnte"))
				addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.CODICE_ENTE_NUMERICO));
			
			//Validazione Allegato
			if(mantieniDocumento == null || !mantieniDocumento){
				if (documento_0 == null) {
					addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.FILE_DOCUMENTO_OBBLIGATORIO));
				}
				else if (documento_0.length() == 0) {
					addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.FILE_DOCUMENTO_VUOTO));
				}
			}
			//Validazione dimensione file
			String dimMaxStr = GDOConfig.getInstance().getProperty(GDOConfig.DIM_MAX_FILE_DOCUMENTO_KB);
			Integer dimMaxInt = 500; //default in caso di errore =500KB
			try {
				 dimMaxInt = Integer.parseInt(dimMaxStr);
			}
			catch(Exception e) {
				log.error("Dimensione massima del file non inserita correttamente in gdo.properties (" +dimMaxStr +")",e);
			}
			//passo da KB a Bytes
			dimMaxInt = 1024 * dimMaxInt;
			if (documento_0 != null && documento_0.length() > dimMaxInt ) {				
				String msg = GDOMessaggi.getMessaggio(GDOMessaggi.FILE_DOCUMENTO_TROPPO_GRANDE).replace("%size",dimMaxStr);
				addFieldError("errori-form", msg);
			}			
		}
	}

	
	protected void popolaOptionsConValoriSelezionati() throws BusinessException {
	
			
			if (documento.getFiltroTipologiaEnte() != null) {
				for (String campo : documento.getFiltroTipologiaEnte().getValori())
					opzioniScelteTipoEnte.put(campo, p.getMappaTipiEnte().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteTipoEnte);
				opzioniScelteTipoEnte = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteTipoEnte.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (documento.getFiltroProvinciaEnte() != null) {
				for (String campo : documento.getFiltroProvinciaEnte().getValori())
					opzioniScelteProvinciaEnte.put(campo, p.getMappaProvince().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteProvinciaEnte);
				opzioniScelteProvinciaEnte = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteProvinciaEnte.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (documento.getFiltroRegioneEnte() != null) {
				for (String campo : documento.getFiltroRegioneEnte().getValori())			
					opzioniScelteRegioneEnte.put(campo, p.getMappaRegioni().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteRegioneEnte);
				opzioniScelteRegioneEnte = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteRegioneEnte.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (documento.getFiltroServizioEnte() != null) {
				for (String campo : documento.getFiltroServizioEnte().getValori())
					opzioniScelteServizioEnte.put(campo, p.getMappaServiziEnte().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteServizioEnte);
				opzioniScelteServizioEnte = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteServizioEnte.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (documento.getFiltroAmbito() != null) {
				for (Integer campo : documento.getFiltroAmbito().getValori())
					opzioniScelteAmbito.put(campo, p.getMappaAmbiti().get(campo));
				
				SortedSet<Map.Entry<Integer,String>> sortedEntries = StringUtils.entriesSortedByValues2(opzioniScelteAmbito);
				opzioniScelteAmbito = new LinkedHashMap<Integer, String>();
				for (Entry<Integer, String> entry : sortedEntries) {
					opzioniScelteAmbito.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (documento.getFiltroRegioneAgente() != null) {
				for (String campo : documento.getFiltroRegioneAgente().getValori())
					opzioniScelteRegioneAgente.put(campo, p.getMappaRegioni().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteRegioneAgente);
				opzioniScelteRegioneAgente = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteRegioneAgente.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (documento.getFiltroServizioAgente() != null) {
				for (String campo : documento.getFiltroServizioAgente().getValori())
					opzioniScelteServizioAgente.put(campo, p.getMappaServiziAgente().get(campo));
				
				SortedSet<Map.Entry<String,String>> sortedEntries = StringUtils.entriesSortedByValues(opzioniScelteServizioAgente);
				opzioniScelteServizioAgente = new LinkedHashMap<String, String>();
				for (Entry<String, String> entry : sortedEntries) {
					opzioniScelteServizioAgente.put(entry.getKey(), entry.getValue());
				}
			}
			
			if (documento.getFiltroSocieta() != null) {
				for (Integer campo : documento.getFiltroSocieta().getValori())					
					opzioniScelteRaggruppamentoSocietario.put(campo, p.getMappaSocieta().get(campo));
				
				SortedSet<Map.Entry<Integer,String>> sortedEntries = StringUtils.entriesSortedByValues2(opzioniScelteRaggruppamentoSocietario);
				opzioniScelteRaggruppamentoSocietario = new LinkedHashMap<Integer, String>();
				for (Entry<Integer, String> entry : sortedEntries) {
					opzioniScelteRaggruppamentoSocietario.put(entry.getKey(), entry.getValue());
				}
			}
		
		
	}

	
	protected void inserisciDocumentoNelBean() throws BusinessException {
		if (documento_0 != null)
		{
			FileInputStream fis;
			byte[] contenutoFile;
			try {
				fis = new FileInputStream(documento_0);
				contenutoFile = IOUtils.toByteArray(fis);
			} 
			catch(FileNotFoundException fnfe) {
				//Dipende dal comportamento dell'utente anomalo sul suo pc, non e` un errore del software
				log.warn("Errore durante la lettura del documento da disco",fnfe);
				throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_CARICAMENTO_DOCUMENTO));
			}
			catch (Exception e) {
				log.warn("Errore durante la lettura del documento da disco",e); 
				throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_CARICAMENTO_DOCUMENTO));
			}
			
			BlobDocumentoBean bean = new BlobDocumentoBean();			
			bean.setContenutoFile(contenutoFile);
			
			documento.setBlob(bean);
			documento.setNomeFile(documento_0FileName);
			
		}
	}			

}
