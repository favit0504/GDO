package it.equitalia.gdo.commons.ejb;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.valueobjects.BlobDocumentoBean;

import java.util.List;


/**
 * 
 * @author Valerio Donnarumma
 *
 */
public interface BlobDocumentoService {
	
	/**
	 * Inserisce un blob di un documento esistente in base dati nella tabella BLOB_DOCUMENTO.
	 * 
	 * @param blobDocumentoBean
	 * @throws BusinessException
	 */
	void creaBlobDocumento(BlobDocumentoBean blobDocumentoBean) throws BusinessException;
	
	/**
	 * Aggiorna un blob di un documento esistente.
	 * 
	 * @param blobDocumentoBean
	 * @throws BusinessException
	 */
	void aggiornaBlobDocumento(BlobDocumentoBean blobDocumentoBean) throws BusinessException;
	
	/**
	 * Elimina un blob di un documento esistente.
	 * 
	 * @param blobDocumentoBean
	 * @throws BusinessException
	 */
	void eliminaBlobDocumento(BlobDocumentoBean blobDocumentoBean) throws BusinessException;
	
	/**
	 * Recupera dal database l'oggetto contentente il blob del documento indicato; se null, recupera tutti i blob censiti.
	 * 
	 * @param blobDocumentoBean
	 * @return
	 * @throws BusinessException
	 */
	List<BlobDocumentoBean> recuperaBlobDocumento(BlobDocumentoBean blobDocumentoBean) throws BusinessException;
	

}
