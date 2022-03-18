package org.masci.rp.sec03;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec05FluxGenerate {

  public static void main(String[] args) {

    Flux.generate(synchronousSink -> {
          System.out.println("emitting");
          synchronousSink.next(DmkUtil.faker()
              .country()
              .name());
        })
        .take(2)
        .subscribe(DmkUtil.subscriber());
  }
}
