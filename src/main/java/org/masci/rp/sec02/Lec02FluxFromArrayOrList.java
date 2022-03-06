package org.masci.rp.sec02;

import org.masci.rp.courseutil.DmkUtil;
import java.util.Arrays;
import java.util.List;
import reactor.core.publisher.Flux;

public class Lec02FluxFromArrayOrList {
  public static void main(String[] args) {
    List<String> strings = Arrays.asList("a", "b", "c");
    Integer[] arr = { 1, 4, 56, 3, 89};

    Flux.fromIterable(strings)
        .subscribe(DmkUtil.onNext());

    Flux.fromArray(arr)
        .subscribe(DmkUtil.onNext());
  }
}
