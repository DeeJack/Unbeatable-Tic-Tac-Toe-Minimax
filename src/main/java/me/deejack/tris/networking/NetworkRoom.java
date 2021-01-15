package me.deejack.tris.networking;

import me.deejack.tris.game.modes.NetworkMultiplayerGame;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface NetworkRoom {
  public CompletableFuture<Optional<NetworkMultiplayerGame>> startGame();
}
