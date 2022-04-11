package org.masci.rp.sec09.assignment;

import java.time.Duration;
import reactor.core.publisher.Flux;

public class OrderService {

  public static Flux<Product> productStream() {
    return Flux.interval(Duration.ofMillis(100))
        .map(i -> new Product());
  }

}
