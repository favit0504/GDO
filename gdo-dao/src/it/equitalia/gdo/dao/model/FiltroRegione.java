package it.equitalia.gdo.dao.model;

import it.equitalia.gdo.dao.model.generic.AbstractFiltro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class FiltroRegione extends AbstractFiltro  {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy="filtroRegione")
	private List<ValoreFiltroRegione> valoriFiltriRegione;

	
	public List<ValoreFiltroRegione> getValoriFiltriRegione() {
		return valoriFiltriRegione;
	}

	public void setValoriFiltriRegione(List<ValoreFiltroRegione> valoriFiltriRegione) {
		this.valoriFiltriRegione = valoriFiltriRegione;
	}

	/**
	 * Se non si aggiungono i valori tramite questo metodo
	 *  non prende la FK quando salva su database su FILTRO_REGIONE
	 *  il valore di ID_FILTRO, "filtro padre" 
	 */
	public void aggiungiValoreFiltroConRiferimentoAlFiltroPadre(ValoreFiltroRegione entitaValore) {

		if (valoriFiltriRegione == null)
			valoriFiltriRegione = new ArrayList<ValoreFiltroRegione>();
		
		entitaValore.setFiltroRegione(this);
		valoriFiltriRegione.add(entitaValore);
		
	}

	public String toString() {
			
			String toString = "FILTROREGIONE ID " + getId() + ", ";
			
			if (valoriFiltriRegione != null)
				toString += "CON " + valoriFiltriRegione.size() + " VALORI";
			
			return toString;
		}
}
