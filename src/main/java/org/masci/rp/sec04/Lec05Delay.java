package org.masci.rp.sec04;

import java.time.Duration;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec05Delay {

  public static void main(String[] args) {

    System.setProperty("reactor.bufferSize.x", "9");

    Flux.range(1, 100)
        .log()
        .delayElements(Duration.ofSeconds(1))
        .subscribe(DmkUtil.subscriber());

    DmkUtil.sleepSeconds(60);
  }
}
