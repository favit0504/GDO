package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface PopolamentoUtenteService {
	
	/**
	 * Servizio che invoca in sequenza il popolamento utente su ogni filtro.
	 * 
	 * @param utenteBean
	 * @return
	 */
	UtenteBean invocaPopolamentoUtente(UtenteBean utenteBean) throws BusinessException;
	
	/**
	 * Popola i servizi del bean UtenteBean.
	 * Il servizio è valido sia per utenze ente che agente.
	 * 
	 * @param chiaveUtente
	 * @return
	 */
	UtenteBean popolaServiziUtente(UtenteBean utente) throws BusinessException;
	
	/**
	 * Popola le province del bean UtenteBean.
	 * 
	 * @param utente con la tripletta ente valorizzata
	 * @return
	 * @throws BusinessException
	 */
	UtenteBean popolaProvinceEnte(UtenteBean utente) throws BusinessException;
	
	/**
	 * Popola le regioni del bean UtenteBean appartenenti ad utenze di tipo ente.
	 * 
	 * @param utente con la tripletta dell'ente valorizzata
	 * @return
	 * @throws BusinessException
	 */
	UtenteBean popolaRegioniEnte(UtenteBean utente) throws BusinessException;
	
	/**
	 * Popola le regioni del bean UtenteBean appartenenti ad utenze di tipo agente.
	 * 
	 * @param utente con la lista delle province valorizzata con gli ambiti di pertinenza dell'utenza
	 * @return
	 * @throws BusinessException
	 */
	UtenteBean popolaRegioniAgente(UtenteBean utente) throws BusinessException;
	
	/**
	 * Popola la tripletta ente del bean UtenteBean appartenente ad utenze di tipo ente.
	 * 
	 * @param utente
	 * @return
	 * @throws BusinessException
	 */
	UtenteBean popolaTriplettaEnte(UtenteBean utente) throws BusinessException;
	
	/**
	 * Popola la tipologia ente del bean UtenteBean.
	 * 
	 * @param utente
	 * @return
	 * @throws BusinessException
	 */
	UtenteBean popolaTipologiaEnte(UtenteBean utente) throws BusinessException;
	
	/**
	 * Popola la tipologia utente del bean UtenteBean.
	 * 
	 * @param utente
	 * @return
	 * @throws BusinessException
	 */
	UtenteBean popolaTipologiaUtente(UtenteBean utente) throws BusinessException;
	
	/**
	 * Popola la lista dei raggruppamenti societari del bean UtenteBean appartenenti ad utenze di tipo agente.
	 * 
	 * @param utente con la lista delle province valorizzata con gli ambiti di pertinenza dell'utenza
	 * @return
	 * @throws BusinessException
	 */
	UtenteBean popolaSocietaAgente(UtenteBean utente) throws BusinessException;
	
	/**
	 * Popola la lista degli ambiti del bean UtenteBean appartenenti ad utenze di tipo agente.
	 * 
	 * @param utente con il campo usd valorizzato
	 * @return
	 * @throws BusinessException
	 * */
	UtenteBean popolaAmbitiAgente(UtenteBean utente) throws BusinessException;

}
