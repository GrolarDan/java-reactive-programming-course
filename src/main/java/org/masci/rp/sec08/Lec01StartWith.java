package org.masci.rp.sec08;

import org.masci.rp.courseutil.DmkUtil;
import org.masci.rp.sec08.helper.NameGenerator;

public class Lec01StartWith {

  public static void main(String[] args) {

    NameGenerator nameGenerator = new NameGenerator();

    nameGenerator.generateNames()
        .take(2)
        .subscribe(DmkUtil.subscriber("John"));

    nameGenerator.generateNames()
        .take(2)
        .subscribe(DmkUtil.subscriber("Mike"));

    nameGenerator.generateNames()
        .filter(n -> n.startsWith("A"))
        .take(2)
        .subscribe(DmkUtil.subscriber("Marshal"));
  }
}
