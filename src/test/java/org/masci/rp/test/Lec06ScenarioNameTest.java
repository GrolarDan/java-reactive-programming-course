package org.masci.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec06ScenarioNameTest {


  @Test
  public void test1() {
    var flux = Flux.just("a", "b", "c");

    var scenarioName = StepVerifierOptions.create().scenarioName("alphabets-test");

    StepVerifier.create(flux, scenarioName)
        .expectNextCount(12)
        .verifyComplete();
  }

  @Test
  public void test2() {
    var flux = Flux.just("a", "b1", "c");

    StepVerifier.create(flux)
        .expectNext("a")
        .as("a-test")
        .expectNext("b")
        .as("b-test")
        .expectNext("c")
        .as("c-test")
        .verifyComplete();
  }


}
