package org.masci.rp.sec03;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateIssueFix {

  public static void main(String[] args) {
    Flux.create(fluxSink -> {
          String country;
          do {
            country = DmkUtil.faker()
                .country()
                .name();
            System.out.println("emmitting : " + country);
            fluxSink.next(country);
          } while (!country.equalsIgnoreCase("canada") && !fluxSink.isCancelled());
          fluxSink.complete();
        })
        .take(3)
        .subscribe(DmkUtil.subscriber());
  }
}
