package org.masci.rp.sec04;

import org.masci.rp.courseutil.DmkUtil;
import org.masci.rp.sec04.helper.OrderService;
import org.masci.rp.sec04.helper.UserService;

public class Lec12FlatMap {

  public static void main(String[] args) {

    UserService.getUsers()
//        .map(user -> OrderService.getOrders(user.getUserId()))
        .flatMap(user -> OrderService.getOrders(user.getUserId()))
        .subscribe(DmkUtil.subscriber());

    DmkUtil.sleepSeconds(60);
  }
}
