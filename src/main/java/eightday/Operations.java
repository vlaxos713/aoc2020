package eightday;

public enum Operations {
  ACCUMULATOR("acc"),
  JUMP("jmp"),
  NO_OPERATION("nop");

  private String key;

  Operations(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public static Operations getFromKey(String key) {
    for (Operations value : values()) {
      if (key.equalsIgnoreCase(value.getKey())) {
        return value;
      }
    }

    throw new IllegalArgumentException("Operation " + key + " is not known");
  }
}
