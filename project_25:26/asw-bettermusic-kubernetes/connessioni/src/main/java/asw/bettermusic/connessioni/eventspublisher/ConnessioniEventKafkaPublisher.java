package asw.bettermusic.connessioni.eventspublisher;

import asw.bettermusic.connessioni.domain.ConnessioniEventPublisher;
import asw.bettermusic.connessioni.api.events.ConnessioniEvent;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import asw.bettermusic.connessioni.api.events.ConnessioniServiceEventChannel;

@Component
public class ConnessioniEventKafkaPublisher implements ConnessioniEventPublisher{
  private static final String TOPIC = ConnessioniServiceEventChannel.channel;

  @Autowired
  private KafkaTemplate<String, ConnessioniEvent> kafkaTemplate;

  public void publishConnessioniEvent(ConnessioniEvent event) {
    kafkaTemplate.send(TOPIC, event);
  }
}
