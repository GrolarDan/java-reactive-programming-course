package org.masci.rp.sec03;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec03FluxTake {

  public static void main(String[] args) {

    Flux.range(1, 10)
        .log()
        .take(3)
        .log()
        .subscribe(DmkUtil.subscriber());
  }
}
