package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.AbstractFiltro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class FiltroServizio extends AbstractFiltro  {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy="filtroParent")
	private List<ValoreFiltroServizio> valori;

	
	public List<ValoreFiltroServizio> getValoriFiltriServizio() {
		return valori;
	}

	public void setValoriFiltriServizio(List<ValoreFiltroServizio> valori) {
		this.valori = valori;
	}

	/**
	 * Se non si aggiungono i valori tramite questo metodo
	 *  non prende la FK quando salva su database su FILTRO_SERVIZIO
	 *  il valore di ID_FILTRO, "filtro padre" 
	 */
	public void aggiungiValoreFiltroConRiferimentoAlFiltroPadre(ValoreFiltroServizio entitaValore) {

		if (valori == null)
			valori = new ArrayList<ValoreFiltroServizio>();
		
		entitaValore.setFiltroServizio(this);
		valori.add(entitaValore);
		
	}
	
	public String toString() {
		
		String toString = "FILTROSERVIZIO ID " + getId() + ", ";
		
		if (valori != null)
			toString += "CON " + valori.size() + " VALORI";
		
		return toString;
	}

}