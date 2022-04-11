package org.masci.rp.sec08.helper;

import com.rp.courseutil.Util;
import java.time.Duration;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Qatar {

  public static Flux<String> getFlights() {
    return Flux.range(1, Util.faker().random().nextInt(1, 5))
        .delayElements(Duration.ofSeconds(1))
        .map(i -> "Qatar " + Util.faker().random().nextInt(100, 999))
        .filter(i -> DmkUtil.faker().random().nextBoolean());
  }
}
