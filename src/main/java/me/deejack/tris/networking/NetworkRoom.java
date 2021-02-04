package me.deejack.tris.networking;

import me.deejack.tris.game.Game;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface NetworkRoom {
  public CompletableFuture<Optional<Game>> startGame();
}
