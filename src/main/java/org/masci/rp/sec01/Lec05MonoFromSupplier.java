package org.masci.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec05MonoFromSupplier {
  public static void main(String[] args) {
//    var mono = Mono.just(getName());

    var mono = Mono.fromSupplier(Lec05MonoFromSupplier::getName);
    mono.subscribe(Util.onNext());
  }

  private static String getName() {
    System.out.println("Generating ...");
    return Util.faker().name().fullName();
  }
}
