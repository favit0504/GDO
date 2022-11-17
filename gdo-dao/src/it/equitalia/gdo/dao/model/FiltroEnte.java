package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.AbstractFiltro;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;


@Entity
public class FiltroEnte extends AbstractFiltro {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy="filtroParent")
	private ValoreFiltroEnte valore;

	
	public ValoreFiltroEnte getValoreFiltroEnte() {
		return valore;
	}

	public void setValoreFiltroEnte(ValoreFiltroEnte valore) {

		//necessario altrimenti non salva su database su FILTRO_ENTE la FK a FILTRO
		valore.setFiltroEnte(this);
		this.valore = valore;
	}

	



}