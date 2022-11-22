package it.equitalia.gdo.dao.converters;

import it.equitalia.gdo.commons.utils.Costanti;
import it.equitalia.gdo.commons.utils.StringUtils;
import it.equitalia.gdo.commons.valueobjects.AllegatoBean;
import it.equitalia.gdo.commons.valueobjects.AmbitoBean;
import it.equitalia.gdo.commons.valueobjects.BeanConFiltriInterface;
import it.equitalia.gdo.commons.valueobjects.BeanVersionabile;
import it.equitalia.gdo.commons.valueobjects.BlobAllegatoBean;
import it.equitalia.gdo.commons.valueobjects.BlobDocumentoBean;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.EnteBean;
import it.equitalia.gdo.commons.valueobjects.FiltroAmbitoBean;
import it.equitalia.gdo.commons.valueobjects.FiltroEnteBean;
import it.equitalia.gdo.commons.valueobjects.FiltroProvinciaBean;
import it.equitalia.gdo.commons.valueobjects.FiltroProvinciaEnteBean;
import it.equitalia.gdo.commons.valueobjects.FiltroRegioneAgenteBean;
import it.equitalia.gdo.commons.valueobjects.FiltroRegioneBean;
import it.equitalia.gdo.commons.valueobjects.FiltroRegioneEnteBean;
import it.equitalia.gdo.commons.valueobjects.FiltroServizioAgenteBean;
import it.equitalia.gdo.commons.valueobjects.FiltroServizioAltriUtentiBean;
import it.equitalia.gdo.commons.valueobjects.FiltroServizioBean;
import it.equitalia.gdo.commons.valueobjects.FiltroServizioEnteBean;
import it.equitalia.gdo.commons.valueobjects.FiltroServizioUtenteEsternoBean;
import it.equitalia.gdo.commons.valueobjects.FiltroSocietaBean;
import it.equitalia.gdo.commons.valueobjects.FiltroTipologiaEnteBean;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.commons.valueobjects.ProvinciaBean;
import it.equitalia.gdo.commons.valueobjects.RegioneBean;
import it.equitalia.gdo.commons.valueobjects.ServizioBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.commons.valueobjects.TipoEnteBean;
import it.equitalia.gdo.dao.model.Allegato;
import it.equitalia.gdo.dao.model.Ambito;
import it.equitalia.gdo.dao.model.BlobAllegato;
import it.equitalia.gdo.dao.model.BlobDocumento;
import it.equitalia.gdo.dao.model.Documento;
import it.equitalia.gdo.dao.model.Ente;
import it.equitalia.gdo.dao.model.FiltroAmbito;
import it.equitalia.gdo.dao.model.FiltroEnte;
import it.equitalia.gdo.dao.model.FiltroProvincia;
import it.equitalia.gdo.dao.model.FiltroProvinciaEnte;
import it.equitalia.gdo.dao.model.FiltroRegione;
import it.equitalia.gdo.dao.model.FiltroRegioneAgente;
import it.equitalia.gdo.dao.model.FiltroRegioneEnte;
import it.equitalia.gdo.dao.model.FiltroServizio;
import it.equitalia.gdo.dao.model.FiltroServizioAgente;
import it.equitalia.gdo.dao.model.FiltroServizioAltriUtenti;
import it.equitalia.gdo.dao.model.FiltroServizioEnte;
import it.equitalia.gdo.dao.model.FiltroServizioUtenteEsterno;
import it.equitalia.gdo.dao.model.FiltroSocieta;
import it.equitalia.gdo.dao.model.FiltroTipologiaEnte;
import it.equitalia.gdo.dao.model.News;
import it.equitalia.gdo.dao.model.Provincia;
import it.equitalia.gdo.dao.model.ProvinciaAgenteAmbito;
import it.equitalia.gdo.dao.model.Regione;
import it.equitalia.gdo.dao.model.Servizio;
import it.equitalia.gdo.dao.model.Sezione;
import it.equitalia.gdo.dao.model.SezioneUtenza;
import it.equitalia.gdo.dao.model.TipoEnte;
import it.equitalia.gdo.dao.model.ValoreFiltroAmbito;
import it.equitalia.gdo.dao.model.ValoreFiltroProvincia;
import it.equitalia.gdo.dao.model.ValoreFiltroRegione;
import it.equitalia.gdo.dao.model.ValoreFiltroServizio;
import it.equitalia.gdo.dao.model.ValoreFiltroSocieta;
import it.equitalia.gdo.dao.model.ValoreFiltroTipologiaEnte;
import it.equitalia.gdo.dao.model.generic.AbstractFiltro;
import it.equitalia.gdo.dao.model.generic.EntitaVersionabile;
import it.equitalia.gdo.dao.model.generic.AbstractFiltro.TIPO_FILTRO;
import it.equitalia.gdo.dao.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class ModelToBean {

	/**
	 * INIZIO METODI DI UTILITA
	 */

	private static void convertiCampiVersionamento(EntitaVersionabile entita, BeanVersionabile bean) {

		bean.setId(entita.getId());
		bean.setCodice(entita.getCodice());

		bean.setOwner(entita.getOwner());
		bean.setDataCreazione(entita.getDataCreazione());

		bean.setOwnerModifica(entita.getOwnerModifica());
		bean.setDataModifica(entita.getDataModifica());

		bean.setValida(entita.getValida());
		if(bean.getValida() != null && bean.getValida()){
			bean.setDescrizioneValida(Costanti.OPZIONI_VALIDA.SI.getDescrizione());
		} else {
			bean.setDescrizioneValida(Costanti.OPZIONI_VALIDA.NO.getDescrizione());
		}
	}

	private static void  popolaFiltri(BeanConFiltriInterface bean, List<AbstractFiltro> filtri) {

		if (filtri != null) {

			for (AbstractFiltro filtroGenerico : filtri) {

				TIPO_FILTRO tipoFiltro = TIPO_FILTRO.valueOf(filtroGenerico.getTipoFiltro());
				switch(tipoFiltro) {

				case FiltroServizioEnte:
					FiltroServizioEnteBean bean9 = execute( (FiltroServizioEnte) filtroGenerico);
					bean.setFiltroServizioEnte( bean9 );
					break;

				case FiltroServizioAgente:
					FiltroServizioAgenteBean filtroBean = execute((FiltroServizioAgente) filtroGenerico);
					bean.setFiltroServizioAgente( filtroBean );
					break;
					
				case FiltroServizioAltriUtenti:
					FiltroServizioAltriUtentiBean filtroUtenti= execute((FiltroServizioAltriUtenti) filtroGenerico);
					bean.setFiltroServizioAltriUtenti( filtroUtenti );
					break;
					
				case FiltroServizioUtenteEsterno:
					FiltroServizioUtenteEsternoBean filtroUtenteEsterno= execute((FiltroServizioUtenteEsterno) filtroGenerico);
					bean.setFiltroServizioUtenteEsterno(filtroUtenteEsterno);
					break;

				case FiltroSocieta:
					FiltroSocietaBean bean2 = execute( (FiltroSocieta) filtroGenerico);
					bean.setFiltroSocieta( bean2 );
					break;

				case FiltroTipologiaEnte: 
					FiltroTipologiaEnteBean bean4 = execute( (FiltroTipologiaEnte) filtroGenerico );
					bean.setFiltroTipologiaEnte( bean4 );
					break;

				case FiltroRegioneAgente:
					FiltroRegioneAgenteBean bean5 = execute( (FiltroRegioneAgente) filtroGenerico );
					bean.setFiltroRegioneAgente( bean5 );
					break;

				case FiltroRegioneEnte:
					FiltroRegioneEnteBean bean6 = execute( (FiltroRegioneEnte) filtroGenerico);
					bean.setFiltroRegioneEnte( bean6 );
					break;

			
				case FiltroProvinciaEnte:
					FiltroProvinciaEnteBean bean8 = execute( (FiltroProvinciaEnte) filtroGenerico);
					bean.setFiltroProvinciaEnte( bean8 );
					break;

				case FiltroEnte:
					FiltroEnteBean bean1 = execute( (FiltroEnte) filtroGenerico);
					bean.setFiltroEnte( bean1 );
					break;
				case FiltroAmbito:
					FiltroAmbitoBean beanAmbito =execute((FiltroAmbito)filtroGenerico);
					bean.setFiltroAmbito(beanAmbito);
				}

			}

		}
	}

	public static FiltroAmbitoBean execute(FiltroAmbito filtroAmbito) {
		FiltroAmbitoBean bean = new FiltroAmbitoBean();
		 bean.setIdFiltro(filtroAmbito.getId());		
		 List<ValoreFiltroAmbito> entitaValori = filtroAmbito.getvaloriFiltriAmbito();		
		 if (entitaValori != null) {
			 List<Integer> valori = new ArrayList<Integer>();			
			 for (ValoreFiltroAmbito entitaValore : entitaValori) {
				 valori.add(entitaValore.getCodiceAmbito());
			 }
			 bean.setValori(valori);
		 }

		 return bean;
	}

	private static void convertiFiltroRegione(FiltroRegione entita,
			FiltroRegioneBean bean) {
		bean.setIdFiltro(entita.getId());		

		List<ValoreFiltroRegione> entitaValori = entita.getValoriFiltriRegione();		
		if (entitaValori != null) {

			List<String> valori = new ArrayList<String>();			
			for (ValoreFiltroRegione entitaValore : entitaValori) {
				valori.add(entitaValore.getCodiceRegione());
			}
			bean.setValori(valori);
		}
	}

	public static void convertiFiltroServizio(FiltroServizio entita, FiltroServizioBean bean){

		bean.setIdFiltro(entita.getId());		

		List<ValoreFiltroServizio> entitaValori = entita.getValoriFiltriServizio();		
		if (entitaValori != null) {

			List<String> valori = new ArrayList<String>();			
			for (ValoreFiltroServizio entitaValore : entitaValori) {
				valori.add(entitaValore.getCodiceServizio());
			}
			bean.setValori(valori);
		}

	}

	private static void convertiFiltroProvincia(FiltroProvincia entita, FiltroProvinciaBean bean){

		bean.setIdFiltro(entita.getId());		

		List<ValoreFiltroProvincia> entitaValori = entita.getValoriFiltriProvincia();		
		if (entitaValori != null) {

			List<String> valori = new ArrayList<String>();			
			for (ValoreFiltroProvincia entitaValore : entitaValori) {
				valori.add(entitaValore.getCodiceProvincia());
			}
			bean.setValori(valori);
		}

	}

	/*
	 * FINE METODI DI UTILITA
	 * -
	 * INIZIO METODI CONVERTITORI VERI E PROPRI
	 */

	public static NewsBean execute(News news, boolean estraiFiltri, boolean recuperaContenutoAllegato){

		NewsBean bean = new NewsBean();
		StringBuilder descrizioneUtente= new StringBuilder();

		convertiCampiVersionamento(news, bean);		

		bean.setTitolo(news.getTitolo());
		bean.setTesto(news.getTesto());
		bean.setStato(news.getStato());
		if(news.getStato() == Costanti.OPZIONI_STATO.ATTIVA.getValue()){
			bean.setDescrizioneStato(Costanti.OPZIONI_STATO.ATTIVA.getDescrizione());
		} else if(news.getStato() == Costanti.OPZIONI_STATO.NON_ATTIVA.getValue()){
			bean.setDescrizioneStato(Costanti.OPZIONI_STATO.NON_ATTIVA.getDescrizione());
		}
		bean.setAgente(news.getAgente());
		if(news.getAgente() != null && news.getAgente()){
			descrizioneUtente.append(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getDescrizione());
		}
		
		bean.setEnte(news.getEnte());
		if (news.getEnte() != null && news.getEnte()) {
			if (descrizioneUtente.length() > 0) {
				descrizioneUtente.append("/");
			}
			descrizioneUtente.append(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getDescrizione());
		}
		
		bean.setAltriUtenti(news.getAltriUtenti());
		if(news.getAltriUtenti() != null && news.getAltriUtenti()){
			if (descrizioneUtente.length() > 0) {
				descrizioneUtente.append("/");
			}
			descrizioneUtente.append(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_ALTRO_UTENTE.getDescrizione());				
		}
		
		bean.setUtenteEsterno(news.getUtenteEsterno());
		if (news.getUtenteEsterno() != null && news.getUtenteEsterno()) {
			if (descrizioneUtente.length() > 0) {
				descrizioneUtente.append("/");
			}
			descrizioneUtente.append(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_UTENTE_ESTERNO.getDescrizione());
		}
		bean.setDescrizioneTipoUtente(descrizioneUtente.toString());
		
		
		if(news.getDataInizioPubblicazione() != null)
			bean.setDataInizioPubblicazione(DateUtils.fromDateToString(news.getDataInizioPubblicazione()));
		if(news.getDataFinePubblicazione() != null)
			bean.setDataFinePubblicazione(DateUtils.fromDateToString(news.getDataFinePubblicazione()));

		if(estraiFiltri){
			popolaFiltri(bean, news.getFiltri());	
		}

		if (news.getAllegato() != null) {
			if(recuperaContenutoAllegato)
				bean.setAllegato( execute(news.getAllegato(), true ) );
			else
				//per default è false
				bean.setAllegato( execute(news.getAllegato() ) );
		}
		
		bean.setSvecchiata(news.getSvecchiata());
		
		bean.setVisualizzaPopUp(news.getVisualizzaPopUp());
		if(news.getVisualizzaPopUp() == Costanti.OPZIONI_VISUALIZZAZIONE_POP_UP.VISUALIZZAZIONE_POP_UP_SI.getValue()){
			bean.setVisualizzaPopUpDescr(Costanti.OPZIONI_VISUALIZZAZIONE_POP_UP.VISUALIZZAZIONE_POP_UP_SI.getDescrizione());
		} else if(news.getVisualizzaPopUp() == Costanti.OPZIONI_VISUALIZZAZIONE_POP_UP.VISUALIZZAZIONE_POP_UP_NO.getValue()){
			bean.setVisualizzaPopUpDescr(Costanti.OPZIONI_VISUALIZZAZIONE_POP_UP.VISUALIZZAZIONE_POP_UP_NO.getDescrizione());
		}

		return bean;

	}


	/**
	 * Di default non recupero il contenuto del file
	 */
	public static AllegatoBean execute(Allegato allegato) {
		return execute(allegato, false);
	}

	/**
	 * @param recuperaContenutoFile == false non leggo il blob (default)
	 * 								== true leggo il blob 
	 */
	public static AllegatoBean execute(Allegato allegato, boolean recuperaContenutoFile){

		AllegatoBean bean = new AllegatoBean();

		bean.setTitolo(allegato.getTitolo());
		bean.setNomeFile(allegato.getNomeFile());
		bean.setId(allegato.getId());

		if (recuperaContenutoFile && allegato.getBlob() != null && allegato.getBlob().getFileBlob() != null) {
			bean.setContenutoFile(allegato.getBlob().getFileBlob());
		}

		return bean;

	}

	/**
	 * Di default non recupero il contenuto del file
	 */
	public static BlobDocumentoBean execute(BlobDocumento blobDocumento) {
		return execute(blobDocumento, false);
	}

	/**
	 * @param recuperaContenutoFile == false non leggo il blob (default)
	 * 								== true leggo il blob 
	 */
	public static BlobDocumentoBean execute(BlobDocumento blobDocumento, boolean recuperaContenutoFile){

		BlobDocumentoBean bean = new BlobDocumentoBean();
		
		bean.setIdBlobDocumento(blobDocumento.getId());

		if (recuperaContenutoFile && blobDocumento.getFileBlob() != null && blobDocumento.getFileBlob().length > 0) {
			bean.setContenutoFile(blobDocumento.getFileBlob());
		}

		return bean;

	}
	
	/**
	 * Di default non recupero il contenuto del file
	 */
	public static BlobAllegatoBean execute(BlobAllegato blobAllegato) {
		return execute(blobAllegato, false);
	}

	/**
	 * @param recuperaContenutoFile == false non leggo il blob (default)
	 * 								== true leggo il blob 
	 */
	public static BlobAllegatoBean execute(BlobAllegato blobAllegato, boolean recuperaContenutoFile){

		BlobAllegatoBean bean = new BlobAllegatoBean();
		
		bean.setIdBlobAllegato(blobAllegato.getId());

		if (recuperaContenutoFile && blobAllegato.getFileBlob() != null && blobAllegato.getFileBlob().length > 0) {
			bean.setContenutoFile(blobAllegato.getFileBlob());
		}

		return bean;

	}
	

	/**
	 * 
	 * @param documento
	 * @param estraiFiltri
	 * @param recuperaContenutoDocumento: specifica se leggere o meno il blob del file (default: no)
	 * 
	 * @return
	 */
	public static DocumentoBean execute(Documento documento, boolean estraiFiltri, boolean recuperaContenutoDocumento){

		DocumentoBean bean = new DocumentoBean();

		convertiCampiVersionamento(documento, bean);


		bean.setTitolo(documento.getTitolo());
		bean.setDescrizione(documento.getDescrizione());
		bean.setStato(documento.getStato());
		if(documento.getStato() == Costanti.OPZIONI_STATO_DOCUMENTI.ATTIVO.getValue()){
			bean.setDescrizioneStato(Costanti.OPZIONI_STATO_DOCUMENTI.ATTIVO.getDescrizione());
		} else if(documento.getStato() == Costanti.OPZIONI_STATO_DOCUMENTI.NON_ATTIVO.getValue()){
			bean.setDescrizioneStato(Costanti.OPZIONI_STATO_DOCUMENTI.NON_ATTIVO.getDescrizione());
		}
		bean.setAgente(documento.getAgente());
		if(documento.getAgente() != null && documento.getAgente()){
			bean.setDescrizioneTipoUtente(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getDescrizione());
		}		
		bean.setEnte(documento.getEnte());
		if(documento.getEnte() != null && documento.getEnte()){
			if(bean.getDescrizioneTipoUtente() != null && !StringUtils.isBlank(bean.getDescrizioneTipoUtente())){
				bean.setDescrizioneTipoUtente(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getDescrizione() + "/" +Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getDescrizione());	
			} else {
				bean.setDescrizioneTipoUtente(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getDescrizione());
			}
		}
		
		if(estraiFiltri){
			popolaFiltri(bean, documento.getFiltri());
		}
		
		if(documento.getSezione() != null)
			bean.setSezione(execute(documento.getSezione(),false));
		
		if(documento.getBlob() != null){
			if(recuperaContenutoDocumento)
				bean.setBlob(execute(documento.getBlob(),true));
			else
				bean.setBlob(execute(documento.getBlob()));
		}
		
		bean.setNomeFile(documento.getNomeFile());
		bean.setSvecchiato(documento.getSvecchiato());
		
		return bean;

	}

	public static SezioneBean execute(Sezione sezione, boolean estraiDocumenti){

		SezioneBean bean = new SezioneBean();

		convertiCampiVersionamento(sezione, bean);

		bean.setTitolo(sezione.getTitolo());
		bean.setDescrizione(sezione.getDescrizione());
		bean.setStato(sezione.getStato());
		if(sezione.getStato() != null){
			if(sezione.getStato() == Costanti.OPZIONI_STATO.ATTIVA.getValue()){
				bean.setDescrizioneStato(Costanti.OPZIONI_STATO.ATTIVA.getDescrizione());
			} else if(sezione.getStato() == Costanti.OPZIONI_STATO.NON_ATTIVA.getValue()){
				bean.setDescrizioneStato(Costanti.OPZIONI_STATO.NON_ATTIVA.getDescrizione());
			}
		}
		if(estraiDocumenti){
			List<DocumentoBean> documentiBean = new ArrayList<DocumentoBean>();
			List<DocumentoBean> documentiBeanValidi = new ArrayList<DocumentoBean>();
			if(sezione.getDocumenti()!=null && sezione.getDocumenti().size() > 0){
				for(Documento doc: sezione.getDocumenti()){
					if(doc.getValida()){
						documentiBean.add(execute(doc, false, true));
						documentiBeanValidi.add(execute(doc, false, true));
					}
				}
			}	

			bean.setDocumenti(documentiBean);
			bean.setDocumentiValidi(documentiBeanValidi);
		}

		List<SezioneUtenza> entitaValori = sezione.getValoriSezioneUtenza();
		if(entitaValori != null && entitaValori.size() > 0){
			List<String> valori = new ArrayList<String>();
			for(SezioneUtenza entitaValore : entitaValori){
				valori.add(entitaValore.getUtenza());
			}
			bean.setUtenti(valori);
		}


		return bean;

	}

	public static ProvinciaBean execute(Provincia provincia) {
		ProvinciaBean bean = new ProvinciaBean();
		bean.setCodice(provincia.getCodiceProvincia());
		bean.setDescrizione(provincia.getProvincia());
		return bean;

	}

	public static ProvinciaBean execute(ProvinciaAgenteAmbito provincia) {
		ProvinciaBean bean = new ProvinciaBean();
		bean.setCodice(provincia.getCodiceProvincia());
		return bean;

	}


	public static RegioneBean execute(Regione regione) {
		RegioneBean rb= new RegioneBean();
		rb.setCodice(regione.getCodiceRegione());
		rb.setDescrizione(regione.getRegione());
		return rb;

	}

	public static ServizioBean execute(Servizio servizio) {
		ServizioBean sb = new ServizioBean();
		sb.setCodice(servizio.getCodiceServizio());
		sb.setDescrizione(servizio.getServizio());
		return sb;

	}

	public static TipoEnteBean execute(TipoEnte tipoEnte) {
		TipoEnteBean teb = new TipoEnteBean();
		teb.setCodice(tipoEnte.getCodiceTipoEnte());
		teb.setDescrizione(tipoEnte.getEnte());
		return teb;

	}

	public static EnteBean execute(Ente ente) {
		EnteBean eb = new EnteBean();
		eb.setCodiceEnte(ente.getCodiceEnte());
		eb.setCodiceUfficio(ente.getCodiceUfficio().trim());
		eb.setTipoUfficio(ente.getTipoUfficio().trim());
		return eb;

	}

	

	/**
	 * 
	 * Filtri
	 * 
	 */

	 public static FiltroRegioneEnteBean execute(FiltroRegioneEnte entita) {

		 FiltroRegioneEnteBean bean = new FiltroRegioneEnteBean();
		 convertiFiltroRegione(entita, bean);		
		 return bean;

	 }

	 public static FiltroRegioneAgenteBean execute(FiltroRegioneAgente entita){

		 FiltroRegioneAgenteBean bean = new FiltroRegioneAgenteBean();
		 convertiFiltroRegione(entita, bean);
		 return bean;

	 }

	

	 public static FiltroProvinciaEnteBean execute(FiltroProvinciaEnte entita) {
		 FiltroProvinciaEnteBean bean = new FiltroProvinciaEnteBean();
		 convertiFiltroProvincia(entita,bean);
		 return bean;
	 }

	 public static FiltroServizioAgenteBean execute(FiltroServizioAgente entita) {
		 FiltroServizioAgenteBean bean = new FiltroServizioAgenteBean();
		 convertiFiltroServizio(entita,bean);
		 return bean;
	 }
	 
	 
	 public static FiltroServizioAltriUtentiBean execute(FiltroServizioAltriUtenti entita) {
		 FiltroServizioAltriUtentiBean bean = new FiltroServizioAltriUtentiBean();
		 convertiFiltroServizio(entita,bean);
		 return bean;
	 }
	 
	 public static FiltroServizioUtenteEsternoBean execute(FiltroServizioUtenteEsterno entita) {
		 FiltroServizioUtenteEsternoBean bean = new FiltroServizioUtenteEsternoBean();
		 convertiFiltroServizio(entita,bean);
		 return bean;
	 }

	 public static FiltroServizioEnteBean execute(FiltroServizioEnte entita) {
		 FiltroServizioEnteBean bean = new FiltroServizioEnteBean();
		 convertiFiltroServizio(entita,bean);
		 return bean;
	 }




	 public static FiltroSocietaBean execute(FiltroSocieta entita){

		 FiltroSocietaBean bean = new FiltroSocietaBean();
		 bean.setIdFiltro(entita.getId());		

		 List<ValoreFiltroSocieta> entitaValori = entita.getValoriFiltriSocieta();		
		 if (entitaValori != null) {

			 List<Integer> valori = new ArrayList<Integer>();
			 for (ValoreFiltroSocieta entitaValore : entitaValori) {
				 valori.add(entitaValore.getCodiceSocieta());
			 }
			 bean.setValori(valori);
		 }

		 return bean;

	 }


	 public static FiltroEnteBean execute(FiltroEnte entita) {
		 FiltroEnteBean bean = new FiltroEnteBean();
		 bean.setIdFiltro(entita.getId());


		 if (entita.getValoreFiltroEnte() != null) {
			 bean.setCodiceEnte(entita.getValoreFiltroEnte().getCodiceEnte());
			 bean.setCodiceUfficioEnte(entita.getValoreFiltroEnte().getCodiceUfficioEnte());
			 bean.setTipoUfficioEnte(entita.getValoreFiltroEnte().getTipoUfficioEnte());
		 }

		 return bean;
	 }

	 public static FiltroTipologiaEnteBean execute(FiltroTipologiaEnte entita){

		 FiltroTipologiaEnteBean bean = new FiltroTipologiaEnteBean();
		 bean.setIdFiltro(entita.getId());		

		 List<ValoreFiltroTipologiaEnte> entitaValori = entita.getValoriFiltroTipologiaEnte();		
		 if (entitaValori != null) {

			 List<String> valori = new ArrayList<String>();			
			 for (ValoreFiltroTipologiaEnte entitaValore : entitaValori) {
				 valori.add(entitaValore.getTipologiaEnte());
			 }
			 bean.setValori(valori);
		 }

		 return bean;

	 }
	 
	 
	 
	 public static AmbitoBean execute(Ambito ambito) {
		 AmbitoBean bean = new AmbitoBean();
			bean.setCodiceAmbito(ambito.getCodiceAmbito());
			bean.setDescrizione(ambito.getDescrizione());
			return bean;

		}

		public static Ambito execute(AmbitoBean ambito) {
			Ambito bean = new Ambito();
			bean.setCodiceAmbito(ambito.getCodiceAmbito());
			bean.setDescrizione(ambito.getDescrizione());
			return bean;

		}

}
