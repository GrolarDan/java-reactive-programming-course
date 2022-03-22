package org.masci.rp.sec04;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec09SwitchIfEmpty {
  public static void main(String[] args) {

    getOrderNumbers()
        .filter(i -> i > 10)
        .switchIfEmpty(fallback())
        .subscribe(DmkUtil.subscriber());
  }

  // redis cache
  private static Flux<Integer> getOrderNumbers() {
    return Flux.range(1, 10);
  }

  // db
  private static Flux<Integer> fallback() {
    return Flux.range(20, 5);
  }
}
