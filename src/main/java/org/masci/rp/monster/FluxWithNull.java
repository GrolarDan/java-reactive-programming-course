package org.masci.rp.monster;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class FluxWithNull {

  public static void main(String[] args) {
    var flux = Flux.range(1, 5)
        .map(i -> i == 3 ? null : i);
  }
}
