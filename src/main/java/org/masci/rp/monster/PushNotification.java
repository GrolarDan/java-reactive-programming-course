package org.masci.rp.monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PushNotification {

  public static void main(String[] args) {
    getAllNativeAppInstances()
        .subscribe(DmkUtil.subscriber());
  }

  private static Flux<NativeAppInstance> getAllNativeAppInstances() {
    AtomicReference<String> continuationTokenHolder = new AtomicReference<>(null);

    var nativeAppInstanceResponse = getNativeAppInstanceResponse(continuationTokenHolder.get());
    return nativeAppInstanceResponse.doOnNext(response -> {
          System.out.println("new continuation token: " + response.getContinuationToken());
          continuationTokenHolder.set(response.getContinuationToken());
        })
        .map(NativeAppInstanceResponse::getContent)
        .flatMapIterable(Function.identity())
        .repeat(() -> Objects.nonNull(continuationTokenHolder.get()));
  }

  private static Mono<NativeAppInstanceResponse> getNativeAppInstanceResponse(String continuationToken) {
    System.out.println("Continuation token : " + continuationToken);

    return Mono.fromSupplier(() -> new NativeAppInstanceResponse(generateNativeAppInstanceList(DmkUtil.faker().random().nextInt(5, 10)), nextContinuationToken()))
        .doOnSubscribe(s -> System.out.println("-- Subscribe"));
  }

  private static List<NativeAppInstance> generateNativeAppInstanceList(int count) {
    List<NativeAppInstance> result = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      result.add(new NativeAppInstance(DmkUtil.faker()
          .internet()
          .uuid()));
    }
    return result;
  }

  private static String nextContinuationToken() {
    var next = DmkUtil.faker()
        .random()
        .nextInt(1, 100);
    System.out.println("-- Random number: " + next);
    if (next >= 80) {
      System.out.println("-- This was last page");
    }
    return next < 80 ? DmkUtil.faker().book().title() : null;
  }
}
