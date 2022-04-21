package org.masci.rp.sec11;

import java.time.Duration;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec05SinkMultiDirectAll {


  public static void main(String[] args) {

    System.setProperty("reactor.bufferSize.small", "16");

    // handle through which we would push items
    Sinks.Many<Object> sink = Sinks.many()
        .multicast()
        .directBestEffort();

    // hande through which subscribers will receive items
    Flux<Object> flux = sink.asFlux();

    flux.subscribe(DmkUtil.subscriber("sam"));
    flux.delayElements(Duration.ofMillis(200)).subscribe(DmkUtil.subscriber("mike"));

    for (int i = 0; i < 100; i++) {
      sink.tryEmitNext(i);

    }

    DmkUtil.sleepSeconds(10);
  }

}
