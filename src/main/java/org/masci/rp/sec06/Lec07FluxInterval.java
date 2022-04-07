package org.masci.rp.sec06;

import java.time.Duration;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec07FluxInterval {

  public static void main(String[] args) {

    Flux.interval(Duration.ofSeconds(1))
        .subscribe(DmkUtil.subscriber());

    DmkUtil.sleepSeconds(60);
  }
}
