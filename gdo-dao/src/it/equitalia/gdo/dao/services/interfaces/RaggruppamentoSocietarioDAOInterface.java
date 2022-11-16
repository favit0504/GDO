package it.equitalia.gdo.dao.services.interfaces;

import it.equitalia.gdo.dao.GenericDAOInterface;

import java.util.List;

@SuppressWarnings("unchecked")
public interface RaggruppamentoSocietarioDAOInterface extends GenericDAOInterface {
	public List<Integer> getListaSocietaPerAgente(List<Integer> ambiti);
}
