package org.masci.rp.sec08;

import java.time.Duration;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec05CombineLatest {

  public static void main(String[] args) {

    Flux.combineLatest(getString(), getNumber(), (s, i) -> String.format("%s%d", s, i))
        .subscribe(DmkUtil.subscriber());

    DmkUtil.sleepSeconds(10);
  }

  private static Flux<String> getString() {
    return Flux.just("A", "B", "C", "D")
        .delayElements(Duration.ofSeconds(1));
  }

  private static Flux<Integer> getNumber() {
    return Flux.just(1, 2, 3)
        .delayElements(Duration.ofSeconds(3));
  }
}
