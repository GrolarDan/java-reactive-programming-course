package org.masci.rp.sec04;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec08DefaultIfEmpty {

  public static void main(String[] args) {

    getOrderNumbers()
        .filter(i -> i > 10)
        .defaultIfEmpty(-100)
        .subscribe(DmkUtil.subscriber());
  }

  private static Flux<Integer> getOrderNumbers() {
    return Flux.range(1, 10);
  }
}
