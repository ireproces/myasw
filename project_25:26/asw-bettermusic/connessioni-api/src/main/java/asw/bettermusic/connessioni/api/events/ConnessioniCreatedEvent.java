package asw.bettermusic.connessioni.api.events;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class ConnessioniCreatedEvent implements ConnessioniEvent {
    Long id;
    String utente;
    String seguito;
    String ruolo;
}
