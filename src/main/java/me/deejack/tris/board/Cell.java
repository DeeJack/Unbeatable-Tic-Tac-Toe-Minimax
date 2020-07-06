package me.deejack.tris.board;

import me.deejack.tris.players.PlayerSymbol;

public class Cell {
    private boolean empty = true;
    private PlayerSymbol symbol;
    private final int row;
    private final int column;

    public Cell(int row, int column) {
        if (row < 0)
            throw new IllegalArgumentException("Negative row");
        if (column < 0)
            throw new IllegalArgumentException("Negative column");
        this.row = row;
        this.column = column;
    }

    public void setSymbol(PlayerSymbol symbol) {
        empty = false;
        this.symbol = symbol;
    }

    public PlayerSymbol getSymbol() {
        return symbol;
    }

    public boolean isEmpty() {
        return empty;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Cell && 
            ((Cell) obj).row == this.row && 
            ((Cell) obj).column == this.column &&
            (((Cell) obj).isEmpty() && this.isEmpty() ||
                ((Cell) obj).symbol.equals(this.symbol));
    }
}