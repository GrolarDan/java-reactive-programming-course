package org.masci.rp.courseutil;

import java.util.function.Consumer;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DmkDefaultSubscriber<T> implements Subscriber<T> {

  private String name = "";
  private final Consumer<String> log;

  public DmkDefaultSubscriber() {
    log = System.out::println;
  }

  public DmkDefaultSubscriber(String name) {
    this.name = name;
    log = System.out::println;
  }

  public DmkDefaultSubscriber(Consumer<String> log) {
    this.log = log;
  }

  public DmkDefaultSubscriber(String name, Consumer<String> log) {
    this.name = name + " - ";
    this.log = log;
  }

  @Override
  public void onSubscribe(Subscription subscription) {
    subscription.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(T next) {
    log.accept(createMessage("%sReceived : %s", name, next));
  }

  @Override
  public void onError(Throwable throwable) {
    log.accept(createMessage("%sERROR : %s", name, throwable.getMessage()));
  }

  @Override
  public void onComplete() {
    log.accept(createMessage("%sCompleted", name));
  }

  private String createMessage(String format, Object... objects) {
    return String.format(format, objects);
  }

}
