package org.masci.rp.sec11;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

public class Lec02SinkUnicast {

  public static void main(String[] args) {

    // handle through which we would push items
    Sinks.Many<Object> sink = Sinks.many()
        .unicast()
        .onBackpressureBuffer();

    // hande through which subscribers will receive items
    var flux = sink.asFlux();

    flux.subscribe(DmkUtil.subscriber("sam"));
    flux.subscribe(DmkUtil.subscriber("mike"));

    sink.tryEmitNext("Hi");
    sink.tryEmitNext("how are you");
    sink.tryEmitNext("?");
  }
}
