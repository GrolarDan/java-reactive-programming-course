package org.masci.rp.monster.then;

import reactor.core.publisher.Mono;

public class ValidateThen {

  public static void main(String[] args) {
    System.out.println("Direct call Mono.just(...) in .then()");
    Mono.empty()
        //    Mono.just("Nazdar")
        //        .switchIfEmpty(Mono.error(Exception::new))
        //        .onErrorContinue((err, obj) -> {
        //          System.out.printf("err: %s%n", err.getMessage());
        //          System.out.printf("obj: %s%n", obj);
        //        })
        .then(Mono.just("Hello First")).doOnNext(System.out::println).block();

    System.out.println("Call in Mono.defer() in .then()");
    Mono.empty().then(Mono.defer(() -> Mono.just("Hello Second"))).doOnNext(System.out::println).block();
//
//    System.out.println("Direct call Mono.just(...) in .then() after error");
//    Mono.error(new Exception("Test")).then(Mono.just("Hello third")).doOnNext(System.out::println).block();

    System.out.println("Call in Mono.defer() in .then() after error");
    Mono.error(new Exception("Test")).log().then(Mono.defer(() -> Mono.just("Hello Fourth"))).doOnNext(System.out::println).block();
  }
}
