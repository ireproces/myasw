package asw.bettermusic.recensioni.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import asw.bettermusic.recensioni.api.events.RecensioniCreatedEvent;
import java.util.*; 

@Service
public class RecensioniServiceImpl implements RecensioniService {

	@Autowired
	private RecensioniRepository recensioniRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private RecensioniEventPublisher recensioniEventPublisher;

	/* Crea una nuova recensione, a partire dai suoi dati, usando solo il DB locale. */ 
 	public Recensione createRecensione(String recensore, String titoloAlbum, String artistaAlbum, String testo, String sunto) {
		Album album = albumRepository.findByTitoloAndArtista(titoloAlbum, artistaAlbum)
			.orElseThrow(() -> new IllegalArgumentException("Album not found: " + titoloAlbum + " - " + artistaAlbum));
		Recensione recensione = new Recensione(recensore, album.getId(),  testo, sunto); 
		try {
			recensione = recensioniRepository.save(recensione);
			RecensioniCreatedEvent event = new RecensioniCreatedEvent(recensione.getId(), recensione.getRecensore(), recensione.getIdAlbum(), recensione.getSunto());
			recensioniEventPublisher.publishRecensioniCreated(event);
			return recensione;
		} catch(Exception e) {
			/* si potrebbe verificare un'eccezione se è violato il vincolo di unicità dell'album */ 
			return null; 
		}
	}

	/* Trova una recensione, dato l'id. */ 
 	public Recensione getRecensione(Long id) {
		Recensione recensione = recensioniRepository.findById(id).orElse(null);
		return recensione;
	}

	/* Trova tutte le recensioni. */ 
	public Collection<Recensione> getRecensioni() {
		Collection<Recensione> recensioni = recensioniRepository.findAll();
		return recensioni;
	}

	/* Trova tutte le recensioni di un album, dato l'id. */ 
	public Collection<Recensione> getRecensioniByIdAlbum(Long idAlbum) { 
		Collection<Recensione> recensioni = recensioniRepository.findByIdAlbum(idAlbum);
		return recensioni;
	}

	/* Trova tutte le recensioni scritte da un recensore. */ 
	public Collection<Recensione> getRecensioniByRecensore(String recensore) {
		Collection<Recensione> recensioni = recensioniRepository.findByRecensore(recensore);
		return recensioni;
	}

}
