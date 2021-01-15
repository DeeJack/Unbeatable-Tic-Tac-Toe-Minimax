package me.deejack.tris.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class RemoteNetworkRoom {
  private Socket remoteRoom;

  public RemoteNetworkRoom(String address, int port) {
    connect(address, port).ifPresentOrElse(socket -> this.remoteRoom = socket, () -> {
      System.err.println("Couldn't connect to remote room"); // TODO: exception
    });
  }

  private Optional<Socket> connect(String address, int port) {
    try {
      return Optional.of(new Socket(address, port));
    } catch (IOException ex) {

      return Optional.empty();
    }
  }
}
