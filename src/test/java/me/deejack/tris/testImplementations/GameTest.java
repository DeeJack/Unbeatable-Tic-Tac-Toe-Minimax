package me.deejack.tris.testImplementations;

import me.deejack.tris.board.Cell;
import me.deejack.tris.game.Game;
import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.Player;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class GameTest extends Game {

  public GameTest(Player firstPlayer, Player secondPlayer) {
    super(firstPlayer, secondPlayer, new DefaultGameLogic(3), 3);
  }

  @Override
  protected CompletableFuture<Void> beforeStart() {
    return completedFuture(null);
  }

  @Override
  protected CompletableFuture<Void> beforeTurn() {
    return completedFuture(null);
  }

  @Override
  protected CompletableFuture<Void> afterTurn() {
    System.out.println(getBoard().toString());
    return completedFuture(null);
  }

  @Override
  protected CompletableFuture<Void> afterMove(Cell move) {
    return completedFuture(null);
  }

  @Override
  protected CompletableFuture<Void> onFinish(Results result) {
    return completedFuture(null);
  }

}