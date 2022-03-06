package org.masci.rp.sec02;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec02FluxIntro {
  public static void main(String[] args) {

    var flux = Flux.just(1, 2, 3, 4, "a", DmkUtil.faker().name().fullName());

    flux.subscribe(
        DmkUtil.onNext(),
        DmkUtil.onError(),
        DmkUtil.onComplete());
  }
}
