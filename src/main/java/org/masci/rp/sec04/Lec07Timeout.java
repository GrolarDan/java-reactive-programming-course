package org.masci.rp.sec04;

import java.time.Duration;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec07Timeout {

  public static void main(String[] args) {

    getOrderNumbers()
        .timeout(Duration.ofSeconds(2), fallback())
        .subscribe(DmkUtil.subscriber());

    DmkUtil.sleepSeconds(60);
  }

  private static Flux<Integer> getOrderNumbers() {
    return Flux.range(1, 10)
        .delayElements(Duration.ofSeconds(1));
  }

  private static Flux<Integer> fallback() {
    return Flux.range(100, 10)
        .delayElements(Duration.ofMillis(200));
  }
}
