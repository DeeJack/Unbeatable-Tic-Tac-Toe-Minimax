package me.deejack.tris.board;

import me.deejack.tris.players.PlayerSymbol;

public class Cell {
  private final int row;
  private final int column;
  private boolean empty = true;
  private PlayerSymbol symbol;
  private int player = -1;

  public Cell(int row, int column) {
    if (row < 0)
      throw new IllegalArgumentException("Negative row");
    if (column < 0)
      throw new IllegalArgumentException("Negative column");
    this.row = row;
    this.column = column;
  }

  public void setSymbol(PlayerSymbol symbol, int player) {
    empty = false;
    this.symbol = symbol;
    this.player = player;
  }

  public int getPlayer() {
    return player;
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