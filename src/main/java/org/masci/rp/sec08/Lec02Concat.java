package org.masci.rp.sec08;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec02Concat {

  public static void main(String[] args) {

    Flux<String> flux1 = Flux.just("a", "b");
    Flux<String> flux2 = Flux.just("c", "d", "e");

    Flux<String> flux = flux1.concatWith(flux2);

    flux.subscribe(DmkUtil.subscriber());
  }
}
