package org.masci.rp.sec03;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerateAssignment {

  public static void main(String[] args) {

    Flux.generate(synchronousSink -> {
          var country = DmkUtil.faker().country().name();
          System.out.println("emitting " + country);
          synchronousSink.next(country);
          if (country.equalsIgnoreCase("canada")) {
            synchronousSink.complete();
          }
        })
        .subscribe(DmkUtil.subscriber());
  }
}
