package asw.bettermusic.album.eventspublisher;

import asw.bettermusic.album.domain.AlbumEventPublisher;
import asw.bettermusic.album.api.events.AlbumCreatedEvent;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import asw.bettermusic.album.api.events.AlbumServiceEventChannel;

@Component
public class AlbumEventKafkaPublisher implements AlbumEventPublisher{
  private static final String TOPIC = AlbumServiceEventChannel.channel;

  @Autowired
  private KafkaTemplate<String, AlbumCreatedEvent> kafkaTemplate;

  public void publishAlbumCreated(AlbumCreatedEvent event) {
    kafkaTemplate.send(TOPIC, event);
  }
}
