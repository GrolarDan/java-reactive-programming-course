package org.masci.rp.sec11.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {

  private String name;
  private final Sinks.Many<SlackMessage> sink;
  private final Flux<SlackMessage> flux;

  public SlackRoom(String name) {
    this.name = name;
    this.sink = Sinks.many()
        .replay()
        .all();
    this.flux = this.sink.asFlux();
  }

  public void joinRoom(SlackMember slackMember) {
    System.out.println(slackMember.getName() + " ------ Joined ----- " + this.name);
    this.subscribe(slackMember);
    slackMember.setMessageConsumer(
        msg -> this.postMessage(msg, slackMember)
    );
  }

  private void subscribe(SlackMember slackMember) {
    this.flux
        .filter(sm -> !slackMember.getName().equals(sm.getSender()))
        .doOnNext(sm -> sm.setReceiver(slackMember.getName()))
        .map(SlackMessage::toString)
        .subscribe(slackMember::receive);
  }

  public void postMessage(String message, SlackMember slackMember) {
    SlackMessage slackMessage = new SlackMessage();
    slackMessage.setSender(slackMember.getName());
    slackMessage.setMessage(message);
    sink.tryEmitNext(slackMessage);
  }
}
