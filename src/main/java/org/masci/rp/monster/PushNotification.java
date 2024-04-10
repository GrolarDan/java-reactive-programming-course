package org.masci.rp.monster;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class PushNotification {

  private static final Duration DATABASE_SCAN_DELAY = Duration.ofMillis(25);
  private static final Duration PROFILE_SERVICE_DELAY = Duration.ofMillis(20);
  private static final Duration AWS_SQS_SEND_DELAY = Duration.ofMillis(200);

  private static final int MIN_GENERATED_RECORDS = 5;
  private static final int MAX_GENERATED_RECORDS = 10;
  private static final int MAX_RECORDS = 30;  // when set to 0, continuation token will be random based

  private static final AtomicInteger count = new AtomicInteger(0);

  public static void main(String[] args) {
    //    AtomicInteger count = new AtomicInteger(0);
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    getAllNativeAppInstances().publishOn(Schedulers.boundedElastic())
        .bufferTimeout(10, Duration.ofSeconds(2))
        .doOnNext(PushNotification::process)
        //        .flatMap(PushNotification::process)
        //        .subscribe();
        .blockLast();

    stopWatch.stop();

    log(String.format("TOTAL %d notifications sent in %s", count.get(), stopWatch.formatTime()));

    //    DmkUtil.sleepSeconds(60);
  }

  private static Flux<String> getAllNativeAppInstances() {
    AtomicReference<String> continuationTokenHolder = new AtomicReference<>(null);

    var nativeAppInstanceResponse = getNativeAppInstanceResponse(continuationTokenHolder.get());
    return nativeAppInstanceResponse.doOnNext(response -> continuationTokenHolder.set(response.getContinuationToken()))
        .map(NativeAppInstanceResponse::getContent)
        .flatMapIterable(Function.identity())
        .map(NativeAppInstance::getProfileId)
        .repeat(() -> StringUtils.isNotEmpty(continuationTokenHolder.get()))
        .log()
        .flatMap(PushNotification::filterByOptin, 32);
  }

  private static Mono<NativeAppInstanceResponse> getNativeAppInstanceResponse(String continuationToken) {
    log("Continuation token : " + continuationToken);

    return Mono.fromSupplier(() -> {
          String name = DmkUtil.faker()
              .name()
              .firstName();
          log("Calling database for: " + name);
          return new NativeAppInstanceResponse(
              generateNativeAppInstanceList(name, DmkUtil.faker().random().nextInt(MIN_GENERATED_RECORDS, MAX_GENERATED_RECORDS)),
              nextContinuationToken()
          );
        })
        .delayElement(DATABASE_SCAN_DELAY);
  }

  private static List<NativeAppInstance> generateNativeAppInstanceList(String name, int count) {
    List<NativeAppInstance> result = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      result.add(new NativeAppInstance(name, DmkUtil.faker()
          .internet()
          .uuid()));
    }
    return result;
  }

  private static String nextContinuationToken() {
    boolean hasNextToken;

    if (MAX_RECORDS > 0) {
      hasNextToken = count.get() < MAX_RECORDS - MAX_GENERATED_RECORDS;
    } else {
      var next = DmkUtil.faker()
          .random()
          .nextInt(1, 100);
      hasNextToken = next < 80;
    }

    if (!hasNextToken) {
      log("This was last page");
    }

    log("Record counts: " + count.get());

    return hasNextToken ? DmkUtil.faker()
        .book()
        .title() : null;
  }

  private static Mono<String> filterByOptin(String profileId) {
    return Mono.just(profileId)
//        .doOnNext(id -> log("Processing profile id: " + id))
        .delayElement(PROFILE_SERVICE_DELAY);
  }

  private static Mono<List<String>> process(List<String> list) {
//    log("Processing buffer: " + list.toString());
    count.getAndAdd(list.size());
    return Mono.just(list)
        .delayElement(AWS_SQS_SEND_DELAY);
  }

  private static void log(String msg) {
    System.out.println(Thread.currentThread()
        .getName() + " \t\t - " + LocalDateTime.now() + " : " + msg);
  }
}
