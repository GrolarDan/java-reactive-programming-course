package org.masci.rp.sec09.assignment;

import java.util.function.Function;
import reactor.core.publisher.Flux;

public class OrderProcessor {

  public static Function<Flux<Product>, Flux<Product>> automotiveProcessing() {
    return flux -> flux
        .doOnNext(p -> System.out.println("Automotive: " + p.getName() + " - old price: " + p.getPrice()))
        .doOnNext(p -> p.setPrice(p.getPrice() * 1.1))
        .doOnNext(p -> p.setName("{{ " + p.getName() + " }}"));
  }

  public static Function<Flux<Product>, Flux<Product>> kidsProcessing() {
    return flux -> flux
        .doOnNext(p -> System.out.println("Kids: " + p.getName() + " - old price: " + p.getPrice()))
        .doOnNext(p -> p.setPrice(p.getPrice() * 0.5d))
        .flatMap(p -> Flux.just(p, getFreeKidsProduct()));
  }

  private static Product getFreeKidsProduct() {
    Product product = new Product();
    product.setName("FREE - " + product.getName());
    product.setPrice(0);
    product.setDepartment("Kids");
    return product;
  }
}
