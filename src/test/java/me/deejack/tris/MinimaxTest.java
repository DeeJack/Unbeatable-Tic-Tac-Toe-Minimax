package me.deejack.tris;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import me.deejack.tris.board.Cell;
import me.deejack.tris.minimax.Minimax;
import me.deejack.tris.players.PlayerSymbol;
import me.deejack.tris.testImplementations.GameTest;
import me.deejack.tris.testImplementations.TestComputerPlayer;

public class MinimaxTest {
    @Test
    public void firstTest() {
        var expected = new Cell[][] { new Cell[] { new Cell(0, 0), new Cell(0, 1), new Cell(0, 2) },
                new Cell[] { new Cell(1, 0), new Cell(1, 1), new Cell(1, 2) },
                new Cell[] { new Cell(2, 0), new Cell(2, 1), new Cell(2, 2) }, };
        
        expected[0][0].setSymbol(new PlayerSymbol('x'), 0);
        expected[1][0].setSymbol(new PlayerSymbol('x'), 0);
        expected[0][2].setSymbol(new PlayerSymbol('x'), 0);
        expected[0][1].setSymbol(new PlayerSymbol('o'), 1);
        expected[1][1].setSymbol(new PlayerSymbol('o'), 1);
            
        var minimax = new Minimax(expected, 9, 1);
        var bestCell = minimax.getBestMove().join();
        System.out.println(bestCell.getRow() + " - " + bestCell.getColumn());
        assertEquals(new Cell(2, 1), bestCell);
    }

    @Test
    public void secondTest() {
        var expected = new Cell[][] { new Cell[] { new Cell(0, 0), new Cell(0, 1), new Cell(0, 2) },
                new Cell[] { new Cell(1, 0), new Cell(1, 1), new Cell(1, 2) },
                new Cell[] { new Cell(2, 0), new Cell(2, 1), new Cell(2, 2) }, };
        
        expected[0][0].setSymbol(new PlayerSymbol('x'), 0);
        expected[1][0].setSymbol(new PlayerSymbol('x'), 0);
        expected[0][2].setSymbol(new PlayerSymbol('x'), 0);
        expected[0][1].setSymbol(new PlayerSymbol('o'), 1);
        expected[1][2].setSymbol(new PlayerSymbol('o'), 1);
            
        var minimax = new Minimax(expected, 1);
        var bestCell = minimax.getBestMove().join();
        System.out.println(bestCell.getRow() + " - " + bestCell.getColumn());
        assertEquals(new Cell(2, 0), bestCell);
    }

    @Test
    public void thirdTest() {
        var expected = new Cell[][] { new Cell[] { new Cell(0, 0), new Cell(0, 1), new Cell(0, 2) },
                new Cell[] { new Cell(1, 0), new Cell(1, 1), new Cell(1, 2) },
                new Cell[] { new Cell(2, 0), new Cell(2, 1), new Cell(2, 2) }, };
        
        expected[0][0].setSymbol(new PlayerSymbol('x'), 0);
        expected[0][2].setSymbol(new PlayerSymbol('x'), 0);
        expected[2][2].setSymbol(new PlayerSymbol('x'), 0);
        expected[0][1].setSymbol(new PlayerSymbol('o'), 1);
        expected[1][2].setSymbol(new PlayerSymbol('o'), 1);
            
        var minimax = new Minimax(expected, 1);
        var bestCell = minimax.getBestMove().join();
        System.out.println(bestCell.getRow() + " - " + bestCell.getColumn());
        assertEquals(new Cell(1, 1), bestCell);
    }

    @Test
    public void fourthTest() {
        var expected = new Cell[][] { new Cell[] { new Cell(0, 0), new Cell(0, 1), new Cell(0, 2) },
                new Cell[] { new Cell(1, 0), new Cell(1, 1), new Cell(1, 2) },
                new Cell[] { new Cell(2, 0), new Cell(2, 1), new Cell(2, 2) }, };
        
        expected[0][0].setSymbol(new PlayerSymbol('x'), 0);
            
        var minimax = new Minimax(expected, 1);
        var bestCell = minimax.getBestMove().join();
        System.out.println(bestCell.getRow() + " - " + bestCell.getColumn());
        assertEquals(new Cell(1, 1), bestCell);
    }
}