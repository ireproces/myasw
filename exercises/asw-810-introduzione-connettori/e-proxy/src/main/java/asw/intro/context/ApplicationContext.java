package asw.intro.context;

import asw.intro.client.Client;
import asw.intro.service.Service;
import asw.intro.server.ServiceImpl;
import asw.intro.connector.ServiceProxy;

import java.util.logging.Logger;

/* Application context per il servizio Service e il client Client. */
public class ApplicationContext {

	/* logger */
	private Logger logger = Logger.getLogger("asw.intro.context");

    /* il singleton per l'application context */
	private static ApplicationContext instance = null;

	/* il riferimento al servizio */
	// mantiene un riferimento al vero servizio perché questo viene
	// gestito come un singleton anche se il client lo vede tramite un proxy
	private Service service = null;

	/* Costruttore dell'application context (privato per singleton). */
	private ApplicationContext() {
		logger.info("Creazione dell'Application Context");
	}

    public static synchronized ApplicationContext getInstance() {
        if (instance==null) {
        	instance = new ApplicationContext();
        }
        return instance;
    }

    /* Factory method per il servizio Service.
     * Se possibile viene restituito uno stesso servizio. */
    public synchronized Service getService() {
		logger.info("ApplicationContext: getService()");

		// se non esiste, crea il vero servizio come singleton
    	if (service==null) {
    		service = new ServiceImpl();
    	}

		// lo incapsula in un proxy al momento della creazione dello stesso
    	Service proxy = new ServiceProxy( service );
        return proxy;
    }

    /* Factory method per il client Client.
     * Ogni volta viene restituito un nuovo client. */
    public Client getClient() {
		logger.info("ApplicationContext: getClient()");
		Client client = new Client();

		// inietta la dipendenza dal servizio (proxy) nel client
    	client.setService( this.getService() );
        return client;
    }

}

