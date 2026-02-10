package asw.bettermusic.recensioni.api.events;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class RecensioniCreatedEvent {
  private Long id;
  private String recensore;
  private Long idAlbum;
  private String sunto;
}
