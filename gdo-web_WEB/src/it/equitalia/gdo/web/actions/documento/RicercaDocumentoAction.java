package it.equitalia.gdo.web.actions.documento;

import it.equitalia.gdo.commons.utils.Costanti;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.Costanti.OPZIONI_TIPOLOGIA_UTENTE;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.web.actions.news.RicercaNewsAction;
import it.equitalia.gdo.web.businessdelegate.DocumentoServiceBD;
import it.equitalia.gdo.web.util.GDOCostantiWeb;
import it.equitalia.gdo.web.util.PopolaFiltriDocumento;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Preparable;

public class RicercaDocumentoAction extends AbstractDocumentoAction implements Preparable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(RicercaNewsAction.class);

	private List<DocumentoBean> elencoDocumenti;
	private List<Integer> statoDocumentoCheck;

	public Integer[] getDefaultValue(){
		return new Integer[] {GDOCostantiWeb.OPZIONI_STATO_DOCUMENTI.ATTIVO.getValue()};
	}

	public String execute() throws Exception {

		//Form non inviato - visualizza il form
		if(documento == null){
			p = new PopolaFiltriDocumento(getSession());
			p.popolaFiltriRicercaDocumento();
			
			return INPUT;
			
		} else {
			// Form ricerca inviato: salvo risultato già validato
			DocumentoServiceBD service = new DocumentoServiceBD();
			impostaTipologiaUtenteFormRicerca();
			elencoDocumenti = service.recuperaDocumento(documento);
			if(elencoDocumenti.size() == 0){
				addActionMessageGDO(GDOMessaggi.getMessaggio(GDOMessaggi.RICERCA_NESSUN_RISULTATO));
				return INPUT;
			} else {
				return SUCCESS;
			}
		}

	}

	public void validaForm(){
		if(documento != null){
			boolean filtriInseriti = filtriRicercaInseriti();
			if(!filtriInseriti){
				addActionErrorGDO((GDOMessaggi.getMessaggio(GDOMessaggi.FILTRI_NON_INSERITI)));
			}

		}

	}

	private boolean filtriRicercaInseriti() {
		boolean campiValorizzati = false;
		// Anche se non è un filtro di FE mi serve quando chiamo la action di ricerca per ID.
		if(documento.getId() != null){
			campiValorizzati = true;
		}
		if(documento.getTitolo()!=null && !StringUtils.isEmpty(documento.getTitolo())){
			campiValorizzati = true;			
		}
		if(documento.getDescrizione()!=null && !StringUtils.isEmpty(documento.getDescrizione())){
			campiValorizzati = true;				
		}
		if(documento.getTitoloSezione()!=null && !StringUtils.isEmpty(documento.getTitoloSezione())){
			campiValorizzati = true;				
		}

		if(tipologiaUtenteCheck != null && !tipologiaUtenteCheck.isEmpty()){
			if(tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO.SOLO_AGENTE.getValue()) && tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO.SOLO_ENTE.getValue()))
			{
				campiValorizzati = true;
				documento.setAgente(null);
				documento.setEnte(null);
			}
			else if(tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO.SOLO_AGENTE.getValue()) ){
				campiValorizzati = true;
				documento.setAgente(true);
				documento.setEnte(false);
			}
			else if(tipologiaUtenteCheck.contains(Costanti.OPZIONI_TIPOLOGIA_UTENTE.SOLO_ENTE.getValue())){
				campiValorizzati = true;
				documento.setAgente(false);
				documento.setEnte(true);
			}
		}
		
		if(statoDocumentoCheck != null && !statoDocumentoCheck.isEmpty()){
			if(statoDocumentoCheck.contains(GDOCostantiWeb.OPZIONI_STATO_DOCUMENTI.ATTIVO.getValue()) && statoDocumentoCheck.contains(GDOCostantiWeb.OPZIONI_STATO_DOCUMENTI.NON_ATTIVO.getValue())){
				campiValorizzati = true;
				documento.setStato(null);
			}
			else if(statoDocumentoCheck.contains(GDOCostantiWeb.OPZIONI_STATO_DOCUMENTI.ATTIVO.getValue())){
				campiValorizzati = true;
				documento.setStato(GDOCostantiWeb.OPZIONI_STATO_DOCUMENTI.ATTIVO.getValue());
			}
			else if(statoDocumentoCheck.contains(GDOCostantiWeb.OPZIONI_STATO_DOCUMENTI.NON_ATTIVO.getValue())){
				campiValorizzati = true;
				documento.setStato(GDOCostantiWeb.OPZIONI_STATO_DOCUMENTI.NON_ATTIVO.getValue());
			}
		}

		return campiValorizzati;
	}

	public List<Integer> getStatoDocumentoCheck() {
		return statoDocumentoCheck;
	}

	public void setStatoDocumentoCheck(List<Integer> statoDocumentoCheck) {
		this.statoDocumentoCheck = statoDocumentoCheck;
	}

	public List<DocumentoBean> getElencoDocumenti() {
		return elencoDocumenti;
	}

	public void setElencoDocumenti(List<DocumentoBean> elencoDocumenti) {
		this.elencoDocumenti = elencoDocumenti;
	}

	/** 
	 * La versione dell'AbstractNewsAction di impostaTipologiaUtente() 
	 * non va bene perche` qui e` possibile che non sia valorizzato
	 * non va preimpostato a false se non e` selezionato
	 */
	protected void impostaTipologiaUtenteFormRicerca() {
		if(tipologiaUtenteCheck != null && tipologiaUtenteCheck.size() > 0){

			// FiltroTipologiaBean tipologiaUtente = new FiltroTipologiaBean();
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO.SOLO_AGENTE.getValue()))
				documento.setAgente(true);
			if(tipologiaUtenteCheck.contains(OPZIONI_TIPOLOGIA_UTENTE_DOCUMENTO.SOLO_ENTE.getValue()))
				documento.setEnte(true);								
		}
	}


}
