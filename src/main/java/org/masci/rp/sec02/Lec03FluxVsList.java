package org.masci.rp.sec02;

import java.time.Instant;
import org.masci.rp.courseutil.DmkUtil;
import org.masci.rp.sec02.helper.NameGenerator;

public class Lec03FluxVsList {
  public static void main(String[] args) {
    System.out.println("Get list of names: " + Instant.now());
    var listOfNames = NameGenerator.getListOfNames(5);
    System.out.println(listOfNames);
    System.out.println("End of reading: " + Instant.now());

    System.out.println("Get flux of names: " + Instant.now());
    NameGenerator.getFluxOfNames(5)
        .subscribe(DmkUtil.onNext());
    System.out.println("End of reading: " + Instant.now());
  }
}
