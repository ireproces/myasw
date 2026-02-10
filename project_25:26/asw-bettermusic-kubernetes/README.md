# BETTERMUSIC (Kubernetes Version)

Progetto del corso di Analisi e progettazione del software per l'anno accademico 2025-2026. 


## Descrizione del progetto

Questo progetto contiene il il codice di ***BetterMusic***, un semplice social network per la condivisione di recensioni di album musicali.  
Gli utenti del sistema possono scrivere e pubblicare delle recensioni, possono poi seguire le recensioni scritte da specifici recensori e quelle degli album realizzati da specifici artisti oppure di specifici generi.  
Quando un utente accede alla pagina delle recensioni che segue, gli vengono mostrate le recensioni dei recensori e degli artisti e dei generi che segue.


## Tecnologie utilizzate
Il progetto sfrutta un ecosistema di tecnologie moderne per garantire la natura distribuita e resiliente dell'applicazione.

### Core Framework & Language
* **Java** & **Spring Boot**: Framework principale utilizzato per lo sviluppo della logica di business e degli adattatori dei microservizi.
* **Gradle**: Strumento di build automation utilizzato per la gestione delle dipendenze e la compilazione del progetto.

### Comunicazione & Messaggistica
* **Apache Kafka**: Message broker utilizzato per l'implementazione del pattern Event-Driven. Distribuito all'interno del cluster Kubernetes per la gestione asincrona degli eventi di dominio.

### Persistenza dei dati
* **PostgreSQL**: Ogni microservizio (album, recensioni, connessioni, recensioni-seguite) utilizza la propria istanza di database relazionale per garantire l'isolamento dei dati. La gestione avviene tramite Deployment e Service Kubernetes.
* **Spring Data JPA**: Utilizzato per l'interazione con le basi di dati attraverso il pattern Repository all'interno degli adattatori di uscita.

### Infrastruttura & Deployment
* **Kubernetes (k8s)**: Utilizzato per l'orchestrazione dei container, garantendo self-healing, service discovery e bilanciamento del carico.
* **Helm**: Utilizzato per gestire l'installazione e la configurazione di componenti infrastrutturali complessi.
* **Docker**: Utilizzato per la creazione delle immagini dei singoli microservizi.
* **API Gateway**: Punto di ingresso unico (porta 8080) che espone i path dei vari microservizi all'esterno.


## Architettura e Microservizi
L'applicazione *BetterMusic* segue un'architettura a **microservizi** basata sul **pattern Event-Driven**.
La comunicazione principale avviene tramite un **broker Apache Kafka**, rendendo il sistema resiliente ed asincrono.\
Ogni modulo rispetta i principi dell'**Architettura Esagonale** ed è internamente strutturato per isolare la logica di business (contenuta nel Package `domain`) dagli adattatori infrastrutturali.

I **microservizi principali** sono:

* Il **servizio *album*** gestisce gli album musicali. 
  Ogni album ha un titolo ed è stato realizzato da un artista, e può essere relativo ad uno o più generi musicali. 
  
  Un esempio di album: 
  * id: 1
  * titolo: The Dark Side of the Moon
  * artista: Pink Floyd
  * generi: Rock, Progressive Rock 
  
  Inoltre, ogni album ha un id numerico univoco. 
  Anche titolo ed artista identificano univocamente gli album. 
  
  Operazioni: 
  * `POST /album` $\to$ aggiunge un nuovo album (dati titolo, artista e una lista di generi)
  * `GET /album/{id}` $\to$ trova un album, dato l'id 
  * `GET /album` $\to$ trova tutti gli album
  * `GET /cercaalbum?titolo={titolo}&artista={artista}` $\to$ trova un album, dati titolo e artista
  * `GET /cercaalbum/artista/{artista}` $\to$ trova tutti gli album di un certo artista
  * `GET /cercaalbum/genere/{genere}` $\to$ trova tutti gli album di un certo genere
  
* Il **servizio *recensioni*** gestisce le recensioni. 
  Ogni recensione è scritta da un recensore, si riferisce ad un album (tramite il suo id),
  e contiene il testo della recensione (che può essere molto lungo) ed un suo sunto (di solito breve). 
  
  Un esempio di recensione: 
  * id: 101
  * recensore: Woody
  * idAlbum: 1
  * testo: Bla bla bla...
  * sunto: Il lato buio dell'animo umano
  
  Una recensione in formato breve è una recensione che non contiene il testo completo della recensione, ma solo il suo sunto. 
  Per motivi di prestazioni, le operazioni che restituiscono più recensioni, le restituiscono in formato breve. 
  
  Operazioni: 
  * `POST /recensioni` $\to$ aggiunge una nuova recensione (dati recensore, titolo dell'album, artista dell'album, testo e sunto della recensione)
  * `GET /recensioni/{id}` $\to$ trova una recensione, dato l'id (la recensione contiene il testo completo della recensione) 
  * `GET /recensioni` $\to$ trova tutte le recensioni (in formato breve) 
  * `GET /cercarecensioni/album/{idAlbum}` $\to$ trova tutte le recensioni (in formato breve) di un certo album, dato il suo id
  * `GET /cercarecensioni/recensore/{recensore}` $\to$ trova tutte le recensioni (in formato breve) di un certo recensore

  Le recensioni vengono validate consultando una base di dati locale degli album, mantenuta aggiornata tramite la sottoscrizione agli eventi di creazione album su Kafka.
  
