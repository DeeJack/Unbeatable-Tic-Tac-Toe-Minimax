package me.deejack.tris.minimax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import me.deejack.tris.board.Cell;
import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.GameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.PlayerSymbol;

public class Minimax {
    private GameLogic logic;
    private final PlayerSymbol symbol;

    public Minimax(Cell[][] table, PlayerSymbol symbol) {
        this.logic = new DefaultGameLogic(3, table);
        this.symbol = symbol;
    }
    
    public byte[][] convertBoard(Cell[][] cells) {
        byte[][] table = new byte[cells.length][cells.length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                byte value = 0;
                if (!cells[i][j].isEmpty())
                    value = cells[i][j].getSymbol().equals(symbol) ? (byte) -1 : 1;
                table[i][j] = value;
            }
        }
        return table;
    }

    private int evaluateMove(Results result, byte[][] cells) {
        var resultValue = 0;
        if (result != Results.DRAW && result != Results.NONE) {
            resultValue = (result == Results.WIN_P1 ? -1 : 1);
        }
        int emptyCells = getEmptyCells(cells).size() + 1;
        
        int value = resultValue * emptyCells;
        return value;
    }

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

    public Cell getBestMove(Cell[][] table) {
        //var result = minimax(table.length * table.length, null, convertBoard(table), false);
        //return result.getValue();
        return minimax(table.length * table.length, convertBoard(table), true).getValue();
    }

    public SimpleEntry<Integer, Cell> minimax(int depth, byte[][] table, boolean maxPlayer) {
        var result = DefaultGameLogic.checkAll(table);
        if (result != Results.NONE) {
            return new SimpleEntry<Integer, Cell>(evaluateMove(result, table), null);
        }
        if (maxPlayer) {
            var asd = getMax(table, depth); 
            if (depth == 9)
                System.out.println(asd);
            return asd;
        } else { 
            var asd = getMin(table, depth); 
            if (depth == 8)
                System.out.println(asd);
            return asd;
        }
    }

    private byte[][] copyBoard(byte[][] board) {
        return Arrays.stream(board).map(byte[]::clone).toArray(byte[][]::new);
    }

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

    private SimpleEntry<Integer, Cell> getMin(byte[][] table, int depth) {
        var minValue = Integer.MAX_VALUE;
        var copiedTable = copyBoard(table);
        var emptyCells = getEmptyCells(copiedTable);
        var minCell = emptyCells.get(0);
        for (var emptyCell : emptyCells) {
            var newTable = copyBoard(copiedTable);
            if (emptyCell.getRow() == 2 && emptyCell.getColumn() == 0)
                System.out.println("asd");
            newTable[emptyCell.getRow()][emptyCell.getColumn()] = 1;
            var newValue = minimax(depth - 1, newTable, true);
            if (newValue.getKey() < minValue) {
                minValue = newValue.getKey();
                minCell = emptyCell;
            };
        }
        return new SimpleEntry<Integer, Cell>(minValue, minCell);
    }
}