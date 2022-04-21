package org.masci.rp.sec11;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {

  public static void main(String[] args) {

    // mono 1 value / empty / error
    var sink = Sinks.one();

    var mono = sink.asMono();

    mono.subscribe(DmkUtil.subscriber("sam"));
    mono.subscribe(DmkUtil.subscriber("mike"));

//    sink.emitValue("hi", (signalType, emitResult) -> {
//      System.out.println(signalType.name());
//      System.out.println(emitResult.name());
//      return false;
//    });
//
//    sink.emitValue("hello", (signalType, emitResult) -> {
//      System.out.println(signalType.name());
//      System.out.println(emitResult.name());
//      return false;
//    });

    sink.tryEmitValue("Hello");
  }
}
