package me.deejack.tris.networking.protocol;

public enum StatusCode {
  UNKNOWN(0), // Not sent yet
  ERROR(1), // Error in the transmission
  OK(2); // All good

  private final int code;

  StatusCode(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
