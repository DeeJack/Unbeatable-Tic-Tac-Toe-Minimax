package me.deejack.tris.networking;

import me.deejack.tris.game.modes.NetworkMultiplayerGame;
import me.deejack.tris.players.types.NetworkPlayer;

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class RemoteNetworkRoom implements NetworkRoom {
  private final String address;
  private final int port;

  public RemoteNetworkRoom(String address, int port) {
    this.address = address;
    this.port = port;
  }

  private CompletableFuture<Socket> start() {
    return CompletableFuture.supplyAsync(() -> {
      var connection = connect();
      if (connection.isEmpty()) {
        return null;
      }
      return connection.get();
    });
  }

  @Override
  public CompletableFuture<Optional<NetworkMultiplayerGame>> startGame() {
    return CompletableFuture.supplyAsync(() -> {
      var remoteSocket = start().join();
      if (remoteSocket == null)
        return Optional.empty();
      return Optional.of(new NetworkMultiplayerGame(new NetworkPlayer(1, remoteSocket), 3));
    });
  }

  private Optional<Socket> connect() {
    try {
      return Optional.of(new Socket(address, port));
    } catch (IOException ex) {
      System.out.printf("Couldn't connect to the remote server! Address: %s, port: %d %n", address, port);
      return Optional.empty();
    }
  }
}
