package it.equitalia.gdo.web.actions.news;

import it.equitalia.gdo.commons.utils.Costanti;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.web.businessdelegate.NewsServiceBD;
import it.equitalia.gdo.web.util.GDOCostantiWeb;
import it.equitalia.gdo.web.util.PopolaFiltriNews;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Preparable;

public class RicercaNewsAction extends AbstractNewsAction  implements Preparable {		

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(RicercaNewsAction.class);

	private List<NewsBean> elencoNews;
	private List<Integer> statoNewsCheck;
	
	public Integer[] getDefaultValue(){
		return new Integer[] {GDOCostantiWeb.OPZIONI_STATO_DOCUMENTI.ATTIVO.getValue()};
	}

	public String execute() throws Exception {

		

		//Form non inviato - visualizza il form
		if (news == null) {

			p = new PopolaFiltriNews(getSession());
			p.popolaFiltriRicercaNews();
			
			return INPUT;
		}
		//form inviato - salva il risultato che e` gia stato validato da validate()

		else{
			NewsServiceBD newsService = new NewsServiceBD();
			impostaTipologiaUtenteFormRicerca();
			elencoNews = newsService.recuperaNews(news);
			if(elencoNews.size()==0){				
				addActionMessageGDO(GDOMessaggi.getMessaggio(GDOMessaggi.RICERCA_NESSUN_RISULTATO));
				return INPUT;
			} else{								
				return SUCCESS;
			}
		}
	}



	public void validaForm(){
		if(news != null){
			boolean filtriInseriti = filtriRicercaInseriti();
			if(!filtriInseriti){
				addActionErrorGDO((GDOMessaggi.getMessaggio(GDOMessaggi.FILTRI_NON_INSERITI)));
			}

		}

	}

	private boolean filtriRicercaInseriti() {
		boolean campiValorizzati = false;
		// Anche se non è un filtro di FE mi serve quando chiamo la action di ricerca per ID.
		if(news.getId() != null){
			campiValorizzati = true;
		}
		if(news.getTitolo()!=null && !StringUtils.isEmpty(news.getTitolo())){
			campiValorizzati = true;			
		}
		if(news.getTesto()!=null && !StringUtils.isEmpty(news.getTesto())){
			campiValorizzati = true;				
		}
		if(news.getOwner()!=null && !StringUtils.isEmpty(news.getOwner())){
			campiValorizzati = true;
		}

		if(statoNewsCheck != null && !statoNewsCheck.isEmpty()){
			if(statoNewsCheck.contains(GDOCostantiWeb.OPZIONI_STATO.ATTIVA.getValue()) && statoNewsCheck.contains(GDOCostantiWeb.OPZIONI_STATO.NON_ATTIVA.getValue())){
				campiValorizzati = true;
				news.setStato(null);
			}
			else if(statoNewsCheck.contains(GDOCostantiWeb.OPZIONI_STATO.ATTIVA.getValue())){
				campiValorizzati = true;
				news.setStato(GDOCostantiWeb.OPZIONI_STATO.ATTIVA.getValue());
			}
			else if(statoNewsCheck.contains(GDOCostantiWeb.OPZIONI_STATO.NON_ATTIVA.getValue())){
				campiValorizzati = true;
				news.setStato(GDOCostantiWeb.OPZIONI_STATO.NON_ATTIVA.getValue());
			}
		}
		
		if(news.isVisualizzaPopUp() != null && news.isVisualizzaPopUp()){
			campiValorizzati = true;
		} else if(news.isVisualizzaPopUp() != null && !news.isVisualizzaPopUp()){
			campiValorizzati = true;
			news.setVisualizzaPopUp(null);
		}			

		if(tipologiaUtenteCheck != null && !tipologiaUtenteCheck.isEmpty()){
			if(tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getValue()) && tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getValue()))
			{
				campiValorizzati = true;
				news.setAgente(null);
				news.setEnte(null);
				news.setUtenteEsterno(null);
				news.setAltriUtenti(null);
			}
			else if(tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getValue()) ){
				campiValorizzati = true;
				news.setAgente(true);
				news.setEnte(false);
				news.setUtenteEsterno(false);
				news.setAltriUtenti(false);
			}
			else if(tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getValue())){
				campiValorizzati = true;
				news.setAgente(false);
				news.setEnte(true);
				news.setUtenteEsterno(false);
				news.setAltriUtenti(false);
			}
			else if(tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_ALTRO_UTENTE.getValue()) && (!tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getValue()) && !tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getValue()))){
				campiValorizzati = true;
				news.setAgente(false);
				news.setEnte(false);
				news.setUtenteEsterno(false);
				news.setAltriUtenti(true);
			}
			else if(tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_UTENTE_ESTERNO.getValue()) && (!tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getValue()) && !tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getValue()))){
				campiValorizzati = true;
				news.setAgente(false);
				news.setEnte(false);
				news.setAltriUtenti(false);
				news.setUtenteEsterno(true);
			}
		}
		else {
			news.setAgente(null);
			news.setEnte(null);
			news.setAltriUtenti(null);
			news.setUtenteEsterno(null);
		}


		if(news.getDataInizioPubblicazione()!=null && !StringUtils.isEmpty(news.getDataInizioPubblicazione())){
			campiValorizzati = true;
		}
		if(news.getDataFinePubblicazione()!= null && !StringUtils.isEmpty(news.getDataFinePubblicazione())){
			campiValorizzati = true;
		}
		return campiValorizzati;
	}

	public List<NewsBean> getElencoNews() {
		return elencoNews;
	}

	public void setElencoNews(List<NewsBean> elencoNews) {
		this.elencoNews = elencoNews;
	}


	public List<Integer> getStatoNewsCheck() {
		return statoNewsCheck;
	}

	public void setStatoNewsCheck(List<Integer> statoNewsCheck) {
		this.statoNewsCheck = statoNewsCheck;
	}

	/** 
	 * La versione dell'AbstractNewsAction di impostaTipologiaUtente() 
	 * non va bene perche` qui e` possibile che non sia valorizzato
	 * non va preimpostato a false se non e` selezionato
	 */
	protected void impostaTipologiaUtenteFormRicerca() {
		if(tipologiaUtenteCheck != null && tipologiaUtenteCheck.size() > 0){

			//	FiltroTipologiaBean tipologiaUtente = new FiltroTipologiaBean();
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE.SOLO_AGENTE.getValue()))
				news.setAgente(true);
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getValue()))
				news.setEnte(true);			
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE.SOLO_ALTRO_UTENTE.getValue()))
				news.setAltriUtenti(true);		
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE.SOLO_UTENTE_ESTERNO.getValue()))
				news.setUtenteEsterno(true);		
		}
	}
}
