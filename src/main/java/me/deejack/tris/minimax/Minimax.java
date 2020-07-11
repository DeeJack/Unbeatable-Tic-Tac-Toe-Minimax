package me.deejack.tris.minimax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import me.deejack.tris.board.Cell;
import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.Results;

/**
 * Find the best move to win the game (or at least achieve a draw)
 */
public class Minimax {
    private final byte[][] initialTable;
    private final int initialDepth;
    private final int playerIndex;

    public Minimax(Cell[][] table, int depth, int playerIndex) {
        this.playerIndex = playerIndex;
        this.initialDepth = depth;
        this.initialTable = convertBoard(table);
    }

    public Minimax(Cell[][] table, int playerIndex) {
        this(table, Integer.MAX_VALUE, playerIndex);
    }

    /**
     * Call the minimax algorithm
     * @return The best move possible
     */
    public Cell getBestMove() {
        var bestCell = minimax(initialDepth, initialTable, true);
        if (bestCell.getValue() == null)
            throw new IllegalStateException(
                    "No action found, minimax error (bestCell = null, value = " + bestCell.getValue() + ")");
        return bestCell.getValue();
    }

    /**
     * Find the best move for a player
     * @param depth The depth until which you want to check, you can use Integer.MAX_VALUE
     * @param table The current table
     * @param maxPlayer true if you want the best move for the maximizing player, false otherwise
     * @return The best move for the player
     */
    public SimpleEntry<Integer, Cell> minimax(int depth, byte[][] table, boolean maxPlayer) {
        var result = DefaultGameLogic.checkAll(table);
        if (result != Results.NONE) {
            return new SimpleEntry<Integer, Cell>(evaluateMove(result, table), null);
        }
        if (maxPlayer)
            return getMax(table, depth);
        else
            return getMin(table, depth);
    }

    /**
     * Get the maximum score possible for the O player (maximizing player)
     * @param table The current table
     * @param depth The current depth
     * @return The best move for the maximing player
     */
    private SimpleEntry<Integer, Cell> getMax(byte[][] table, int depth) {
        var maxValue = Integer.MIN_VALUE;
        var copiedTable = copyBoard(table);
        var emptyCells = getEmptyCells(copiedTable);
        var maxCell = emptyCells.get(0);
        for (var emptyCell : emptyCells) {
            var newTable = copyBoard(copiedTable);
            newTable[emptyCell.getRow()][emptyCell.getColumn()] = -1;
            var newValue = minimax(depth - 1, newTable, false);
            if (newValue.getKey() > maxValue) {
                maxValue = newValue.getKey();
                maxCell = emptyCell;
            }
        }
        return new SimpleEntry<Integer, Cell>(maxValue, maxCell);
    }

    /**
     * Get the minimum score possible, which is the best for the X player (minimizing player)
     * @param table The current table
     * @param depth The current depth
     * @return The cell with the minimum score
     */
    private SimpleEntry<Integer, Cell> getMin(byte[][] table, int depth) {
        var copiedTable = copyBoard(table);
        var emptyCells = getEmptyCells(copiedTable);
        var minValue = Integer.MAX_VALUE;
        var minCell = emptyCells.get(0);
        for (var emptyCell : emptyCells) {
            var newTable = copyBoard(copiedTable);
            newTable[emptyCell.getRow()][emptyCell.getColumn()] = 1;
            var newValue = minimax(depth - 1, newTable, true);
            if (newValue.getKey() < minValue) {
                minValue = newValue.getKey();
                minCell = emptyCell;
            }
        }
        return new SimpleEntry<Integer, Cell>(minValue, minCell);
    }

    /**
     * Convert a 2D array of the Cell class to a 2D array of bytes,
     * The number are: 0 if empty, 1 if player 0, -1 if player 1
     * @param cells The array of Cells
     * @return The converted byte array
     */
    private byte[][] convertBoard(Cell[][] cells) {
        byte[][] table = new byte[cells.length][cells.length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                byte value = 0;
                if (!cells[i][j].isEmpty())
                    value = (cells[i][j].getPlayer() == this.playerIndex ? (byte) -1 : 1);
                table[i][j] = value;
            }
        }
        return table;
    }

    /**
     * Calculate the value of the table
     * The value is calculated by multiplying the number of empty cells and 
     * [-1 if it's a loss, 1 if it's a win, 0 if it's a draw] 
     * @param result The result of the table (win, loss, draw)
     * @param table The table
     * @return The value of the table
     */
    private int evaluateMove(Results result, byte[][] table) {
        var resultValue = 0;
        if (result != Results.DRAW && result != Results.NONE) {
            resultValue = (result == Results.WIN_P1 ? -1 : 1);
        }
        int emptyCells = getEmptyCells(table).size() + 1;

        int value = resultValue * emptyCells;
        return value;
    }

    /**
     * Get the empty cells in the 2D array (the cells can be 1, -1 or 0 in case they are empty)
     * @param cells The 2D array of bytes
     * @return The list of the empty cells
     */
    public List<Cell> getEmptyCells(byte[][] cells) {
        var table = new ArrayList<Cell>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (cells[i][j] == 0)
                    table.add(new Cell(i, j));
            }
        }
        return table;
    }

    /**
     * Create a copy of a 2 dimensional array of bytes
     * @param board The 2D byte array
     * @return the copy of the array
     */
    private byte[][] copyBoard(byte[][] board) {
        return Arrays.stream(board).map(byte[]::clone).toArray(byte[][]::new);
    }
}