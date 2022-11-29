package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.Costanti.TipologiaUtente;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;

import java.util.List;



public interface NewsFrontendService {
		
	List<NewsBean> recuperaNewsAttiveDestinateAdUtenteEnte(String chiaveUtente, TipologiaUtente tipologiaUtente) throws BusinessException;
	List<NewsBean> recuperaNewsAttiveDestinateAdUtenteAgente(String chiaveUtente, TipologiaUtente tipologiaUtente) throws BusinessException;
	List<NewsBean> recuperaNewsAttivePerAltriUtenti(String chiaveUtente,TipologiaUtente tipologiaUtente) throws BusinessException;
	List<NewsBean> recuperaNewsAttivePerUtenteEsterno(String chiaveUtente, TipologiaUtente altriutenti)  throws BusinessException;
	Boolean popolaEValutaUtente(UtenteBean utenteBean, NewsBean newsBean) throws BusinessException;

}
