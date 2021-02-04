package me.deejack.tris.players.types;

import me.deejack.tris.board.Board;
import me.deejack.tris.board.Cell;
import me.deejack.tris.networking.packets.Message;
import me.deejack.tris.networking.protocol.MessageHandler;
import me.deejack.tris.players.DefaultPlayer;
import me.deejack.tris.players.PlayerSymbol;
import me.deejack.tris.players.listeners.ErrorListener;

import java.net.Socket;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class NetworkPlayer extends DefaultPlayer {
  private final Socket socket;
  private final MessageHandler messageHandler;
  private ErrorListener errorListener;

  public NetworkPlayer(int index, Socket socket) {
    this("Unknown", new PlayerSymbol('O'), index, socket);
  }

  public NetworkPlayer(String name, PlayerSymbol symbol, int index, Socket socket) {
    super(index);
    this.socket = socket;
    this.messageHandler = new MessageHandler(socket);
    setName(name);
    setSymbol(symbol);
  }

  @Override
  public CompletableFuture<String> getInput(String question) {
    //sendMessage(question);
    System.out.println("WAITING FOR A MESSAGE");
    var message = messageHandler.receiveMessage().join();
    var textMessage = message.map(Message::getMessage).orElse("");
    //System.out.println("RETURNING MESSAGE RECEIVED: " + textMessage);
    return completedFuture(textMessage);
  }

  public int getIntInput(String question) {
    return Integer.parseInt(getInput(question).join());
  }

  @Override
  public CompletableFuture<Void> askName() {
    var future = CompletableFuture.runAsync(() -> {
      String name = "";
      do {
        name = getInput("").join().trim();
      } while (name.length() == 0);
      setName(name);
    });
    return future;
  }

  @Override
  public CompletableFuture<Void> sendMessage(String message) {
    messageHandler.sendMessage(new Message(message + "|" + getIndex())).join();
    return completedFuture(null);
  }

  @Override
  public CompletableFuture<Cell> getNextMove(Board board) {
    int row = getIntInput("");
    int column = getIntInput("");
    return completedFuture(new Cell(row, column));
  }
}
