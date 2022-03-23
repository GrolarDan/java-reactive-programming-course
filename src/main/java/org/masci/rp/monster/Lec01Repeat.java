package org.masci.rp.monster;

import com.rp.courseutil.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec01Repeat {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

//        getIntegers()
      getMonoIntegers().flux()
                .repeat(() -> atomicInteger.get() < 14)
                .subscribe(DmkUtil.subscriber());


    }


    private static Flux<Integer> getIntegers(){
        return Flux.range(1, 3)
                    .doOnSubscribe(s -> System.out.println("Subscribed"))
                    .doOnComplete(() -> System.out.println("--Completed"))
                    .map(i -> atomicInteger.getAndIncrement());
    }

    private static Mono<List<Integer>> getMonoIntegers() {
      return Mono.fromSupplier(() -> createData())
          .doOnSubscribe(s -> System.out.println("Subscribed"));
    }

    private static List<Integer> createData() {
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < 3; i++) {
        list.add(atomicInteger.getAndIncrement());
      }
      return list;
    }
}
