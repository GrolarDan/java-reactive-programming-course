package org.masci.rp.sec05;

import java.time.Duration;
import java.util.stream.Stream;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec02HotShare {

  public static void main(String[] args) {
    Flux<String> movieStream = Flux.fromStream(Lec02HotShare::getMovie)
        .delayElements(Duration.ofSeconds(2))
        .share();

    movieStream
        .subscribe(DmkUtil.subscriber("sam"));

    DmkUtil.sleepSeconds(5);

    movieStream
        .subscribe(DmkUtil.subscriber("mike"));

    DmkUtil.sleepSeconds(60);
  }

  private static Stream<String> getMovie() {
    System.out.println("Got the movie streaming req.");
    return Stream.of(
        "Scene 1",
        "Scene 2",
        "Scene 3",
        "Scene 4",
        "Scene 5",
        "Scene 6",
        "Scene 7",
        "Scene 8"
    );
  }
}
