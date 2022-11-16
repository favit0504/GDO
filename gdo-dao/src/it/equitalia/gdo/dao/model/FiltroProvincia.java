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
public class FiltroProvincia extends AbstractFiltro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy="filtroParent")
	private List<ValoreFiltroProvincia> valori;

	
	public List<ValoreFiltroProvincia> getValoriFiltriProvincia() {
		return valori;
	}

	public void setValoriFiltriProvincia(List<ValoreFiltroProvincia> valori) {
		this.valori = valori;
	}

	/**
	 * Se non si aggiungono i valori tramite questo metodo
	 *  non prende la FK quando salva su database su FILTRO_PROVINCIA
	 *  il valore di ID_FILTRO, "filtro padre" 
	 */
	public void aggiungiValoreFiltroConRiferimentoAlFiltroPadre(ValoreFiltroProvincia entitaValore) {

		if (valori == null)
			valori = new ArrayList<ValoreFiltroProvincia>();
		
		entitaValore.setFiltroProvincia(this);
		valori.add(entitaValore);
		
	}

}
