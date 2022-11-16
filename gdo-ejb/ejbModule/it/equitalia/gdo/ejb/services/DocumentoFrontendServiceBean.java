package it.equitalia.gdo.ejb.services;


import it.equitalia.gdo.commons.ejb.impl.DocumentoFrontendServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.DocumentoFrontendServiceRemote;
import it.equitalia.gdo.commons.ejb.impl.DocumentoServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.PopolamentoUtenteServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.ValutazioneUtenteServiceLocal;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.Costanti;
import it.equitalia.gdo.commons.utils.StringUtils;
import it.equitalia.gdo.commons.utils.Costanti.TipologiaUtente;
import it.equitalia.gdo.commons.valueobjects.DocumentoBean;
import it.equitalia.gdo.commons.valueobjects.SezioneBean;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import org.apache.log4j.Logger;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
@SuppressWarnings("unused")
@Stateless(name="ejb/DocumentoFrontendService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DocumentoFrontendServiceBean implements DocumentoFrontendServiceLocal, DocumentoFrontendServiceRemote {


	private static final Logger logger = Logger.getLogger(DocumentoFrontendServiceBean.class);

	@Resource
	private SessionContext ctx;

	@EJB DocumentoServiceLocal documentoService;
	@EJB PopolamentoUtenteServiceLocal popolamentoUtenteService;
	@EJB ValutazioneUtenteServiceLocal valutazioneUtenteService;

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Map<Integer, SezioneBean> recuperaDocumentiAttiviDestinatiAdUtente(String chiaveUtente) throws BusinessException {

		Map<Integer, SezioneBean> mappaSezioniConDocumenti = new HashMap<Integer, SezioneBean>();
		TipologiaUtente tipologiaUtente = null;

		if(chiaveUtente != null && !StringUtils.isBlank(chiaveUtente)){

			if(chiaveUtente.startsWith(Costanti.TipologiaUtente.ENTE.getValue())){
				tipologiaUtente = TipologiaUtente.ENTE;
			} 
			else if(chiaveUtente.startsWith(Costanti.TipologiaUtente.AGENTE.getValue())){
				tipologiaUtente = TipologiaUtente.AGENTE;
			}
			//Per la chiave X non passo alcun valore
		}

		try {
			List<DocumentoBean> documentiValidiEdAttiviPerUtenza = documentoService.recuperaDocumentiAttiviPerUtente(tipologiaUtente);

			UtenteBean utenteBean = new UtenteBean();
			utenteBean.setUsd(chiaveUtente);
			utenteBean = popolamentoUtenteService.invocaPopolamentoUtente(utenteBean);

			Iterator<DocumentoBean> it = documentiValidiEdAttiviPerUtenza.iterator();
			
			while (it.hasNext()) {
				DocumentoBean documentoBean = it.next();
				if (valutazioneUtenteService.valutaUtente(utenteBean, documentoBean)) {
					
					if(!mappaSezioniConDocumenti.containsKey(documentoBean.getSezione().getId())){
						SezioneBean s = new SezioneBean();
						s.setId(documentoBean.getSezione().getId());
						s.setTitolo(documentoBean.getSezione().getTitolo());
						
						List<DocumentoBean> documenti = new ArrayList<DocumentoBean>();
						s.setDocumenti(documenti);
						
						mappaSezioniConDocumenti.put(s.getId(), s);
					} 
					
					mappaSezioniConDocumenti.get(documentoBean.getSezione().getId()).getDocumenti().add(documentoBean);
					
				}
			}
			
		}
		catch(Exception e) {
			logger.error("Errore in fase di recupero dei documenti per " + chiaveUtente, e);
		}
		
		return mappaSezioniConDocumenti;
		
	}

	public Boolean popolaEValutaUtente(UtenteBean utenteBean, DocumentoBean documentoBean) throws BusinessException{
		UtenteBean utente = popolamentoUtenteService.invocaPopolamentoUtente(utenteBean);
		if(utente != null){
			return valutazioneUtenteService.valutaUtente(utente, documentoBean);
		}
		return false;
	}

}