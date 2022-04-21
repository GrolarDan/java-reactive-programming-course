package org.masci.rp.sec11.assignment;

import org.reactivestreams.Subscriber;
import reactor.core.publisher.Sinks;

public class SlackRoom {

  private final Sinks.Many<SlackMessage> sink;

  public SlackRoom() {
    this.sink = Sinks.many().replay().all();
  }

  public void joinRoom(Subscriber<SlackMessage> subscriber) {
    sink.asFlux().subscribe(subscriber);
  }

  public void sendMessage(SlackMessage msg) {
    sink.tryEmitNext(msg);
  }

}
