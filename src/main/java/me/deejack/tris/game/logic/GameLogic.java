package me.deejack.tris.game.logic;

public interface GameLogic {
    public Results checkWin(int modifiedRow, int modifiedColumn, int player);
}