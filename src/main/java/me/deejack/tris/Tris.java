package me.deejack.tris;

import java.io.Console;
import java.util.Scanner;

import me.deejack.tris.game.Game;
import me.deejack.tris.game.modes.LocalMultiplayerGame;
import me.deejack.tris.game.modes.NetworkMultiplayerGame;
import me.deejack.tris.game.modes.SinglePlayerGame;
import me.deejack.tris.players.types.MinimaxPlayer;

public class Tris {
    public static boolean DEBUG = true;
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var game = new Tris().chooseMode();
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
        return response;
    }

    private Game chooseMode() {
        System.out.println("What mode do you want to play?");
        System.out.println("1) Singleplayer \n2) Local multiplayer \n3) Network mulitplayer");
        int response = getIntInput(1, 3);
        switch (response) {
            case 1:
                return chooseDifficulty();
            case 2:
                return new LocalMultiplayerGame(3);
            case 3:
                return new NetworkMultiplayerGame(3);
            default:
                throw new AssertionError();
        }
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
}