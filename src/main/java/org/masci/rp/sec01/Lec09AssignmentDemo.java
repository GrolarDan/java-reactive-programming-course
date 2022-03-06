package org.masci.rp.sec01;

import org.masci.rp.courseutil.DmkUtil;
import org.masci.rp.sec01.assignment.FileService;
import reactor.core.publisher.Mono;

/**
 *
 * @author Daniel
 */
public class Lec09AssignmentDemo {

  public static void main(String[] args) {
    Mono<String> readFile = FileService.read("file01.txt");
    DmkUtil.sleepSeconds(3);
    readFile.subscribe(
        DmkUtil.onNext(),
        DmkUtil.onError(),
        DmkUtil.onComplete()
    );
    
    // error
    Mono<String> readErrorFile = FileService.read("fileXXX.txt");
    DmkUtil.sleepSeconds(2);
    readErrorFile.subscribe(
        DmkUtil.onNext(), 
        DmkUtil.onError(), 
        DmkUtil.onComplete()
    );
  }

}
