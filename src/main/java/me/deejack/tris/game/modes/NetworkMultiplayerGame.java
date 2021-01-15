package me.deejack.tris.game.modes;

import me.deejack.tris.game.Game;
import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.Player;
import me.deejack.tris.players.PlayerSymbol;
import me.deejack.tris.players.types.LocalPlayer;
import me.deejack.tris.players.types.NetworkPlayer;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class NetworkMultiplayerGame extends Game {
  private final Player[] players = new Player[2];

  public NetworkMultiplayerGame(int columns) {
    super(new LocalPlayer(0), new NetworkPlayer(1), new DefaultGameLogic(columns), columns);
    players[0] = getPlayers()[0];
    players[1] = getPlayers()[1];
  }

  @Override
  protected CompletableFuture<Void> beforeStart() {
    System.out.println("------------P1---------------");
    players[0].askName().join();
    players[0].setSymbol(new PlayerSymbol('X'));
    //players[0].askSymbol();
    System.out.println("------------P2---------------");
    players[1].askName().join();
    players[0].setSymbol(new PlayerSymbol('O'));
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
