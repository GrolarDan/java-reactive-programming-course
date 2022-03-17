package org.masci.rp.courseutil;

import com.github.javafaker.Faker;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;

public class DmkUtil {

  private static final Faker FAKER = Faker.instance();

  public static <T> Consumer<T> onNext() {
    return next -> System.out.println("Received : " + next);
  }

  public static Consumer<Throwable> onError() {
    return err -> System.out.println("ERROR: " + err.getMessage());
  }

  public static Runnable onComplete() {
    return () -> System.out.println("Completed");
  }

  public static Faker faker() {
    return FAKER;
  }

  public static void sleepSeconds(int seconds) {
    try {
      System.out.println("... sleeping ...");
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException ex) {
      System.out.println(ex);
    }
  }

  public static <T> Subscriber<T> subscriber() {
    return new DmkDefaultSubscriber<>();
  }

  public static <T> Subscriber<T> subscriber(String name) {
    return new DmkDefaultSubscriber<>(name);
  }

//  public static <T> Subscriber<T> subscriberSlf4j() {
//    return new DmkDefaultSubscriber<>(log::info);
//  }
//
//  public static <T> Subscriber<T> subscriberSlf4j(String name) {
//    return new DmkDefaultSubscriber<>(name, log::info);
//  }
}
