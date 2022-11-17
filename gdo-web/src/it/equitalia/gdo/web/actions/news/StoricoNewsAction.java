package it.equitalia.gdo.web.actions.news;

import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.web.businessdelegate.NewsServiceBD;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Preparable;

public class StoricoNewsAction extends AbstractNewsAction  implements Preparable {		

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(StoricoNewsAction.class);

	private List<NewsBean> elencoNews;

	public String execute() throws Exception {
		
		if(news != null && news.getCodice() != null){
			NewsServiceBD newsService = new NewsServiceBD();
			elencoNews = newsService.recuperaStoricoNewsByCodice(news.getCodice());
			
			if(elencoNews.size()==0){
				addActionMessageGDO(GDOMessaggi.getMessaggio(GDOMessaggi.RICERCA_NESSUN_RISULTATO));
				
			}
			return SUCCESS;

		} else {
			//messaggio non correttissimo, ma non si presenta mai se l'utente non si inventa qualcosa
			addActionMessageGDO(GDOMessaggi.getMessaggio(GDOMessaggi.RICERCA_NESSUN_RISULTATO));
			return ERROR;
			
		}
		
	}
	

	public List<NewsBean> getElencoNews() {
		return elencoNews;
	}

	public void setElencoNews(List<NewsBean> elencoNews) {
		this.elencoNews = elencoNews;
	}



}
