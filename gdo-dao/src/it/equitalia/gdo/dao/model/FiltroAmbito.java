package it.equitalia.gdo.dao.model;

import it.equitalia.gdo.dao.model.generic.AbstractFiltro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class FiltroAmbito extends AbstractFiltro  {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy="filtroAmbito")
	private List<ValoreFiltroAmbito> valoriFiltriAmbito;

	
	public List<ValoreFiltroAmbito> getvaloriFiltriAmbito() {
		return valoriFiltriAmbito;
	}

	public void setvaloriFiltriAmbito(List<ValoreFiltroAmbito> valoriFiltriAmbito) {
		this.valoriFiltriAmbito = valoriFiltriAmbito;
	}

	
	public void aggiungiValoreFiltroConRiferimentoAlFiltroPadre(ValoreFiltroAmbito entitaValore) {

		if (valoriFiltriAmbito == null)
			valoriFiltriAmbito = new ArrayList<ValoreFiltroAmbito>();
		
		entitaValore.setFiltroAmbito(this);
		valoriFiltriAmbito.add(entitaValore);
		
	}

	public String toString() {
			
			String toString = "filtroAmbito ID " + getId() + ", ";
			
			if (valoriFiltriAmbito != null)
				toString += "CON " + valoriFiltriAmbito.size() + " VALORI";
			
			return toString;
		}
}
