package org.masci.rp.sec01.assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import reactor.core.publisher.Mono;

/**
 *
 * @author Daniel
 */
public class FileService {

  private static final Path PATH = Paths.get("src/main/resources/assignment/sec01");
  
  public static Mono<String> read(String fileName) {
    System.out.println("read: " + fileName);
    return Mono.fromSupplier(() -> readFile(fileName));
  }

  public static Mono<Void> write(String fileName, String content) {
    System.out.println("write: " + fileName);
    return Mono.fromRunnable(() -> writeFile(fileName, content));
  }
  
  public static Mono<Void> delete(String fileName) {
    System.out.println("delete: " + fileName);
    return Mono.fromRunnable(() -> deleteFile(fileName));
  }
  
  private static String readFile(String fileName) {
    try {
      System.out.println("reading file ...");
      return Files.readString(PATH.resolve(fileName));
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  private static void writeFile(String fileName, String content) {
    try {
      System.out.println("writing file");
      Files.writeString(PATH.resolve(fileName), content);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  private static void deleteFile(String fileName) {
    try {
      System.out.println("deleting file");
      Files.delete(PATH.resolve(fileName));
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  
  
}
