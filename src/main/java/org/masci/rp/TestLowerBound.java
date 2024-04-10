package org.masci.rp;

import java.util.ArrayList;
import java.util.List;

public class TestLowerBound {
  public static void main(String[] args) {
    List<? super Integer> l1 = new ArrayList<>();
    l1.add(11);
    List<? super Number> l2 = new ArrayList<>();
    Number number = 12;
    l2.add(number);
  }
}
