package org.masci.rp.sec04;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec04LimitRate {

  public static void main(String[] args) {

    Flux.range(0, 1000)
        .log()
        .limitRate(100, 95)
        .subscribe(DmkUtil.subscriber());
  }
}
