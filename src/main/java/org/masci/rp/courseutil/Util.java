package org.masci.rp.courseutil;

import com.github.javafaker.Faker;
import java.util.function.Consumer;

public class Util {

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
}
