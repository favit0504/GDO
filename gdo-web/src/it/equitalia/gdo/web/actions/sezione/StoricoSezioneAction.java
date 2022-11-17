package it.equitalia.gdo.web.actions.sezione;

import java.util.List;

import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.web.businessdelegate.SezioneServiceBD;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Preparable;

public class StoricoSezioneAction extends AbstractSezioneAction implements Preparable {
	
private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(StoricoSezioneAction.class);
	
	private List<SezioneBean> elencoSezioni;

	public String execute() throws Exception {
		
		if(sezione != null && sezione.getCodice() != null){
			SezioneServiceBD sezioneService = new SezioneServiceBD();
			elencoSezioni = sezioneService.recuperaStroricoByCodice(sezione.getCodice());
			
			if(elencoSezioni.size()==0){
				addActionMessageGDO(GDOMessaggi.getMessaggio(GDOMessaggi.RICERCA_NESSUN_RISULTATO));
				
			}
			return SUCCESS;

		} else {
			//messaggio non correttissimo, ma non si presenta mai se l'utente non si inventa qualcosa
			addActionMessageGDO(GDOMessaggi.getMessaggio(GDOMessaggi.RICERCA_NESSUN_RISULTATO));
			return ERROR;
			
		}
	}

	public List<SezioneBean> getElencoSezioni() {
		return elencoSezioni;
	}

	public void setElencoSezioni(List<SezioneBean> elencoSezioni) {
		this.elencoSezioni = elencoSezioni;
	}

	

}
