package me.deejack.tris;

import me.deejack.tris.game.Game;
import me.deejack.tris.game.modes.LocalMultiplayerGame;
import me.deejack.tris.game.modes.SinglePlayerGame;
import me.deejack.tris.networking.LocalNetworkRoom;
import me.deejack.tris.networking.NetworkRoom;
import me.deejack.tris.networking.RemoteNetworkRoom;
import me.deejack.tris.players.types.MinimaxPlayer;
import me.deejack.tris.utils.LoadingAnimation;

import java.net.UnknownHostException;
import java.util.Scanner;

public class Tris {
  public static boolean DEBUG = true;
  private final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    var game = new Tris().chooseMode();
    if (game == null)
      return;
    var start = game.start();
    start.join();
  }

  private int getIntInput(int min, int max) {
    int response = 0;
    boolean ok = false;
    do {
      ok = scanner.hasNextInt();
      if (!ok) {
        System.out.println("Insert a number!");
        scanner.next();
        continue;
      }
      response = scanner.nextInt();
      ok = response >= min && response <= max;
      if (!ok)
        System.out.println("Insert a valid mode!");
    } while (!ok);
    scanner.nextLine();
    return response;
  }

  private Game chooseMode() {
    System.out.println("What mode do you want to play?");
    System.out.println("1) Singleplayer \n2) Local multiplayer \n3) Multiplayer (remote)");
    int response = getIntInput(1, 3);
    return switch (response) {
      case 1 -> chooseDifficulty();
      case 2 -> new LocalMultiplayerGame(3);
      case 3 -> chooseNetworkGame();
      default -> throw new AssertionError();
    };
  }

  private Game chooseDifficulty() {
    System.out.println("Ok, choose the difficulty");
    System.out.println("1) Normal 2) Hard 3) Impossible");
    int difficulty = getIntInput(1, 3);
    return switch (difficulty) {
      case 1 -> new SinglePlayerGame(3);
      case 2 -> new SinglePlayerGame(3, new MinimaxPlayer(1));
      case 3 -> new SinglePlayerGame(3, new MinimaxPlayer());
      default -> throw new AssertionError();
    };
  }

  private Game chooseNetworkGame() {
    System.out.println("Ok, choose the type of network game");
    System.out.println("1) Host 2) Connect to remote host");
    int choice = getIntInput(1, 2);
    if (choice == 1) {
      try {
        return getRemoteGame(new LocalNetworkRoom());
      } catch (UnknownHostException e) {
        e.printStackTrace();
        return null;
      }
    } else if (choice == 2) {
      System.out.print("Address: ");
      scanner.reset();
      String address = scanner.nextLine();
      System.out.println("Port: ");
      int port = getIntInput(0, Integer.MAX_VALUE);
      return getRemoteGame(new RemoteNetworkRoom(address, port));
    } else {
      throw new AssertionError();
    }
  }

  private Game getRemoteGame(NetworkRoom room) {
    var load = new LoadingAnimation();
    load.start();
    var game = room.startGame().join();
    load.stop();
    if (game.isEmpty()) {
      System.out.println("Couldn't start the local server");
      return null;
    }
    return game.get();
  }
}