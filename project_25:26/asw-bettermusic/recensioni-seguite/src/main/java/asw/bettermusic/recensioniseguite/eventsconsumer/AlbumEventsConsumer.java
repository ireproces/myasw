package asw.bettermusic.recensioniseguite.eventsconsumer;

import asw.bettermusic.album.api.events.AlbumCreatedEvent;
import asw.bettermusic.recensioniseguite.domain.*;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import asw.bettermusic.album.api.events.AlbumServiceEventChannel;

@Component
public class AlbumEventsConsumer {

  @Autowired
  private RecensioniSeguiteEventListener recensioniSeguiteEventListener;

  @KafkaListener(topics = AlbumServiceEventChannel.channel, groupId = "recensioni-seguite",
    properties = "spring.json.value.default.type=asw.bettermusic.album.api.events.AlbumCreatedEvent")
  public void listen(AlbumCreatedEvent event) {
    recensioniSeguiteEventListener.onAlbumCreated(event);
  }
}
