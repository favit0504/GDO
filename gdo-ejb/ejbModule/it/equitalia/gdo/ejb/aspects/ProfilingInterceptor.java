package it.equitalia.gdo.ejb.aspects;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class ProfilingInterceptor {
	
	
	private static final Logger logger = Logger.getLogger( ProfilingInterceptor.class );

	
	/**
	 * Esegue il log dei tempi di esecuzione di un metodo di business 
	 * secondo gli standard di log eqs (1.5) 
	 * 
	 * 
	 * @param ic
	 * @return
	 * @throws Exception
	 */
    @AroundInvoke
    public Object profile(InvocationContext ic) throws Exception {
    	logger.debug("SERVICE." + ic.getMethod().getName() );
        
    	long startTime = 0;
        try {
        	startTime = System.currentTimeMillis();
            return ic.proceed();
        }
        catch (Exception e) {
        	logger.error(e.getMessage(), e);
        	throw e;
		} 
        finally {
    		logger.debug("SERVICE." + ic.getMethod().getName() + "\t" + (System.currentTimeMillis() - startTime) ); 
        }
     }

}
