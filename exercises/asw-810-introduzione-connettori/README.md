# 🧩 Progetto asw-810-intoduzione-connettori
Si tratta di semplici esempi, che introducono l'uso dei connettori.

---

## 📁 1. Struttura dei progetti
In generale:
- package `service` $\to$ definisce l'interfaccia `Service` del servizio
- package `server`
    - classe `ServiceImpl.java` $\to$ definisce l'implementazione del servizio
    - classe `Server.java` $\to$ (ove presente) serve a creare ed avviare il servizio
- package `client`
    - classe `Client.java` $\to$ definisce il client del servizio
- package `main`
    - classe `Main.java` $\to$ (un'applicazione) serve a creare ed avviare il client

Inoltre, ove presenti:
- package `connector` $\to$ definiscono connettori (come factory o proxy)
- package `context` $\to$ definisce l'application context