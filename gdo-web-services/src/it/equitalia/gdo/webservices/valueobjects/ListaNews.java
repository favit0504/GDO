package it.equitalia.gdo.webservices.valueobjects;

import it.equitalia.gdo.commons.valueobjects.NewsBean;

import java.io.Serializable;
import java.util.List;

public class ListaNews implements Serializable {

	private static final long serialVersionUID = 2763226865945568435L;
	
	private List<NewsBean> listaNews;

	public List<NewsBean> getListaNews() {
		return listaNews;
	}

	public void setListaNews(List<NewsBean> listaNews) {
		this.listaNews = listaNews;
	}
		
}
