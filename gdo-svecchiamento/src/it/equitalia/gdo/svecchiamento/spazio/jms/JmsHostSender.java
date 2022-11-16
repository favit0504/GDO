package it.equitalia.gdo.svecchiamento.spazio.jms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import it.equitalia.gdo.commons.exception.BusinessException;
import it.equitalia.gdo.commons.exception.ResourceLookupFailureException;
import it.equitalia.gdo.svecchiamento.spazio.HostSender;
import it.equitalia.gdo.svecchiamento.spazio.jms.SpazioJMSHelperGmeExtention.TIPO_OPERAZIONE_HELPER;
import it.equitalia.gdo.svecchiamento.spazio.util.ConfigurazioneSpazio;
import it.equitalia.gdo.svecchiamento.spazio.util.ConfigurazioneSpazio.Property;

import javax.jms.JMSException;
import javax.jms.Queue;

import org.apache.log4j.Logger;

import com.primeur.javax.jms.SpazioBytesFile;
import com.primeur.javax.jms.SpazioProperty;
import com.primeur.javax.jms.SpazioQueueSender;
import com.primeur.spazio.jms.SPQueueConnection;
import com.primeur.spazio.jms.SPQueueConnectionFactory;
import com.primeur.spazio.jms.SPQueueSession;

public class JmsHostSender implements HostSender {

    private static final String ELAB_CODE_ORCH_SUITE = "CLFD";
	private static final int CONN_OPT_ORCH_SUITE = 0x00000800;
	private static final Logger _logger = Logger.getLogger(JmsHostSender.class);

    
    private void copyFileIntoSpazioBean(byte[] file, SpazioBytesFile spazioBytesFile) throws IOException,JMSException {

		InputStream ios = null;
		try {
			byte[] buffer = new byte[2048];
			ios = new ByteArrayInputStream(file);
			int read = 0;
			while ((read = ios.read(buffer)) != -1)
				spazioBytesFile.writeBytes(buffer, 0, read);
		}
		catch (IOException c) {
			_logger.error(c);
			throw c;
		}
		catch (JMSException e) {
			_logger.error(e);
			throw e;
		}
		finally {
			try {
				if (ios != null)
					ios.close();
			}
			catch (Throwable cx) {
				_logger.error("errore chiusura inputStream copy into Spazio Bean, JMS Sending");
				_logger.error(cx);
			}
		}
	}
    
    
    public String send(byte[] fileBinario, String nomeFile, String correlationId, boolean saltaCoda) throws BusinessException
    {
        String internalNumber = null;
        SPQueueConnection connection = null;
        SPQueueSession session = null;
        SpazioQueueSender sender = null;

        
        // il try è per gestire correttamente il rilascio delle risorse e
        // possibili errori
        try {
        	if(saltaCoda)
        		return internalNumber = SpazioProperty.INT_NUM;
            SPQueueConnectionFactory fact = SpazioJMSHelperGmeExtention.createQueueConnectionFactory(TIPO_OPERAZIONE_HELPER.GDO_FLUSSI);
            
            //Le seguenti due opzioni servono per far funzionare l'orchestration Suite 
            fact.setConnectOptions(CONN_OPT_ORCH_SUITE);
            fact.setElabCode(ELAB_CODE_ORCH_SUITE);	
            
            //Lo start è invocato nell'helper
            connection = SpazioJMSHelperGmeExtention.createQueueConnection(fact);         
            session = SpazioJMSHelperGmeExtention.createQueueSession(connection);
            Queue queue = SpazioJMSHelperGmeExtention.createQueue(TIPO_OPERAZIONE_HELPER.GDO_FLUSSI);
            sender = SpazioJMSHelperGmeExtention.createSender(session, queue);       
            SpazioBytesFile spazioBytesFile = session.createBytesFile();
            //fileSpazio.setStringProperty(SpazioProperty.OUT_MINI_PATH_NAME, absolutePath);
            spazioBytesFile.setStringProperty(SpazioProperty.DESCRIPTION, nomeFile);
            spazioBytesFile.setStringProperty(SpazioProperty.U_CLASS,  ConfigurazioneSpazio.getInstance().getProperty(Property.GDO_FLUSSI_USERCLASS));
            spazioBytesFile.setStringProperty(SpazioProperty.CORREL_ID, correlationId);
            spazioBytesFile.setStringProperty(SpazioProperty.SENDER,  ConfigurazioneSpazio.getInstance().getProperty(Property.GDO_FLUSSI_SPAZIO_USER));
            spazioBytesFile.clearBody(sender);
			copyFileIntoSpazioBean(fileBinario, spazioBytesFile);
			sender.send(spazioBytesFile);
			internalNumber = spazioBytesFile.getStringProperty(SpazioProperty.INT_NUM);
            
     

        }
        catch (IOException e) {
        	_logger.error(e);
        	throw new BusinessException("Vi è stato un problema di comunicazione con il servizio di invio dati.", e);	
		}
          catch (JMSException e) {
                _logger.error(e);
                throw new BusinessException("Vi è stato un problema di comunicazione con il servizio di invio dati.", e);
            }
         catch (ComunicazioneSpazioException ecs) {

            _logger.error(ecs);
            throw new BusinessException("Vi è stato un problema di comunicazione con il servizio di invio dati.", ecs);
         }
         catch (ResourceLookupFailureException e) {
            _logger.error(e);
            throw new BusinessException("Vi è stato un problema di comunicazione con il servizio di invio dati.", e);
        } finally {
            SpazioJMSHelperGmeExtention.close(sender, session, connection);
        }

        _logger.debug("Invio file tramite spazio jms terminata.");
        return internalNumber;
    }

}
