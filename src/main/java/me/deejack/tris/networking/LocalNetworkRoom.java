package me.deejack.tris.networking;

import me.deejack.tris.game.modes.NetworkMultiplayerGame;
import me.deejack.tris.players.types.NetworkPlayer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class LocalNetworkRoom implements NetworkRoom {
  private final int port;
  private final String address;
  private ServerSocket server;
  private boolean open = false;

  /**
   * Start the room on port 9999 in the local network
   *
   * @throws UnknownHostException If the local host name could not be resolved into an address {@link InetAddress#getLocalHost()}
   */
  public LocalNetworkRoom() throws UnknownHostException {
    this(9999);
  }

  /**
   * Provide a port different from the default 9999
   *
   * @param port The port for the server
   * @throws UnknownHostException If the local host name could not be resolved into an address {@link InetAddress#getLocalHost()}
   */
  public LocalNetworkRoom(int port) throws UnknownHostException {
    this(InetAddress.getLocalHost().getHostAddress(), port);
  }

  /**
   * Open the server giving the address and the port directly
   *
   * @param address The address for the server, for example "127.0.0.1" or a public IP
   * @param port    The port for the server, for example 9999
   */
  public LocalNetworkRoom(String address, int port) {
    this.port = port;
    this.address = address;
  }

  /**
   * Start the server with the given parameters (address, port);
   *
   * @return true if the server opened successfully, false otherwise
   */
  public boolean startServer() {
    var serverOpened = openServer();
    serverOpened.ifPresentOrElse(server -> {
      this.server = server;
      open = true;
      System.out.println("Started local server on address: " + address + " and port: " + port);
    }, () -> {
      System.err.printf("Couldn't open server on port: %d and address %s%n", port, address);
    });
    return serverOpened.isPresent();
  }

  private Optional<ServerSocket> openServer() {
    try {
      return Optional.of(new ServerSocket(port, 1, InetAddress.getByName(address)));
    } catch (IOException ex) {
      return Optional.empty();
    }
  }

  /**
   * Start the multiplayer game in a local room;
   * It starts the server with the given parameters (address, port)
   * If the server opened successfully, it will wait and accept a remote player
   * When the player is accepted, if there is no problem, the instance of the MultiPlayerGame will be returned
   * If an 'empty' optional is returned, an error occurred
   *
   * @return A {@link Optional<NetworkMultiplayerGame>} if the server is opened successfully and the player has been accepted,
   * it will return an {@link Optional#empty()} otherwise.
   */
  @Override
  public CompletableFuture<Optional<NetworkMultiplayerGame>> startGame() {
    return CompletableFuture.supplyAsync(() -> {
      startServer();
      if (!open || server.isClosed())
        return Optional.empty();
      var optionalPlayer = acceptPlayer().join();
      if (optionalPlayer.isEmpty())
        return Optional.empty();
      System.out.println("Player accepted");
      return Optional.of(new NetworkMultiplayerGame(optionalPlayer.get(), 3));
    });
  }

  private CompletableFuture<Optional<NetworkPlayer>> acceptPlayer() {
    return CompletableFuture.supplyAsync(() -> {
      try {
        System.out.println("Waiting for player to accept");
        var remotePlayer = server.accept();
        return Optional.of(new NetworkPlayer(1, remotePlayer));
      } catch (IOException e) {
        System.err.printf("Couldn't accept player, error: %s", e.getMessage());
      }
      return Optional.empty();
    });
  }
}
