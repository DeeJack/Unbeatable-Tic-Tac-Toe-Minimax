package me.deejack.tris.networking.packets;

public class Message implements Packet {
  private final String message;

  public Message(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "Message{" +
            "message='" + message + '\'' +
            '}';
  }
}
