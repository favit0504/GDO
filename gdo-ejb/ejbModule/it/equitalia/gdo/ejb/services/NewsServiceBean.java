package it.equitalia.gdo.ejb.services;


import it.equitalia.gdo.commons.ejb.impl.NewsServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.NewsServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.Costanti.TipologiaUtente;
import it.equitalia.gdo.commons.valueobjects.NewsBean;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.converters.BeanToModel;
import it.equitalia.gdo.dao.converters.ModelToBean;
import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.News;
import it.equitalia.gdo.dao.oracle.AllegatoOracleDAO;
import it.equitalia.gdo.dao.services.interfaces.AllegatoDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.NewsDAOInterface;
import it.equitalia.gdo.ejb.aspects.ProfilingInterceptor;
import it.equitalia.gdo.svecchiamento.spazio.HostSender;
import it.equitalia.gdo.svecchiamento.spazio.SpazioCommunicatorFactory;
import it.equitalia.gdo.svecchiamento.spazio.http.ejb.GDOInoltroBean;
import it.equitalia.gdo.svecchiamento.spazio.util.GdoInoltroException;
import it.equitalia.gdo.svecchiamento.spazio.util.SvecchiamentoConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
@Stateless(name="ejb/NewsService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NewsServiceBean extends ServiceWithDAOFactory<NewsDAOInterface> implements NewsServiceLocal, NewsServiceRemote {



	private static final Logger logger = Logger.getLogger(NewsServiceBean.class);
	@Resource
	private SessionContext ctx;
	
	@EJB NewsServiceLocal newsService;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public NewsBean aggiornaNews(NewsBean newsBean, boolean mantieniAllegato) throws BusinessException {
		NewsBean updated = null;

		try{			

			//trasformo bean in input in entita
			News news = BeanToModel.execute(newsBean);
			//rimuovo la visualizzazione tramite pop up dell'attuale news visualizzata come tale
			if(news.getVisualizzaPopUp()){
				getDAO().rimuoviVisualizzazionePopUp();				
			}			
			//eseguo operazione update
			news = (News)getDAO().update(news);
			//restituisco il bean senza i filtri [associazioni lazy mantenute]
			updated = ModelToBean.execute(news, false, false);

			if (newsBean.getAllegato() == null && mantieniAllegato) {

				Integer idVersionePrecedenteConAllegato = newsBean.getId();
				Integer idNuovaVersione = updated.getId();

				AllegatoDAOInterface dao = new AllegatoOracleDAO(entityManager);
				dao.mantieniVecchioAllegato(idVersionePrecedenteConAllegato, idNuovaVersione);
			}

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			ctx.setRollbackOnly();
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_UPDATE_NEWS), e);
		}

		return updated;
	}





	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public NewsBean creaNews(NewsBean newsBean) throws BusinessException {
		NewsBean saved = null;

		try{

			NewsDAOInterface newsDao = getDAO();
			newsDao.setEntityManager(this.entityManager);
			News newsDaInserire = BeanToModel.execute(newsBean);
			if(newsDaInserire.getVisualizzaPopUp()){
				 newsDao.rimuoviVisualizzazionePopUp();				
			}
				
			
			News newsSalvata = newsDao.insert(newsDaInserire);
			saved = ModelToBean.execute(newsSalvata, false, false);		

		} catch (DataAccessException dae){
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			//throw new BusinessException(dae.getMessage());
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_INSERT_NEWS), dae);
		}

		return saved;
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<NewsBean> recuperaNews(NewsBean newsBean) throws BusinessException {

		try{
			Map<String, Object> params = new HashMap<String, Object>();
			if(newsBean != null){
				News news = BeanToModel.execute(newsBean);
				if(news.getId() != null){
					params.put(NewsDAOInterface.PARAM_ID_NEWS, news.getId());	
				}
				if(news.getCodice() != null){
					params.put(NewsDAOInterface.PARAM_CODICE_NEWS, news.getCodice());	
				}
				if(news.getTitolo() != null && !StringUtils.isEmpty(newsBean.getTitolo())){
					params.put(NewsDAOInterface.PARAM_TITOLO, news.getTitolo());	
				}

				if(news.getTesto() != null && !StringUtils.isEmpty(newsBean.getTesto())){
					params.put(NewsDAOInterface.PARAM_TESTO, news.getTesto());	
				}

				if(news.getStato() != null){
					params.put(NewsDAOInterface.PARAM_STATO, news.getStato());	
				}
				if(news.getOwner() != null &&  !StringUtils.isEmpty(newsBean.getOwner())){
					params.put(NewsDAOInterface.PARAM_OWNER, news.getOwner());	
				}
				if(news.getDataCreazione() != null){
					params.put(NewsDAOInterface.PARAM_DATA_CREAZIONE, news.getDataCreazione());	
				}
				if(news.getOwnerModifica() != null && !StringUtils.isEmpty(newsBean.getOwnerModifica())){
					params.put(NewsDAOInterface.PARAM_OWNER_MODIFICA, news.getOwnerModifica());	
				}
				if(news.getDataModifica() != null){
					params.put(NewsDAOInterface.PARAM_DATA_MODIFICA, news.getDataModifica());	
				}
				if(news.getDataInizioPubblicazione() != null){
					params.put(NewsDAOInterface.PARAM_DATA_INIZIO_PUBBLICAZIONE,news.getDataInizioPubblicazione());	
				}
				if(news.getDataFinePubblicazione() != null){
					params.put(NewsDAOInterface.PARAM_DATA_FINE_PUBBLICAZIONE, news.getDataFinePubblicazione());	
				}
				if((news.getEnte() != null && news.getAgente() != null && news.getAltriUtenti() != null)){
					if(!(news.getEnte() && news.getAgente() && news.getAltriUtenti())){
						if(news.getEnte()){
							params.put(NewsDAOInterface.PARAM_ENTE, news.getEnte());
						}
						if(news.getAgente()){
							params.put(NewsDAOInterface.PARAM_AGENTE, news.getAgente());
						}	
						if(news.getAltriUtenti()){
							params.put(NewsDAOInterface.PARAM_ALTRI_UTENTI, news.getAltriUtenti());
						}
					}
				}
				if(news.getVisualizzaPopUp() != null)
					params.put(NewsDAOInterface.PARAM_FLG_POP_UP, news.getVisualizzaPopUp());

			}
			NewsDAOInterface newsDao = getDAO();

			List<News> news = newsDao.findByParameter(params);
			List<NewsBean> newsBeanList = new ArrayList<NewsBean>();

			if(news.size() > 0){
				for(News n: news)
					newsBeanList.add(ModelToBean.execute(n, false, false));	
			}

			return newsBeanList;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_NEWS), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public NewsBean recuperaNewsById(Integer id) throws BusinessException {

		try{
			return ModelToBean.execute(getDAO().find(id),true,false);


		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_NEWS), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<NewsBean> recuperaStoricoNewsByCodice(Integer codiceNews) throws BusinessException {

		try{
			Map<String, Object> params = new HashMap<String, Object>();
			if(codiceNews != null) {
				params.put(NewsDAOInterface.PARAM_CODICE_NEWS, codiceNews);
			}
			NewsDAOInterface newsDao = getDAO();

			List<News> news = newsDao.findStoricoNews(params);
			List<NewsBean> newsBeanList = new ArrayList<NewsBean>();

			if(news.size() > 0){
				for(News n: news)
					newsBeanList.add(ModelToBean.execute(n, false, false));	
			}

			return newsBeanList;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_NEWS), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<NewsBean> recuperaNewsAttivePerUtente(TipologiaUtente tipologiaUtente) throws BusinessException {

		try{
			Map<String, Object> params = new HashMap<String, Object>();
			if(tipologiaUtente.getValue().equals(TipologiaUtente.ENTE.getValue())){
				params.put(NewsDAOInterface.PARAM_ENTE, true);
			} else if(tipologiaUtente.getValue().equals(TipologiaUtente.AGENTE.getValue())){
				params.put(NewsDAOInterface.PARAM_AGENTE, true);
			} else if(!tipologiaUtente.getValue().equals(TipologiaUtente.AGENTE.getValue()) && !tipologiaUtente.getValue().equals(TipologiaUtente.ENTE.getValue())){
				params.put(NewsDAOInterface.PARAM_ALTRI_UTENTI, true);
			}
			NewsDAOInterface newsDao = getDAO();

			List<News> news = newsDao.findNewsPerTipologiaUtente(params);
			List<NewsBean> newsBeanList = new ArrayList<NewsBean>();

			if(news.size() > 0){
				for(News n: news)
					newsBeanList.add(ModelToBean.execute(n, true, false));	
			}

			return newsBeanList;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_NEWS), t);
		}

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<NewsBean> recuperaNewsPerSvecchiamentoSenzaAllegato() throws BusinessException {

		try{
			NewsDAOInterface newsDao = getDAO();

			List<News> news = newsDao.findNewsDaSvecchiareSenzaAllegato();
			List<NewsBean> newsBeanList = new ArrayList<NewsBean>();

			if(news.size() > 0){
				for(News n: news)
					newsBeanList.add(ModelToBean.execute(n, false, false));	
			}
			

			return newsBeanList;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_NEWS_FILE_SVECCHIAMENTO), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<NewsBean> recuperaNewsPerSvecchiamento() throws BusinessException {

		try{
			NewsDAOInterface newsDao = getDAO();

			List<News> news = newsDao.findBlobPerSvecchiamento();
			List<NewsBean> newsBeanList = new ArrayList<NewsBean>();

			if(news.size() > 0){
				for(News n: news)
					newsBeanList.add(ModelToBean.execute(n, false, true));	
			}
			

			return newsBeanList;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_NEWS_FILE_SVECCHIAMENTO), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public boolean svecchiaNews() throws BusinessException {
		boolean result = false;
		NewsDAOInterface newsDao = getDAO();

		try{
			//Recupero blob da svecchiare
			List<NewsBean> newsList = recuperaNewsPerSvecchiamento();

			if(newsList != null && newsList.size() > 0){
				for (NewsBean newsBean: newsList) {
					if(newsBean.getAllegato().getContenutoFile() != null && newsBean.getAllegato().getContenutoFile().length > 0){
						byte[] fileUploadContent = newsBean.getAllegato().getContenutoFile();
						String nomeFileUpload = newsBean.getAllegato().getNomeFile();

						if(GDOConfig.getInstance().getProperty(GDOConfig.TIPO_CODA_SPAZIO).equals("HTTP")){
							
							result = newsService.invocaSpazioHTTP(newsBean, fileUploadContent, nomeFileUpload);
							
						} else if(GDOConfig.getInstance().getProperty(GDOConfig.TIPO_CODA_SPAZIO).equals("JMS")){
							
							result = newsService.invocaSpazioJMS(newsBean, fileUploadContent, nomeFileUpload);
						}
					} 
					else {
						logger.info(SvecchiamentoConstants.BLOB_NEWS_EMPTY);
						result = true;
					}
				}
			}
			else {
				logger.info(SvecchiamentoConstants.NO_NEWS_FILE_FOUND);
				result = true;
			}
			
			//Svecchio altre (eventuali) news che non hanno allegati
			List<NewsBean> newsSenzaAllegatoList = recuperaNewsPerSvecchiamentoSenzaAllegato();
			for (NewsBean n: newsSenzaAllegatoList) {
				newsDao.impostaComeSvecchiata(n.getId());
				logger.info("La news senza allegato con id " + n.getId() + " è stata contrassegnata come svecchiata.");
			}

			return result;


		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			ctx.setRollbackOnly();
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_NEWS_FILE_SVECCHIAMENTO), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Interceptors({ProfilingInterceptor.class})
	public boolean invocaSpazioJMS(NewsBean newsBean, byte[] fileUploadContent, String nomeFileUpload) {
		boolean result = false;
		
		try{
			// ------------OPERAZIONI PRELIMINARI------------START
			//Cancello il blob e valorizzo flag svecchiato=true
			newsBean.getAllegato().setContenutoFile(null);
			newsBean.setSvecchiata(true);
			News newsDaAggiornare = BeanToModel.execute(newsBean);
			
			int updated = getDAO().eliminaBlobAllegato(newsDaAggiornare.getAllegato().getId());
			if(updated > 0)
				getDAO().impostaComeSvecchiata(newsDaAggiornare.getId());
			// ------------OPERAZIONI PRELIMINARI------------END
			
			//Invio file ad Host (Spazio Primeur)
			HostSender sender = null;
			boolean salta_coda = GDOConfig.getInstance().getProperty(GDOConfig.SALTA_CODA).equals("1") ? true:false;
			if(salta_coda){
				sender = SpazioCommunicatorFactory.getFactory(SpazioCommunicatorFactory.TipoComunicatore.TEST_SPAZIOJMS).getTestSender();	
			} else {
				sender = SpazioCommunicatorFactory.getFactory(SpazioCommunicatorFactory.TipoComunicatore.SPAZIOJMS).getSender();
			}
			
			if(sender == null)
				throw new BusinessException(SvecchiamentoConstants.SENDER_ERROR);
			
			String correlationId = GDOConfig.NEWS_CORRELATION_ID + newsBean.getId() + "-" + nomeFileUpload;
			if(correlationId.length() > 24){
				correlationId = correlationId.substring(0, 24);
			}
			
			sender.send(fileUploadContent, nomeFileUpload, correlationId, salta_coda);

			result = true;
			logger.info("La news con id " + newsBean.getId() + " e correlationId " + correlationId + " è stata svecchiata correttamente.");
			
		} catch (BusinessException be){
			ctx.setRollbackOnly();
			logger.error(be.getMessage(), be);
		}
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Interceptors({ProfilingInterceptor.class})
	public boolean invocaSpazioHTTP(NewsBean newsBean, byte[] fileUploadContent, String nomeFileUpload) {
		boolean result = false;
		
		try{
			// ------------OPERAZIONI PRELIMINARI------------START
			//Cancello il blob e valorizzo flag svecchiato=true
			newsBean.getAllegato().setContenutoFile(null);
			newsBean.setSvecchiata(true);
			News newsDaAggiornare = BeanToModel.execute(newsBean);
			
			int updated = getDAO().eliminaBlobAllegato(newsDaAggiornare.getAllegato().getId());
			if(updated > 0)
				getDAO().impostaComeSvecchiata(newsDaAggiornare.getId());
			// ------------OPERAZIONI PRELIMINARI------------END
			
			//Invio file Spazio via HTTP
			GDOInoltroBean inoltro = new GDOInoltroBean();
			inoltro.inoltroFile(fileUploadContent, nomeFileUpload);
			
			result = true;
			logger.info("La news con id " + newsBean.getId() + " è stata svecchiata correttamente.");
			
			
		} catch (GdoInoltroException gdo){
			ctx.setRollbackOnly();
			logger.error(gdo.getMessage(), gdo);
		}
		return result;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Interceptors({ProfilingInterceptor.class})
	public List<NewsBean> recuperaNewsPopUp() {
		List<NewsBean> newsBeanList = null;
		List<News> newsPopUp = getDAO().findNewsPopUp();
		if(newsPopUp.size() > 0){
			newsBeanList = new ArrayList<NewsBean>();
			for(News n: newsPopUp)
				newsBeanList.add(ModelToBean.execute(n, false, false));	
		}
		return newsBeanList;
	}


	@Override
	protected NewsDAOInterface getSpecificDAO(AbstractDAOFactory adf) {
		return adf.getNewsDAO();
	}





//	@Override
//	public List<NewsBean> recuperaNewsAttivePerAltriUtenti(
//			TipologiaUtente tipologiaUtente) throws BusinessException {
//		// TODO Stub di metodo generato automaticamente
//		return null;
//	}








}