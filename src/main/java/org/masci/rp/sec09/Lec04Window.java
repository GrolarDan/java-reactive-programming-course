package org.masci.rp.sec09;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec04Window {

  private final static AtomicInteger atomicInteger = new AtomicInteger();

  public static void main(String[] args) {

    eventStream()
        .window(Duration.ofSeconds(2))
        .flatMap(flux -> saveEvents(flux))
        .subscribe(DmkUtil.subscriber());

    DmkUtil.sleepSeconds(10);
  }

  private static Flux<String> eventStream() {
    return Flux.interval(Duration.ofMillis(500))
        .map(i -> "event-" + i);
  }

  private static Mono<Integer> saveEvents(Flux<String> flux) {
    return flux.doOnNext(e -> System.out.println("saving " + e))
        .doOnComplete(() -> {
          System.out.println("saved this batch");
          System.out.println("----------------");
        })
        .then(Mono.just(atomicInteger.getAndIncrement()));
  }
}
