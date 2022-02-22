package org.masci.rp.sec02;

import org.masci.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec02FluxIntro {
  public static void main(String[] args) {

    var flux = Flux.just(1, 2, 3, 4, "a", Util.faker().name().fullName());

    flux.subscribe(
        Util.onNext(),
        Util.onError(),
        Util.onComplete());
  }
}
