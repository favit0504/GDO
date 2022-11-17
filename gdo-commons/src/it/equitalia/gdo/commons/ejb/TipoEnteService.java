package it.equitalia.gdo.commons.ejb;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.TipoEnteBean;

import java.util.List;



public interface TipoEnteService {
	List<TipoEnteBean> recuperaTipoEnti() throws BusinessException;

}
