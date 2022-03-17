package org.masci.rp.sec03;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

  public static void main(String[] args) {

    Flux.create(fluxSink -> {

      String country;

      do {
        country = DmkUtil.faker().country().name();
        fluxSink.next(country);
      } while (!country.equalsIgnoreCase("canada"));
      // for cycle
      //      for (int i = 0; i < 10; i++) {
      //        fluxSink.next(DmkUtil.faker().country().name());
      //      }
      // add object one by one
      //      fluxSink.next(1);
      //      fluxSink.next(2);

      fluxSink.complete();
    }).subscribe(DmkUtil.subscriber());
  }
}
