package org.masci.rp.sec02;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import org.masci.rp.sec02.assignment.StockPricePublisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Lec10StockPriseAssignment {

  public static void main(String[] args) throws InterruptedException {

    CountDownLatch latch = new CountDownLatch(1);

    var subscriber = new Subscriber<Integer>() {
      private Subscription subscription;

      @Override
      public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
        this.subscription = subscription;
      }

      @Override
      public void onNext(Integer price) {
        System.out.println(LocalDateTime.now() + " : Price : " + price);
        if ( price < 90 || 110 < price ) {
          subscription.cancel();
          latch.countDown();
        }
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("onError: " + throwable.getMessage());
        latch.countDown();
      }

      @Override
      public void onComplete() {
        System.out.println("onComplete");
        latch.countDown();
      }
    };

    StockPricePublisher.getPrice().subscribeWith(subscriber);

    latch.await();
  }
}
