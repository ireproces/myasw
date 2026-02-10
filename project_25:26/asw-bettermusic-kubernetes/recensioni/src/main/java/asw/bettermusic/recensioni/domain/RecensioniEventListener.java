package asw.bettermusic.recensioni.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import asw.bettermusic.album.api.events.AlbumCreatedEvent;

@Service
public class RecensioniEventListener {
    
    @Autowired
    private AlbumRepository albumRepository;

    public void onAlbumCreated(AlbumCreatedEvent event) {
        Album album = new Album(event.getId(), event.getTitolo(), event.getArtista(), event.getGeneri());
        albumRepository.save(album);
    }
}
