package org.masci.rp.sec09.helper;

import lombok.Getter;
import org.masci.rp.courseutil.DmkUtil;

@Getter
public class BookOrder {

  private String title;
  private String author;
  private String category;
  private double price;

  public BookOrder() {
    var book = DmkUtil.faker().book();

    this.title = book.title();
    this.author = book.author();
    this.category = book.genre();
    this.price = Double.parseDouble(DmkUtil.faker().commerce().price());
  }
}
