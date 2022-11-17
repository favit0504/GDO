package it.equitalia.gdo.svecchiamento.spazio;

import it.equitalia.gdo.svecchiamento.spazio.jms.JmsHostSender;
import it.equitalia.gdo.svecchiamento.spazio.jms.MySender;

import org.apache.log4j.Logger;

public class SpazioJmsCommunicatorFactory extends SpazioCommunicatorFactory {
    
    private static final Logger _logger = Logger.getLogger(SpazioJmsCommunicatorFactory.class);
    
    private static SpazioJmsCommunicatorFactory _INSTANCE = new SpazioJmsCommunicatorFactory();
    
    public static final SpazioJmsCommunicatorFactory getInstance()
    {
        return _INSTANCE;
    }

    @Override
    public HostSender getSender()
    {
        _logger.debug("Creazione strategy di invio ad host");
        return new JmsHostSender();
    }
    
    @Override
    public MySender getTestSender()
    {
        _logger.debug("Creazione strategy di TEST di invio ad host");
        return new MySender();
    }

}
