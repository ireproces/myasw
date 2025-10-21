package asw.socket.service;

/**
 * Interfaccia del servizio CounterService.
 */
public interface CounterService {

    // dichiarazione delle OPERAZIONI FUNZIONALI del servizio
    // vengono definite all'interno della classe ServiceClientTCPProxy

    public void incrementCounter() throws RemoteException;

	public int getGlobalCounter() throws RemoteException;

    public int getSessionCounter() throws RemoteException;

}

