package org.masci.rp.sec08.helper;

import java.util.ArrayList;
import java.util.List;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class NameGenerator {

  private List<String> cache = new ArrayList<>();

  public Flux<String> generateNames() {
    return Flux.generate(stringSynchronousSink -> {
      System.out.println("Generate fresh");
      DmkUtil.sleepSeconds(1);
      var name = DmkUtil.faker().name().firstName();
      cache.add(name);
      stringSynchronousSink.next(name);
    })
        .cast(String.class)
        .startWith(getFromCache());
  }

  private Flux<String> getFromCache() {
    return Flux.fromIterable(cache);
  }
}