* Il **servizio *connessioni*** gestisce le connessioni degli utenti con le recensioni che seguono, 
  e più precisamente con gli artisti, con i recensori e con i generi musicali che essi seguono. 

  Le connessioni sono delle terne *utente-seguito-ruolo*, 
  in cui:
  * l'*utente* è chi segue, 
  * *seguito* è chi o che cosa è seguito (un artista oppure uno che scrive recensioni oppure un genere musicale),
  * *ruolo* rappresenta il ruolo del seguito e può essere *ARTISTA*, *RECENSORE* oppure *GENERE*. 
  
  Per esempio, la terna *Alice-Pink Floyd-ARTISTA* indica che *Alice* è interessata a seguire le recensioni degli album dei *Pink Floyd*.\
  Invece, la terna *Bob-OndaRock-RECENSORE* indica che *Bob* è interessato a seguire le recensioni scritte da *OndaRock*.\
  Infine, la terna *Carlo-Pop-GENERE* indica che *Carlo* è interessato a seguire le recensioni sugli album del genere *Pop*. 

  Operazioni: 
  * `POST /connessioni` $\to$ aggiunge una nuova connessione utente-seguito-ruolo (dati utente, seguito e ruolo)
  * `GET /connessioni` $\to$ trova tutte le connessioni 
  * `GET /connessioni/{utente}` $\to$ trova tutte le connessioni di un certo utente
  * `GET /connessioni/{utente}/{ruolo}` $\to$ trova tutte le connessioni di un certo utente relative a un certo ruolo
  * `DELETE /connessioni` $\to$ cancella una connessione utente-seguito-ruolo (dati utente, seguito e ruolo)

* Il **servizio *recensioni-seguite*** consente ad un utente di trovare le recensioni degli artisti, dei recensori e dei generi musicali che segue. 

  Operazioni: 
  * `GET /recensioniseguite/{utente}` $\to$ trova tutti le recensioni seguite da un certo utente, 
    ovvero le recensioni di artisti di album e di recensori e di generi musicali seguiti da quell'utente
	(le recensioni sono in formato breve)

  Agisce come un query-model (pattern CQRS), aggregando in tempo reale gli eventi provenienti dai servizi album, recensioni e connessioni per fornire risposte immediate senza interdipendenze sincrone.
  
* Il **servizio *api-gateway*** (esposto sulla porta *8080*) è l'API gateway dell'applicazione che: 
  * espone il servizio *album* sul path `/album` - ad esempio, `GET /album/album/{id}`
  * espone il servizio *recensioni* sul path `/recensioni` - ad esempio, `GET /recensioni/recensioni`
  * espone il servizio *connessioni* sul path `/connessioni` - ad esempio, `GET /connessioni/connessioni/{utente}`
  * espone il servizio *recensioni-seguite* sul path `/recensioni-seguite` - ad esempio, `GET /recensioni-seguite/recensioniseguite/{utente}`

### Topic Kafka
Per garantire il disaccoppiamento e la resilienza del sistema, sono stati definiti i seguenti topic su cui viaggiano gli **eventi di dominio**:

