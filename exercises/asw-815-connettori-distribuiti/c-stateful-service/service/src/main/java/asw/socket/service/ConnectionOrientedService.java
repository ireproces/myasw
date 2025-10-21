package asw.socket.service;

import asw.socket.service.RemoteException;

/**
 * Operazioni aggiuntive per servizi remoti di tipo connection-oriented.
 */
public interface ConnectionOrientedService {

	// dichiarazione delle OPERAZIONI DI CONTROLLO della CONNESSIONE (tipche
	// dei servizi connection-oriented)
	// la definizione avviene nella classe ServiceClientTCPProxy
	
	public void connect() throws RemoteException;

	public void disconnect() throws RemoteException;

}
