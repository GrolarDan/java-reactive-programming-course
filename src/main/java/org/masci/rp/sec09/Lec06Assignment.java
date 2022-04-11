package org.masci.rp.sec09;

import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import org.masci.rp.courseutil.DmkUtil;
import org.masci.rp.sec09.assignment.OrderProcessor;
import org.masci.rp.sec09.assignment.OrderService;
import org.masci.rp.sec09.assignment.Product;
import reactor.core.publisher.Flux;

public class Lec06Assignment {

  public static void main(String[] args) {

    Map<String, Function<Flux<Product>, Flux<Product>>> map = Map.of(
        "Kids", OrderProcessor.kidsProcessing(),
        "Automotive", OrderProcessor.automotiveProcessing()
    );

    Set<String> allowedDepartments = map.keySet();

    OrderService.productStream()
        .filter(p -> allowedDepartments.contains(p.getDepartment()))
        .groupBy(Product::getDepartment)
        .flatMap(gf -> map.get(gf.key()).apply(gf))
        .subscribe(DmkUtil.subscriber());

    DmkUtil.sleepSeconds(60);
  }
}
