package asw.intro.client;

import asw.intro.service.Service;

import java.util.logging.*;

/* Client del servizio. */
public class Client {

	/* logger */
	private Logger logger = Logger.getLogger("asw.intro.client");

	/* il servizio Service */
	// gli oggetti Client ora si occupano solo di definire le sue dipendenze
	// ma NON di crearle
	private Service service;

	/* Crea un nuovo client. */
	public Client() {
		logger.info("Creazione di un nuovo Client");
	}

	/* Setter per il servizio Service. */
	// questo metodo viene usato per l'iniezione della dipendenza dall'esterno,
	// in particolare dall'applicazione Main, rendendo il client INDIPENDENTE dal connettore
	public void setService(Service service) {
		logger.info("Client: setService()");
		this.service = service;
	}

	/* L'esecuzione del client, che richiede l'esecuzione del servizio Service. */
	public void run() {
		/* nota: il servizio service.alpha converte una stringa in maiuscole */
		logger.info("Client: Ora uso un servizio che converte una stringa in MAIUSCOLE");

		String[] data = { "ciao", "Prova", "FINE" };

		for (String arg : data) {
			logger.info("Client: calling alpha(" + arg + ")");
			String result = service.alpha(arg);
			logger.info("Client: alpha(" + arg + ") ==> " + result);
		}
	}

}
