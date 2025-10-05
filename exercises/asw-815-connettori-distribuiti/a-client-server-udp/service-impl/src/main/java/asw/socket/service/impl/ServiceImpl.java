package asw.socket.service.impl;

import asw.socket.service.*;

import java.util.logging.Logger;

/**
 * Implementazione del servizio Service.
 * 
 * Per semplicita' il logging viene fatto qui nel servizio,
 * ma sarebbe piu' opportuno farlo altrove (in un proxy).
 */
public class ServiceImpl implements Service {

	/* logger */
	private Logger logger = Logger.getLogger("asw.socket.service.impl");

	/**
	 * Per la funzionalità del metodo e le precondizioni, vedi l'interfaccia Service.
	 */
	public String alpha(String arg) throws ServiceException {
		if (arg==null || arg.length()<4) {
			logger.info("ServiceImpl: executing alpha: ServiceException: argument is null or too short.");
			throw new ServiceException("ServiceImpl: executing alpha: ServiceException: argument is null or too short.");
		} else {
			String result = arg.toUpperCase();
			logger.info("ServiceImpl: executing alpha(" + arg + ") ==> " + result);
			return result;
		}
    }

	/**
	 * Per la funzionalità del metodo e le precondizioni, vedi l'interfaccia Service.
	 *
	 * Introduce una caratteristica dell'implementazione, non dell'interfaccia: un ritardo
	 * pari a arg.length()/10 secondi.
	 * Questo potrebbe causare un timeout nell'accesso remoto, se arg fosse troppo lunga.
	 */
	public String beta(String arg) throws ServiceException {
		if (arg==null) {
			logger.info("ServiceImpl: executing beta: ServiceException: argument is null.");
			throw new ServiceException("ServiceImpl: executing beta: ServiceException: argument is null.");
		} else {
			String result = arg.toLowerCase();
			logger.info("ServiceImpl: executing beta(" + arg + ") ==> " + result);

			/* introduce un ritardo proporzionale ad arg.length() */
			sleep(arg.length()*100);
	    	return result;
		}
    }

	/* Introduce un ritardo di esattamente delay millisecondi. */
	private static void sleep(int delay) {
        try {
        	// int delay = (int)(Math.random()*maxDelay);
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
	}	
	
}

