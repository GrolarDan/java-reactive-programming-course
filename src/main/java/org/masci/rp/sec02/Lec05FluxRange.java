package org.masci.rp.sec02;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {

  public static void main(String[] args) {

    Flux.range(3, 10)
        .map(i -> DmkUtil.faker().name().fullName())
        .subscribe(DmkUtil.onNext());
  }
}
