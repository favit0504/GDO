package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.AmbitoBean;

import java.util.List;

public interface AmbitoService {
	public List<AmbitoBean> recuperaAmbiti() throws BusinessException;
}
