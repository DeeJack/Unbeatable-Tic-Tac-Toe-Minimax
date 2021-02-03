package me.deejack.tris.players.listeners;

import java.util.EventListener;

@FunctionalInterface
public interface ErrorListener extends EventListener {
  void onError(Exception e);

  default void sendError(Exception e) {
    onError(e);
  }
}
