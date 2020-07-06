package me.deejack.tris.game.logic;

import me.deejack.tris.board.Board;

public interface GameLogic {
    public Results checkWin(int modifiedRow, int modifiedColumn, int player);
}