package it.equitalia.gdo.dao;

import java.util.Calendar;

import javax.persistence.Query;

import it.equitalia.gdo.dao.model.generic.EntitaVersionabile;


public abstract class EntitaVersionabileDAO<T extends EntitaVersionabile> extends GenericAbstractDao<T> 

{
	
	private static final String CODICE = "codice";
	private static final String TEMPLATE_QUERY_VERSIONE_ATTUALE = "select n from %s n where n.codice=:"+CODICE+" and n.valida=true";
	
	private String istanziaQuery(final T t) {

		return String.format(TEMPLATE_QUERY_VERSIONE_ATTUALE, type.getName());

	}
	
	public T update(final T t) {
		T entitaPrecedente = recuperaVersioneAttualePerCodice(t);
		entitaPrecedente.setValida(false);
		entityManager.persist(entitaPrecedente);
		t.setDataModifica(Calendar.getInstance().getTime());
		t.setValida(true);
		return super.insert(t);
	}

	@SuppressWarnings("unchecked")
	protected T recuperaVersioneAttualePerCodice(T t) {
		Query q =entityManager.createQuery(istanziaQuery(t));
		q.setParameter(CODICE, t.getCodice());
		return (T)q.getSingleResult();
	}
	
	public T insert(final T t) {
		preparaEntitaPerInserimento(t);
		entityManager.persist(t);
		return t;
	}

	protected void preparaEntitaPerInserimento(final T t) {
		impostaIdEntitaEFigli(t);
		t.setCodice(t.getId());
		t.setValida(true);
		t.setDataCreazione(Calendar.getInstance().getTime());
	}    

}
