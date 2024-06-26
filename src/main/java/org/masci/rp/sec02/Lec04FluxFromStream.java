package org.masci.rp.sec02;

import org.masci.rp.courseutil.DmkUtil;
import java.util.List;
import reactor.core.publisher.Flux;

public class Lec04FluxFromStream {

  public static void main(String[] args) {
    var list = List.of(1, 2, 3, 4, 5);
//    var stream = list.stream();

//    stream.forEach(System.out::println);
//    stream.forEach(System.out::println);
//    var integerFlux = Flux.fromStream(stream);
    var integerFlux = Flux.fromStream(list::stream);

    integerFlux
        .subscribe(
            DmkUtil.onNext(),
            DmkUtil.onError(),
            DmkUtil.onComplete()
        );
    integerFlux
        .subscribe(
            DmkUtil.onNext(),
            DmkUtil.onError(),
            DmkUtil.onComplete()
        );
  }
}
