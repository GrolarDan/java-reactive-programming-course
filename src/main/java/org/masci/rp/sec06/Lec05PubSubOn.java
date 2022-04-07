package org.masci.rp.sec06;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05PubSubOn {
  public static void main(String[] args) {

    var flux = Flux.create(fluxSink -> {
          printThreadName("create");
          for (int i = 0; i < 4; i++) {
            fluxSink.next(i);
          }
          fluxSink.complete();
        })
        .doOnNext(i -> printThreadName("next " + i));

    flux
        .publishOn(Schedulers.parallel())
        .doOnNext(i -> printThreadName("next " + i))
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(v -> printThreadName("sub  " + v));

    DmkUtil.sleepSeconds(5);
  }

  private static void printThreadName(String msg) {
    System.out.println(msg + "\t\t: Thread : " + Thread.currentThread()
        .getName());
  }

}
