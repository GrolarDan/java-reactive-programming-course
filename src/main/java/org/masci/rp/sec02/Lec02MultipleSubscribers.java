package org.masci.rp.sec02;

import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {
  public static void main(String[] args) {
    var integerFlux = Flux.just(1, 2, 3, 4);
    var eventFlux = integerFlux.filter(i -> i % 2 == 0);

    integerFlux.subscribe(i -> System.out.println("Sub 1 : " + i));

    eventFlux.subscribe(i -> System.out.println("Sub 2 : " + i));
  }
}
