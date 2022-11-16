package it.equitalia.gdo.ejb.services;


import it.equitalia.gdo.commons.ejb.impl.NewsFrontendServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.NewsFrontendServiceRemote;
import it.equitalia.gdo.commons.ejb.impl.NewsServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.PopolamentoUtenteServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.ValutazioneUtenteServiceLocal;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.Costanti.TipologiaUtente;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

@SuppressWarnings("unused")
@Stateless(name="ejb/NewsFrontendService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NewsFrontendServiceBean implements NewsFrontendServiceLocal, NewsFrontendServiceRemote {
	
	
	private static final Logger logger = Logger.getLogger(NewsFrontendServiceBean.class);
	
	@Resource
	private SessionContext ctx;
	
	@EJB NewsServiceLocal newsService;
	@EJB PopolamentoUtenteServiceLocal popolamentoUtenteService;
	@EJB ValutazioneUtenteServiceLocal valutazioneUtenteService;

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<NewsBean> recuperaNewsAttiveDestinateAdUtenteEnte(String chiaveUtente, TipologiaUtente tipologiaUtente) throws BusinessException {

		List<NewsBean> newsFiltrate = new LinkedList<NewsBean>();
		
		try {
			List<NewsBean> newsValideEdAttivePerUtenza = newsService.recuperaNewsAttivePerUtente(tipologiaUtente);
			
			UtenteBean utenteBean = new UtenteBean();
			utenteBean.setUsd(chiaveUtente);
			utenteBean = popolamentoUtenteService.invocaPopolamentoUtente(utenteBean);
			
			Iterator<NewsBean> it = newsValideEdAttivePerUtenza.iterator();
									
			while (it.hasNext()) {
				NewsBean newsBean = it.next();
				if (valutazioneUtenteService.valutaUtente(utenteBean, newsBean)) {
					rimuoviFiltri(newsBean);
					newsFiltrate.add(newsBean);				
				}
			}
		}
		catch(Exception e) {
			logger.error("Errore in fase di recupero delle news Agente per " + chiaveUtente, e);
		}
		 return newsFiltrate;
	}
	
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<NewsBean> recuperaNewsAttiveDestinateAdUtenteAgente(String chiaveUtente, TipologiaUtente tipologiaUtente) throws BusinessException {

		//devo restituire una lista anche vuota, altrimenti la JSP di SSO va in eccezione
		List<NewsBean> newsFiltrate = new LinkedList<NewsBean>();
		try {
			List<NewsBean> newsValideEdAttivePerUtenza = newsService.recuperaNewsAttivePerUtente(tipologiaUtente);
			
			UtenteBean utenteBean = new UtenteBean();
			utenteBean.setUsd(chiaveUtente);
			utenteBean = popolamentoUtenteService.invocaPopolamentoUtente(utenteBean);
			
			
			Iterator<NewsBean> it = newsValideEdAttivePerUtenza.iterator();		
			
			
			while (it.hasNext()) {
				NewsBean newsBean = it.next();
				if (valutazioneUtenteService.valutaUtente(utenteBean, newsBean)) {
					rimuoviFiltri(newsBean);
					newsFiltrate.add(newsBean);
				}
			}
			 
		}
		catch(Exception e) {
			logger.error("Errore in fase di recupero delle news Agente per " + chiaveUtente, e);
		}
		
		return newsFiltrate;
	}

	public Boolean popolaEValutaUtente(UtenteBean utenteBean, NewsBean newsBean) throws BusinessException{
		UtenteBean utente = popolamentoUtenteService.invocaPopolamentoUtente(utenteBean);
		if(utente != null){
			return valutazioneUtenteService.valutaUtente(utente, newsBean);
		}
		return false;
	}

	/* Metodo di utilita` */
	private void rimuoviFiltri(NewsBean newsBean) {
		newsBean.setFiltroEnte(null);
		newsBean.setFiltroAmbito(null);
		newsBean.setFiltroProvinciaEnte(null);
		newsBean.setFiltroRegioneAgente(null);
		newsBean.setFiltroRegioneEnte(null);
		newsBean.setFiltroServizioAgente(null);
		newsBean.setFiltroServizioEnte(null);
		newsBean.setFiltroSocieta(null);	
	}
  
}