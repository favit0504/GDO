package it.equitalia.gdo.dao.oracle;

import it.equitalia.gdo.commons.utils.Costanti;
import it.equitalia.gdo.dao.EntitaVersionabileDAO;
import it.equitalia.gdo.dao.model.FiltroAmbito;
import it.equitalia.gdo.dao.model.FiltroEnte;
import it.equitalia.gdo.dao.model.FiltroProvincia;
import it.equitalia.gdo.dao.model.FiltroRegione;
import it.equitalia.gdo.dao.model.FiltroServizio;
import it.equitalia.gdo.dao.model.FiltroSocieta;
import it.equitalia.gdo.dao.model.FiltroTipologiaEnte;
import it.equitalia.gdo.dao.model.News;
import it.equitalia.gdo.dao.model.ValoreFiltroAmbito;
import it.equitalia.gdo.dao.model.ValoreFiltroEnte;
import it.equitalia.gdo.dao.model.ValoreFiltroProvincia;
import it.equitalia.gdo.dao.model.ValoreFiltroRegione;
import it.equitalia.gdo.dao.model.ValoreFiltroServizio;
import it.equitalia.gdo.dao.model.ValoreFiltroSocieta;
import it.equitalia.gdo.dao.model.ValoreFiltroTipologiaEnte;
import it.equitalia.gdo.dao.model.generic.AbstractFiltro;
import it.equitalia.gdo.dao.model.generic.AbstractFiltro.TIPO_FILTRO;
import it.equitalia.gdo.dao.services.interfaces.NewsDAOInterface;
import it.equitalia.gdo.dao.utils.ListUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class NewsOracleDAO extends EntitaVersionabileDAO<News> implements NewsDAOInterface {
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(NewsOracleDAO.class);		
	
	public NewsOracleDAO(EntityManager em) {
		this.entityManager = em;
	}
	
	public int eliminaBlobAllegato(Integer idBlobAllegato) {
		
		Query q = entityManager.createNativeQuery("UPDATE BLOB_ALLEGATO SET FILE_BLOB=EMPTY_BLOB() WHERE ID_BLOB_ALLEGATO=?");
		
		q.setParameter(1, idBlobAllegato);
		return q.executeUpdate();
		
	}
	
	public int impostaComeSvecchiata(Integer idNews) {
		
		Query q = entityManager.createNativeQuery("UPDATE NEWS SET SVECCHIATA=1 WHERE ID=?");
		
		q.setParameter(1, idNews);
		return q.executeUpdate();
		
	}
	
	public int rimuoviVisualizzazionePopUp() {
		
		Query q = entityManager.createNativeQuery("UPDATE NEWS SET FLG_POP_UP=0 WHERE FLG_POP_UP=1 AND STATO=1 AND VALIDA=1");
		return q.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> findByParameter(Map<String, Object> params) {
		
		String queryString = "select n from "+type.getName()+" n where n.valida = true ";		
		
		if(params.get(PARAM_ID_NEWS)!=null){
			queryString+="and n.id = :" + PARAM_ID_NEWS + " ";
		}
		if(params.get(PARAM_CODICE_NEWS)!=null){
			queryString+="and n.codice = :" + PARAM_CODICE_NEWS + " ";
		}
		if(params.get(PARAM_VALIDA)!=null){
			queryString+="and n.valida = :" + PARAM_VALIDA + " ";
		}
		if(params.get(PARAM_TITOLO)!=null){
			queryString+="and n.titolo like :" + PARAM_TITOLO + " ";
		}
		if(params.get(PARAM_TESTO)!=null){
			queryString+="and n.testo like :" + PARAM_TESTO + " ";
		}
		if(params.get(PARAM_STATO)!=null){
			queryString+="and n.stato = :" + PARAM_STATO + " ";
		}
		if(params.get(PARAM_OWNER)!=null){
			queryString+="and n.owner = :" + PARAM_OWNER + " ";
		}
		if(params.get(PARAM_DATA_CREAZIONE)!=null){
			queryString+="and n.dataCreazione = :" + PARAM_DATA_CREAZIONE + " ";
		}
		if(params.get(PARAM_OWNER_MODIFICA)!=null){
			queryString+="and n.ownerModifica = :" + PARAM_OWNER_MODIFICA + " ";
		}
		if(params.get(PARAM_DATA_MODIFICA)!=null){
			queryString+="and n.dataModifica = :" + PARAM_DATA_MODIFICA + " ";
		}
		if(params.get(PARAM_DATA_INIZIO_PUBBLICAZIONE)!=null && params.get(PARAM_DATA_FINE_PUBBLICAZIONE)!=null){
			queryString+="and (n.dataInizioPubblicazione >= :" + PARAM_DATA_INIZIO_PUBBLICAZIONE + ") ";
			queryString+="and (n.dataFinePubblicazione <= :" + PARAM_DATA_FINE_PUBBLICAZIONE + ") ";
		
		} else if(params.get(PARAM_DATA_INIZIO_PUBBLICAZIONE)!=null){
			queryString+="and (n.dataInizioPubblicazione >= :" + PARAM_DATA_INIZIO_PUBBLICAZIONE + " ) ";  
			
		} else if(params.get(PARAM_DATA_FINE_PUBBLICAZIONE)!=null){
			queryString+="and (n.dataFinePubblicazione <= :" + PARAM_DATA_FINE_PUBBLICAZIONE + ") ";  
			
		}
		if(params.get(PARAM_ENTE)!=null){
			queryString+="and n.ente = :" + PARAM_ENTE + " ";
		}
		if(params.get(PARAM_AGENTE)!=null){
			queryString+="and n.agente = :" + PARAM_AGENTE + " ";
		}
		if(params.get(PARAM_ALTRI_UTENTI)!=null){
			queryString+="and n.altriUtenti = :" + PARAM_ALTRI_UTENTI + " ";
		}
		if(params.get(PARAM_FLG_POP_UP)!=null){
			queryString+="and n.visualizzaPopUp = :" + PARAM_FLG_POP_UP + " ";
		}
		
		queryString +=" order by n.dataCreazione desc ";
		
		Query q = entityManager.createQuery(queryString);
		
		if(params.get(PARAM_ID_NEWS)!=null){
			q.setParameter(PARAM_ID_NEWS, Integer.parseInt(params.get(PARAM_ID_NEWS).toString()));
		}
		if(params.get(PARAM_CODICE_NEWS)!=null){
			q.setParameter(PARAM_CODICE_NEWS, params.get(PARAM_CODICE_NEWS));
		}
		if(params.get(PARAM_VALIDA)!=null){
			q.setParameter(PARAM_VALIDA, params.get(PARAM_VALIDA));
		}
		if(params.get(PARAM_TITOLO)!=null){
			q.setParameter(PARAM_TITOLO, "%" + params.get(PARAM_TITOLO) + "%");
		}
		if(params.get(PARAM_TESTO)!=null){
			q.setParameter(PARAM_TESTO, "%" + params.get(PARAM_TESTO) + "%");
		}
		if(params.get(PARAM_STATO)!=null){
			q.setParameter(PARAM_STATO, params.get(PARAM_STATO));
		}
		if(params.get(PARAM_OWNER)!=null){
			q.setParameter(PARAM_OWNER, params.get(PARAM_OWNER));
		}
		if(params.get(PARAM_DATA_CREAZIONE)!=null){
			q.setParameter(PARAM_DATA_CREAZIONE, params.get(PARAM_DATA_CREAZIONE));
		}
		if(params.get(PARAM_OWNER_MODIFICA)!=null){
			q.setParameter(PARAM_OWNER_MODIFICA, params.get(PARAM_OWNER_MODIFICA));
		}
		if(params.get(PARAM_DATA_MODIFICA)!=null){
			q.setParameter(PARAM_DATA_MODIFICA, params.get(PARAM_DATA_MODIFICA));
		}
		if(params.get(PARAM_DATA_INIZIO_PUBBLICAZIONE)!=null && params.get(PARAM_DATA_FINE_PUBBLICAZIONE)!=null){
			q.setParameter(PARAM_DATA_INIZIO_PUBBLICAZIONE, (Date)params.get(PARAM_DATA_INIZIO_PUBBLICAZIONE), TemporalType.DATE);
			q.setParameter(PARAM_DATA_FINE_PUBBLICAZIONE, (Date)params.get(PARAM_DATA_FINE_PUBBLICAZIONE), TemporalType.DATE);
			
		} else if(params.get(PARAM_DATA_INIZIO_PUBBLICAZIONE)!=null){
			q.setParameter(PARAM_DATA_INIZIO_PUBBLICAZIONE, (Date)params.get(PARAM_DATA_INIZIO_PUBBLICAZIONE), TemporalType.DATE);
			
		} else if(params.get(PARAM_DATA_FINE_PUBBLICAZIONE)!=null){
			q.setParameter(PARAM_DATA_FINE_PUBBLICAZIONE, (Date)params.get(PARAM_DATA_FINE_PUBBLICAZIONE), TemporalType.DATE);
			
		}
		if(params.get(PARAM_ENTE)!=null){
			q.setParameter(PARAM_ENTE, params.get(PARAM_ENTE));
		}
		if(params.get(PARAM_AGENTE)!=null){
			q.setParameter(PARAM_AGENTE, params.get(PARAM_AGENTE));
		}
		if(params.get(PARAM_ALTRI_UTENTI)!=null){
			q.setParameter(PARAM_ALTRI_UTENTI, params.get(PARAM_ALTRI_UTENTI));
		}
		if(params.get(PARAM_FLG_POP_UP)!=null){
			q.setParameter(PARAM_FLG_POP_UP, params.get(PARAM_FLG_POP_UP));
		}
		
		List<News> news= q.getResultList();
		return news;
		
	}

	

	
	/**
	 * Setto gli Id nei valoriFiltro e nei Filtri.
	 */
	  protected void setChildIds(News t) {
		  
		if (t.getAllegato() != null) {
			t.getAllegato().setId(getIdValue(t.getAllegato()));
			
			if (t.getAllegato().getBlob() != null) {
				t.getAllegato().getBlob().setId( getIdValue(t.getAllegato().getBlob())  );
			}
		}
		  
		if(t.getFiltri()!= null && t.getFiltri().size()>0) {
	  
			
			
			for (AbstractFiltro filtro : t.getFiltri()) {
				
				
				filtro.setId(getIdValue(filtro));
				
				TIPO_FILTRO tipoFiltro = TIPO_FILTRO.valueOf(filtro.getTipoFiltro());
				switch(tipoFiltro) {
							
//					case FiltroTipologia: 
//						ValoreFiltroTipologia valoreTipologia = ( (FiltroTipologia) filtro ).getValoreFiltroTipologia();
//						if (valoreTipologia != null)
//							valoreTipologia.setId( getIdValue(valoreTipologia) );
//						break;
//						
					case FiltroServizioEnte:
					case FiltroServizioAgente:
					case FiltroServizioAltriUtenti:
						List<ValoreFiltroServizio> listaValoriFiltro = ( (FiltroServizio) filtro ).getValoriFiltriServizio();					
						for (ValoreFiltroServizio valoreServizio : ListUtils.emptyIfNull(listaValoriFiltro) ) 
						{
							valoreServizio.setId( getIdValue(valoreServizio));	
						}													
						break;
						
					case FiltroServizioUtentiEsterni:
						List<ValoreFiltroServizio> listaValoriFiltro3 = ( (FiltroServizio) filtro ).getValoriFiltriServizio();					
						for (ValoreFiltroServizio valoreServizio : ListUtils.emptyIfNull(listaValoriFiltro3) ) 
						{
							valoreServizio.setId( getIdValue(valoreServizio));	
						}													
						break;
						
					case FiltroSocieta:
						List<ValoreFiltroSocieta> listaValoriFiltro2 = ( (FiltroSocieta) filtro ).getValoriFiltriSocieta();					
						for (ValoreFiltroSocieta valoreSocieta : ListUtils.emptyIfNull(listaValoriFiltro2) ) 
						{
							valoreSocieta.setId( getIdValue(valoreSocieta) );	
						}													
						break;
					
						
					case FiltroTipologiaEnte: 
						List<ValoreFiltroTipologiaEnte> valoriTipologiaEnte = ( (FiltroTipologiaEnte) filtro ).getValoriFiltroTipologiaEnte();
						for (ValoreFiltroTipologiaEnte valoreTipologiaEnte : ListUtils.emptyIfNull(valoriTipologiaEnte) ) 
						{
							valoreTipologiaEnte.setId( getIdValue(valoreTipologiaEnte) );	
						}	
						break;

					case FiltroRegioneEnte:
					case FiltroRegioneAgente:
						List<ValoreFiltroRegione> listaValoriRegione = ( (FiltroRegione) filtro ).getValoriFiltriRegione();					
						for (ValoreFiltroRegione valoreRegione : ListUtils.emptyIfNull(listaValoriRegione) ) 
						{
							valoreRegione.setId( getIdValue(valoreRegione) );	
						}													
						break;
						
					case FiltroProvinciaEnte:
					
						List<ValoreFiltroProvincia> listaValoriProvincia = ( (FiltroProvincia) filtro ).getValoriFiltriProvincia();					
						for (ValoreFiltroProvincia valoreProvincia : ListUtils.emptyIfNull(listaValoriProvincia) ) 
						{
							valoreProvincia.setId( getIdValue(valoreProvincia) );	
						}													
						break;
				 case FiltroAmbito:
						List<ValoreFiltroAmbito> valoriFiltroAmbito = ( (FiltroAmbito) filtro ).getvaloriFiltriAmbito();					
						for (ValoreFiltroAmbito valoreAmbito : ListUtils.emptyIfNull(valoriFiltroAmbito) ) 
						{
							valoreAmbito.setId( getIdValue(valoreAmbito) );	
						}													
						break;

					case FiltroEnte:
						ValoreFiltroEnte valoreEnte = ( (FiltroEnte) filtro ).getValoreFiltroEnte();
						if (valoreEnte != null)
							valoreEnte.setId( getIdValue(valoreEnte));
						break;

				}
				
			}
		}
		
		
	}

	@SuppressWarnings("unchecked")
	public List<News> findStoricoNews(Map<String, Object> params) {
		
		String queryString = "select n from "+type.getName()+" n where 1=1 ";		
		
		if(params.get(PARAM_ID_NEWS)!=null){
			queryString+="and n.id = :" + PARAM_ID_NEWS + " ";
		}
		if(params.get(PARAM_CODICE_NEWS)!=null){
			queryString+="and n.codice = :" + PARAM_CODICE_NEWS + " ";
		}
		if(params.get(PARAM_VALIDA)!=null){
			queryString+="and n.valida = :" + PARAM_VALIDA + " ";
		}
		if(params.get(PARAM_TITOLO)!=null){
			queryString+="and n.titolo like :" + PARAM_TITOLO + " ";
		}
		if(params.get(PARAM_TESTO)!=null){
			queryString+="and n.testo like :" + PARAM_TESTO + " ";
		}
		if(params.get(PARAM_STATO)!=null){
			queryString+="and n.stato = :" + PARAM_STATO + " ";
		}
		if(params.get(PARAM_OWNER)!=null){
			queryString+="and n.owner = :" + PARAM_OWNER + " ";
		}
		if(params.get(PARAM_DATA_CREAZIONE)!=null){
			queryString+="and n.dataCreazione = :" + PARAM_DATA_CREAZIONE + " ";
		}
		if(params.get(PARAM_OWNER_MODIFICA)!=null){
			queryString+="and n.ownerModifica = :" + PARAM_OWNER_MODIFICA + " ";
		}
		if(params.get(PARAM_DATA_MODIFICA)!=null){
			queryString+="and n.dataModifica = :" + PARAM_DATA_MODIFICA + " ";
		}
		if(params.get(PARAM_DATA_INIZIO_PUBBLICAZIONE)!=null){
			queryString+="and n.dataInizioPubblicazione = :" + PARAM_DATA_INIZIO_PUBBLICAZIONE + " ";
		}
		if(params.get(PARAM_DATA_FINE_PUBBLICAZIONE)!=null){
			queryString+="and n.dataFinePubblicazione = :" + PARAM_DATA_FINE_PUBBLICAZIONE + " ";
		}
		if(params.get(PARAM_ENTE)!=null){
			queryString+="and n.ente = :" + PARAM_ENTE + " ";
		}
		if(params.get(PARAM_AGENTE)!=null){
			queryString+="and n.agente = :" + PARAM_AGENTE + " ";
		}
		if(params.get(PARAM_ALTRI_UTENTI)!=null){
			queryString+="and n.altriUtenti = :" + PARAM_ALTRI_UTENTI + " ";
		}
		queryString+=" order by n.id desc ";
		
		Query q = entityManager.createQuery(queryString);
		
		if(params.get(PARAM_ID_NEWS)!=null){
			q.setParameter(PARAM_ID_NEWS, Integer.parseInt(params.get(PARAM_ID_NEWS).toString()));
		}
		if(params.get(PARAM_CODICE_NEWS)!=null){
			q.setParameter(PARAM_CODICE_NEWS, params.get(PARAM_CODICE_NEWS));
		}
		if(params.get(PARAM_VALIDA)!=null){
			q.setParameter(PARAM_VALIDA, params.get(PARAM_VALIDA));
		}
		if(params.get(PARAM_TITOLO)!=null){
			q.setParameter(PARAM_TITOLO, "%" + params.get(PARAM_TITOLO) + "%");
		}
		if(params.get(PARAM_TESTO)!=null){
			q.setParameter(PARAM_TESTO, "%" + params.get(PARAM_TESTO) + "%");
		}
		if(params.get(PARAM_STATO)!=null){
			q.setParameter(PARAM_STATO, params.get(PARAM_STATO));
		}
		if(params.get(PARAM_OWNER)!=null){
			q.setParameter(PARAM_OWNER, params.get(PARAM_OWNER));
		}
		if(params.get(PARAM_DATA_CREAZIONE)!=null){
			q.setParameter(PARAM_DATA_CREAZIONE, params.get(PARAM_DATA_CREAZIONE));
		}
		if(params.get(PARAM_OWNER_MODIFICA)!=null){
			q.setParameter(PARAM_OWNER_MODIFICA, params.get(PARAM_OWNER_MODIFICA));
		}
		if(params.get(PARAM_DATA_MODIFICA)!=null){
			q.setParameter(PARAM_DATA_MODIFICA, params.get(PARAM_DATA_MODIFICA));
		}
		if(params.get(PARAM_DATA_INIZIO_PUBBLICAZIONE)!=null){
			q.setParameter(PARAM_DATA_INIZIO_PUBBLICAZIONE, params.get(PARAM_DATA_INIZIO_PUBBLICAZIONE));
		}
		if(params.get(PARAM_DATA_FINE_PUBBLICAZIONE)!=null){
			q.setParameter(PARAM_DATA_FINE_PUBBLICAZIONE, params.get(PARAM_DATA_FINE_PUBBLICAZIONE));
		}
		if(params.get(PARAM_ENTE)!=null){
			q.setParameter(PARAM_ENTE, params.get(PARAM_ENTE));
		}
		if(params.get(PARAM_AGENTE)!=null){
			q.setParameter(PARAM_AGENTE, params.get(PARAM_AGENTE));
		}
		if(params.get(PARAM_ALTRI_UTENTI)!=null){
			queryString+="and n.altriUtenti = :" + PARAM_ALTRI_UTENTI + " ";
		}
		
		List<News> news= q.getResultList();
		return news;
	}
	
	@SuppressWarnings("unchecked")
	public List<News> findNewsPerTipologiaUtente(Map<String, Object> params) {
		String queryString = "select n from "+type.getName()+" n where n.valida = true and n.stato = 1 ";	
		
		if(params.get(PARAM_ENTE)!=null){
			queryString+="and n.ente = :" + PARAM_ENTE + " ";
		}
		if(params.get(PARAM_AGENTE)!=null){
			queryString+="and n.agente = :" + PARAM_AGENTE + " ";
		}
		if(params.get(PARAM_ALTRI_UTENTI)!=null){
			queryString+=" and n.altriUtenti = :" + PARAM_ALTRI_UTENTI + " ";
		}
		
		queryString+=" AND (n.dataInizioPubblicazione IS NULL OR current_date >= n.dataInizioPubblicazione )";
				
		queryString+=" AND (n.dataFinePubblicazione IS NULL OR current_date <= n.dataFinePubblicazione) ";
		
		queryString+=" order by n.dataInizioPubblicazione DESC ";
		
		Query q = entityManager.createQuery(queryString);
		
		if(params.get(PARAM_ENTE)!=null){
			q.setParameter(PARAM_ENTE, params.get(PARAM_ENTE));
		}
		if(params.get(PARAM_AGENTE)!=null){
			q.setParameter(PARAM_AGENTE, params.get(PARAM_AGENTE));
		}
		if(params.get(PARAM_ALTRI_UTENTI)!=null){
			q.setParameter(PARAM_ALTRI_UTENTI, params.get(PARAM_ALTRI_UTENTI));
		}
		List<News> news= q.getResultList();
		return news;
	}
	
	@SuppressWarnings("unchecked")
	public List<News> findNewsDaSvecchiareSenzaAllegato() {
		String queryString = "select n " +
							 "from " +type.getName() + " n " +
							 "left join fetch n.allegato " +
							 "where n.allegato is null and n.valida=false and n.svecchiata=false";
		
		Query q = entityManager.createQuery(queryString);
		
		List<News> newsDaSvecchiare = q.getResultList();
		
		return newsDaSvecchiare;
	}
	
	@SuppressWarnings("unchecked")
	public List<News> findNewsPopUp() {
		String queryString = "select n.* " +
		 "from news n " +
		 "where n.flg_pop_up = 1 "+
		 "and n.stato = "+ Costanti.OPZIONI_STATO.ATTIVA.getValue()+" "+
		 "and n.valida = 1";
		Query q = entityManager.createNativeQuery(queryString, News.class);
		List<News> newsPopUp = q.getResultList();	
		return newsPopUp;

	}
	
	
	@SuppressWarnings("unchecked")
	public List<News> findBlobPerSvecchiamento() {
		String queryString = "select n " +
							 "from " +type.getName() + " n " +
							 "join fetch n.allegato.blob " +
							 "where n.allegato is not null and n.valida=false and n.svecchiata=false";
		
		Query q = entityManager.createQuery(queryString);
		
		List<News> newsDaSvecchiare = q.getResultList();
		
		return newsDaSvecchiare;
	}

	public News updateSenzaVersionare(final News n) {
		return entityManager.merge(n);
	}


}