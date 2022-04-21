package org.masci.rp.sec11.assignment;

import java.util.function.Consumer;

public class SlackMember {

  private Consumer<String> messageConsumer;
  private String name;

  public SlackMember(String name) {
    this.name = name;
  }

  String getName() {
    return name;
  }

  void setMessageConsumer(Consumer<String> messageConsumer) {
    this.messageConsumer = messageConsumer;
  }

  void receive(String message) {
    System.out.println(message);
  }

  public void sendMessage(String message) {
    messageConsumer.accept(message);
  }

}
