package org.masci.rp.sec03;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.masci.rp.courseutil.DmkUtil;
import org.masci.rp.sec03.assignment.FileReaderService;

public class Lec09FileReaderServiceAssignment {

  public static void main(String[] args) {
    var readerService = new FileReaderService();

    Path path = Paths.get("src/main/resources/assignment/sec03/file01.txt");
    readerService.read(path)
        .map(s -> {
          Integer i = DmkUtil.faker().random().nextInt(0, 10);
          if (i > 8) {
            throw new RuntimeException("oops");
          }
          return s;
        })
        .take(20)
        .subscribe(DmkUtil.subscriber());
  }
}
