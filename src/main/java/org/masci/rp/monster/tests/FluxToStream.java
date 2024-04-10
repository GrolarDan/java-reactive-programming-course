package org.masci.rp.monster.tests;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class FluxToStream {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    List<Integer> list = new CopyOnWriteArrayList<>();
    executorService
        .submit(() -> list.addAll(getList()))
        .get();

    list.forEach(c -> DmkUtil.log("forEach: " + c));
  }

  private static List<Integer> getList() {
    return Flux.fromStream(IntStream.range(0, 20).boxed())
        .doOnNext(c -> DmkUtil.log("flux: " + c))
        .doOnNext(c -> {
          try {
            Thread.sleep(200);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        })
        .collectList()
        .block();
  }
}
