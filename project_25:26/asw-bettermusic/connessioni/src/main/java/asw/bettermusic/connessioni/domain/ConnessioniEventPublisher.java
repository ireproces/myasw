package asw.bettermusic.connessioni.domain;

import asw.bettermusic.connessioni.api.events.ConnessioniEvent;

public interface ConnessioniEventPublisher {
    void publishConnessioniEvent(ConnessioniEvent event);
}
