package org.masci.rp.sec11;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec04SinkMulti {

  public static void main(String[] args) {
    // handle through which we would push items
    Sinks.Many<Object> sink = Sinks.many()
        .multicast()
        .onBackpressureBuffer();

    // hande through which subscribers will receive items
    Flux<Object> flux = sink.asFlux();


    sink.tryEmitNext("Hi");
    sink.tryEmitNext("how are you");

    flux.subscribe(DmkUtil.subscriber("sam"));
    flux.subscribe(DmkUtil.subscriber("mike"));

    sink.tryEmitNext("?");

    flux.subscribe(DmkUtil.subscriber("jake"));

    sink.tryEmitNext("new msg");
  }
}
