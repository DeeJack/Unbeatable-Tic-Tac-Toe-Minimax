package me.deejack.tris.game;

import me.deejack.tris.board.Board;
import me.deejack.tris.board.Cell;
import me.deejack.tris.game.logic.GameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.Player;
import me.deejack.tris.players.types.NetworkPlayer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Game {
  private final Board board;
  private final Player[] players = new Player[2];
  private final GameLogic logic;
  private final AtomicInteger round = new AtomicInteger(0);
  private boolean finished = false;
  private Results result = Results.NONE;

  public Game(Player firstPlayer, Player secondPlayer, GameLogic logic, int columns) {
    if (firstPlayer == null || secondPlayer == null)
      throw new IllegalArgumentException("Player must not be null");
    players[0] = firstPlayer;
    players[1] = secondPlayer;
    this.logic = logic;
    this.board = new Board(columns);
  }

  protected abstract CompletableFuture<Void> beforeStart();

  protected abstract CompletableFuture<Void> beforeTurn();

  protected CompletableFuture<Void> onTurn() {
    var currentPlayer = players[round.get() % 2];
    boolean isRemotePlayer = currentPlayer instanceof NetworkPlayer;
    var future = CompletableFuture.runAsync(() -> {
      if (!isRemotePlayer)
        currentPlayer.sendMessage("It's your turn, " + currentPlayer.getName());
      Cell choosenCell;
      boolean result;
      do {
        choosenCell = currentPlayer.getNextMove(board).join();
        result = board.changeCellStatus(choosenCell.getRow(), choosenCell.getColumn(), currentPlayer);
        if (!result && !isRemotePlayer)
          currentPlayer.sendMessage("Incorrect data, please try again").join();
        if (result && !isRemotePlayer)
          afterMove(choosenCell);
      } while (!result);
      var gameResult = logic.checkWin(choosenCell.getRow(), choosenCell.getColumn(), round.get() % 2);
      if (gameResult != Results.NONE) {
        onFinish(gameResult);
        this.result = gameResult;
        finished = true;
      }
      round.incrementAndGet();
    });
    return future;
  }

  protected abstract CompletableFuture<Void> afterTurn();

  protected abstract CompletableFuture<Void> afterMove(Cell move);

  public CompletableFuture<Results> start() {
    CompletableFuture<Results> future = CompletableFuture.supplyAsync(() -> {
      beforeStart().join();
      /*
       * if (players[0].getSymbol().equals(players[1].getSymbol())) { players[0].
       * sendMessage("You can't have the same symbol, your symbol has been changed to another one!"
       * ); players[1].
       * sendMessage("You can't have the same symbol, your symbol has been changed to another one!"
       * ); return completedFuture(Results.NONE); }
       */
      do {
        beforeTurn().join();
        onTurn().join();
        afterTurn().join();
      } while (!finished);
      return result;
    });
    return future;
  }

  protected abstract CompletableFuture<Void> onFinish(Results result);

  public void afterFinish(Results result) {
    int playerOne = 0; // draw
    switch (result) {
      case WIN_P1:
        playerOne = 1;
        break;
      case WIN_P2:
        playerOne = -1;
        break;
      default:
        throw new IllegalStateException();
    }
    players[0].addResult(playerOne);
    players[1].addResult(playerOne * -1);
  }

  protected Player[] getPlayers() {
    return players;
  }

  public int getRound() {
    return round.get();
  }

  public Board getBoard() {
    return board;
  }

  public Results getResult() {
    return result;
  }
}