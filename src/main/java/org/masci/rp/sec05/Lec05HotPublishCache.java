package org.masci.rp.sec05;

import java.time.Duration;
import java.util.stream.Stream;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec05HotPublishCache {

  public static void main(String[] args) {
    Flux<String> movieStream = Flux.fromStream(() -> getMovie())
        .delayElements(Duration.ofSeconds(1))
        .publish()
        .autoConnect(0);

    DmkUtil.sleepSeconds(3);

    movieStream
        .subscribe(DmkUtil.subscriber("sam"));

    DmkUtil.sleepSeconds(10);

    System.out.println("Mike is about to join");

    movieStream
        .subscribe(DmkUtil.subscriber("mike"));

    DmkUtil.sleepSeconds(60);
  }

  // movie-theater
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
