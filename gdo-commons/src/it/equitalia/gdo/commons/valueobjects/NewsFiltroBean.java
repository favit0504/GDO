package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class NewsFiltroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idNews;
	private Integer idFiltro;

	public NewsFiltroBean() {
		
	}

	public Integer getIdNews() {
		return idNews;
	}

	public void setIdNews(Integer idNews) {
		this.idNews = idNews;
	}

	public Integer getIdFiltro() {
		return idFiltro;
	}

	public void setIdFiltro(Integer idFiltro) {
		this.idFiltro = idFiltro;
	}



}
