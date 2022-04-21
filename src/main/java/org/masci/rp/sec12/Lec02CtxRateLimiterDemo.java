package org.masci.rp.sec12;

import org.masci.rp.courseutil.DmkUtil;
import org.masci.rp.sec12.helper.BookService;
import org.masci.rp.sec12.helper.UserService;
import reactor.util.context.Context;

public class Lec02CtxRateLimiterDemo {

  public static void main(String[] args) {

    BookService.getBook()
        .repeat(2)
        .contextWrite(UserService.userCategoryContext())
        .contextWrite(Context.of("user", "mike"))
        .subscribe(DmkUtil.subscriber());
  }
}
