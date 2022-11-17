package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.AbstractFiltro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
@Entity
public class FiltroSocieta extends AbstractFiltro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy="filtroParent")
	private List<ValoreFiltroSocieta> valori;

	
	public List<ValoreFiltroSocieta> getValoriFiltriSocieta() {
		return valori;
	}

	public void setValoriFiltriSocieta(List<ValoreFiltroSocieta> valori) {
		this.valori = valori;
	}

	/**
	 * Se non si aggiungono i valori tramite questo metodo
	 *  non prende la FK quando salva su database su FILTRO_SOCIETA
	 *  il valore di ID_FILTRO, "filtro padre" 
	 */
	public void aggiungiValoreFiltroConRiferimentoAlFiltroPadre(ValoreFiltroSocieta entitaValore) {

		if (valori == null)
			valori = new ArrayList<ValoreFiltroSocieta>();
		
		entitaValore.setFiltroSocieta(this);
		valori.add(entitaValore);
		
	}

}