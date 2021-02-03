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

public class RemoteRoomGame extends Game {
  private final LoadingAnimation loadingAnimation = new LoadingAnimation();
  private final LocalPlayer localPlayer;
  private final NetworkPlayer networkPlayer;

  public RemoteRoomGame(NetworkPlayer networkPlayer, int columns) {
    super(networkPlayer, new LocalPlayer(1), new DefaultGameLogic(columns), columns);
    this.networkPlayer = networkPlayer;
    this.localPlayer = (LocalPlayer) getPlayers()[1];
  }

  @Override
  protected CompletableFuture<Void> beforeStart() {
    System.out.println("------------P1---------------");
    loadingAnimation.start();
    networkPlayer.askName().join();
    networkPlayer.setSymbol(new PlayerSymbol('X'));
    loadingAnimation.stop();
    //players[0].askSymbol();
    System.out.println("------------P2---------------");
    localPlayer.askName().join();
    localPlayer.setSymbol(new PlayerSymbol('O'));
    //players[1].askSymbol();
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
