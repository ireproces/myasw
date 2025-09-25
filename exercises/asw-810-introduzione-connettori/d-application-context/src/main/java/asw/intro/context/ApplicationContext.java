package asw.intro.context;

import asw.intro.client.Client;
import asw.intro.service.Service;
import asw.intro.server.ServiceImpl;

import java.util.logging.Logger;

/* Application context per il servizio Service e il client Client. */
// componente che si occupa della creazione e delle iniezioni delle dipendenze
// ad un certo insieme di oggetti (in questo caso, Client e Service)
public class ApplicationContext {

	/* logger */
	private Logger logger = Logger.getLogger("asw.intro.context");

    /* il singleton per l'application context */
	private static ApplicationContext instance = null;

	/* il riferimento al servizio */
	private Service service = null;

	// non è presente alcun riferimento al Client poiché i client non sono
	// condivisi: ognuno è indipendente dagli altri.

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
	// questo perché il servizio è pensato per essere condiviso tra più client
    public synchronized Service getService() {
		logger.info("ApplicationContext: getService()");
    	if (service==null) {
    		service = new ServiceImpl();
    	}
        return service;
    }

    /* Factory method per il client Client.
     * Ogni volta viene restituito un nuovo client. */
    public Client getClient() {
		logger.info("ApplicationContext: getClient()");
		Client client = new Client();

		// iniezione della dipendenza nel client ed invocazione "implicita"
		// del metodo di creazione del servizio (se ancora inesistente)
    	client.setService( this.getService() );
        return client;
    }

}

