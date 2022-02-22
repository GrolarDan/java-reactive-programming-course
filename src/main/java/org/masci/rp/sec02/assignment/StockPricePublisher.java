package org.masci.rp.sec02.assignment;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import org.masci.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class StockPricePublisher {

  public static Flux<Integer> getPrice() {
    AtomicInteger atomicInteger = new AtomicInteger(100);
    return Flux.interval(Duration.ofSeconds(1))
        .map(i -> atomicInteger.getAndAccumulate(
            Util.faker().random().nextInt(-5, +5),
            Integer::sum
        ));
  }

}
