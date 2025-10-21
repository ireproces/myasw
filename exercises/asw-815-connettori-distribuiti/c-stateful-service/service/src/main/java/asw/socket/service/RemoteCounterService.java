package asw.socket.service;

// interfaccia remota completa vista dal client.
// unifica:
// - operazioni del servizio vero e proprio (CounterService)
// - operazioni del servizio vero e proprio (ConnectionOrientedService)
public interface RemoteCounterService extends CounterService, ConnectionOrientedService {

}
