package me.deejack.tris.game.modes.multiplayer;

import me.deejack.tris.game.Game;
import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.PlayerSymbol;
import me.deejack.tris.players.types.LocalPlayer;
import me.deejack.tris.players.types.NetworkPlayer;
import me.deejack.tris.utils.LoadingAnimation;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class LocalRoomGame extends Game {
  private final LoadingAnimation loadingAnimation = new LoadingAnimation();
  private final LocalPlayer localPlayer;
  private final NetworkPlayer networkPlayer;

  public LocalRoomGame(NetworkPlayer networkPlayer, int columns) {
    super(new LocalPlayer(0), networkPlayer, new DefaultGameLogic(columns), columns);
    this.networkPlayer = networkPlayer;
    this.localPlayer = (LocalPlayer) getPlayers()[0];
  }

  @Override
  protected CompletableFuture<Void> beforeStart() {
    System.out.println("------------P1---------------");
    localPlayer.askName().join();
    localPlayer.setSymbol(new PlayerSymbol('X'));
    networkPlayer.sendMessage(localPlayer.getName()).join();

    System.out.println("------------P2---------------");
    loadingAnimation.start();
    networkPlayer.askName().join();
    networkPlayer.setSymbol(new PlayerSymbol('O'));
    loadingAnimation.stop();
    return completedFuture(null);
  }

  @Override
  protected CompletableFuture<Void> beforeTurn() {
    return completedFuture(null);
  }

  @Override
  protected CompletableFuture<Void> onFinish(Results result) {
    System.out.println(result);
    return completedFuture(null);
  }

  @Override
  protected CompletableFuture<Void> afterTurn() {
    System.out.println(getBoard().toString());
    return completedFuture(null);
  }
}
