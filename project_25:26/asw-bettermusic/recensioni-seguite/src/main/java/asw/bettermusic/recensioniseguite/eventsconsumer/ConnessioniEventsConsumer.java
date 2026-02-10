package asw.bettermusic.recensioniseguite.eventsconsumer;

import asw.bettermusic.recensioniseguite.domain.*;
import asw.bettermusic.connessioni.api.events.ConnessioniEvent;
import asw.bettermusic.connessioni.api.events.ConnessioniServiceEventChannel;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class ConnessioniEventsConsumer {

  @Autowired
  private RecensioniSeguiteEventListener recensioniSeguiteEventListener;

  @KafkaListener(topics = ConnessioniServiceEventChannel.channel, groupId = "recensioni-seguite",
    properties = "spring.json.value.default.type=asw.bettermusic.connessioni.api.events.ConnessioniEvent")
  public void listen(ConnessioniEvent event) {
    recensioniSeguiteEventListener.onConnessioniEvent(event);
  }
}
