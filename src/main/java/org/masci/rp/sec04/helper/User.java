package org.masci.rp.sec04.helper;

import lombok.Data;
import lombok.ToString;
import org.masci.rp.courseutil.DmkUtil;

@Data
@ToString
public class User {
  private int userId;
  private String name;

  public User(int userId) {
    this.userId = userId;
    this.name = DmkUtil.faker().name().fullName();
  }
}
