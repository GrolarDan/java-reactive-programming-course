package org.masci.rp.sec03;

import com.rp.courseutil.Util;
import com.rp.sec03.helper.NameProducer;
import org.masci.rp.courseutil.DmkUtil;
import reactor.core.publisher.Flux;

public class Lec08FluxPush {

    public static void main(String[] args) {


        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer)
                .subscribe(DmkUtil.subscriber());

        Runnable runnable = nameProducer::produce;

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(2);


    }


}
