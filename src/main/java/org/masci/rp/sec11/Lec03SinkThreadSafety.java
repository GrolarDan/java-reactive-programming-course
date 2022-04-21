package org.masci.rp.sec11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec03SinkThreadSafety {

  public static void main(String[] args) {

    // handle through which we would push items
    Sinks.Many<Object> sink = Sinks.many()
        .unicast()
        .onBackpressureBuffer();

    // hande through which subscribers will receive items
    Flux<Object> flux = sink.asFlux();
    List<Object> list = new ArrayList<>();

    flux.subscribe(list::add);

//    for (int i = 0; i < 1000; i++) {
//      final int j = i;
//      CompletableFuture.runAsync(() -> {
//        sink.tryEmitNext(j);
//      });
//    }

    for (int i = 0; i < 1000; i++) {
      final int j = i;
      CompletableFuture.runAsync(() -> {
        sink.emitNext(j, (s, e) -> true);
      });
    }

    DmkUtil.sleepSeconds(3);
    System.out.println(list.size());
  }
}
