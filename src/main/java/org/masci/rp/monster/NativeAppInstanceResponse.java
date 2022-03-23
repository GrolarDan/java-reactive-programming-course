package org.masci.rp.monster;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NativeAppInstanceResponse {

  private List<NativeAppInstance> content;
  private String continuationToken;
}
