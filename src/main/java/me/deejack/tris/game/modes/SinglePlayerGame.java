package me.deejack.tris.game.modes;

import me.deejack.tris.board.Cell;
import me.deejack.tris.game.Game;
import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.Player;
import me.deejack.tris.players.types.LocalPlayer;
import me.deejack.tris.players.types.RandomComputerPlayer;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class SinglePlayerGame extends Game {
  private final LocalPlayer player;

  public SinglePlayerGame(int columns) {
    this(columns, new RandomComputerPlayer());
  }

  // TODO: create a common class between the computer player
  public SinglePlayerGame(int columns, Player otherPlayer) {
    super(new LocalPlayer(0), otherPlayer, new DefaultGameLogic(columns), columns);
    this.player = (LocalPlayer) super.getPlayers()[0];
  }

  @Override
  public CompletableFuture<Void> beforeStart() {
    player.askName().join();
    return completedFuture(null);
  }

  @Override
  public CompletableFuture<Void> beforeTurn() {
    System.out.println(getBoard().toString());
    return completedFuture(null);
  }

  @Override
  public CompletableFuture<Void> onFinish(Results result) {
    System.out.println(result);
    return completedFuture(null);
  }

  @Override
  protected CompletableFuture<Void> afterMove(Cell move) {
    return completedFuture(null);
  }

  @Override
  protected CompletableFuture<Void> afterTurn() {
    System.out.println(getBoard().toString());
    return completedFuture(null);
  }
}