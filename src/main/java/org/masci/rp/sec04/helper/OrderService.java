package org.masci.rp.sec04.helper;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class OrderService {

  private static Map<Integer, List<PurchaseOrder>> db =
      Map.of(
          1, List.of(new PurchaseOrder(1), new PurchaseOrder(1), new PurchaseOrder(1)),
          2, List.of(new PurchaseOrder(2), new PurchaseOrder(2))
      );

  public static Flux<PurchaseOrder> getOrders(int userId) {
    return Flux.create((FluxSink<PurchaseOrder> purchaseOrderFluxSink) -> {
      db.get(userId).forEach(purchaseOrderFluxSink::next);
      purchaseOrderFluxSink.complete();
    })
        .delayElements(Duration.ofSeconds(1));
  }
}
