package me.deejack.tris;

import me.deejack.tris.board.Board;
import me.deejack.tris.game.PlayerSymbol;

public class Tris {
    public static void main(String[] args) {
        var board = new Board(3);
        board.changeCellStatus(1, 1, new PlayerSymbol('x'));
        board.changeCellStatus(1, 2, new PlayerSymbol('x'));
        System.out.println(board.toString());
    }

    public boolean isWorking() {
        return true;
    }
}