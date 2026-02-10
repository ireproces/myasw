package asw.bettermusic.recensioniseguite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.bettermusic.album.api.events.AlbumCreatedEvent;
import asw.bettermusic.recensioni.api.events.RecensioniCreatedEvent;
import asw.bettermusic.connessioni.api.events.ConnessioniCreatedEvent;
import asw.bettermusic.connessioni.api.events.ConnessioniDeletedEvent;
import asw.bettermusic.connessioni.api.events.ConnessioniEvent;


@Service
public class RecensioniSeguiteEventListener {
    
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private RecensioniRepository recensioniRepository;

    @Autowired
    private ConnessioniRepository connessioniRepository;

    public void onAlbumCreated(AlbumCreatedEvent event) {
        Album album = new Album(event.getId(), event.getTitolo(), event.getArtista(), event.getGeneri());
        albumRepository.save(album);
    }

    public void onRecensioniCreated(RecensioniCreatedEvent event) {
        Recensione recensione = new Recensione(event.getId(), event.getRecensore(), event.getIdAlbum(), event.getSunto());
        recensioniRepository.save(recensione);
    }

    public void onConnessioniEvent(ConnessioniEvent event) {
        if (event instanceof ConnessioniCreatedEvent) {
            ConnessioniHandleCreate((ConnessioniCreatedEvent) event);
        } else if (event instanceof ConnessioniDeletedEvent) {
            ConnessioniHandleDelete((ConnessioniDeletedEvent) event);
        }
    }

    public void ConnessioniHandleCreate(ConnessioniCreatedEvent event) {
        Connessione connessione = new Connessione(event.getId(), event.getUtente(), event.getSeguito(), event.getRuolo());
        connessioniRepository.save(connessione);
    }

    public void ConnessioniHandleDelete(ConnessioniDeletedEvent event) {
        Connessione connessione = connessioniRepository.findById(event.getId()).orElse(null);
        if (connessione != null) {
            connessioniRepository.delete(connessione);
        }
    }

    
}
