package org.masci.rp.sec11.assignment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SlackMessage {

  private String from;
  private String message;

  @Override
  public String toString() {
    return from + " : " + message;
  }
}
