package me.deejack.tris.game.logic;

public interface GameLogic {
  Results checkWin(int modifiedRow, int modifiedColumn, int player);
}