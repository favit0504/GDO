package it.equitalia.gdo.dao.oracle;

import it.equitalia.gdo.dao.EntitaVersionabileDAO;
import it.equitalia.gdo.dao.model.Documento;
import it.equitalia.gdo.dao.model.FiltroAmbito;
import it.equitalia.gdo.dao.model.FiltroEnte;
import it.equitalia.gdo.dao.model.FiltroProvincia;
import it.equitalia.gdo.dao.model.FiltroRegione;
import it.equitalia.gdo.dao.model.FiltroServizio;
import it.equitalia.gdo.dao.model.FiltroSocieta;
import it.equitalia.gdo.dao.model.FiltroTipologiaEnte;
import it.equitalia.gdo.dao.model.ValoreFiltroAmbito;
import it.equitalia.gdo.dao.model.ValoreFiltroEnte;
import it.equitalia.gdo.dao.model.ValoreFiltroProvincia;
import it.equitalia.gdo.dao.model.ValoreFiltroRegione;
import it.equitalia.gdo.dao.model.ValoreFiltroServizio;
import it.equitalia.gdo.dao.model.ValoreFiltroSocieta;
import it.equitalia.gdo.dao.model.ValoreFiltroTipologiaEnte;
import it.equitalia.gdo.dao.model.generic.AbstractFiltro;
import it.equitalia.gdo.dao.model.generic.AbstractFiltro.TIPO_FILTRO;
import it.equitalia.gdo.dao.services.interfaces.DocumentoDAOInterface;
import it.equitalia.gdo.dao.utils.ListUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class DocumentoOracleDAO extends EntitaVersionabileDAO<Documento> implements DocumentoDAOInterface {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(DocumentoOracleDAO.class);

	
	public DocumentoOracleDAO(EntityManager em) {
		this.entityManager = em;
	}
	
	public int eliminaBlobDocumento(Integer idBlobDocumento) {
		
		Query q = entityManager.createNativeQuery("UPDATE BLOB_DOCUMENTO SET FILE_BLOB=EMPTY_BLOB() WHERE ID_BLOB_DOCUMENTO=?");
		
		q.setParameter(1, idBlobDocumento);
		return q.executeUpdate();
		
	}
	
	public int impostaComeSvecchiato(Integer idDocumento) {
		
		Query q = entityManager.createNativeQuery("UPDATE DOCUMENTO SET SVECCHIATO=1 WHERE ID=?");
		
		q.setParameter(1, idDocumento);
		return q.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Documento> findByParameter(Map<String, Object> params) {

		String queryString = "select d from Documento d join fetch d.sezione where d.valida = true ";

		if(params.get(PARAM_ID_DOCUMENTO)!=null){
			queryString+="and d.id = :" + PARAM_ID_DOCUMENTO + " ";
		}
		if(params.get(PARAM_CODICE_DOCUMENTO)!=null){
			queryString+="and d.codice = :" + PARAM_CODICE_DOCUMENTO + " ";
		}
		if(params.get(PARAM_TITOLO)!=null){
			queryString+="and d.titolo like :" + PARAM_TITOLO + " ";
		}
		if(params.get(PARAM_DESCRIZIONE)!=null){
			queryString+="and d.descrizione like :" + PARAM_DESCRIZIONE + " ";
		}
		if(params.get(PARAM_STATO)!=null){
			queryString+="and d.stato = :" + PARAM_STATO + " ";
		}
		if(params.get(PARAM_DATA_CREAZIONE)!=null){
			queryString+="and d.dataCreazione = :" + PARAM_DATA_CREAZIONE + " ";
		}
		if(params.get(PARAM_OWNER_MODIFICA)!=null){
			queryString+="and d.ownerModifica = :" + PARAM_OWNER_MODIFICA + " ";
		}
		if(params.get(PARAM_DATA_MODIFICA)!=null){
			queryString+="and d.dataModifica = :" + PARAM_DATA_MODIFICA + " ";
		}
		if(params.get(PARAM_DATA_INIZIO_VALIDITA)!=null){
			queryString+="and d.dataInizioPubblicazione = :" + PARAM_DATA_INIZIO_VALIDITA + " ";
		}
		if(params.get(PARAM_DATA_FINE_VALIDITA)!=null){
			queryString+="and d.dataFinePubblicazione = :" + PARAM_DATA_FINE_VALIDITA + " ";
		}
		if(params.get(PARAM_TITOLO_SEZIONE)!=null){
			queryString+="and d.sezione.titolo like :" + PARAM_TITOLO_SEZIONE + " ";
		}
		if(params.get(PARAM_ENTE)!=null){
			queryString+="and d.ente = :" + PARAM_ENTE + " ";
		}
		if(params.get(PARAM_AGENTE)!=null){
			queryString+="and d.agente = :" + PARAM_AGENTE + " ";
		}

		queryString+=" order by d.dataCreazione desc ";
		
		Query q = entityManager.createQuery(queryString);

		if(params.get(PARAM_ID_DOCUMENTO)!=null){
			q.setParameter(PARAM_ID_DOCUMENTO, Integer.parseInt(params.get(PARAM_ID_DOCUMENTO).toString()));
		}
		if(params.get(PARAM_CODICE_DOCUMENTO)!=null){
			q.setParameter(PARAM_CODICE_DOCUMENTO, params.get(PARAM_CODICE_DOCUMENTO));
		}
		if(params.get(PARAM_TITOLO)!=null){
			q.setParameter(PARAM_TITOLO, "%" + params.get(PARAM_TITOLO) + "%");
		}
		if(params.get(PARAM_DESCRIZIONE)!=null){
			q.setParameter(PARAM_DESCRIZIONE, "%" + params.get(PARAM_DESCRIZIONE) + "%");
		}
		if(params.get(PARAM_STATO)!=null){
			q.setParameter(PARAM_STATO, params.get(PARAM_STATO));
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
		if(params.get(PARAM_DATA_INIZIO_VALIDITA)!=null){
			q.setParameter(PARAM_DATA_INIZIO_VALIDITA, params.get(PARAM_DATA_INIZIO_VALIDITA));
		}
		if(params.get(PARAM_DATA_FINE_VALIDITA)!=null){
			q.setParameter(PARAM_DATA_FINE_VALIDITA, params.get(PARAM_DATA_FINE_VALIDITA));
		}
		if(params.get(PARAM_TITOLO_SEZIONE)!=null){
			q.setParameter(PARAM_TITOLO_SEZIONE, "%" + params.get(PARAM_TITOLO_SEZIONE) + "%");
		}
		if(params.get(PARAM_ENTE)!=null){
			q.setParameter(PARAM_ENTE, params.get(PARAM_ENTE));
		}
		if(params.get(PARAM_AGENTE)!=null){
			q.setParameter(PARAM_AGENTE, params.get(PARAM_AGENTE));
		}

		return q.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> findStoricoDocumento(Map<String, Object> params) {
		
		String queryString = "select d from "+type.getName()+" d where 1=1 ";		
		
		if(params.get(PARAM_CODICE_DOCUMENTO)!=null){
			queryString+="and d.codice = :" + PARAM_CODICE_DOCUMENTO + " ";
		}
		
		queryString+=" order by d.id desc ";
		
		Query q = entityManager.createQuery(queryString);
		
		if(params.get(PARAM_CODICE_DOCUMENTO)!=null){
			q.setParameter(PARAM_CODICE_DOCUMENTO, params.get(PARAM_CODICE_DOCUMENTO));
		}
		
		List<Documento> documenti= q.getResultList();
		
		return documenti;
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> findDocumentiPerTipologiaUtente(Map<String, Object> params) {
		String queryString = "select d from "+type.getName()+" d join fetch d.sezione where d.valida = true and d.stato = 1 and d.sezione.stato = 1";
		
		if(params.get(PARAM_ENTE)!=null){
			queryString+="and d.ente = :" + PARAM_ENTE + " ";
		}
		if(params.get(PARAM_AGENTE)!=null){
			queryString+="and d.agente = :" + PARAM_AGENTE + " ";
		}
		
		Query q = entityManager.createQuery(queryString);
		
		if(params.get(PARAM_ENTE)!=null){
			q.setParameter(PARAM_ENTE, params.get(PARAM_ENTE));
		}
		if(params.get(PARAM_AGENTE)!=null){
			q.setParameter(PARAM_AGENTE, params.get(PARAM_AGENTE));
		}
		
		List<Documento> documenti = q.getResultList();
		
		return documenti;
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> findBlobPerSvecchiamento() {
//		String queryString = "select b.ID_BLOB_DOCUMENTO, b.FILE_BLOB from DOCUMENTO d join BLOB_DOCUMENTO b on d.ID_BLOB=b.ID_BLOB_DOCUMENTO " +
//							 "where d.VALIDA=0 and d.SVECCHIATO=0";
		
		String queryString = "select d from " +type.getName() + " d join fetch d.blob where d.valida=false and d.svecchiato=false";
		
		Query q = entityManager.createQuery(queryString);
		
		List<Documento> docsDaSvecchiare = q.getResultList();
		
		return docsDaSvecchiare;
	}

	/**
	 * Setto gli Id nei valoriFiltro e nei Filtri.
	 */
	protected void setChildIds(Documento d) {

		if (d.getBlob() != null) {
			d.getBlob().setId(getIdValue(d.getBlob()));
			
		}

		if(d.getFiltri()!= null && d.getFiltri().size()>0) {

			for (AbstractFiltro filtro : d.getFiltri()) {

				filtro.setId(getIdValue(filtro));

				TIPO_FILTRO tipoFiltro = TIPO_FILTRO.valueOf(filtro.getTipoFiltro());
				switch(tipoFiltro) {

//					case FiltroTipologia: 
//						ValoreFiltroTipologia valoreTipologia = ( (FiltroTipologia) filtro ).getValoreFiltroTipologia();
//						if (valoreTipologia != null)
//							valoreTipologia.setId( getIdValue(valoreTipologia) );
//						break;
							
				case FiltroServizioEnte:
				case FiltroServizioAgente:
					List<ValoreFiltroServizio> listaValoriFiltro = ( (FiltroServizio) filtro ).getValoriFiltriServizio();					
					for (ValoreFiltroServizio valoreServizio : ListUtils.emptyIfNull(listaValoriFiltro) ) 
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
	
	public Documento updateSenzaVersionare(final Documento d) {
		return entityManager.merge(d);
	}
	
	public Documento insert(final Documento t) {
		preparaEntitaPerInserimento(t);		
		if(t.getBlob()!=null)
			entityManager.persist(t.getBlob());		
		entityManager.persist(t);
		return t;
	}
	
	public Documento update(final Documento t) {
		Documento entitaPrecedente = recuperaVersioneAttualePerCodice(t);
		entitaPrecedente.setValida(false);
		entityManager.persist(entitaPrecedente);
		t.setDataModifica(Calendar.getInstance().getTime());
		t.setValida(true);
		
		t.setId(getIdValue(t));
		t.setCodice(entitaPrecedente.getCodice());
		t.setValida(true);
		t.setDataCreazione(Calendar.getInstance().getTime());
		
		setChildIds(t);
		
		//In aggiornamento ricreo un nuovo BLOB uguale a quello invalidato.
		//Lo svecchiamento provvederà ad eliminare i BLOB invalidati.
		if(t.getBlob()!=null){
			entityManager.persist(t.getBlob());
		}
		
		entityManager.persist(t);
		
		return t;
	}
	

}