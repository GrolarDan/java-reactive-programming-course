package org.masci.rp.sec01;

import java.util.stream.Stream;

public class Lec01 {
  public static void main(String[] args) {
    var stream = Stream.of(1)
        .map(i -> {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          return i * 2;
        });

    stream.forEach(System.out::println);
  }
}
