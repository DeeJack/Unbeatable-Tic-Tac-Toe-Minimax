package me.deejack.tris.utils;

import java.util.concurrent.CompletableFuture;

public class LoadingAnimation {
  private boolean stopped = true;
  private CompletableFuture<Void> currentTask;

  public LoadingAnimation() {

  }

  public void start() {
    stopped = false;
    currentTask = CompletableFuture.runAsync(() -> {
      do {
        System.out.printf("\\");
        System.out.printf("\b");
        pause(600);
        System.out.printf("|");
        System.out.printf("\b");
        pause(600);
        System.out.printf("/");
        System.out.printf("\b");
        pause(600);
      } while (!stopped);
    });
  }

  private void pause(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ex) {
    }
  }

  public void stop() {
    if (currentTask != null && !currentTask.isCancelled()) {
      currentTask.cancel(true);
    }
  }
}
