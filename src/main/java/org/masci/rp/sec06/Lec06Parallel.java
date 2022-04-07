package org.masci.rp.sec06;

import java.util.ArrayList;
import java.util.List;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec06Parallel {
  public static void main(String[] args) {

    Flux.range(1, 10)
        .parallel(3)
        .runOn(Schedulers.boundedElastic())
        .doOnNext(i -> printThreadName("next " + i))
        .subscribe(v -> printThreadName("sub  " + v));

    DmkUtil.sleepSeconds(5);
  }

  private static void printThreadName(String msg) {
    System.out.println(msg + "\t\t: Thread : " + Thread.currentThread()
        .getName());
  }

}
