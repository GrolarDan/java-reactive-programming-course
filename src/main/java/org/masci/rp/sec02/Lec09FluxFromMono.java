package org.masci.rp.sec02;

import org.masci.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec09FluxFromMono {
  public static void main(String[] args) {
    // Mono to Flux
    Mono<String> mono = Mono.just("a");
    Flux<String> flux = Flux.from(mono);
    flux.subscribe(Util.onNext());

    // Flux to Mono
    Flux.range(1, 10)
        .filter(i -> i > 3)
        .next()
        .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
  }
}
