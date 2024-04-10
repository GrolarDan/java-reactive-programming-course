package org.masci.rp.monster;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class LocationTaxonomy {

  public static void main(String[] args) {
    Flux.fromIterable(
        List.of(
            new Location("city 1", "region 1", null, null),
            new Location("city 2", null, null, null),
            new Location(null, "region 3", null, null)
        ))
        .log()
        .publishOn(Schedulers.boundedElastic())
        .flatMap(location ->
            Flux
                .zip(getLocation(location.getCity()).log(), getLocation(location.getRegion()).log())
                .map(t -> {
                  location.setNewCity(t.getT1().orElse(null));
                  location.setNewRegion(t.getT2().orElse(null));
                  return location;
                }),
            32
        )
        .subscribe(DmkUtil.subscriber());

    DmkUtil.sleepSeconds(60);
  }

  private static Mono<Optional<String>> getLocation(String old) {
    var millis = DmkUtil.faker().random().nextInt(2000, 5000);
    System.out.println("will wait for " + (StringUtils.isBlank(old) ? 0 : millis));
    return StringUtils.isBlank(old) ? Mono.just(Optional.empty()) : Mono.just(Optional.of(old + " - " + DmkUtil.faker().random().nextInt(20))).delayElement(Duration.ofMillis(millis));
  }

  @Data
  @AllArgsConstructor
  private static class Location {
    String city;
    String region;
    String newCity;
    String newRegion;
  }
}
