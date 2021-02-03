package me.deejack.tris.networking.packets;

import me.deejack.tris.networking.protocol.StatusCode;

public interface Packet {
  String getMessage();

  String getChecksum();

  StatusCode getCode();

  void generateChecksum();

  String toRawPacket();
}
