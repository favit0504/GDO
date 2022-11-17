package it.equitalia.gdo.ejb.services;


import it.equitalia.gdo.commons.ejb.impl.ValutazioneUtenteServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.ValutazioneUtenteServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.Costanti.RaggruppamentoSocietario;
import it.equitalia.gdo.commons.utils.Costanti.TipologiaUtente;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.FiltroEnteBean;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.GenericAbstractDao;
import it.equitalia.gdo.dao.model.generic.AbstractFiltro;
import it.equitalia.gdo.dao.model.generic.AbstractFiltro.TIPO_FILTRO;
import it.equitalia.gdo.dao.model.generic.GenericType;
import it.equitalia.gdo.ejb.aspects.ProfilingInterceptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
@SuppressWarnings("unused")
@Stateless(name="ejb/ValutazioneUtenteService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ValutazioneUtenteServiceBean extends ServiceWithDAOFactory<GenericAbstractDao<GenericType>> implements ValutazioneUtenteServiceLocal, ValutazioneUtenteServiceRemote {
	
	private static final Logger logger = Logger.getLogger(ValutazioneUtenteServiceBean.class);
	
	@Resource
	private SessionContext ctx;
	
	/**
	 * GESTIONE VALUTAZIONE FILTRI NEWS - INIZIO
	 * 
	 */
	
	
	/**
	 * Metodo che chiama in cascata tutte le valutazioni
	 * sui singoli tipi di filtro news
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public boolean valutaUtente(UtenteBean utente, NewsBean newsBean) throws BusinessException {
		
		boolean visibile = true;
		
		if(newsBean != null){
			
			visibile = visibile && valutaTipologiaUtente(utente, newsBean); 
//			visibile = visibile && valutaTipologiaAltriUtenti(utente, newsBean);
			visibile = visibile && valutaServizi(utente, newsBean);			
			visibile = visibile && valutaProvince(utente, newsBean);
			visibile = visibile && valutaRegioni(utente, newsBean);						
			visibile = visibile && valutaSocieta(utente, newsBean);
			visibile = visibile && valutaEnte(utente, newsBean);
			visibile = visibile && valutaTipologiaEnte(utente, newsBean);
			visibile = visibile && valutaAmbiti(utente,newsBean);
			
		}
		
		return visibile;

	}

	public boolean valutaAmbiti(UtenteBean utente, NewsBean newsBean) {
		if (utente.getTipologiaUtente() == TipologiaUtente.EQUITALIA
				|| utente.getTipologiaUtente() == TipologiaUtente.ENTE)
			return true;
		if (newsBean.getFiltroAmbito() != null) {
			if (newsBean.getFiltroAmbito().getValori() != null) {
				if (utente.getAmbiti() == null)
					return false;
				for (Integer ambito : newsBean.getFiltroAmbito().getValori())
					if (utente.getAmbiti().contains(ambito))
						return true;
				return false;
			}

		}
		return true;
	}
	
	public boolean valutaAmbiti(UtenteBean utente, DocumentoBean documento) {
		if (utente.getTipologiaUtente() == TipologiaUtente.EQUITALIA
				|| utente.getTipologiaUtente() == TipologiaUtente.ENTE)
			return true;
		if (documento.getFiltroAmbito() != null) {
			if (documento.getFiltroAmbito().getValori() != null) {
				if (utente.getAmbiti() == null)
					return false;
				for (Integer ambito : documento.getFiltroAmbito().getValori())
					if (utente.getAmbiti().contains(ambito))
						return true;
				return false;
			}

		}
		return true;
	}

	public boolean valutaTipologiaEnte(UtenteBean utente, NewsBean newsBean) {
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA || utente.getTipologiaUtente()==TipologiaUtente.AGENTE)
			return true;
		AbstractFiltro.TIPO_FILTRO tipo = TIPO_FILTRO.FiltroTipologiaEnte;
		return valutaFiltroStringa(utente, newsBean, tipo);			
	}
	
	public boolean valutaTipologiaUtente(UtenteBean utente, NewsBean newsBean) throws BusinessException {
		if (utente.getTipologiaUtente() == null)
			throw new BusinessException("Utente non valido");	
		
		if(utente.getTipologiaUtente()!=TipologiaUtente.EQUITALIA && utente.getTipologiaUtente()!=TipologiaUtente.AGENTE && utente.getTipologiaUtente()!=TipologiaUtente.ENTE)
			return newsBean.getAltriUtenti();
		else		
				switch (utente.getTipologiaUtente()) {				
					case EQUITALIA:
						return true;
					case ENTE:
						return newsBean.getEnte();
					case AGENTE:
						return newsBean.getAgente();
					default:
						throw new BusinessException("Utente non valido");
		}
	}
	
	
	
//	public boolean valutaTipologiaAltriUtenti(UtenteBean utente, NewsBean newsBean) throws BusinessException {
//		if (utente.getTipologiaUtente() == null)
//			throw new BusinessException("Utente non valido");	
//		if(utente.getTipologiaUtente()!=TipologiaUtente.EQUITALIA && utente.getTipologiaUtente()!=TipologiaUtente.AGENTE && utente.getTipologiaUtente()!=TipologiaUtente.ENTE)
//			newsBean.getAltriUtenti();
//		
//		return false;
//	}
	
	public boolean valutaEnte(UtenteBean utente, NewsBean newsBean) {
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA || utente.getTipologiaUtente()==TipologiaUtente.AGENTE)
			return true;
		FiltroEnteBean feb = newsBean.getFiltroEnte();
		
		if (feb == null)
			return true;
		
		boolean codiceEnte = true;
		boolean codiceUfficio = true;
		boolean tipoUfficio = true;
		
		if (feb.getCodiceEnte() != null)
			codiceEnte = feb.getCodiceEnte().equals(utente.getEnte().getCodiceEnte());
		if (feb.getCodiceUfficioEnte() != null)
			codiceUfficio = feb.getCodiceUfficioEnte().equals(utente.getEnte().getCodiceUfficio());
		if (feb.getTipoUfficioEnte() != null)
			tipoUfficio = feb.getTipoUfficioEnte().equals(utente.getEnte().getTipoUfficio());
		
		if(codiceEnte && codiceUfficio && tipoUfficio)
			return true;
		return false;
	}

	public boolean valutaSocieta(UtenteBean utente, NewsBean newsBean) {
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA || utente.getTipologiaUtente()==TipologiaUtente.ENTE)
			return true;
		
		if (newsBean.getFiltroSocieta() == null)
			return true;
		
		List<Integer> societaNews = newsBean.getFiltroSocieta().getValori();
		
		if (societaNews == null || societaNews.size() == 0)
			return true;
		
		List<Integer> societaUtente = new ArrayList<Integer>();
		if(utente.getSocieta()!=null)
			for(RaggruppamentoSocietario r:utente.getSocieta())
				societaUtente.add(r.getCodice());

		if (societaUtente != null)
		{
			for (Integer singolaSocietaUtente : societaUtente)
				if (societaNews.contains(singolaSocietaUtente))
					return true;
		}
		return false;
		
	}

	public boolean valutaRegioni(UtenteBean utente, NewsBean newsBean) {
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA)
			return true;
		AbstractFiltro.TIPO_FILTRO tipo = utente.getTipologiaUtente()==TipologiaUtente.AGENTE?TIPO_FILTRO.FiltroRegioneAgente:TIPO_FILTRO.FiltroRegioneEnte;
		return valutaFiltroStringa(utente, newsBean, tipo);
		
	}

	private boolean valutaFiltroStringa(UtenteBean utente, NewsBean newsBean, AbstractFiltro.TIPO_FILTRO tipo) {
		Set<String> valori = popolaValori(tipo, newsBean);
		List<String> valoriUtente=popolaValoriUtente(tipo, utente);
		return valutaValoriFiltro(valori, valoriUtente);
	}
	
	private boolean valutaValoriFiltro(Set<String> serviziFiltro, List<String> serviziUtente) {
		if(serviziFiltro == null || serviziFiltro.size()==0)
			return true;
		if (serviziUtente != null)
		{
			for (String servizioUtente : serviziUtente)
				if (serviziFiltro.contains(servizioUtente))
					return true;
		}
		return false;
	}
	
	private List<String> popolaValoriUtente(AbstractFiltro.TIPO_FILTRO tipoFiltro, UtenteBean utente){
		switch (tipoFiltro) {
		
			case FiltroEnte:
				return null;
			/*case FiltroProvinciaAgente:
				return utente.getProvince();*/
			case FiltroProvinciaEnte:
				return utente.getProvince();
			case FiltroRegioneAgente:
				return utente.getRegioni();
			case FiltroRegioneEnte:
				return utente.getRegioni();
			case FiltroServizioAgente:
				return utente.getServizi();
			case FiltroServizioEnte:
				return utente.getServizi();	
			case FiltroServizioAltriUtenti:
				return utente.getServizi();	
			case FiltroTipologiaEnte:
				List<String> tipologiaEnte = new ArrayList<String>();
				tipologiaEnte.add(utente.getTipologiaEnte());
				return tipologiaEnte;
			
				


		default:
			break;
		}
		
		return null;
	}
	
	private Set<String> popolaValori(AbstractFiltro.TIPO_FILTRO tipoFiltro, NewsBean bean) {
		Set<String> valori = new HashSet<String>();
		List<String> filtri = null;
		switch (tipoFiltro) {
		case FiltroEnte:
			
			
			break;	
		case FiltroProvinciaEnte:
			if(bean.getFiltroProvinciaEnte()!=null)
				filtri = bean.getFiltroProvinciaEnte().getValori();
			break;
		case FiltroRegioneAgente:
			if(bean.getFiltroRegioneAgente()!=null)
				filtri = bean.getFiltroRegioneAgente().getValori();
			break;
		case FiltroRegioneEnte:
			if(bean.getFiltroRegioneEnte()!=null)
				filtri = bean.getFiltroRegioneEnte().getValori();
			break;
		case FiltroServizioAgente:
			if(bean.getFiltroServizioAgente()!=null)
				filtri = bean.getFiltroServizioAgente().getValori();
			break;
		case FiltroServizioEnte:
			if(bean.getFiltroServizioEnte()!=null)
				filtri = bean.getFiltroServizioEnte().getValori();
			break;		
		case FiltroServizioAltriUtenti:
			if(bean.getFiltroServizioAltriUtenti()!=null)
				filtri = bean.getFiltroServizioAltriUtenti().getValori();
			break;
		case FiltroTipologiaEnte:
			if(bean.getFiltroTipologiaEnte()!=null)
				filtri = bean.getFiltroTipologiaEnte().getValori();
			
			break;
	
	}
		if(filtri!=null)
			valori.addAll(filtri);
		return valori;
	}

	@Interceptors({ProfilingInterceptor.class})
	public boolean valutaServizi(UtenteBean utente, NewsBean newsBean) {
		AbstractFiltro.TIPO_FILTRO tipo = null;
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA)
			return true;
		if(utente.getTipologiaUtente()==TipologiaUtente.AGENTE){
			tipo = TIPO_FILTRO.FiltroServizioAgente;
		}else if(utente.getTipologiaUtente()==TipologiaUtente.ENTE){
			tipo = TIPO_FILTRO.FiltroServizioEnte;
		}else if(utente.getTipologiaUtente()!=TipologiaUtente.EQUITALIA && utente.getTipologiaUtente()!=TipologiaUtente.AGENTE && utente.getTipologiaUtente()!=TipologiaUtente.ENTE) {
			tipo = TIPO_FILTRO.FiltroServizioAltriUtenti;
		}
		return valutaFiltroStringa(utente, newsBean, tipo);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public boolean valutaProvince(UtenteBean utente, NewsBean newsBean){
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA || utente.getTipologiaUtente()==TipologiaUtente.AGENTE)
			return true;
		return valutaFiltroStringa(utente, newsBean, TIPO_FILTRO.FiltroProvinciaEnte);
	}
	
	/**
	 * GESTIONE VALUTAZIONE FILTRI NEWS - FINE
	 * 
	 * 
	 */
	
	
	
	/**
	 * GESTIONE VALUTAZIONE FILTRI DOCUMENTI - INIZIO
	 * 
	 * 
	 */
	
	/**
	 * Metodo che chiama in cascata tutte le valutazioni
	 * sui singoli tipi di filtro documenti
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public boolean valutaUtente(UtenteBean utente, DocumentoBean documentoBean) throws BusinessException {
		
		boolean visibile = true;		
		if(documentoBean != null){
			
			visibile = visibile && valutaTipologiaUtente(utente, documentoBean); 			
			visibile = visibile && valutaServizi(utente, documentoBean);			
			visibile = visibile && valutaProvince(utente, documentoBean);
			visibile = visibile && valutaRegioni(utente, documentoBean);						
			visibile = visibile && valutaSocieta(utente, documentoBean);
			visibile = visibile && valutaEnte(utente, documentoBean);
			visibile = visibile && valutaTipologiaEnte(utente, documentoBean);
			visibile = visibile && valutaAmbiti(utente,documentoBean);
			
		}
		
		return visibile;


	}
	
	public boolean valutaTipologiaEnte(UtenteBean utente, DocumentoBean documentoBean) {
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA || utente.getTipologiaUtente()==TipologiaUtente.AGENTE)
			return true;
		AbstractFiltro.TIPO_FILTRO tipo = TIPO_FILTRO.FiltroTipologiaEnte;
		return valutaFiltroStringa(utente, documentoBean, tipo);			
	}
	
	public boolean valutaTipologiaUtente(UtenteBean utente, DocumentoBean documentoBean) throws BusinessException {
		
		if (utente.getTipologiaUtente() == null)
			throw new BusinessException("Utente non valido");
		
		switch (utente.getTipologiaUtente()) {				
			case EQUITALIA:
				return true;
			case ENTE:
				return documentoBean.getEnte();
			case AGENTE:
				return documentoBean.getAgente();
			default:
				throw new BusinessException("Utente non valido");
		}
		
	}
	
	public boolean valutaEnte(UtenteBean utente, DocumentoBean documentoBean) {
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA || utente.getTipologiaUtente()==TipologiaUtente.AGENTE)
			return true;
		FiltroEnteBean feb = documentoBean.getFiltroEnte();
		
		if (feb == null)
			return true;
		
		boolean codiceEnte = true;
		boolean codiceUfficio = true;
		boolean tipoUfficio = true;
		
		if (feb.getCodiceEnte() != null)
			codiceEnte = feb.getCodiceEnte().equals(utente.getEnte().getCodiceEnte());
		if (feb.getCodiceUfficioEnte() != null)
			codiceUfficio = feb.getCodiceUfficioEnte().equals(utente.getEnte().getCodiceUfficio());
		if (feb.getTipoUfficioEnte() != null)
			tipoUfficio = feb.getTipoUfficioEnte().equals(utente.getEnte().getTipoUfficio());
		
		if(codiceEnte && codiceUfficio && tipoUfficio)
			return true;
		return false;
	}
	
	public boolean valutaSocieta(UtenteBean utente, DocumentoBean documentoBean) {
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA || utente.getTipologiaUtente()==TipologiaUtente.ENTE)
			return true;
		
		if (documentoBean.getFiltroSocieta() == null)
			return true;
		
		List<Integer> societaDocumento = documentoBean.getFiltroSocieta().getValori();
		
		if (societaDocumento == null || societaDocumento.size() == 0)
			return true;
		
		List<Integer> societaUtente = new ArrayList<Integer>();
		if(utente.getSocieta()!=null)
			for(RaggruppamentoSocietario r:utente.getSocieta())
				societaUtente.add(r.getCodice());

		if (societaUtente != null)
		{
			for (Integer singolaSocietaUtente : societaUtente)
				if (societaDocumento.contains(singolaSocietaUtente))
					return true;
		}
		return false;
		
	}
	
	public boolean valutaRegioni(UtenteBean utente, DocumentoBean documentoBean) {
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA)
			return true;
		AbstractFiltro.TIPO_FILTRO tipo = utente.getTipologiaUtente()==TipologiaUtente.AGENTE?TIPO_FILTRO.FiltroRegioneAgente:TIPO_FILTRO.FiltroRegioneEnte;
		return valutaFiltroStringa(utente, documentoBean, tipo);
		
	}
	
	private boolean valutaFiltroStringa(UtenteBean utente, DocumentoBean documentoBean, AbstractFiltro.TIPO_FILTRO tipo) {
		Set<String> valori = popolaValori(tipo, documentoBean);
		List<String> valoriUtente=popolaValoriUtente(tipo, utente);
		return valutaValoriFiltro(valori, valoriUtente);
	}
	
	private Set<String> popolaValori(AbstractFiltro.TIPO_FILTRO tipoFiltro, DocumentoBean bean) {
		Set<String> valori = new HashSet<String>();
		List<String> filtri = null;
		switch (tipoFiltro) {
		case FiltroEnte:
			
			
			break;
	
		case FiltroProvinciaEnte:
			if(bean.getFiltroProvinciaEnte()!=null)
				filtri = bean.getFiltroProvinciaEnte().getValori();
			break;
		case FiltroRegioneAgente:
			if(bean.getFiltroRegioneAgente()!=null)
				filtri = bean.getFiltroRegioneAgente().getValori();
			break;
		case FiltroRegioneEnte:
			if(bean.getFiltroRegioneEnte()!=null)
				filtri = bean.getFiltroRegioneEnte().getValori();
			break;
		case FiltroServizioAgente:
			if(bean.getFiltroServizioAgente()!=null)
				filtri = bean.getFiltroServizioAgente().getValori();
			break;
		case FiltroServizioEnte:
			if(bean.getFiltroServizioEnte()!=null)
				filtri = bean.getFiltroServizioEnte().getValori();
			break;			
		case FiltroTipologiaEnte:
			if(bean.getFiltroTipologiaEnte()!=null)
				filtri = bean.getFiltroTipologiaEnte().getValori();
			
			break;
	
	}
		if(filtri!=null)
			valori.addAll(filtri);
		return valori;
	}
	
	@Interceptors({ProfilingInterceptor.class})
	public boolean valutaServizi(UtenteBean utente, DocumentoBean documentoBean) {
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA)
			return true;
		if(utente.getTipologiaUtente()!=TipologiaUtente.EQUITALIA && utente.getTipologiaUtente()!=TipologiaUtente.AGENTE && utente.getTipologiaUtente()!=TipologiaUtente.ENTE)
			return true;
		AbstractFiltro.TIPO_FILTRO tipo = utente.getTipologiaUtente()==TipologiaUtente.AGENTE?TIPO_FILTRO.FiltroServizioAgente:TIPO_FILTRO.FiltroServizioEnte;
		return valutaFiltroStringa(utente, documentoBean, tipo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public boolean valutaProvince(UtenteBean utente, DocumentoBean documentoBean){
		if(utente.getTipologiaUtente()==TipologiaUtente.EQUITALIA || utente.getTipologiaUtente()==TipologiaUtente.AGENTE)
			return true;
		return valutaFiltroStringa(utente, documentoBean, TIPO_FILTRO.FiltroProvinciaEnte);
	}
	
	/**
	 * GESTIONE VALUTAZIONE FILTRI DOCUMENTI - FINE
	 * 
	 * 
	 */
	
	
	

	@Override
	protected GenericAbstractDao<GenericType> getSpecificDAO(
			AbstractDAOFactory adf) {		
		return null;
	}




}