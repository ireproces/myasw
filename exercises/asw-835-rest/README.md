# 🧩 Progetto asw-835-rest

## 🍃 REST e Spring
Spring Web MVC fornisce supporto per REST, in 2 modalità:
a. classe annotata come `@RestController`;
b. classe annotata come `@Controller` e parametri delle operazioni annotati come `@RequestBody` o `@ResponseBody`

Sia i parametri che il risultato di un'operazione remota possono essere "atomici" oppure oggetti di un Presentation Model.\
Questi **dati** vengono **scambiati su HTTP** e **Spring si occupa della conversione** nel formato adatto in modo trasparente rispetto allo sviluppatore.

---

## 🌐 Tecnologia REST
**REST** (REpresentational State Transfer) è una **tecnologia** impiegata **per l'invocazione remota**, **basata su HTTP**.\
Consente:
- *lato client*: effetuare l'**invocazione remota di un operazione del server tramite una richiesta HTTP all'URI associato** all'operazione;
- *lato server*: **associare un'operazione ad un metodo HTTP tramite un URI**, questo avviene sulla base di un'interfaccia uniforme.

### 📦 Risorse e Rappresentazioni
Una **risorsa** è **qualsiasi elemento informativo di interesse** a cui viene associato un nome univoco, chiamato **identificatore di risorsa** (es. un URI).

Una **rappresentazione** è un **gruppo di dati per una risorsa** (es. file JSON o XML).\
Il loro **impiego** consente di **disaccoppiare il modo in cui i servizi memorizzano internamente le risorse dal modo** (o dai modi) **con cui le condividono esternamente** $\to$ **nella comunicazione** tra client e servizio, **vengono scambiate rappresentazioni** di risorse.

### ⚙️ Servizi REST
Un **servizio REST** consente di **gestire una o più collezioni omogenee di risorse**.\
Il servizio definisce:
- **per ciascuna collezione**, un URI di base – **collection URI**;
    le **operazioni** riferite possono essere:
    - `GET` $\to$ restituisce tutti gli elementi della collezione;
    - `PUT` $\to$ sostituisce la collezione con un’altra collezione;
    - `POST` $\to$ crea un nuovo elemento della collezione, e gli assegna un nuovo URI;
    - `PATCH` $\to$ modifica la collezione sulla base di una lista di cambiamenti specificati nella richiesta;
    - `DELETE` $\to$ cancella l'intera collezione.
- **ciascuna istanza di risorsa** ha un proprio URI – **element URI**.
    le **operazioni** riferite possono essere:
    - `GET` $\to$ restituisce uno specifico elemento della collezione;
    - `PUT` $\to$ crea un nuovo elemento della collezione, oppure lo aggiorna;
    - `POST` $\to$ considera l’elemento della collezione come un’altra collezione, e ne aggiunge un elemento;
    - `PATCH` $\to$ aggiorna parzialmente uno specifico elemento della collezione;
    - `DELETE` $\to$ cancella l’elemento della collezione.

#### Server e Client
Il **server** **esegue** le **operazioni** remote **in modo concorrente** nell’ambito di **un thread** (lato server) differente e separato.

I **client** possono essere **molti**, e accedere al server in modo concorrente.\
È possibile che le **invocazioni remote** di questi ultimi avvengano **in modalità asincrona**, sono ottenibili tramite **operazioni** annotate come **`@Async`**:
- l'operazione viene **eseguita in un thread separato** rispetto a quello del suo chiamante;
- l'annotazione può essere usata **solo per metodi pubblci**;
- la classe dell'applicazione Spring Boot deve essere annotata come `@EnableAsync`

---

## 💾 Formato JSON
Il **formato JSON** (JavaScript Object Notation) è un formato di **interscambio di dati**, **testuale** e leggero e, soprattutto, **facile per il software da interpretare e da generare** (in una varità di linguaggi di programmazione).

I due **costrutti** principali del formato sono:
1. **oggetto** (**record**) è un **gruppo di coppie nome/valore** (`"nome": valore`);
2. **array** (lista) è una **sequenza ordinata di valori**.

---

## OpenAPI Specification e Swagger UI
**OpenAPI Specification** definisce una descrizione standard delle API REST, che consente di **ispezionare facilmente l'interfaccia di un servizio** (senza dover comprenderne il codice).

**Swagger UI** consente di **interagire con un servizio REST mediante un'interfaccia web** generata automaticamente a partire dall’interfaccia del servizio.