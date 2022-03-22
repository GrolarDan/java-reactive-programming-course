package org.masci.rp.sec04.helper;

import lombok.Data;
import lombok.ToString;
import org.masci.rp.courseutil.DmkUtil;

@Data
@ToString
public class Person {
  private String name;
  private int age;

  public Person() {
    this.name = DmkUtil.faker().name().firstName();
    this.age = DmkUtil.faker().random().nextInt(1, 30);
  }
}
