package org.masci.rp.monster;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

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
        .flatMapIterable(Function.identity());
  }

  private static Mono<NativeAppInstanceResponse> getNativeAppInstanceResponse(String continuationToken) {
    System.out.println("Continuation token : " + continuationToken);

    return Mono.just(new NativeAppInstanceResponse(generateNativeAppInstanceList(5), nextContinuationToken()));
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
    return next < 90 ? DmkUtil.faker().book().title() : null;
  }
}
