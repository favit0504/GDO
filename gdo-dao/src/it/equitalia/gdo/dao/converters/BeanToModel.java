package it.equitalia.gdo.dao.converters;

import it.equitalia.gdo.commons.utils.StringUtils;
import it.equitalia.gdo.commons.valueobjects.AllegatoBean;
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
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.dao.model.Allegato;
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
import it.equitalia.gdo.dao.model.Sezione;
import it.equitalia.gdo.dao.model.SezioneUtenza;
import it.equitalia.gdo.dao.model.ValoreFiltroAmbito;
import it.equitalia.gdo.dao.model.ValoreFiltroEnte;
import it.equitalia.gdo.dao.model.ValoreFiltroProvincia;
import it.equitalia.gdo.dao.model.ValoreFiltroRegione;
import it.equitalia.gdo.dao.model.ValoreFiltroServizio;
import it.equitalia.gdo.dao.model.ValoreFiltroSocieta;
import it.equitalia.gdo.dao.model.ValoreFiltroTipologiaEnte;
import it.equitalia.gdo.dao.model.generic.AbstractFiltro;
import it.equitalia.gdo.dao.model.generic.EntitaVersionabile;
import it.equitalia.gdo.dao.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class BeanToModel {

	/**
	 * Inizio metodi di utilita
	 */
	
	
	private static void convertiCampiVersionamento(EntitaVersionabile entita, BeanVersionabile bean) {
		
		entita.setId(bean.getId());
		entita.setCodice(bean.getCodice());
		
		entita.setOwner(bean.getOwner());
		entita.setDataCreazione(bean.getDataCreazione());
		
		entita.setOwnerModifica(bean.getOwnerModifica());
		entita.setDataModifica(bean.getDataModifica());
		
		entita.setValida(bean.getValida());
	}
	
	private static List<AbstractFiltro> convertiFiltri(BeanConFiltriInterface bean) {
		
		List<AbstractFiltro> list = new ArrayList<AbstractFiltro>();
		
		if (bean.getFiltroEnte() != null) {
			FiltroEnte filtro = execute(bean.getFiltroEnte());
			if (filtro != null) //== null se il filtro e` precompilato con tripletta ("","","") 
				list.add( filtro );
		}
		
		if (bean.getFiltroTipologiaEnte() != null) {
			FiltroTipologiaEnte filtro = execute(bean.getFiltroTipologiaEnte());
			list.add( filtro );
		}
		
		if (bean.getFiltroSocieta() != null) {
			FiltroSocieta filtro = execute(bean.getFiltroSocieta());
			list.add( filtro );
		}
		
		if (bean.getFiltroServizioEnte() != null) {
			FiltroServizioEnte filtro = execute(bean.getFiltroServizioEnte());
			list.add( filtro );
		}
		
		if (bean.getFiltroServizioAgente() != null) {
			FiltroServizioAgente filtro = execute(bean.getFiltroServizioAgente());
			list.add( filtro );
		}	
		
		if (bean.getFiltroServizioAltriUtenti() != null) {
			FiltroServizioAltriUtenti filtro = execute(bean.getFiltroServizioAltriUtenti());
			list.add( filtro );
		}
		
		if (bean.getFiltroServizioUtenteEsterno() != null) {
			FiltroServizioUtenteEsterno filtro = execute(bean.getFiltroServizioUtenteEsterno());
			list.add( filtro );
		}
		
		if (bean.getFiltroRegioneAgente() != null) {
			FiltroRegioneAgente filtro = execute(bean.getFiltroRegioneAgente());
			list.add( filtro );
		}
		
		if (bean.getFiltroRegioneEnte() != null) {
			FiltroRegioneEnte filtro = execute(bean.getFiltroRegioneEnte());
			list.add( filtro );
		}
		
		
		
		if (bean.getFiltroProvinciaEnte() != null) {
			FiltroProvinciaEnte filtro = execute(bean.getFiltroProvinciaEnte());
			list.add( filtro );
		}
		if (bean.getFiltroAmbito()!=null)
		{
			FiltroAmbito filtro = execute(bean.getFiltroAmbito());
			list.add( filtro );
			
		}
		
		return list;
	}
	
	/**
	 * Convertitore Utilizzato sia per FiltroProvinciaAgente che per FiltroProvinciaEnte
	 * @param entita
	 * @param bean
	 */
	private static void convertiFiltroProvincia(FiltroProvincia entita, FiltroProvinciaBean bean) {

		entita.setId(bean.getIdFiltro());		

		if (bean.getValori() != null) {

			for (String valore : bean.getValori()) {
				ValoreFiltroProvincia entitaValore = new ValoreFiltroProvincia();
				entitaValore.setCodiceProvincia(valore);
			
				entita.aggiungiValoreFiltroConRiferimentoAlFiltroPadre(entitaValore);
			}
		}

	}
	
	/**
	 * Convertitore Utilizzato sia per FiltroRegioneAgente che per FiltroRegioneEnte
	 * @param entita
	 * @param bean
	 */
	private static void convertiFiltroRegione(FiltroRegione entita, FiltroRegioneBean bean) {

		entita.setId(bean.getIdFiltro());		

		if (bean.getValori() != null) {

			for (String valore : bean.getValori()) {
				ValoreFiltroRegione entitaValore = new ValoreFiltroRegione();
				entitaValore.setCodiceRegione(valore);
			
				entita.aggiungiValoreFiltroConRiferimentoAlFiltroPadre(entitaValore);
			}
		}

	}
	
	public static void convertiFiltroServizio(FiltroServizio entita, FiltroServizioBean bean) {

		entita.setId(bean.getIdFiltro());
	
		if (bean.getValori() != null) {

			for (String valore : bean.getValori()) {
				ValoreFiltroServizio entitaValore = new ValoreFiltroServizio();
				entitaValore.setCodiceServizio(valore);
			
				entita.aggiungiValoreFiltroConRiferimentoAlFiltroPadre(entitaValore);
			}
		}

	}
	
	/**
	 * Fine metodi di utilita, sotto si trovano i metodi reali
	 * di conversione
	 */
	
	
	public static Allegato execute(AllegatoBean allegatoBean){
		
		Allegato model = new Allegato();
		model.setId(allegatoBean.getId());
		model.setTitolo(allegatoBean.getTitolo());
		model.setNomeFile(allegatoBean.getNomeFile());				
		//allegato
		BlobAllegato blob = new BlobAllegato();
		blob.setFileBlob(allegatoBean.getContenutoFile());		
		model.setBlob(blob);				
		return model;		
	}
	
	public static BlobDocumento execute(BlobDocumentoBean blobDocumentoBean){
		
		BlobDocumento model = new BlobDocumento();
		model.setId(blobDocumentoBean.getIdBlobDocumento());
		
		if(blobDocumentoBean.getContenutoFile() != null && blobDocumentoBean.getContenutoFile().length > 0)
			model.setFileBlob(blobDocumentoBean.getContenutoFile());
		
		return model;		
	}
	
	public static BlobAllegato execute(BlobAllegatoBean blobAllegatoBean){
		
		BlobAllegato model = new BlobAllegato();
		model.setId(blobAllegatoBean.getIdBlobAllegato());
		
		if(blobAllegatoBean.getContenutoFile() != null && blobAllegatoBean.getContenutoFile().length > 0)
			model.setFileBlob(blobAllegatoBean.getContenutoFile());
		
		return model;
	}
	
	public static Documento execute(DocumentoBean documentoBean){
		
		Documento model = new Documento();
		
		convertiCampiVersionamento(model, documentoBean);
				
		model.setTitolo(documentoBean.getTitolo());
		model.setDescrizione(documentoBean.getDescrizione());
		model.setStato(documentoBean.getStato());
		model.setEnte(documentoBean.getEnte());
		model.setAgente(documentoBean.getAgente());
		if(documentoBean.getSezione() != null)
			model.setSezione(execute(documentoBean.getSezione()));
		
		// FILTRI
		model.setFiltri(convertiFiltri(documentoBean));
		
		//allegato
		if(documentoBean.getBlob() != null){
			model.setBlob(execute(documentoBean.getBlob()));	
		}
		
		model.setNomeFile(documentoBean.getNomeFile());
		model.setSvecchiato(documentoBean.getSvecchiato());
		
		return model;
		
	}
	
	public static News execute(NewsBean newsBean){
		
		News model = new News();
		
		convertiCampiVersionamento(model, newsBean);
		
		model.setEnte(newsBean.getEnte());
		model.setAgente(newsBean.getAgente());
		model.setAltriUtenti(newsBean.getAltriUtenti());
		model.setUtenteEsterno(newsBean.getUtenteEsterno());
		model.setTitolo(newsBean.getTitolo());
		model.setTesto(newsBean.getTesto());
		model.setStato(newsBean.getStato());
		
		if(newsBean.getDataInizioPubblicazione() != null && newsBean.getDataInizioPubblicazione().length()>0){
			model.setDataInizioPubblicazione(DateUtils.fromStringToDate(newsBean.getDataInizioPubblicazione()));
		}
		if(newsBean.getDataFinePubblicazione() != null && newsBean.getDataFinePubblicazione().length()>0){
			model.setDataFinePubblicazione(DateUtils.fromStringToDate(newsBean.getDataFinePubblicazione()));
		}
		
		//FILTRI, elaborati in un metodo a parte
		model.setFiltri(convertiFiltri(newsBean));
		
		if (newsBean.getAllegato() != null) {
			Allegato allegato = execute(newsBean.getAllegato());
			model.setAllegato( allegato );
		}
		
		model.setSvecchiata(newsBean.getSvecchiata());
		
		model.setVisualizzaPopUp(newsBean.isVisualizzaPopUp());
		
		return model;
		
	}
	
	public static Sezione execute(SezioneBean sezioneBean){
		
		Sezione model = new Sezione();
		
		convertiCampiVersionamento(model, sezioneBean);
		
		model.setTitolo(sezioneBean.getTitolo());
		model.setDescrizione(sezioneBean.getDescrizione());
		model.setStato(sezioneBean.getStato());
		
		List<Documento> documenti = new ArrayList<Documento>();
		if(sezioneBean.getDocumenti() != null && sezioneBean.getDocumenti().size() > 0){
			for(DocumentoBean doc: sezioneBean.getDocumenti())
				documenti.add(execute(doc));
		}
		model.setDocumenti(documenti);
		
		List<String> utenti = sezioneBean.getUtenti();
		if(utenti != null && utenti.size() > 0){
			for(String user : utenti) {
				SezioneUtenza entitaValore = new SezioneUtenza();
				entitaValore.setUtenza(user);
				
				model.aggiungiValoreUtenzaConRiferimentoAllaSezione(entitaValore);
			}
		}
		
		return model;
		
	}
	
	public static Ente execute(EnteBean ente) {
		Ente eb = new Ente();
		eb.setCodiceEnte(ente.getCodiceEnte());
		eb.setCodiceUfficio(ente.getCodiceUfficio());
		eb.setTipoUfficio(ente.getTipoUfficio());
		return eb;
		
	}
	
	
	 
	
	/** FILTRI **/
	
	
	
	public static FiltroProvinciaEnte execute(FiltroProvinciaEnteBean bean)
	{
		FiltroProvinciaEnte fpe = new FiltroProvinciaEnte();
		convertiFiltroProvincia(fpe, bean);
		fpe.setTipoFiltro(AbstractFiltro.TIPO_FILTRO.FiltroProvinciaEnte.name());
		return fpe;		
	}
	
	public static FiltroRegioneAgente execute(FiltroRegioneAgenteBean bean)
	{
		FiltroRegioneAgente fra = new FiltroRegioneAgente();
		convertiFiltroRegione(fra, bean);
		fra.setTipoFiltro(AbstractFiltro.TIPO_FILTRO.FiltroRegioneAgente.name());
		return fra;		
	}
	
	
	public static FiltroRegioneEnte execute(FiltroRegioneEnteBean bean)
	{
		FiltroRegioneEnte fra = new FiltroRegioneEnte();
		convertiFiltroRegione(fra, bean);
		fra.setTipoFiltro(AbstractFiltro.TIPO_FILTRO.FiltroRegioneEnte.name());
		return fra;		
	}

	public static FiltroServizioAgente execute(FiltroServizioAgenteBean bean)
	{
		FiltroServizioAgente fsa = new FiltroServizioAgente();
		convertiFiltroServizio(fsa, bean);
		fsa.setTipoFiltro(AbstractFiltro.TIPO_FILTRO.FiltroServizioAgente.name());
		return fsa;		
	}
	
	
	public static FiltroServizioEnte execute(FiltroServizioEnteBean bean)
	{
		FiltroServizioEnte fse = new FiltroServizioEnte();
		convertiFiltroServizio(fse, bean);
		fse.setTipoFiltro(AbstractFiltro.TIPO_FILTRO.FiltroServizioEnte.name());
		return fse;		
	}
	
	public static FiltroServizioAltriUtenti execute(FiltroServizioAltriUtentiBean bean)
	{
		FiltroServizioAltriUtenti fsa = new FiltroServizioAltriUtenti();
		convertiFiltroServizio(fsa, bean);
		fsa.setTipoFiltro(AbstractFiltro.TIPO_FILTRO.FiltroServizioAltriUtenti.name());
		return fsa;		
	}
	
	public static FiltroServizioUtenteEsterno execute(FiltroServizioUtenteEsternoBean bean)
	{
		FiltroServizioUtenteEsterno fsa = new FiltroServizioUtenteEsterno();
		convertiFiltroServizio(fsa, bean);
		fsa.setTipoFiltro(AbstractFiltro.TIPO_FILTRO.FiltroServizioUtenteEsterno.name());
		return fsa;		
	}
	
	
	public static FiltroSocieta execute(FiltroSocietaBean bean) {

		FiltroSocieta entita = new FiltroSocieta();
		entita.setId(bean.getIdFiltro());
		entita.setTipoFiltro(AbstractFiltro.TIPO_FILTRO.FiltroSocieta.name());
		

		if (bean.getValori() != null) {

			for (Integer valore : bean.getValori()) {
				ValoreFiltroSocieta entitaValore = new ValoreFiltroSocieta();
				entitaValore.setCodiceSocieta(valore);
			
				entita.aggiungiValoreFiltroConRiferimentoAlFiltroPadre(entitaValore);
			}
		}

		return entita;

	}
	
	
	
	

	public static FiltroEnte execute(FiltroEnteBean bean){
		
		
		if ( bean.getCodiceEnte() == null 
				&& StringUtils.isBlank(bean.getCodiceUfficioEnte())
				&& StringUtils.isBlank(bean.getTipoUfficioEnte()) )
			return null;
		
		FiltroEnte entita = new FiltroEnte();
		
		entita.setId(bean.getIdFiltro());
		entita.setTipoFiltro( AbstractFiltro.TIPO_FILTRO.FiltroEnte.name() );
		
		
		ValoreFiltroEnte valore = new ValoreFiltroEnte();
		valore.setCodiceEnte(bean.getCodiceEnte());
		valore.setTipoUfficioEnte(bean.getTipoUfficioEnte());
		valore.setCodiceUfficioEnte(bean.getCodiceUfficioEnte());
		
		entita.setValoreFiltroEnte(valore);
		
		return entita;
		
	}
	
	public static FiltroTipologiaEnte execute(FiltroTipologiaEnteBean bean){
		
		FiltroTipologiaEnte entita = new FiltroTipologiaEnte();
		entita.setId(bean.getIdFiltro());
		entita.setTipoFiltro(AbstractFiltro.TIPO_FILTRO.FiltroTipologiaEnte.name());
		

		if (bean.getValori() != null) {

			for (String valore : bean.getValori()) {
				ValoreFiltroTipologiaEnte entitaValore = new ValoreFiltroTipologiaEnte();
				entitaValore.setTipologiaEnte(valore);
			
				entita.aggiungiValoreFiltroConRiferimentoAlFiltroPadre(entitaValore);
			}
		}

		return entita;
		
	}
	
	
public static FiltroAmbito execute(FiltroAmbitoBean bean){
		
	    FiltroAmbito entita = new FiltroAmbito();
		entita.setId(bean.getIdFiltro());
		entita.setTipoFiltro(AbstractFiltro.TIPO_FILTRO.FiltroAmbito.name());		
		if (bean.getValori() != null) {
			for (Integer valore : bean.getValori()) {
				ValoreFiltroAmbito entitaValore = new ValoreFiltroAmbito();
				entitaValore.setCodiceAmbito(valore);			
				entita.aggiungiValoreFiltroConRiferimentoAlFiltroPadre(entitaValore);
			}
		}

		return entita;
		
	}
	
}
