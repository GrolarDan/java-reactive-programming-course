package org.masci.rp.sec01;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Mono;

/**
 *
 * @author Daniel
 */
public class Lec06SupplierRefactoring {

  public static void main(String[] args) {

    getName();
    getName().subscribe(DmkUtil.onNext());
    getName();
  }

  private static Mono<String> getName() {
    System.out.println("entered getName method");
    return Mono.fromSupplier(() -> {
      System.out.println("Generating name...");
      DmkUtil.sleepSeconds(3);
      return DmkUtil.faker().name().fullName();
    }).map(String::toUpperCase);
  }
}
