package org.masci.rp.sec02;

import java.time.Duration;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec08FluxInterval {
  public static void main(String[] args) {
    Flux.interval(Duration.ofSeconds(1))
        .map(i -> DmkUtil.faker().name().fullName())
        .subscribe(DmkUtil.onNext());

    DmkUtil.sleepSeconds(5);
  }
}
