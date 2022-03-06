package org.masci.rp.sec02;

import java.util.concurrent.atomic.AtomicReference;
import org.masci.rp.courseutil.DmkUtil;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class Lec06Subscription {
  public static void main(String[] args) {
    AtomicReference<Subscription> atomicReference = new AtomicReference<>();
    Flux.range(1, 20)
        .log()
        .subscribeWith(new Subscriber<Integer>() {
          @Override
          public void onSubscribe(Subscription subscription) {
            System.out.println("Received Sub: " + subscription);
            atomicReference.set(subscription);
          }

          @Override
          public void onNext(Integer integer) {
            System.out.println("onNext: " + integer);
          }

          @Override
          public void onError(Throwable throwable) {
            System.out.println("onError: " + throwable.getMessage());
          }

          @Override
          public void onComplete() {
            System.out.println("onComplete");
          }
        });

    DmkUtil.sleepSeconds(3);
    atomicReference.get().request(3);
    DmkUtil.sleepSeconds(5);
    atomicReference.get().request(3);
    DmkUtil.sleepSeconds(5);
    System.out.println("going to cancel");
    atomicReference.get().cancel();
    DmkUtil.sleepSeconds(3);
    atomicReference.get().request(4);

    DmkUtil.sleepSeconds(3);
  }
}
