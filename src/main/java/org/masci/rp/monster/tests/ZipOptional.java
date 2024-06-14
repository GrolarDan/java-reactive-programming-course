package org.masci.rp.monster.tests;

import java.util.Optional;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Mono;

public class ZipOptional {
  public static void main(String[] args) {
    Mono<Integer> first = Mono.just(1);
    //    Mono<String> second = Mono.just("Hello");
    Mono<String> second = Mono.empty();

    greeting(first.singleOptional(), second.singleOptional()).subscribe(DmkUtil.subscriber());
  }

  private static Mono<String> greeting(Mono<Optional<Integer>> positionMono, Mono<Optional<String>> greetingMono) {
    return Mono.zip(positionMono, greetingMono,
        (positionOptional, greetingOptional) -> {
          Integer position = positionOptional.orElse(0);
          String result = greetingOptional.orElse("Good morning");
          return String.format("%d. %s!", position, result);
        });
  }
}
