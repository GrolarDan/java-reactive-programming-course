package org.masci.rp.sec03;

import org.masci.rp.courseutil.DmkUtil;
import org.masci.rp.sec03.helper.NameProducer;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactoring {

  public static void main(String[] args) {

    NameProducer nameProducer = new NameProducer();

    Flux.create(nameProducer)
        .subscribe(DmkUtil.subscriber());

    Runnable runnable = nameProducer::produce;

    for (int i = 0; i < 10; i++) {
      new Thread(runnable).start();
    }

    DmkUtil.sleepSeconds(2);
  }

}
