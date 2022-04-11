package org.masci.rp.sec08;

import org.masci.rp.courseutil.DmkUtil;
import org.masci.rp.sec08.helper.AmericanAirlines;
import org.masci.rp.sec08.helper.Emirates;
import org.masci.rp.sec08.helper.Qatar;
import reactor.core.publisher.Flux;

public class Lec03Merge {

  public static void main(String[] args) {

    var merge = Flux.merge(
        Qatar.getFlights(),
        Emirates.getFlights(),
        AmericanAirlines.getFlights()
    );

    merge.subscribe(DmkUtil.subscriber());

    DmkUtil.sleepSeconds(30);
  }
}
