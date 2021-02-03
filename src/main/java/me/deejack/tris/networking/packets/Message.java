package me.deejack.tris.networking.packets;

import me.deejack.tris.networking.protocol.StatusCode;

import java.util.Optional;

public class Message implements Packet {
  private final String message;
  private final StatusCode code;
  private String checkSum;

  public Message(String message) {
    this.message = message;
    this.code = StatusCode.OK;
  }

  public Message(String message, StatusCode code) {
    this.message = message;
    this.code = code;
  }

  public static Optional<Message> fromRawPacket(String rawMessage) {
    try {
      String[] parts = rawMessage.split("\\|");
      var code = StatusCode.values()[Integer.parseInt(parts[0])];
      var textMessage = parts[1];
      var checksum = parts[2];
      var message = new Message(textMessage, code);
      if (!MessageValidator.checkMessage(message, checksum))
        return Optional.empty();
      return Optional.of(message);
    } catch (Exception ex) {
      return Optional.empty();
    }
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public String getChecksum() {
    return checkSum;
  }

  @Override
  public StatusCode getCode() {
    return code;
  }

  @Override
  public void generateChecksum() {
    checkSum = MessageValidator.generateChecksum(this);
  }

  @Override
  public String toRawPacket() {
    return code.getCode() + "|" + message + "|" + checkSum;
  }

  @Override
  public String toString() {
    return "Message{" +
            "message='" + message + '\'' +
            ", code=" + code +
            ", checkSum='" + checkSum + '\'' +
            '}';
  }
}
