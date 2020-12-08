package eightday;

import lombok.Data;

@Data
public class ExecutionEntry {
  private final Operations operation;
  private final Integer value;
  private boolean executed;
}
