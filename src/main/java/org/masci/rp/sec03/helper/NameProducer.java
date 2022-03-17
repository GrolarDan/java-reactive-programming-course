package org.masci.rp.sec03.helper;

import java.util.function.Consumer;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.FluxSink;

public class NameProducer implements Consumer<FluxSink<String>> {

  private FluxSink<String> fluxSink;

  @Override
  public void accept(FluxSink<String> stringFluxSink) {
    this.fluxSink = stringFluxSink;
  }

  public void produce() {
    String name = DmkUtil.faker().name().fullName();
    String thread = Thread.currentThread().getName();
    fluxSink.next(thread + " : " + name);
  }
}
