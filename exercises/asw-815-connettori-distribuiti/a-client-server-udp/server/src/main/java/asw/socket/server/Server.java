package asw.socket.server;

import asw.socket.service.Service;
import asw.socket.service.impl.ServiceImpl;
import asw.socket.server.connector.ServiceServerUDPProxy;

import java.util.logging.Logger;

/* server per il servizio */
public class Server {

	private static int SERVER_PORT = 6789;

	/* logger */
	private static Logger logger = Logger.getLogger("asw.socket.server");

    // punto di ingresso del server
    // l'istanza del server in realtà non esiste, viene iniziato direttamente il proxy
	public static void main(String[] args) {

        // dipendenza dall'implementazione del servizio
        Service service = new ServiceImpl();
		logger.info("Server: starting... (type CTRL+C to stop)");

        // creazione e avvio del proxy lato server
        ServiceServerUDPProxy server = new ServiceServerUDPProxy(service, SERVER_PORT);
        server.run();
        logger.info("Server: stopped");
    }
}


