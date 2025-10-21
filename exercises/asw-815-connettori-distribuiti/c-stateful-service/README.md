# 🧩 Progetto asw-815 versione c-stateful-service
Questo progetto implementa un **servizio distribuito client-server** stateful basato su socket **TCP**.
L’obiettivo è mostrare come gestire sessioni di stato lato server e lato client, mantenendo **separato lo stato globale dell’applicazione da quello di sessione** di ciascun client.

---

## 📁 Struttura del progetto
```
c-stateful-service
├── client/
│   └── src/main/java/asw/socket/client/
│       ├── Client.java
│       ├── connector/ServiceClientTCPProxy.java
│       ├── context/ApplicationContext.java
│       └── main/Main.java
├── server/
│   └── src/main/java/asw/socket/server/
│       ├── Server.java
│       ├── connector/CounterServiceServerTCPProxy.java
│       ├── connector/ServerThread.java
│       ├── CounterServiceImpl.java
│       ├── CounterApplicationState.java
│       └── CounterSessionState.java
├── service/
│   └── src/main/java/asw/socket/service/
│       ├── CounterService.java
│       ├── ConnectionOrientedService.java
│       ├── RemoteCounterService.java
│       └── RemoteException.java
├── service-impl/
│   └── src/main/java/asw/socket/server/
│       ├── CounterApplicationState.java
│       ├── CounterServiceImpl.java
│       ├── CounterSessionState.java
```
👉 Ciascun package corrisponde ad un ruolo:
- **client** $\to$ logica di invocazione del servizio remoto
- **server** $\to$ logica di gestione delle connessioni TCP e invocazione del servizio lato server
- **service** $\to$ contratto del servizio (interfacce condivise + eccezioni)
- **service-impl** $\to$ implementazione effettiva del servizio e gestione dello stato (globale e di sessione)

### 🖥️ Package `client`
**Componente di rete lato client**, invia richieste e riceve risposte tramite socket TCP.

Contiene:
- `Client.java` $\to$ programma principale del client
- `connector/ServiceClientTCPProxy.java` $\to$ implementa il proxy remoto, che traduce le chiamate ai metodi del servizio in messaggi TCP inviati al server (`writeTF`/`readUTF`)
- `context/ApplicationContext.java` $\to$ gestore (singleton) che crea client e proxy già configurati
- `main/Main.java` $\to$ punto di ingresso dell'applicazione client

### 💻 Package `server`
**Componente di rete lato server**, riceve connessioni, gestisce thread dedicati e inoltra le richieste al servizio.

Contiene:
- `Server.java` $\to$ programma principale del server, avvia un server socket sulla porta `7869` e resta in attesa di connessioni client
- `connector/ServiceServerTCPProxy.java` $\to$ implementa il proxy lato client: accetta connessioni e crea per ognuna un `ServerThread` con un’istanza di servizio dedicata
- `connector/ServerThread.java` $\to$ gestisce la comunicazione TCP con un singolo client

### 🧠 Package `service`
Definisce il **contratto del servizio remoto** e le eccezioni associate.

Contiene:
- `CounterService.java` $\to$ interfaccia base del servizio con i metodi di business (`incrementCounter`, `getGlobalCounter`,`getSessionCounter`)
- `ConnectionOrientedService.java` $\to$ interfaccia che definisce il comportamento connection-oriented
- `RemoteCounterService.java` $\to$ unione delle due interfacce precedenti, usata lato client e server
- `RemoteException.java` $\to$ eccezione sollevata in caso di errori di comunicazione remota (solo dal proxy lato client)

### ⚙️ Package `service-imp`
Implementazione **stateful** del servizio, incapsula la **logica applicativa**.

Contiene:
- `CounterServiceImpl.java` $\to$ implementa i metodi del servizio; **ogni client** ha la propria istanza, con uno **stato di sessione dedicato**
- `CounterApplicationState.java` $\to$ singleton che mantiene lo **stato globale condiviso**
- `CounterSessionState.java` $\to$ **stato locale di sessione**, associato a un singolo client/thread

---

## 🧱 Funzionamento Complessivo

