package it.equitalia.gdo.web.actions.sezione;

import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.actions.news.RicercaNewsAction;
import it.equitalia.gdo.web.businessdelegate.SezioneServiceBD;
import it.equitalia.gdo.web.util.GDOCostantiWeb;
import it.equitalia.gdo.web.util.PopolaCampiSezione;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Preparable;

public class RicercaSezioneAction extends AbstractSezioneAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(RicercaNewsAction.class);
	
	private List<SezioneBean> elencoSezioni;
	private List<Integer> statoSezioneCheck;
	
	public Integer[] getDefaultValue(){
		return new Integer[] {GDOCostantiWeb.OPZIONI_STATO_DOCUMENTI.ATTIVO.getValue()};
	}
	
	public String execute() throws Exception {
		
		// Form ricerca non inviato: visualizzazione
		if(sezione == null){
			
			p = new PopolaCampiSezione(getSession());
			p.popolaStatoSezione();
			
			return INPUT;
		}
		else {
			// Form ricerca inviato: salvo risultato già validato
			SezioneServiceBD sezioneService = new SezioneServiceBD();
			elencoSezioni = sezioneService.recuperaSezione(sezione);
			if(elencoSezioni != null && elencoSezioni.size() == 0){
				addActionMessageGDO(GDOMessaggi.getMessaggio(GDOMessaggi.RICERCA_NESSUN_RISULTATO));
				return INPUT;
			} else {
				return SUCCESS;
			}
			
		}
		
	}
	
	public void validaForm(){
		if(sezione != null){
			boolean filtriInseriti = filtriRicercaInseriti();
			if(!filtriInseriti){
				addActionErrorGDO((GDOMessaggi.getMessaggio(GDOMessaggi.FILTRI_NON_INSERITI)));
			}

		}

	}
	
	private boolean filtriRicercaInseriti() {
		boolean campiValorizzati = false;
		// Anche se non è un filtro di FE mi serve quando chiamo la action di ricerca per ID.
		if(sezione.getId() != null){
			campiValorizzati = true;
		}
		if(sezione.getTitolo()!=null && !StringUtils.isEmpty(sezione.getTitolo())){
			campiValorizzati = true;			
		}
		if(sezione.getDescrizione()!=null && !StringUtils.isEmpty(sezione.getDescrizione())){
			campiValorizzati = true;				
		}
		
		if(statoSezioneCheck != null && !statoSezioneCheck.isEmpty()){
			if(statoSezioneCheck.contains(GDOCostantiWeb.OPZIONI_STATO.ATTIVA.getValue()) && statoSezioneCheck.contains(GDOCostantiWeb.OPZIONI_STATO.NON_ATTIVA.getValue())){
				campiValorizzati = true;
				sezione.setStato(null);
			}
			else if(statoSezioneCheck.contains(GDOCostantiWeb.OPZIONI_STATO.ATTIVA.getValue())){
				campiValorizzati = true;
				sezione.setStato(GDOCostantiWeb.OPZIONI_STATO.ATTIVA.getValue());
			}
			else if(statoSezioneCheck.contains(GDOCostantiWeb.OPZIONI_STATO.NON_ATTIVA.getValue())){
				campiValorizzati = true;
				sezione.setStato(GDOCostantiWeb.OPZIONI_STATO.NON_ATTIVA.getValue());
			}
		}
		
		return campiValorizzati;
	}
	

	public List<Integer> getStatoSezioneCheck() {
		return statoSezioneCheck;
	}

	public void setStatoSezioneCheck(List<Integer> statoSezioneCheck) {
		this.statoSezioneCheck = statoSezioneCheck;
	}

	public List<SezioneBean> getElencoSezioni() {
		return elencoSezioni;
	}

	public void setElencoSezioni(List<SezioneBean> elencoSezioni) {
		this.elencoSezioni = elencoSezioni;
	}
	
	
}
