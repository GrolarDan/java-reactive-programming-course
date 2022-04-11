package org.masci.rp.sec09.assignment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.masci.rp.courseutil.DmkUtil;

@ToString
@Getter
@Setter
public class Product {

  private String name;
  private String department;
  private double price;

  public Product() {
    var commerce = DmkUtil.faker().commerce();

    this.name = commerce.productName();
    this.department = commerce.department();
    this.price = Double.parseDouble(commerce.price());
  }
}
