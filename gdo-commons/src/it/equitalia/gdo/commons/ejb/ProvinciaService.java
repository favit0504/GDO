package it.equitalia.gdo.commons.ejb;
import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.EnteBean;
import it.equitalia.gdo.commons.valueobjects.ProvinciaBean;

import java.util.List;

public interface ProvinciaService {
	List<ProvinciaBean> recuperaProvinceAttive() throws BusinessException;
	List<ProvinciaBean> recuperaProvinceAttivePerEnte(EnteBean ente) throws BusinessException;
	

}
