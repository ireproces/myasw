package asw.socket.service;

/**
 * Interfaccia del servizio Service.
 *
 * ServiceException: eccezione applicativa personalizzata (definita dal programmatore)
 *  per segnalare errori legati al servizio stesso
 * RemoteException: eccezione di comunicazione remota, indica che si è verificato un
 *  errore di rete o di invocazione remota durante la chiamata del metodo
 */
public interface Service {

	/**
	 * Converte in lettere maiuscole la stringa arg. È richiesto che arg
	 * sia lunga almeno 4 caratteri.
	 * 
	 * Se arg == null o arg.length() < 4, viene sollevata l'eccezione ServiceException.
	 */
    public String alpha(String arg) throws ServiceException, RemoteException;

    /**
	 * Converte in lettere minuscole la stringa arg. Non pone vincoli sulla
	 * lunghezza di arg.
	 * 
	 * Se arg == null, viene sollevata l'eccezione ServiceException.
	 */
    public String beta(String arg) throws ServiceException, RemoteException;

}

