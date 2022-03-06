package org.masci.rp.sec01;

import java.util.concurrent.CompletableFuture;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Mono;

/**
 *
 * @author Daniel
 */
public class Lec07MonoFromFuture {
  
  public static void main(String[] args) {
    
    Mono.fromFuture(getName())
        .subscribe(DmkUtil.onNext());
    
    DmkUtil.sleepSeconds(1);
  }
  
  private static CompletableFuture<String> getName() {
    return CompletableFuture.supplyAsync(() -> DmkUtil.faker().name().fullName());
  }
}
