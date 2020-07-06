package me.deejack.tris;

import me.deejack.tris.game.LocalMultiplayerGame;
import me.deejack.tris.game.SinglePlayerGame;

public class Tris {
    public static void main(String[] args) {
        // var board = new Board(3);
        // board.changeCellStatus(1, 1, new PlayerSymbol('x'));
        // board.changeCellStatus(1, 2, new PlayerSymbol('x'));
        // System.out.println(board.toString());
        // var player = new LocalPlayer();
        // player.askName();
        // player.askSymbol();
        var game = new LocalMultiplayerGame(3);
        game.start();
    }

    public boolean isWorking() {
        return true;
    }
}