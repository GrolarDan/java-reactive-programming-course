package org.masci.rp.sec04;

import java.util.function.Function;
import org.masci.rp.courseutil.DmkUtil;
import org.masci.rp.sec04.helper.Person;
import reactor.core.publisher.Flux;

public class Lec10Transform {

  public static void main(String[] args) {

    getPerson()
        .transform(applyFilterMap())
        .subscribe(DmkUtil.subscriber());
  }

  private static Flux<Person> getPerson() {
    return Flux.range(1, 10)
        .map(i -> new Person());
  }

  private static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
    return flux -> flux
        .filter(p -> p.getAge() > 10)
        .doOnNext(p -> p.setName(p.getName().toUpperCase()))
        .doOnDiscard(Person.class, p -> System.out.println("Not allowing: " + p));
  }
}
