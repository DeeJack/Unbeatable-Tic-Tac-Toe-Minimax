package me.deejack.tris.players.types;

import me.deejack.tris.board.Board;
import me.deejack.tris.board.Cell;
import me.deejack.tris.players.DefaultPlayer;
import me.deejack.tris.players.PlayerSymbol;

import java.net.Socket;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class NetworkPlayer extends DefaultPlayer {
  private final Socket socket;

  public NetworkPlayer(int index, Socket socket) {
    this("Unknown", new PlayerSymbol('O'), index, socket);
  }

  public NetworkPlayer(String name, PlayerSymbol symbol, int index, Socket socket) {
    super(index);
    this.socket = socket;
    setName(name);
    setSymbol(symbol);
  }

  @Override
  public CompletableFuture<String> getInput(String question) {
    sendMessage(question, false);
    return completedFuture("");
  }

  public int getIntInput(String question) {
    return -1;
  }

  @Override
  public CompletableFuture<Void> askName() {
    var future = CompletableFuture.runAsync(() -> {
      String name = "";
      do {
        name = getInput("Insert your name: ").join().trim();
      } while (name.length() == 0);
      sendMessage("Your name is: " + name);
      setName(name);
    });
    return future;
  }

  public void askSymbol() {
    char[] input = new char[0];
    do {
      input = getInput("Insert your symbol: ").join().trim().toCharArray();
    } while (input.length == 0);

    char symbol = input[0];
    sendMessage("Your symbol is: " + symbol);
    setSymbol(new PlayerSymbol(symbol));
  }

  @Override
  public CompletableFuture<Void> sendMessage(String message) {
    sendMessage(message, true);
    return completedFuture(null);
  }

  public void sendMessage(String message, boolean newLine) {
    if (newLine)
      System.out.println(message);
    else
      System.out.print(message);
  }

  @Override
  public CompletableFuture<Cell> getNextMove(Board board) {
    int row = getIntInput("Select the row: ");
    int column = getIntInput("Select the column: ");
    return completedFuture(new Cell(row, column));
  }
}