### 1️⃣ Avvio del server
1. Il server viene avviato mediante la creazione di un'istanza `SocketServer` in ascolto sulla porta predefinita `7869`
2. Rimane in ascolto di richieste di connessione, che accetta tramite la primitiva `accept()`
3. Per ogni connessione con un client instaurata:
    - istanzia un `ServerThread` $\to$ ogni thread rappresenta una sessione dedicata al singolo client;
    - passa il socket accettato al thread $\to$ la gestione di ogni singolo thread è assegnata al proxy lato server (`CounterServiceServerTCPProxy`).

### 2️⃣ Avvio del Client
1. L'avvio del client avviene tramite il metodo main, nella classe `Main`, ed inizializza l'applicazione tramite:
    - il singleton `ApplicationContext`si occupa della creazione e configurazione del `CounterService` remoto $\to$ corrisponde ad un proxy remoto (`ServiceClientTCPProxy`) che gestisce la comunicazione TCP.

### 3️⃣ Connessione iniziale
Lato **client**, gestita da `ServiceClientTCPProxy`:
1. si connette al server, tramite primitiva `connect()`;
2. apre un socket verso il server, fornento host e porta;
3. crea gli stream (`DataInputStream in` e `DataOutputStream out`);
4. invia la stringa `"CONNECT"` al server, tramite il metodo `doOperation("CONNECT")`;
5. riceve `"ACK"` dal server che certifica la connessione stabilita.

Lato **server**:
1. l'istanza di `ServerThread` riceve il messaggio `"CONNECT"`;
2. crea una nuova istanza di `CounterServiceImpl`;
3. risponde con un `"ACK"` al client.

✅ Ora la sessione client-server è attiva, con un’istanza dedicata del servizio e uno stato di sessione privato.

### 4️⃣ Esecuzione delle Operazioni
Ogni **invocazione** dal client è **tradotta in una richiesta stringa** (tramite il proxy) **e inviata sul canale** TCP.

📍 `incrementCounter()`

Lato **client**:
1. `ServiceClientTCPProxy.incrementCounter()` invoca il metodo `doOperation("incrementCounter")`;
2. il metodo `doOperation`:
    - scrive `"incrementCounter"` sul socket, tramite l'operazione `out.writeUTF()`;
    - attende conferma dal server, tramite l'operazione `in.readUTF()`.

Lato **server**:
1. `ServerThread` riceve `"incrementCounter"`;
2. chiama il metodo `service.incrementCounter()` sulla propria istanza `CounterServiceImpl`;
3. il metodo `incrementCounter`:
    - incrementa contatore globale, tramite l'operazione `CounterApplicationState.incrementGlobalCounter()`;
    - incrementa contatore di sessione, tramite l'operazione `CounterSessionState.incrementSessionCounter()`.
4. restituisce risposta vuota o di conferma;
5. il proxy server invia la risposta al client.

📍 `getGlobalCounter()`

Lato **client**:
1. invia la richiesta al server, tramite l'operazione `doOperation("getGlobalCounter")`.

Lato **server**:
1. esegue il metodo `CounterServiceImpl.getGlobalCounter()` che legge il valore globale da `CounterApplicationState` e ritorna un intero;
2. l'intero viene convertito in stringa dal server e successivamente inviato al client.

📍 `getSessionCounter()`

Lato **client**:
1. invia la richiesta al server, tramite l'operazione `doOperation("getSessionCounter")`.

Lato **server**:
1. esegue il metodo `CounterServiceImpl.getSessionCounter()` che restituisce il contatore della sessione (incrementato solo per questo client) e ritorna un intero;
2. l'intero viene convertito in stringa dal server e successivamente inviato al client.

### 5️⃣ Disconnessione
Lato **client**:
1. si disconnette dal server, invocando il metodo `doOperation("DISCONNECT")`;
2. chiude socket e stream.

Lato **server**:
1. l'istanza di `ServerThread` riceve il messaggio `"DISCONNECT"`;
3. risponde con un `"ACK"` al client;
4. chiude il socket;
5. termina l'esecuzione dell'istanza di `ServerThread`.