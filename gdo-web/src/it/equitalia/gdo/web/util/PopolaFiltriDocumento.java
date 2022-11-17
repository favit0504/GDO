package it.equitalia.gdo.web.util;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO;
import it.equitalia.gdo.commons.utils.Costanti.RaggruppamentoSocietario;
import it.equitalia.gdo.commons.valueobjects.AmbitoBean;
import it.equitalia.gdo.commons.valueobjects.ProvinciaBean;
import it.equitalia.gdo.commons.valueobjects.RegioneBean;
import it.equitalia.gdo.commons.valueobjects.ServizioBean;
import it.equitalia.gdo.commons.valueobjects.TipoEnteBean;
import it.equitalia.gdo.web.businessdelegate.AmbitoServiceBD;
import it.equitalia.gdo.web.businessdelegate.ProvinciaServiceBD;
import it.equitalia.gdo.web.businessdelegate.RegioneServiceBD;
import it.equitalia.gdo.web.businessdelegate.ServizioServiceBD;
import it.equitalia.gdo.web.businessdelegate.TipoEnteServiceBD;
import it.equitalia.gdo.web.util.GDOCostantiWeb.OPZIONI_STATO_DOCUMENTI;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class PopolaFiltriDocumento {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(PopolaFiltriDocumento.class);
	
	SessionMap<String, Object> sessionMap;

	private static String OPZIONI_TIPOLOGIA_UTENTE_KEY = "opzioniTipologiaUtenteDocumento";
	private static String OPZIONI_STATO_DOCUMENTI_KEY = "opzioniStatoDocumenti";
	private static String OPZIONI_PROVINCIA = "opzioniProvincia";
	private static String OPZIONI_SERVIZIO_ENTE = "opzioniServizioEnte";
	private static String OPZIONI_SERVIZIO_AGENTE = "opzioniServizioAgente";
	private static String OPZIONI_REGIONE = "opzioniRegione";
	private static String OPZIONI_TIPO_ENTE = "opzioniTipoEnte";
	private static String OPZIONI_RAGGRUPPAMENTO_SOCIETARIO = "opzioniRaggruppamentoSocietario";
	private static String OPZIONI_AMBITO = "opzioniAmbito";
	
	
	protected Map<Integer,String> opzioniRaggruppamentoSocietario = new HashMap<Integer,String>();
	protected Map<String,String> opzioniProvincia = new LinkedHashMap<String, String>();
	protected Map<String,String> opzioniRegione = new LinkedHashMap<String,String>();
	protected Map<String,String> opzioniServizioEnte = new LinkedHashMap<String,String>();
	protected Map<String,String> opzioniServizioAgente = new LinkedHashMap<String,String>();
	protected Map<String,String> opzioniTipoEnte = new LinkedHashMap<String,String>();
	protected Map<Integer,String> opzioniTipologiaUtenteDocumento = new HashMap<Integer,String>();
	protected Map<Integer,String> opzioniStatoDocumenti = new HashMap<Integer,String>();
	protected Map<Integer,String> opzioniAmbito = new LinkedHashMap<Integer,String>();

	
	public PopolaFiltriDocumento(SessionMap<String, Object> s) {		
		sessionMap = s;
	}
	
	/**
	 * Chiamato dalla pagina del form per la ricerca delle news
	 * @param sessionMap
	 * @throws Exception
	 */
	public void popolaFiltriRicercaDocumento() throws Exception {

		if (!sessionMap.containsKey(OPZIONI_TIPOLOGIA_UTENTE_KEY)){
			popolaTipologiaUtente();
		}

		if (!sessionMap.containsKey(OPZIONI_STATO_DOCUMENTI_KEY)){
			popolaStato();
		}
	}
	

	public void popolaFiltriDocumento() throws Exception {

		if(!sessionMap.containsKey(OPZIONI_PROVINCIA)) {
			popolaProvincia();
		}

		if(!sessionMap.containsKey(OPZIONI_SERVIZIO_ENTE)) {
			popolaServizioEnte();
		}
		
		if(!sessionMap.containsKey(OPZIONI_SERVIZIO_AGENTE)) {
			popolaServizioAgente();
		}

		if(!sessionMap.containsKey(OPZIONI_REGIONE)) {
			popolaRegione();
		}

		if(!sessionMap.containsKey(OPZIONI_TIPO_ENTE)) {			
			popolaTipoEnte();			
		}

		if(!sessionMap.containsKey(OPZIONI_RAGGRUPPAMENTO_SOCIETARIO)){
			popolaRaggruppamento();
		}

		if(!sessionMap.containsKey(OPZIONI_TIPOLOGIA_UTENTE_KEY)){
			popolaTipologiaUtente();
		}

		if(!sessionMap.containsKey(OPZIONI_STATO_DOCUMENTI_KEY)){
			popolaStato();
		}
		if(!sessionMap.containsKey(OPZIONI_AMBITO))
			popolaAmbito();
	}

	private void popolaAmbito() throws BusinessException {
		AmbitoServiceBD ambitoService = new AmbitoServiceBD();
		List<AmbitoBean> ambitiAttivi = ambitoService.recuperaAmbiti();

		if(ambitiAttivi != null && ambitiAttivi.size() > 0){
			for (AmbitoBean amb : ambitiAttivi) {
				opzioniAmbito.put(amb.getCodiceAmbito(), amb.getDescrizione());
			}	
		}

		if(opzioniAmbito != null && opzioniAmbito.size() > 0){
			sessionMap.put(OPZIONI_AMBITO, opzioniAmbito);
		}
	}

	private void popolaProvincia() throws BusinessException {
		
		ProvinciaServiceBD provinciaService = new ProvinciaServiceBD();
		List<ProvinciaBean> provinceAttive = provinciaService.recuperaProvinceAttive();

		if(provinceAttive != null && provinceAttive.size() > 0){
			for (ProvinciaBean prov : provinceAttive) {
				opzioniProvincia.put(prov.getCodice(), prov.getDescrizione());
			}	
		}

		if(opzioniProvincia != null && opzioniProvincia.size() > 0){
			sessionMap.put(OPZIONI_PROVINCIA, opzioniProvincia);
		}
	}

	private void popolaServizioEnte() throws BusinessException {
		
		ServizioServiceBD servizioService = new ServizioServiceBD();
		List<ServizioBean> serviziAttivi = servizioService.recuperaServiziEnte();

		if(serviziAttivi != null && serviziAttivi.size() > 0){
			for (ServizioBean serv : serviziAttivi) {
				opzioniServizioEnte.put(serv.getCodice(), serv.getDescrizione());
			}
		}
		if(opzioniServizioEnte != null && opzioniServizioEnte.size() > 0){
			sessionMap.put(OPZIONI_SERVIZIO_ENTE, opzioniServizioEnte);
		}
	}
	
	private void popolaServizioAgente() throws BusinessException {
		
		ServizioServiceBD servizioService = new ServizioServiceBD();
		List<ServizioBean> serviziAttivi = servizioService.recuperaServiziAgente();

		if(serviziAttivi != null && serviziAttivi.size() > 0){
			for (ServizioBean serv : serviziAttivi) {
				opzioniServizioAgente.put(serv.getCodice(), serv.getDescrizione());
			}
		}
		if(opzioniServizioAgente != null && opzioniServizioAgente.size() > 0){
			sessionMap.put(OPZIONI_SERVIZIO_AGENTE, opzioniServizioAgente);
		}
	}

	private void popolaRegione() throws BusinessException {

		RegioneServiceBD regioneService = new RegioneServiceBD();
		List<RegioneBean> regioniAttive = regioneService.recuperaRegioniAttive();

		if(regioniAttive != null && regioniAttive.size() > 0){
			for (RegioneBean reg : regioniAttive) {
				opzioniRegione.put(reg.getCodice(), reg.getDescrizione());
			}	
		}
		if(opzioniRegione != null && opzioniRegione.size() > 0){
			sessionMap.put(OPZIONI_REGIONE, opzioniRegione);
		}
	}

	private void popolaTipoEnte() throws BusinessException {
		
		TipoEnteServiceBD tipoEnteService = new TipoEnteServiceBD();
		List<TipoEnteBean> tipoEntiAttivi = tipoEnteService.recuperaTipoEnti();

		if(tipoEntiAttivi != null && tipoEntiAttivi.size() > 0){
			for (TipoEnteBean t: tipoEntiAttivi) {
				opzioniTipoEnte.put(t.getCodice(), t.getDescrizione());
			}	
		}
		if(opzioniTipoEnte != null && opzioniTipoEnte.size() > 0){
			sessionMap.put(OPZIONI_TIPO_ENTE, opzioniTipoEnte);
		}			
	}

	private void popolaRaggruppamento() {		
		for(RaggruppamentoSocietario o : RaggruppamentoSocietario.getOrderedValues())						
			opzioniRaggruppamentoSocietario.put(o.getCodice(), o.getDescrizione());

		sessionMap.put("opzioniRaggruppamentoSocietario", opzioniRaggruppamentoSocietario);
	}

	private void popolaTipologiaUtente() {
		for(OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO o : OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO.values())						
			opzioniTipologiaUtenteDocumento.put(o.getValue(), o.getDescrizione());

		sessionMap.put(OPZIONI_TIPOLOGIA_UTENTE_KEY, opzioniTipologiaUtenteDocumento);
	}
	
	private void popolaStato() {
		for(OPZIONI_STATO_DOCUMENTI o : OPZIONI_STATO_DOCUMENTI.values())						
			opzioniStatoDocumenti.put(o.getValue(), o.getDescrizione());

		sessionMap.put(OPZIONI_STATO_DOCUMENTI_KEY, opzioniStatoDocumenti);
	}
	
	/**
	 * Metodo usato nella modifica documenti per prendere la mappa chiave/valore dei servizi Ente
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getMappaServiziEnte() throws BusinessException {
		
		/* Campo gia` popolato? */
		if (opzioniServizioEnte.size() > 0)
		{
			return  opzioniServizioEnte;	
		}
		else 
		/* Campo opzioniServizio inizializzato ma vuoto, vado a recuperarlo dalla sessione
		   [ASSUNZIONE che sia stato gia` popolato in precedenza] */
		{
			Object oggettoInSessione = sessionMap.get(OPZIONI_SERVIZIO_ENTE);
			
			if (!(oggettoInSessione instanceof Map))
				throw new BusinessException("Errore durante il recupero della lista dei servizi ente");
		
			Map<String,String> result = (Map<String,String>) oggettoInSessione;
			return result;
		}
	
	}
	
	/**
	 * Metodo usato nella modifica documenti per prendere la mappa chiave/valore dei servizi Agente
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getMappaServiziAgente() throws BusinessException {
		
		/* Campo gia` popolato? */
		if (opzioniServizioAgente.size() > 0)
		{
			return  opzioniServizioAgente;	
		}
		else 
		/* Campo opzioniServizio inizializzato ma vuoto, vado a recuperarlo dalla sessione
		   [ASSUNZIONE che sia stato gia` popolato in precedenza] */
		{
			Object oggettoInSessione = sessionMap.get(OPZIONI_SERVIZIO_AGENTE);
			
			if (!(oggettoInSessione instanceof Map))
				throw new BusinessException("Errore durante il recupero della lista dei servizi agente");
		
			Map<String,String> result = (Map<String,String>) oggettoInSessione;
			return result;
		}
	
	}

	@SuppressWarnings("unchecked")
	public Map<String,String> getMappaTipiEnte() throws BusinessException {
		
		/* Campo gia` popolato? */
		if (opzioniTipoEnte.size() > 0)
		{
			return  opzioniTipoEnte;	
		}
		else 
		/* Campo opzioniTipoEnte inizializzato ma vuoto, vado a recuperarlo dalla sessione
		   [ASSUNZIONE che sia stato gia` popolato in precedenza] */
		{
			Object oggettoInSessione = sessionMap.get(OPZIONI_TIPO_ENTE);
			
			if (!(oggettoInSessione instanceof Map))
				throw new BusinessException("Errore durante il recupero delle tipologie enti");
		
			Map<String,String> result = (Map<String,String>) oggettoInSessione;
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String,String> getMappaProvince() throws BusinessException {
		
		/* Campo gia` popolato? */
		if (opzioniProvincia.size() > 0)
		{
			return  opzioniProvincia;	
		}
		else 
		/* Campo opzioniProvincia inizializzato ma vuoto, vado a recuperarlo dalla sessione
		   [ASSUNZIONE che sia stato gia` popolato in precedenza] */
		{
			Object oggettoInSessione = sessionMap.get(OPZIONI_PROVINCIA);
			
			if (!(oggettoInSessione instanceof Map))
				throw new BusinessException("Errore durante il recupero delle province");
		
			Map<String,String> result = (Map<String,String>) oggettoInSessione;
			return result;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer,String> getMappaAmbiti() throws BusinessException {
		
		/* Campo gia` popolato? */
		if (opzioniAmbito.size() > 0)
		{
			return  opzioniAmbito;	
		}
		else 
		/* Campo opzioniProvincia inizializzato ma vuoto, vado a recuperarlo dalla sessione
		   [ASSUNZIONE che sia stato gia` popolato in precedenza] */
		{
			Object oggettoInSessione = sessionMap.get(OPZIONI_AMBITO);
			
			if (!(oggettoInSessione instanceof Map))
				throw new BusinessException("Errore durante il recupero degli ambiti");
		
			Map<Integer,String> result = (Map<Integer,String>) oggettoInSessione;
			return result;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String,String> getMappaRegioni() throws BusinessException {
		
		/* Campo gia` popolato? */
		if (opzioniRegione.size() > 0)
		{
			return  opzioniRegione;	
		}
		else 
		/* Campo opzioniRegione inizializzato  ma vuoto, vado a recuperarlo dalla sessione
		   [ASSUNZIONE che sia stato gia` popolato in precedenza] */
		{
			Object oggettoInSessione = sessionMap.get(OPZIONI_REGIONE);
			
			if (!(oggettoInSessione instanceof Map))
				throw new BusinessException("Errore durante il recupero delle regioni");
		
			Map<String,String> result = (Map<String,String>) oggettoInSessione;
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	public Map<Integer,String> getMappaSocieta() throws BusinessException {
		
		/* Campo gia` popolato? */
		if (opzioniRaggruppamentoSocietario.size() > 0)
		{
			return  opzioniRaggruppamentoSocietario;	
		}
		else 
		/* Campo opzioniRaggruppamentoSocietario inizializzato ma vuoto, vado a recuperarlo dalla sessione
		   [ASSUNZIONE che sia stato gia` popolato in precedenza] */
		{
			Object oggettoInSessione = sessionMap.get(OPZIONI_RAGGRUPPAMENTO_SOCIETARIO);
			
			if (!(oggettoInSessione instanceof Map))
				throw new BusinessException("Errore durante il recupero dei raggruppamenti societari");
		
			Map<Integer,String> result = (Map<Integer,String>) oggettoInSessione;
			return result;
		}
	}

}
