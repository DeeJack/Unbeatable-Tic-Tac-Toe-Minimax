package me.deejack.tris.players.types;

import me.deejack.tris.board.Board;
import me.deejack.tris.board.Cell;
import me.deejack.tris.players.DefaultPlayer;
import me.deejack.tris.players.PlayerSymbol;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class RandomComputerPlayer extends DefaultPlayer {
  public RandomComputerPlayer(PlayerSymbol symbol, String name) {
    super(1);
    setSymbol(symbol);
    setName(name);
  }

  public RandomComputerPlayer() {
    this(new PlayerSymbol('O'), "The computer");
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
    var random = new Random();
    var emptyCells = board.getEmptyCells();
    var nextCell = emptyCells[random.nextInt(emptyCells.length)];
    return completedFuture(nextCell);
  }

  @Override
  public CompletableFuture<Void> askName() {
    return CompletableFuture.completedFuture(null);
  }

}