package org.masci.rp.sec04;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec01Handle {
  public static void main(String[] args) {
    Flux.range(0, Integer.MAX_VALUE)
        .handle((i, synchronousSink) -> {
          String country = DmkUtil.faker().country().name();
          synchronousSink.next(country);
          if (country.equalsIgnoreCase("canada")) {
            synchronousSink.complete();
          }
        })
        .subscribe(DmkUtil.subscriber());
  }
}
