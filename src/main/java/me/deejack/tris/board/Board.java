package me.deejack.tris.board;

import me.deejack.tris.players.PlayerSymbol;

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

    private void createEmptyBoard() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean changeCellStatus(int row, int column, PlayerSymbol symbol) {
        if (row < 0 || column < 0 || 
            row > columns - 1 || column > columns - 1)
            return false;
        if (!cells[row][column].isEmpty())
            return false;
        if (symbol == null)
            return false;
        cells[row][column].setSymbol(symbol);
        return true;
    }

    public Cell[][] getCells() {
        return cells.clone();
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (var rows : cells) {
            for (var cell : rows) {
                builder.append(
                    cell.isEmpty() ? 
                        ' ' : cell.getSymbol()
                ).append(" | ");
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append("\n");
        }
        return builder.toString();
    }
}