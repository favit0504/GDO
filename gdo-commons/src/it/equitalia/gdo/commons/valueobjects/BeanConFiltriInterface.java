package it.equitalia.gdo.commons.valueobjects;

/**
 * Interfaccia comune ai value objects con filtri
 * Serve per manipolarli in fase di conversione
 * con un'interfaccia comune
 */
public interface BeanConFiltriInterface {

	
	public FiltroEnteBean getFiltroEnte();

	public void setFiltroEnte(FiltroEnteBean filtroEnte);

	public FiltroSocietaBean getFiltroSocieta();

	public void setFiltroSocieta(FiltroSocietaBean filtroSocieta);
	
	public FiltroTipologiaEnteBean getFiltroTipologiaEnte();

	public void setFiltroTipologiaEnte(FiltroTipologiaEnteBean filtroTipologiaEnte);

	//public FiltroTipologiaBean getFiltroTipologia();

//	public void setFiltroTipologia(FiltroTipologiaBean filtroTipologia);
	
	public FiltroServizioEnteBean getFiltroServizioEnte();
	
	public void setFiltroServizioEnte(FiltroServizioEnteBean filtroServizio);
	
	public FiltroServizioAltriUtentiBean getFiltroServizioAltriUtenti();
	
	public void setFiltroServizioAltriUtenti(FiltroServizioAltriUtentiBean filtroServizio);
	
	public FiltroServizioAgenteBean getFiltroServizioAgente();
	
	public void setFiltroServizioAgente(FiltroServizioAgenteBean filtroServizio);
	
	public FiltroRegioneAgenteBean getFiltroRegioneAgente();

	public void setFiltroRegioneAgente(FiltroRegioneAgenteBean filtroRegioneAgente);

	public FiltroRegioneEnteBean getFiltroRegioneEnte();

	public void setFiltroRegioneEnte(FiltroRegioneEnteBean filtroRegioneEnte);

	

	public FiltroProvinciaEnteBean getFiltroProvinciaEnte();

	public void setFiltroProvinciaEnte(FiltroProvinciaEnteBean filtroProvinciaEnte);
	
	public FiltroAmbitoBean getFiltroAmbito();
	public void setFiltroAmbito(FiltroAmbitoBean filtroAmbito);
}
