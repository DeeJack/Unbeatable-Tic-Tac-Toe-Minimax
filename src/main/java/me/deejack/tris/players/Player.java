package me.deejack.tris.players;

import me.deejack.tris.board.Board;
import me.deejack.tris.board.Cell;

import java.util.concurrent.CompletableFuture;

public interface Player {
  PlayerSymbol getSymbol();

  void setSymbol(PlayerSymbol symbol);

  String getName();

  int getWins();

  int getLosses();

  int getDraws();

  int getIndex();

  /**
   * -1 = loss
   * 0 = draw
   * 1 = win
   */
  void addResult(int result);

  CompletableFuture<Void> sendMessage(String message);

  CompletableFuture<String> getInput(String question);

  CompletableFuture<Cell> getNextMove(Board board);

  CompletableFuture<Void> askName();
}