package asw.bettermusic.recensioniseguite.eventsconsumer;


import asw.bettermusic.recensioniseguite.domain.*;
import asw.bettermusic.recensioni.api.events.RecensioniCreatedEvent;
import asw.bettermusic.recensioni.api.events.RecensioniServiceEventChannel;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class RecensioniEventsConsumer {

  @Autowired
  private RecensioniSeguiteEventListener recensioniSeguiteEventListener;

  @KafkaListener(topics = RecensioniServiceEventChannel.channel, groupId = "recensioni-seguite",
    properties = "spring.json.value.default.type=asw.bettermusic.recensioni.api.events.RecensioniCreatedEvent")
  public void listen(RecensioniCreatedEvent event) {
    recensioniSeguiteEventListener.onRecensioniCreated(event);
  }
}
