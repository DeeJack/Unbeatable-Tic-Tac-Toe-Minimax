package me.deejack.tris.players.types;

import me.deejack.tris.board.Board;
import me.deejack.tris.board.Cell;
import me.deejack.tris.minimax.Minimax;
import me.deejack.tris.players.DefaultPlayer;
import me.deejack.tris.players.PlayerSymbol;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class MinimaxPlayer extends DefaultPlayer {
  private final int depth;

  public MinimaxPlayer(int depth) {
    super(1);
    this.depth = depth;
    setName("The computer");
    setSymbol(new PlayerSymbol('O'));
  }

  public MinimaxPlayer() {
    this(Integer.MAX_VALUE);
  }

  @Override
  public CompletableFuture<Void> sendMessage(String message) {
    return completedFuture(null);
  }

  @Override
  public CompletableFuture<String> getInput(String question) {
    return completedFuture("");
  }

  @Override
  public CompletableFuture<Cell> getNextMove(Board board) {
    var minimax = new Minimax(board.deepCopy().getCells(), this.depth, 1);
    CompletableFuture<Cell> future = CompletableFuture.supplyAsync(() -> {
      var bestMove = minimax.getBestMove().join();
      return bestMove;
    });
    return future;
  }
}