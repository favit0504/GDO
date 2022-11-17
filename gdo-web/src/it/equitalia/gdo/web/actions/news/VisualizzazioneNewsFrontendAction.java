package it.equitalia.gdo.web.actions.news;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.valueobjects.AllegatoBean;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;
import it.equitalia.gdo.web.actions.AbstractBaseAction;
import it.equitalia.gdo.web.businessdelegate.AllegatoServiceBD;
import it.equitalia.gdo.web.businessdelegate.NewsFrontendServiceBD;
import it.equitalia.gdo.web.businessdelegate.NewsServiceBD;

import com.opensymphony.xwork2.Preparable;

public class VisualizzazioneNewsFrontendAction extends AbstractBaseAction  implements Preparable {
	
	private static final long serialVersionUID = 1L;	
	private static Logger log = Logger.getLogger(VisualizzazioneNewsFrontendAction.class);
	
	private String id;	
	private NewsBean news;
	private String filename;
	private InputStream fileInputStream;
	private static String titoloPagina = "Dettaglio news";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setNews(NewsBean news) {
		this.news = news;
	}

	public NewsBean getNews() {
		return news;
	}

	public String getTitoloPagina() {
		return titoloPagina;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	

	private boolean valutaAbilitazioneUtente(int idNews) throws BusinessException {		
					
		NewsServiceBD newsBD = new NewsServiceBD();
		news = newsBD.recuperaNewsById(idNews);
					
		String usd = getUtente();
		//String usd = "C014003";
		UtenteBean utenteBean = new UtenteBean();
		utenteBean.setUsd(usd);
		NewsFrontendServiceBD newsFrontendServiceBD = new NewsFrontendServiceBD();
		boolean abilitato = newsFrontendServiceBD.popolaEValutaUtente(utenteBean, news);
		return abilitato;
		
	}
	
	public String download() throws Exception{
		HttpServletRequest request =  ServletActionContext.getRequest();		
		log.info("Download allegato frontend, parametro id=" + Arrays.asList( request.getParameter("id") ));
		
		if(id!=null){
			try{
			Integer idNews = Integer.parseInt(this.id);
			boolean abilitato = valutaAbilitazioneUtente(idNews);	
			if(abilitato){
				if(news!=null && news.getAllegato()!= null){
					int idAllegato = news.getAllegato().getId();
					
					AllegatoServiceBD service = new AllegatoServiceBD();
					
					AllegatoBean allegato = service.recuperaAllegato(idAllegato);
					if (allegato != null) {
						
						filename = allegato.getNomeFile();
						fileInputStream = new ByteArrayInputStream(allegato.getContenutoFile());				
						return SUCCESS;
					}					
					else 
					{
						addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_ALLEGATO_NON_TROVATO));
						return ERROR;
					}
				}
				else {
					addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_ALLEGATO_NON_TROVATO));
					return ERROR;
				}
			} 
			else {
				addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.NEWS_NON_VSUALIZZABILE));
				return ERROR;
			}
			} catch (BusinessException e) {
				log.error("Errore durante il reperimento dell'allegato", e);
				addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_ALLEGATO));
				return ERROR;
			}
			catch (Exception e) {
				log.error("Errore durante il reperimento dell'allegato", e);
				addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_ALLEGATO));
				return ERROR;
			}
		}
		else 
		{
			addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_ALLEGATO_NON_TROVATO));
			return ERROR;
		}
	}


	public void prepare() throws Exception {
		//Stub di metodo generato automaticamente		
	}
	
	//Al momento la visualizzazione Frontend non e` utilizzata, si utilizza popup JS
	/*
	public String visualizza() throws Exception {		
		
		if (id != null)		
		{
			try{
				Integer idNews = Integer.parseInt(this.id);
				boolean abilitato = valutaAbilitazioneUtente(idNews);
				if(abilitato){	
					if( news != null && news.getTitolo()!= null && news.getTitolo().trim().length() != 0)
						titoloPagina = news.getTitolo();
					return SUCCESS;
					
				}
				else {
					addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.NEWS_NON_VSUALIZZABILE));
					return ERROR;
				}	
			}catch (BusinessException e) {
				log.error("Errore durante il reperimento della news id="+id, e);
				addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_NEWS));
				return ERROR;
			}
			catch (Exception e) {
				log.error("Errore durante il reperimento della news id="+id, e);
				addActionErrorGDO(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_NEWS));
				return ERROR;
			}
		}
		else {
			addFieldError("errori-form", GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_NEWS));
			return ERROR;
		}
		
		
	}*/

}
