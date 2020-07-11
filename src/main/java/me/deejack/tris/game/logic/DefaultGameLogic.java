package me.deejack.tris.game.logic;

import java.util.Arrays;

import me.deejack.tris.board.Cell;

public class DefaultGameLogic implements GameLogic {
    private final byte[] counters;
    private final int columns;
    private int total = 0;

    public DefaultGameLogic(int columns) {
        this.counters = new byte[columns * 2 + 2];
        this.columns = columns;
        Arrays.fill(counters, (byte) 0);
    }

    public DefaultGameLogic(int columns, Cell[][] board) {
        this(columns);

    }

    @Override
    public Results checkWin(int modifiedRow, int modifiedColumn, int player) {
        total++;
        int playerValue = player == 0 ? 1 : -1;
        Results winningPlayer = (player == 0 ? Results.WIN_P1 : Results.WIN_P2);

        counters[modifiedRow] += playerValue;
        counters[columns + modifiedColumn] += playerValue;
        if (modifiedColumn + modifiedRow == columns - 1) // Inverse oblique win
            counters[columns * 2 + 1] += playerValue;
        if (modifiedColumn == modifiedRow)
            counters[columns * 2] += playerValue;

        if (Math.abs(counters[modifiedRow]) == columns)
            return winningPlayer;
        if (Math.abs(counters[columns + modifiedColumn]) == columns)
            return winningPlayer;
        if (Math.abs(counters[columns * 2]) == columns || Math.abs(counters[columns * 2 + 1]) == columns)
            return winningPlayer;

        if (total == columns * columns)
            return Results.DRAW;
        return Results.NONE;
    }

    public static Results checkAll(byte[][] board) {
        var logic = new DefaultGameLogic(board.length);
        var result = Results.NONE;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0)
                    result = logic.checkWin(i, j, board[i][j] == 1 ? 0 : -1);
            }
        }
        return result;
    }
}