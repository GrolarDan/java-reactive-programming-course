package org.masci.rp.sec04;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec02HandleAssignment {

  public static void main(String[] args) {
    Flux.generate(synchronousSink -> synchronousSink.next(DmkUtil.faker().country().name()))
        .map(Object::toString)
        .handle((s, synchronousSink) -> {
          synchronousSink.next(s);
          if (s.equalsIgnoreCase("canada")) {
            synchronousSink.complete();
          }
        })
        .subscribe(DmkUtil.subscriber());
  }
}
