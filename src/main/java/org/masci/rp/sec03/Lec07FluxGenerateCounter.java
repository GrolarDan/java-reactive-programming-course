package org.masci.rp.sec03;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateCounter {
  public static void main(String[] args) {
    Flux.generate(() -> 1, (counter, sink) -> {
          var country = DmkUtil.faker()
              .country()
              .name();
          sink.next(country);
          if (counter >= 10 || country.equalsIgnoreCase("canada")) {
            sink.complete();
          }
          return counter + 1;
        })
        .take(4)
        .subscribe(DmkUtil.subscriber());
  }
}