1. **Topic `album`**
  * **Producer**: Servizio *album*
  * **Consumer**: Servizi *recensioni* e *recensioni-seguite*
  * **Evento**: `AlbumCreatedEvent`, emesso e notificato ogni volta che un nuovo album viene registrato nel sistema (contiene tutti i dati dell'album)

2. **Topic `recensioni`**
  * **Producer**: Servizio *recensioni*
  * **Consumer**: Servizio *recensioni-seguite*
  * **Evento**: `RecensioneCreatedEvent`, emesso e notificato ogni volta che una nuova recensione viene registrata nel sistema (contiene i dati della recensione in formato breve)

3. **Topic `connessioni`**
  * **Producer**: Servizio *connessioni*
  * **Consumer**: Servizio *recensioni-seguite*
  * **Eventi**: `ConnessioneCreatedEvent` e `ConnessioneDeletedEvent`, emesso e notificato ogni volta che una nuova connessione utente-seguito-ruolo viene creata o cancellata

### Struttura del Progetto
Il progetto è organizzato come un **sistema multi-modulo** Gradle.\
Ogni componente logico è suddiviso in due moduli distinti per gestire correttamente le dipendenze e i contratti di interfaccia.\\

Oltre al codice sorgente, il progetto include una cartella `/kafka/kubernetes` dedicata alle configurazioni K8s.

#### Pattern dei Moduli
Ogni microservizio è composto da due **moduli distinti**:
* **Modulo di Implementazione** `<service>` (es. `album`, `recensioni`) $\to$ contiene l'intera applicazione Spring Boot, la logica di business, l'accesso al database e la configurazione di Kafka. **Non è condiviso** con gli altri servizi.
* **Modulo di Interfaccia** `<service>-api` (es. `album-api`, `recensioni-api`) $\to$ è una **libreria leggera condivisa tra i microservizi** che permette agli altri servizi di conoscere la struttura dei dati senza dipendere dall'implementazione. Definisce i **Contratti**: le classi degli Eventi e gli oggetti per le chiamate REST.

#### Organizzazione Interna
All'interno del modulo di implementazione, tutti i microservizi rispettano rigorosamente la **divisione in package** prevista dall'Architettura Esagonale:
* **`domain`** $\to$ contiene le Entità, la logica di business e le Porte (interfacce). È isolato da qualsiasi tecnologia esterna.
* **`rest`** (Inbound Adapter) $\to$ gestisce l'esposizione delle API REST verso l'esterno.
* **`eventpublisher`** (Outbound Adapter) $\to$ implementa la pubblicazione fisica degli messaggi sul broker Kafka.
* **`eventsconsumer`** (Inbound Adapter - ove presente) $\to$ gestisce la ricezione e l'elaborazione degli eventi in arrivo dai topic Kafka.

## Esecuzione su Kubernetes
L'**applicazione** è **progettata per essere rilasciata su un cluster Kubernetes**.\
Ogni microservizio ed ogni componente dell'infrastruttura (PostgreSQL, Kafka) gira in un container separato.

### Ambiente di Esecuzione
L'**enviroment** utilizzato per lo sviluppo e l'esecuzione è il **cluster** fornito nella [repository del corso](https://github.com/aswroma3/asw/tree/main/environments/virtualbox-ubuntu-environments/kube-cluster), **gestito tramite VirtualBox e Vagrant**.\\

L'**infrastruttura** comprende:
* una macchina virtuale di sviluppo (`kube-dev`) esterna al cluster, utilizzata come client
* il cluster Kubernetes vero e proprio costituito da tre nodi:
  1. un nodo Control Plane (`kube-1`)
  2. due nodi Worker (`kube-2` e `kube-3`) dedicati ai workload applicativi

### Deployoment 
Per **avviare il sistema**, ed automatizzare la creazione delle risorse kubernetes nei namespace `asw` e `kafka`, è disponibile uno script dedicato:
```bash
./start-bettermusic.sh
```

Una volta completato il deployment, è possibile **monitorare lo stato delle risorse** tramite i comandi `kubectl`.
* **Pod Applicativi** $\to$ comando per il monitoraggio dello stato dei pod relativi al namespace `asw`:
  ```bash
  kubectl get pods -n asw
  ```
* **Infrastruttura Kafka** $\to$ comando per il monitoraggio dello stato dei pod elativi al namespace `kafka`:
  ```bash
  kubectl get pods -n kafka
  ```
* **Servizi e Networking** $\to$ comando per il monitoraggio dello stato dei pod relativi a tutti i namespaces:
  ```bash
  kubectl get services --all-namespaces
  ```

### Undeployment
Per **arrestare** il sistema **e pulire** le risorse create, è possibile eseguire lo script di rimozione:
```bash
./undeploy-bettermusic.sh
```

### Inizializzazione e Test
Sono forniti degli script per automatizzare il caricamento dei dati e la verifica del sistema:
* **Inizializzazione** $\to$ `./do-init-bettermusic-db.sh` carica i dati di esempio iniziali.
* **Verifica Aggiornamenti** $\to$ gli script `do-update-albums.sh`, `do-update-recensioni.sh` e `do-update-connessioni.sh` simulano nuovi inserimenti e verificano che i servizi asincroni aggiornino correttamente le proprie basi di dati tramite Kafka.
* **Esecuzione Query** $\to$ `./run-sample-queries.sh` esegue le interrogazioni di test tramite l'API Gateway sulla porta 8080.

#### Utility e Debug
Per un controllo più granulare del sistema, sono disponibili ulteriori script:
* **Ispezione Dati** $\to$ gli script `do-get-...` (es. `./do-get-albums.sh`) permettono di consultare lo stato locale di ogni servizio.
* **Monitoraggio Kafka** $\to$ gli script `test_kafka_...` consentono di ispezionare i messaggi in transito sui topic Kafka per verificare la corretta comunicazione asincrona.

## Autori

Alessandro Cavina, Francesco Giovanardi, Leonardo Crozzoli, Tommaso Gioia, Irene Proietti Cesarini
