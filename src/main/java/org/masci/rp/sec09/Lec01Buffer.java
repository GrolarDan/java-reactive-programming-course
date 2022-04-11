package org.masci.rp.sec09;

import java.time.Duration;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec01Buffer {

  public static void main(String[] args) {

    eventStream()
        .buffer(Duration.ofSeconds(2))
        .subscribe(DmkUtil.subscriber());

    DmkUtil.sleepSeconds(10);
  }

  private static Flux<String> eventStream() {
    return Flux.interval(Duration.ofMillis(300))
        .map(i -> "event-" + i);
  }
}
