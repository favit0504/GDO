package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.AbstractFiltro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "GDO.FILTRO")
public class FiltroTipologiaEnte extends AbstractFiltro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy="filtroParent")
	private List<ValoreFiltroTipologiaEnte> valori;

	
	public List<ValoreFiltroTipologiaEnte> getValoriFiltroTipologiaEnte() {
		return valori;
	}

	public void setValoriFiltroTipologiaEnte(List<ValoreFiltroTipologiaEnte> valori) {
		this.valori = valori;
	}

	/**
	 * Se non si aggiungono i valori tramite questo metodo
	 *  non prende la FK quando salva su database su FILTRO_PROVINCIA
	 *  il valore di ID_FILTRO, "filtro padre" 
	 */
	public void aggiungiValoreFiltroConRiferimentoAlFiltroPadre(ValoreFiltroTipologiaEnte entitaValore) {

		if (valori == null)
			valori = new ArrayList<ValoreFiltroTipologiaEnte>();
		
		entitaValore.setFiltroTipologiaEnte(this);
		valori.add(entitaValore);
		
	}
	



}