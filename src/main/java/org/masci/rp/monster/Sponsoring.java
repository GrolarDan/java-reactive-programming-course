package org.masci.rp.monster;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Sponsoring {

  public static void main(String[] args) {
    Mono<List<Integer>> result = Flux.range(0, 3)
//        .buffer(10)
//        .flatMap(Sponsoring::processBlock)
        .flatMap(Sponsoring::process)
        .collectList();

    var monoResult = result.block();
    System.out.println("Result: " + monoResult);
  }

  private static Flux<Integer> process(Integer data) {
    System.out.println("Processing: " + data);
    return Flux.just(data);
  }

  private static Flux<Integer> processBlock(List<Integer> data) {
    System.out.println("Processing: " + data);

    return Flux.fromIterable(data);
  }
}
