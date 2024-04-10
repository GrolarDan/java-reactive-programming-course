package org.masci.rp.monster.then;

import reactor.core.publisher.Mono;

public class ValidateThen {

  public static void main(String[] args) {
    Mono.empty()
//    Mono.just("Nazdar")
//        .switchIfEmpty(Mono.error(Exception::new))
//        .onErrorContinue((err, obj) -> {
//          System.out.printf("err: %s%n", err.getMessage());
//          System.out.printf("obj: %s%n", obj);
//        })
        .then(Mono.just("Hello"))
        .doOnNext(System.out::println)
        .block();
  }
}
