package me.deejack.tris.utils;

import java.util.concurrent.CompletableFuture;

public class LoadingAnimation {
  private boolean stopped = true;
  private CompletableFuture<Void> currentTask;

  public LoadingAnimation() {

  }

  public void start() {
    stopped = false;
    currentTask = CompletableFuture.supplyAsync(() -> {
      do {
        System.out.print("\\");
        System.out.print("\b");
        pause(600);
        System.out.print("|");
        System.out.print("\b");
        pause(600);
        System.out.print("/");
        System.out.print("\b");
        pause(600);
      } while (!stopped);
      return null;
    });
  }

  private void pause(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ignored) {
    }
  }

  public void stop() {
    if (currentTask != null && !currentTask.isCancelled()) {
      System.out.print("\b");
      currentTask.cancel(true);
    }
  }
}
