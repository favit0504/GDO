package it.equitalia.gdo.ejb.services;


import it.equitalia.gdo.commons.ejb.impl.PopolamentoUtenteServiceLocal;
import it.equitalia.gdo.commons.ejb.impl.PopolamentoUtenteServiceRemote;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.utils.Costanti;
import it.equitalia.gdo.commons.utils.GDOMessaggi;
import it.equitalia.gdo.commons.utils.Costanti.RaggruppamentoSocietario;
import it.equitalia.gdo.commons.utils.Costanti.TipologiaUtente;
import it.equitalia.gdo.commons.valueobjects.UtenteBean;
import it.equitalia.gdo.dao.AbstractDAOFactory;
import it.equitalia.gdo.dao.GenericAbstractDao;
import it.equitalia.gdo.dao.AbstractDAOFactory.DAOFactoryType;
import it.equitalia.gdo.dao.converters.BeanToModel;
import it.equitalia.gdo.dao.converters.ModelToBean;
import it.equitalia.gdo.dao.db2.EnteDB2DAO;
import it.equitalia.gdo.dao.db2.ProvinciaDB2DAO;
import it.equitalia.gdo.dao.db2.RaggruppamentoSocietarioDB2DAO;
import it.equitalia.gdo.dao.db2.RegioneDB2DAO;
import it.equitalia.gdo.dao.db2.ServizioDB2DAO;
import it.equitalia.gdo.dao.exceptions.DataAccessException;
import it.equitalia.gdo.dao.model.Ente;
import it.equitalia.gdo.dao.model.Provincia;
import it.equitalia.gdo.dao.model.Regione;
import it.equitalia.gdo.dao.model.generic.GenericType;
import it.equitalia.gdo.dao.oracle.EnteOracleDAO;
import it.equitalia.gdo.dao.oracle.ProvinciaOracleDAO;
import it.equitalia.gdo.dao.oracle.ServizioOracleDAO;
import it.equitalia.gdo.dao.services.interfaces.EnteDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.ProvinciaDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.RaggruppamentoSocietarioDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.RegioneDAOInterface;
import it.equitalia.gdo.dao.services.interfaces.ServizioDAOInterface;
import it.equitalia.gdo.ejb.aspects.ProfilingInterceptor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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
@Stateless(name="ejb/PopolamentoUtenteService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PopolamentoUtenteServiceBean extends ServiceWithDAOFactory<GenericAbstractDao<GenericType>> implements PopolamentoUtenteServiceLocal, PopolamentoUtenteServiceRemote {


	private static final Logger logger = Logger.getLogger(PopolamentoUtenteServiceBean.class);
	@Resource
	private SessionContext ctx;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public UtenteBean invocaPopolamentoUtente(UtenteBean utente) throws BusinessException{
		try{
			if (utente != null) {
				popolaTipologiaUtente(utente);
				popolaServiziUtente(utente);

				if (utente.getTipologiaUtente() == TipologiaUtente.AGENTE) {
					popolaAmbitiAgente(utente);
					popolaRegioniAgente(utente);
					popolaSocietaAgente(utente);
				} else if (utente.getTipologiaUtente() == TipologiaUtente.ENTE) {
					popolaTriplettaEnte(utente);
					popolaTipologiaEnte(utente);
					popolaProvinceEnte(utente);
					popolaRegioniEnte(utente);
				} else if (utente.getTipologiaUtente() != TipologiaUtente.UTENTEESTERNO) {
					popolaServiziUtente(utente);
				} else {
					popolaServiziUtente(utente);
				}

				return utente;
			}

			return null;


		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_POPOLAMENTO_UTENTE), t);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public UtenteBean popolaProvinceEnte(UtenteBean utente) throws BusinessException{
		
		try{
			ProvinciaDAOInterface oracleDAO = new ProvinciaDB2DAO();
			factoryType = DAOFactoryType.DB2;
			oracleDAO.setEntityManager(getEntityManager());

			List<Provincia> province = oracleDAO.getListaProvincePerEnte(BeanToModel.execute(utente.getEnte()));

			List<String> siglaProvince = new ArrayList<String>();
			for(Provincia p: province){
				siglaProvince.add(p.getCodiceProvincia());
			}
			utente.setProvince(siglaProvince);

			return utente;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_PROVINCIA), t);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public UtenteBean popolaAmbitiAgente(UtenteBean utente) throws BusinessException{
		
		try{
			ProvinciaDAOInterface oracleDAO = new ProvinciaOracleDAO();
			factoryType = DAOFactoryType.ORACLE_GEU;
			oracleDAO.setEntityManager(getEntityManager());			
			List<Integer> ambiti = oracleDAO.getListaProvincePerAgente(utente.getUsd());					
			utente.setAmbiti(ambiti);

			return utente;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_PROVINCIA), t);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public UtenteBean popolaServiziUtente(UtenteBean utente) throws BusinessException {
		
		try{
			List<String> listaIdServizi = new ArrayList<String>();
			ServizioDAOInterface oracleDAO = new ServizioOracleDAO();	
			ServizioDAOInterface DB2DAO = new ServizioDB2DAO();
    
			if (!utente.getUsd().startsWith(Costanti.TipologiaUtente.AGENTE.getValue())
					&& !utente.getUsd().startsWith(Costanti.TipologiaUtente.EQUITALIA.getValue())
					&& !utente.getUsd().startsWith(Costanti.TipologiaUtente.UTENTEESTERNO.getValue())
					&& !utente.getUsd().startsWith(Costanti.TipologiaUtente.ENTE.getValue())) {
				factoryType = DAOFactoryType.DB2;
				DB2DAO.setEntityManager(getEntityManager());
				listaIdServizi = DB2DAO.getListaServiziAttiviAltriUtenti(utente.getUsd());
			} else if (utente.getUsd().startsWith(Costanti.TipologiaUtente.UTENTEESTERNO.getValue())) {
				factoryType = DAOFactoryType.DB2;
				DB2DAO.setEntityManager(getEntityManager());
				listaIdServizi = DB2DAO.getListaServiziAttiviUtenteEsterno(utente.getUsd());
			} else {
				factoryType = DAOFactoryType.ORACLE_GEU;
				oracleDAO.setEntityManager(getEntityManager());
				listaIdServizi = oracleDAO.getListaServiziAttiviUtenteEnteoAgente(utente.getUsd());
			}
                  			
			//nota: a differenza delle altre liste, qua passo
			//direttamente la listarestituita dal DAO, 
			//che non e ` un ArrayList
			utente.setServizi(listaIdServizi);

			return utente;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_SERVIZIO), t);
		}

	}


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public UtenteBean popolaRegioniEnte(UtenteBean utente) throws BusinessException{
		try{
			RegioneDAOInterface db2DAO = new RegioneDB2DAO();		
			factoryType = DAOFactoryType.DB2;
			db2DAO.setEntityManager(getEntityManager());
			
			List<Regione> regioni = db2DAO.getListaRegioniPerEnte(BeanToModel.execute(utente.getEnte()));

			List<String> codiceRegioni = new ArrayList<String>();
			for(Regione r: regioni){
				codiceRegioni.add(r.getCodiceRegione());
			}
			utente.setRegioni(codiceRegioni);

			return utente;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_REGIONE), t);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public UtenteBean popolaRegioniAgente(UtenteBean utente) throws BusinessException{
		try{

			
			List<String> codiceRegioni = new ArrayList<String>();

			if(utente.getAmbiti() != null){
				RegioneDAOInterface db2DAO = new RegioneDB2DAO();		
				factoryType = DAOFactoryType.DB2;
				db2DAO.setEntityManager(getEntityManager());
				
				List<Regione> regioni = db2DAO.getListaRegioniPerAgente(utente);

				for(Regione r: regioni){
					codiceRegioni.add(r.getCodiceRegione());
				}
			}

			utente.setRegioni(codiceRegioni);						
			return utente;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_REGIONE), t);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public UtenteBean popolaTipologiaEnte(UtenteBean utente) throws BusinessException {
		try {
			EnteDAOInterface db2DAO = new EnteDB2DAO();
			factoryType = DAOFactoryType.DB2;
			db2DAO.setEntityManager(getEntityManager());

			String tipologiaEnte = (db2DAO.getTipologiaEnte(BeanToModel.execute(utente.getEnte())));
			utente.setTipologiaEnte(tipologiaEnte != null ? tipologiaEnte.trim() : tipologiaEnte);

			return utente;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_TIPOLOGIA_ENTE), t);
		}

	}

	public UtenteBean popolaTriplettaEnte(UtenteBean utente) throws BusinessException {
		
		try{
			EnteDAOInterface oracleDAO = new EnteOracleDAO();
			factoryType = DAOFactoryType.ORACLE_GEU;
			oracleDAO.setEntityManager(getEntityManager());

			Ente tripletta = oracleDAO.getTriplettaEnte(utente.getUsd());
			utente.setEnte(ModelToBean.execute(tripletta));

			return utente;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_TRIPLETTA_ENTE), t);
		}
	}

	public UtenteBean popolaTipologiaUtente(UtenteBean utente) throws BusinessException {
		try{
			if(utente.getUsd().startsWith(Costanti.TipologiaUtente.ENTE.getValue())){
				utente.setTipologiaUtente(Costanti.TipologiaUtente.ENTE);

			} else if(utente.getUsd().startsWith(Costanti.TipologiaUtente.AGENTE.getValue())){
				utente.setTipologiaUtente(Costanti.TipologiaUtente.AGENTE);

			} else if(utente.getUsd().startsWith(Costanti.TipologiaUtente.EQUITALIA.getValue())){
				utente.setTipologiaUtente(Costanti.TipologiaUtente.EQUITALIA);

			} else if(utente.getUsd().startsWith(Costanti.TipologiaUtente.UTENTEESTERNO.getValue())){
				utente.setTipologiaUtente(Costanti.TipologiaUtente.UTENTEESTERNO);

			} else if (!utente.getUsd().startsWith(Costanti.TipologiaUtente.AGENTE.getValue())
					&& !utente.getUsd().startsWith(Costanti.TipologiaUtente.EQUITALIA.getValue())
					&& !utente.getUsd().startsWith(Costanti.TipologiaUtente.ENTE.getValue())
					&& !utente.getUsd().startsWith(Costanti.TipologiaUtente.UTENTEESTERNO.getValue())) {
				utente.setTipologiaUtente(Costanti.TipologiaUtente.ALTRIUTENTI);
			}

			return utente;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_POPOLAMENTO_TIPOLOGIA_UTENTE), t);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Interceptors({ProfilingInterceptor.class})
	public UtenteBean popolaSocietaAgente(UtenteBean utente) throws BusinessException{
		try{					

			List<RaggruppamentoSocietario> societa = new ArrayList<RaggruppamentoSocietario>();
			if(utente.getAmbiti() != null && utente.getAmbiti().size()>0)
			{
				RaggruppamentoSocietarioDAOInterface db2DAO = new RaggruppamentoSocietarioDB2DAO();
				factoryType = DAOFactoryType.DB2;
				db2DAO.setEntityManager(getEntityManager());
				
				List<Integer> codiciConcessionari = db2DAO.getListaSocietaPerAgente(utente.getAmbiti());

				for(Integer codice : codiciConcessionari){
					RaggruppamentoSocietario r = RaggruppamentoSocietario.getByCodice(codice);
					if(r!= null)
						societa.add(RaggruppamentoSocietario.getByCodice(codice));
				}
			}		
			utente.setSocieta(societa);			
			return utente;

		} catch (DataAccessException dae) {
			ctx.setRollbackOnly();
			logger.error(dae.getMessage(), dae);
			throw new BusinessException(dae.getMessage());
		} catch(Throwable t){
			logger.error(t, t);
			throw new BusinessException(GDOMessaggi.getMessaggio(GDOMessaggi.ERRORE_RECUPERA_SOCIETA_AGENTE), t);
		}
	}

	@Override
	protected GenericAbstractDao<GenericType> getSpecificDAO(
			AbstractDAOFactory adf) {		
		return null;
	}




}