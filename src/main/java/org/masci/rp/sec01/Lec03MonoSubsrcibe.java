package org.masci.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoSubsrcibe {
  public static void main(String[] args) {
    // Publisher
    var mono = Mono.just("ball")
        .map(String::length)
        .map(l -> l / 1);

    // 1
    // mono.subscribe();

    // 2
    mono.subscribe(
        Util.onNext(),
        Util.onError(),
        Util.onComplete()
    );
  }
}
