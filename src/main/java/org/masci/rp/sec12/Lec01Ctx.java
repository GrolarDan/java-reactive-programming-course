package org.masci.rp.sec12;

import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Ctx {

  public static void main(String[] args) {

    getWelcomeMessage()
        .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
        .contextWrite(Context.of("user", "sam"))
        .subscribe(DmkUtil.subscriber());
  }

  private static Mono<String> getWelcomeMessage() {
    return Mono.deferContextual(ctx -> {
      if (ctx.hasKey("user")) {
        return Mono.just("Welcome " + ctx.get("user"));
      } else {
        return Mono.error(new RuntimeException("unauthenticated"));
      }
    });
  }
}
