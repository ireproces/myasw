package asw.intro.client;

import asw.intro.service.Service;
import asw.intro.server.ServiceImpl;

import java.util.logging.*;

/* Client del servizio. */
public class Client {

	/* logger */
	private Logger logger = Logger.getLogger("asw.intro.client");

	/* il servizio Service */
	// DIPENDENZA DIRETTA con l'implementazione del servizio offerto dal server
	private Service service;

	/* Crea un nuovo client. */
	public Client() {
		logger.info("Creazione di un nuovo Client");

		// il client soddisfa autonomamente la sua dipendenza
		// new ServiceImpl() rappresenta il CONNETTORE
		// la variabile del servizio è di tipo Service (interfaccia)
		// ma l'oggetto creato è di tipo ServiceImpl (implementazione concreta
		this.service = new ServiceImpl();
	}

	/* L'esecuzione del client, che richiede l'esecuzione del servizio Service. */
	public void run() {
		/* nota: il servizio service.alpha converte una stringa in maiuscole */
		logger.info("Client: Ora uso un servizio che converte una stringa in MAIUSCOLE");

		String[] data = { "ciao", "Prova", "FINE" };

		for (String arg : data) {
			logger.info("Client: calling alpha(" + arg + ")");

			// CHIAMATA DI PROCEDURA LOCALE
			// è una chiamata sincrona (durante l'esecuzione del servizio
			// il client è bloccato - in attesa)
			String result = service.alpha(arg);

			logger.info("Client: alpha(" + arg + ") ==> " + result);
		}
	}

}
