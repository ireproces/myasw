package asw.intro.service;

/* Interfaccia del servizio Service. */

// definisce il "contratto" del servizio, permette di avere più versioni
// diverse del servizio senza cambiare il codice del Client (vedi commenti nel codice del Client)
//
// astrazione --> Service.java
// implementazione --> ServiceImpl.java
public interface Service {

	/* Restituisce la stringa passata come parametro,
	 * ma in lettere maiuscole. */
	// dichiarazione del metodo del servizio
	public String alpha(String arg);

}

