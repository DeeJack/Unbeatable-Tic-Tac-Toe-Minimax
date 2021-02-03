package me.deejack.tris.networking.protocol;

import me.deejack.tris.networking.packets.Message;
import me.deejack.tris.players.listeners.ErrorListener;

import java.io.*;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.failedFuture;

public class MessageHandler {
  private final Socket socket;
  private ErrorListener errorListener;

  public MessageHandler(Socket socket) {
    this.socket = socket;
    this.errorListener = System.err::println;
  }

  public MessageHandler(Socket socket, ErrorListener errorListener) {
    this.socket = socket;
    this.errorListener = errorListener;
  }

  public void sendAcknowledgment(Message message) {
    //sendMessage(new Message("", StatusCode.OK));
  }

  public void sendFailed() {
    //sendMessage(new Message("", StatusCode.ERROR));
  }

  public CompletableFuture<Void> sendMessage(Message message) {
    System.out.println("\n----------- Sending message: " + message + "\n");
    return CompletableFuture.runAsync(() -> {
      try {
        var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write(message.toRawPacket() + '\n');
        writer.flush();
      } catch (IOException e) {
        errorListener.sendError(e);
      }
    });
  }

  public void setErrorListener(ErrorListener errorListener) {
    this.errorListener = errorListener;
  }

  public CompletableFuture<Optional<Message>> receiveMessage() {
    try {
      var inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      var input = inputStream.readLine();
      System.out.println("\n----------- Received message: " + input + "\n");
      var message = Message.fromRawPacket(input);
      message.ifPresent(m -> System.out.println("Parsed message: " + m));
      if (message.isEmpty() || message.get().getCode() != StatusCode.OK)
        sendFailed();
      else
        sendAcknowledgment(message.get());
      return completedFuture(message);
    } catch (IOException e) {
      errorListener.sendError(e);
      return failedFuture(e);
    }
  }
}
