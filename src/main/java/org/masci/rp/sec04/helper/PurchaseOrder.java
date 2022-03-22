package org.masci.rp.sec04.helper;

import lombok.Data;
import lombok.ToString;
import org.masci.rp.courseutil.DmkUtil;

@Data
@ToString
public class PurchaseOrder {

  private String item;
  private String price;
  private int userId;

  public PurchaseOrder(int userId) {
    this.userId = userId;
    this.item = DmkUtil.faker().commerce().productName();
    this.price = DmkUtil.faker().commerce().price();
  }
}
