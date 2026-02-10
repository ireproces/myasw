package asw.bettermusic.recensioni.eventspublisher;


import asw.bettermusic.recensioni.api.events.RecensioniCreatedEvent;
import asw.bettermusic.recensioni.domain.RecensioniEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import asw.bettermusic.recensioni.api.events.RecensioniServiceEventChannel;

@Component
public class RecensioniEventKafkaPublisher implements RecensioniEventPublisher{
  private static final String TOPIC = RecensioniServiceEventChannel.channel;

  @Autowired
  private KafkaTemplate<String, RecensioniCreatedEvent> kafkaTemplate;

  public void publishRecensioniCreated(RecensioniCreatedEvent event) {
    kafkaTemplate.send(TOPIC, event);
  }
}
