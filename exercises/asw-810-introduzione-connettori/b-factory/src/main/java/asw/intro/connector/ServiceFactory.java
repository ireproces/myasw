package asw.intro.connector;

import asw.intro.service.Service;
import asw.intro.server.ServiceImpl;

import java.util.logging.Logger;

/* Factory per il servizio Service. */
public class ServiceFactory {

	/* logger */
	private Logger logger = Logger.getLogger("asw.intro.connector");

    /* il singleton per la factory */
	// implementa il pattern singleton
	// l'obiettivo e' di avere una sola factory in tutta l'applicazione
	private static ServiceFactory instance = null;

	/* il riferimento al servizio */
	// contiene l’oggetto Service effettivo che la factory fornisce al client
	// inizializzarla a null significa che non esiste ancora un’istanza del
	// servizio, che verra' creata SOLO ED UNICAMENTE alla prima invocazione
	// di getService (lazy initialization)
	private Service service = null;

	/* Costruttore della factory (privato per singleton). */
	private ServiceFactory() {
		logger.info("Creazione della ServiceFactory");
	}

	// entrambi i metodi sono definiti synchronized, questo li rende thread-safe, 
	// in un'applicazione multi-threaded:
	// - getInstance() --> garantisce che un solo thread possa creare l’istanza della factory
	// - getService() --> garantisce che un solo thread possa creare l’istanza del servizio
	// NB:
	// anche se ServiceImpl non è un singleton di per sé, la factory restituisce sempre lo
	// stesso oggetto, simulando un comportamento singleton a livello di istanza gestita.

    public static synchronized ServiceFactory getInstance() {

		// questo controllo e' necessario per il singleton
        if (instance==null) {
        	instance = new ServiceFactory();
        }
		// se siamo qui, l'istanza esiste (che sia stata appena creata o meno)
		// quindi la restituiamo al Client che ne ha fatto richiesta
        return instance;
    }

    /* Factory method per il servizio Service. */
    public synchronized Service getService() {
		logger.info("ServiceFactory: getService()");
    	if (service==null) {

			// la factory FA PARTE DEL CONNETTORE: è lei che decide
			// quale implementazione del servizio fornire al Client
    		service = new ServiceImpl();
    	}
        return service;
    }

}

