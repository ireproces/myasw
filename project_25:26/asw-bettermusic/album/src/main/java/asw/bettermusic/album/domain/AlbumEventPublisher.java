package asw.bettermusic.album.domain;

import asw.bettermusic.album.api.events.AlbumCreatedEvent;

public interface AlbumEventPublisher {
    
    void publishAlbumCreated(AlbumCreatedEvent event);
}
