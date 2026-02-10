package asw.bettermusic.recensioni.domain;

import asw.bettermusic.recensioni.api.events.RecensioniCreatedEvent;

public interface RecensioniEventPublisher {
    void publishRecensioniCreated(RecensioniCreatedEvent event);
}
