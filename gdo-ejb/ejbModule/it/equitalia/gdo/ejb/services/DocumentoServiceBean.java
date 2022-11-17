package it.equitalia.gdo.ejb.services;


import it.equitalia.gdo.commons.ejb.impl.DocumentoServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.DocumentoServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.GDOConfig;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.StringUtils;
import it.equitalia.gdo.commons.utils.Costanti.TipologiaUtente;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.converters.BeanToModel;
import it.equitalia.gdo.dao.converters.ModelToBean;
import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Documento;
import it.equitalia.gdo.dao.services.interfaces.DocumentoDAOInterface;
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

import org.apache.log4j.Logger;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
@Stateless(name="ejb/DocumentoService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DocumentoServiceBean extends ServiceWithDAOFactory<DocumentoDAOInterface> implements DocumentoServiceLocal, DocumentoServiceRemote {

	private static final Logger logger = Logger.getLogger(DocumentoServiceBean.class);
	@Resource
	private SessionContext ctx;
	
	@EJB DocumentoServiceLocal documentoService;


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public DocumentoBean aggiornaDocumento(DocumentoBean documentoBean, SezioneBean sezioneBean, boolean mantieniDocumento) throws BusinessException {
		DocumentoBean updated = null;

		try{
			DocumentoDAOInterface documentoDao = getDAO();
			if(mantieniDocumento){
				documentoBean.setBlob(recuperaFileByIdDocumento(documentoBean.getId()).getBlob());
			}
			Documento docDaAggiornare = BeanToModel.execute(documentoBean);
			docDaAggiornare.setSezione(BeanToModel.execute(sezioneBean));
			Documento docAggiornato = documentoDao.update(docDaAggiornare);
			updated = ModelToBean.execute(docAggiornato, false, false);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			ctx.setRollbackOnly();
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_UPDATE_DOCUMENTO), e);
		}
		return updated;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public DocumentoBean creaDocumento(DocumentoBean documentoBean, SezioneBean sezioneBean) throws BusinessException {
		DocumentoBean saved = null;

		try{
			DocumentoDAOInterface documentoDao = getDAO();
			Documento docDaInserire = BeanToModel.execute(documentoBean);
			docDaInserire.setSezione(BeanToModel.execute(sezioneBean));
			Documento docSalvato = documentoDao.insert(docDaInserire);
			saved = ModelToBean.execute(docSalvato, false, false);

		} catch (DataAccessException dae){
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			//throw new BusinessException(dae.getMessage());
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_INSERT_DOCUMENTO), dae);
		}
		return saved;
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<DocumentoBean> recuperaDocumento(DocumentoBean documentoBean) throws BusinessException {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			if(documentoBean != null){
				if(documentoBean.getId() != null){
					params.put(DocumentoDAOInterface.PARAM_ID_DOCUMENTO, documentoBean.getId());	
				}
				if(documentoBean.getCodice() != null) {
					params.put(DocumentoDAOInterface.PARAM_CODICE_DOCUMENTO, documentoBean.getCodice());	
				}
				if(documentoBean.getTitolo() != null && !StringUtils.isBlank(documentoBean.getTitolo())){
					params.put(DocumentoDAOInterface.PARAM_TITOLO, documentoBean.getTitolo());	
				}
				if(documentoBean.getDescrizione() != null && !StringUtils.isBlank(documentoBean.getDescrizione())){
					params.put(DocumentoDAOInterface.PARAM_DESCRIZIONE, documentoBean.getDescrizione());	
				}
				if(documentoBean.getStato() != null) {
					params.put(DocumentoDAOInterface.PARAM_STATO, documentoBean.getStato());	
				}
				if(documentoBean.getDataCreazione() != null){
					params.put(DocumentoDAOInterface.PARAM_DATA_CREAZIONE, documentoBean.getDataCreazione());	
				}
				if(documentoBean.getOwnerModifica() != null){
					params.put(DocumentoDAOInterface.PARAM_OWNER_MODIFICA, documentoBean.getOwnerModifica());	
				}
				if(documentoBean.getDataModifica() != null){
					params.put(DocumentoDAOInterface.PARAM_DATA_MODIFICA, documentoBean.getDataModifica());	
				}
				if((documentoBean.getEnte() != null && documentoBean.getAgente() != null)){
					if(!(documentoBean.getEnte() && documentoBean.getAgente())){
						if(documentoBean.getEnte()){
							params.put(DocumentoDAOInterface.PARAM_ENTE, documentoBean.getEnte());
						}
						if(documentoBean.getAgente()){
							params.put(DocumentoDAOInterface.PARAM_AGENTE, documentoBean.getAgente());
						}
					}
				}

				if(documentoBean.getTitoloSezione() != null && documentoBean.getTitoloSezione() != null){
					params.put(DocumentoDAOInterface.PARAM_TITOLO_SEZIONE, documentoBean.getTitoloSezione());	
				}
			}

			List<Documento> docs = getDAO().findByParameter(params);
			List<DocumentoBean> docBeanList = new ArrayList<DocumentoBean>();

			if(docs.size() > 0){
				for(Documento d: docs)
					docBeanList.add(ModelToBean.execute(d, false, false));	
			}

			return docBeanList;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_DOCUMENTO), t);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public DocumentoBean recuperaDocumentoById(Integer id) throws BusinessException {

		try{
			return ModelToBean.execute(getDAO().find(id), true, false);


		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_DOCUMENTO), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public DocumentoBean recuperaFileByIdDocumento(Integer id) throws BusinessException {

		try{
			return ModelToBean.execute(getDAO().find(id), false, true);


		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_DOCUMENTO), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<DocumentoBean> recuperaStoricoDocumentoByCodice(Integer codiceDocumento) throws BusinessException {

		try{
			Map<String, Object> params = new HashMap<String, Object>();
			if(codiceDocumento != null) {
				params.put(DocumentoDAOInterface.PARAM_CODICE_DOCUMENTO, codiceDocumento);
			}
			DocumentoDAOInterface documentoDao = getDAO();

			List<Documento> documenti = documentoDao.findStoricoDocumento(params);
			List<DocumentoBean> documentiBeanList = new ArrayList<DocumentoBean>();

			if(documenti.size() > 0){
				for(Documento d: documenti)
					documentiBeanList.add(ModelToBean.execute(d, false, false));	
			}

			return documentiBeanList;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_DOCUMENTO), t);
		}

	}

	@Override
	protected DocumentoDAOInterface getSpecificDAO(AbstractDAOFactory adf) {
		return adf.getDocumentoDAO();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<DocumentoBean> recuperaDocumentiAttiviPerUtente(TipologiaUtente tipologiaUtente) throws BusinessException {

		try{
			Map<String, Object> params = new HashMap<String, Object>();
			if(tipologiaUtente != null){
				if(tipologiaUtente.getValue().equals(TipologiaUtente.ENTE.getValue())){
					params.put(DocumentoDAOInterface.PARAM_ENTE, true);
				} else if(tipologiaUtente.getValue().equals(TipologiaUtente.AGENTE.getValue())){
					params.put(DocumentoDAOInterface.PARAM_AGENTE, true);
				}
			}
			DocumentoDAOInterface documentoDao = getDAO();

			List<Documento> documenti = documentoDao.findDocumentiPerTipologiaUtente(params);
			List<DocumentoBean> documentiBeanList = new ArrayList<DocumentoBean>();

			if(documenti.size() > 0){
				for(Documento d: documenti)
					documentiBeanList.add(ModelToBean.execute(d, true, false));	
			}

			return documentiBeanList;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_DOCUMENTO), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public List<DocumentoBean> recuperaDocumentiPerSvecchiamento() throws BusinessException {

		try{
			DocumentoDAOInterface documentoDao = getDAO();

			List<Documento> docs = documentoDao.findBlobPerSvecchiamento();
			List<DocumentoBean> docBeanList = new ArrayList<DocumentoBean>();

			if(docs.size() > 0){
				for(Documento doc: docs)
					docBeanList.add(ModelToBean.execute(doc, false, true));	
			}

			return docBeanList;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_DOC_FILE_SVECCHIAMENTO), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public boolean svecchiaDocumenti() throws BusinessException {
		boolean result = false;

		try{
			//Recupero blob da svecchiare
			List<DocumentoBean> docsList = recuperaDocumentiPerSvecchiamento();

			if(docsList != null && docsList.size() > 0){
				for (DocumentoBean docBean: docsList) {
					if(docBean.getBlob().getContenutoFile() != null && docBean.getBlob().getContenutoFile().length > 0){
						byte[] fileUploadContent = docBean.getBlob().getContenutoFile();
						String nomeFileUpload = docBean.getNomeFile();

						if(GDOConfig.getInstance().getProperty(GDOConfig.TIPO_CODA_SPAZIO).equals("HTTP")){

							result = documentoService.invocaSpazioHTTP(docBean, fileUploadContent, nomeFileUpload);

						} else if(GDOConfig.getInstance().getProperty(GDOConfig.TIPO_CODA_SPAZIO).equals("JMS")){
							
							result = documentoService.invocaSpazioJMS(docBean, fileUploadContent, nomeFileUpload);
						}
					}
					else {
						logger.info(SvecchiamentoConstants.BLOB_DOC_EMPTY);
						result = true;
					}
				}
			} 
			else {
				logger.info(SvecchiamentoConstants.NO_DOC_FILE_FOUND);
				result = true;
			}

			return result;

		} catch (DataAccessException dae) {
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_DOC_FILE_SVECCHIAMENTO), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Interceptors({ProfilingInterceptor.class})
	public boolean invocaSpazioJMS(DocumentoBean docBean, byte[] fileUploadContent, String nomeFileUpload) {
		boolean result = false;
		
		try {
			// ------------OPERAZIONI PRELIMINARI------------START
			//Cancello il blob e valorizzo flag svecchiato=true
			docBean.getBlob().setContenutoFile(null);
			docBean.setSvecchiato(true);
			Documento docDaAggiornare = BeanToModel.execute(docBean);

			int updated = getDAO().eliminaBlobDocumento(docDaAggiornare.getBlob().getId());
			if(updated > 0)
				getDAO().impostaComeSvecchiato(docDaAggiornare.getId());
			// ------------OPERAZIONI PRELIMINARI------------END
			
			//Invio file ad Host (Spazio Primeur) via JMS
			HostSender sender = null;
			boolean salta_coda = GDOConfig.getInstance().getProperty(GDOConfig.SALTA_CODA).equals("1") ? true:false;
			if(salta_coda){
				sender = SpazioCommunicatorFactory.getFactory(SpazioCommunicatorFactory.TipoComunicatore.TEST_SPAZIOJMS).getTestSender();	
			} else {
				sender = SpazioCommunicatorFactory.getFactory(SpazioCommunicatorFactory.TipoComunicatore.SPAZIOJMS).getSender();	
			}

			if(sender == null)
				throw new BusinessException(SvecchiamentoConstants.SENDER_ERROR);

			String correlationId = GDOConfig.DOC_CORRELATION_ID + docBean.getId() + "-" + nomeFileUpload;
			if(correlationId.length() > 24){
				correlationId = correlationId.substring(0, 24);
			}
			
			sender.send(fileUploadContent, nomeFileUpload, correlationId, salta_coda);
			
			result = true;
			logger.info("Il documento con id " + docBean.getId() + " e correlationId " + correlationId + " è stato svecchiato correttamente.");
			
		} catch (BusinessException be){
			ctx.setRollbackOnly();
			logger.error(be.getMessage(), be);
		}
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Interceptors({ProfilingInterceptor.class})
	public boolean invocaSpazioHTTP(DocumentoBean docBean, byte[] fileUploadContent, String nomeFileUpload) {
		boolean result = false;
		
		try {
			// ------------OPERAZIONI PRELIMINARI------------START
			//Cancello il blob e valorizzo flag svecchiato=true
			docBean.getBlob().setContenutoFile(null);
			docBean.setSvecchiato(true);
			Documento docDaAggiornare = BeanToModel.execute(docBean);

			int updated = getDAO().eliminaBlobDocumento(docDaAggiornare.getBlob().getId());
			if(updated > 0)
				getDAO().impostaComeSvecchiato(docDaAggiornare.getId());
			// ------------OPERAZIONI PRELIMINARI------------END
			
			
			//Invio file Spazio via HTTP
			GDOInoltroBean inoltro = new GDOInoltroBean();
			inoltro.inoltroFile(fileUploadContent, nomeFileUpload);

			result = true;
			logger.info("Il documento con id " + docBean.getId() + " è stato svecchiato correttamente.");

		} catch (GdoInoltroException gdo){
			ctx.setRollbackOnly();
			logger.error(gdo.getMessage(), gdo);
		}
		return result;
	}


}