package org.masci.rp.monster;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class PushNotification {

  public static void main(String[] args) {
    getAllNativeAppInstances()
//        .parallel(8)
        .publishOn(Schedulers.boundedElastic())
        .bufferTimeout(5, Duration.ofSeconds(2))
//        .doOnNext(list -> log(list.toString()))
        .flatMap(PushNotification::process)
        .subscribe();

    DmkUtil.sleepSeconds(60);
  }

  private static Flux<NativeAppInstance> getAllNativeAppInstances() {
    AtomicReference<String> continuationTokenHolder = new AtomicReference<>(null);

    var nativeAppInstanceResponse = getNativeAppInstanceResponse(continuationTokenHolder.get());
    return nativeAppInstanceResponse.doOnNext(response -> {
//          System.out.println("new continuation token: " + response.getContinuationToken());
          continuationTokenHolder.set(response.getContinuationToken());
        })
        .repeat(() -> Objects.nonNull(continuationTokenHolder.get()))
        .map(NativeAppInstanceResponse::getContent)
        .flatMapIterable(Function.identity());
  }

  private static Mono<NativeAppInstanceResponse> getNativeAppInstanceResponse(String continuationToken) {
    log("Continuation token : " + continuationToken);

    return Mono.fromSupplier(() -> {
          String name = DmkUtil.faker().name().firstName();
          log("Calling database for: " + name);
          return new NativeAppInstanceResponse(generateNativeAppInstanceList(name, DmkUtil.faker().random().nextInt(5, 10)), nextContinuationToken());
        })
        .delayElement(Duration.ofSeconds(3));
  }

  private static List<NativeAppInstance> generateNativeAppInstanceList(String name, int count) {
    List<NativeAppInstance> result = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      result.add(new NativeAppInstance(name, DmkUtil.faker().internet().uuid()));
    }
    return result;
  }

  private static String nextContinuationToken() {
    var next = DmkUtil.faker()
        .random()
        .nextInt(1, 100);
//    System.out.println("-- Random number: " + next);
    if (next >= 80) {
      log("This was last page");
    }
    return next < 80 ? DmkUtil.faker().book().title() : null;
  }

  private static void log(String msg) {
    System.out.println(Thread.currentThread().getName() + " \t\t - " + LocalDateTime.now() + " : " + msg);
  }

  private static Mono<NativeAppInstance> process(NativeAppInstance nativeAppInstance) {
    log(nativeAppInstance.toString());
    DmkUtil.sleepSeconds(2);
    return Mono.just(nativeAppInstance);
  }

  private static Mono<List<NativeAppInstance>> process(List<NativeAppInstance> nativeAppInstance) {
    log("Processing buffer");
    for (NativeAppInstance instance : nativeAppInstance) {
      log(instance.toString());
    }
    log("Simulating process finish");
    DmkUtil.sleepSeconds(2);
    return Mono.just(nativeAppInstance);
  }
}
