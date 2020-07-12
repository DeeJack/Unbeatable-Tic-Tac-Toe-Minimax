package me.deejack.tris.board;

import java.util.Arrays;

import me.deejack.tris.players.Player;

public class Board {
    private Cell[][] cells;
    private final int columns;

    public Board(int columns) {
        if (columns < 3)
            throw new IllegalArgumentException("At least 3 columns!");
        cells = new Cell[columns][columns];
        this.columns = columns;
        createEmptyBoard();
    }

    public Board(Cell[][] cells) {
        this.cells = copyBoard(cells);
        this.columns = cells.length;
    }

    private void createEmptyBoard() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean changeCellStatus(int row, int column, Player player) {
        if (row < 0 || column < 0 || row > columns - 1 || column > columns - 1)
            return false;
        if (!cells[row][column].isEmpty())
            return false;
        if (player.getSymbol() == null)
            return false;
        cells[row][column].setSymbol(player.getSymbol(), player.getIndex());
        return true;
    }

    public Cell[][] getCells() {
        return cells.clone();
    }

    public int getColumns() {
        return columns;
    }

    public Cell[] getEmptyCells() {
        return Arrays.stream(cells).flatMap((Cell[] row) -> Arrays.stream(row)).filter(cell -> cell.isEmpty())
                .toArray(Cell[]::new);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (var rows : cells) {
            for (var cell : rows) {
                builder.append(cell.isEmpty() ? ' ' : cell.getSymbol()).append(" | ");
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append("\n");
        }
        return builder.toString();
    }

    public Board deepCopy() {
        return new Board(cells);
    }

    /**
     * Create a copy of a 2 dimensional array of bytes
     * 
     * @param board
     *                  The 2D byte array
     * @return the copy of the array
     */
    private Cell[][] copyBoard(Cell[][] board) {
        return Arrays.stream(board).map(Cell[]::clone).toArray(Cell[][]::new);
    }
}