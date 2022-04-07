package org.masci.rp.sec06;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03SubscribeOnMultipleItems {
  public static void main(String[] args) {

    var flux = Flux.create(fluxSink -> {
          printThreadName("create");
          for (int i = 0; i < 4; i++) {
            fluxSink.next(i);
            DmkUtil.sleepSeconds(1);
          }
          fluxSink.complete();
        })
        .doOnNext(i -> printThreadName("next " + i));

    flux
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(v -> printThreadName("sub  " + v));

    flux
        .subscribeOn(Schedulers.parallel())
        .subscribe(v -> printThreadName("sub  " + v));

    DmkUtil.sleepSeconds(5);
  }

  private static void printThreadName(String msg) {
    System.out.println(msg + "\t\t: Thread : " + Thread.currentThread()
        .getName());
  }
}
