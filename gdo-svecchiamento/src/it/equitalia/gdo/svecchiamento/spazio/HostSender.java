package it.equitalia.gdo.svecchiamento.spazio;

import it.equitalia.gdo.commons.exception.BusinessException;


public interface HostSender {
    
    public String send(byte[] fileBinario, String nomeFile, String correlationId, boolean isAmbienteTest) throws BusinessException;

}
