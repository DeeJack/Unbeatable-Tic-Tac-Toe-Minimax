package me.deejack.tris;

import org.junit.Test;

import me.deejack.tris.game.logic.Results;
import me.deejack.tris.testImplementations.GameTest;
import me.deejack.tris.testImplementations.TestComputerPlayer;

import static org.junit.Assert.*;

public class WinTest {
    @Test
    public void testRowWin() {
        var playerOne = new TestComputerPlayer('X', "PC0");
        var playerTwo = new TestComputerPlayer('O', "PC1");
        playerOne.addInputs(0, 0, 0, 1, 0, 2, 0);
        playerTwo.addInputs(1, 1, 1, 0, 2, 0, 0);
        var game = new GameTest(playerOne, playerTwo);
        game.start();
        assertEquals(Results.WIN_P1, game.getResult());
    }

    @Test
    public void testColumnWin() {
        var playerOne = new TestComputerPlayer('X', "PC0");
        var playerTwo = new TestComputerPlayer('O', "PC1");
        playerOne.addInputs(1, 1, 0, 1, 0, 2);
        playerTwo.addInputs(0, 0, 1, 0, 2, 0);
        var game = new GameTest(playerOne, playerTwo);
        game.start();
        assertEquals(Results.WIN_P2, game.getResult());
    }

    @Test
    public void testObliqueWin() {
        var playerOne = new TestComputerPlayer('X', "PC0");
        var playerTwo = new TestComputerPlayer('O', "PC1");
        playerOne.addInputs(1, 1, 0, 0, 2, 2);
        playerTwo.addInputs(0, 1, 1, 0, 2, 1);
        var game = new GameTest(playerOne, playerTwo);
        game.start();
        assertEquals(Results.WIN_P1, game.getResult());
    }

    @Test
    public void testInverseObliqueWin() {
        var playerOne = new TestComputerPlayer('X', "PC0");
        var playerTwo = new TestComputerPlayer('O', "PC1");
        playerOne.addInputs(1, 1, 0, 2, 2, 0);
        playerTwo.addInputs(0, 1, 1, 0, 2, 1);
        var game = new GameTest(playerOne, playerTwo);
        game.start();
        assertEquals(Results.WIN_P1, game.getResult());
    }

    @Test
    public void testDraw() {
        var playerOne = new TestComputerPlayer('X', "PC0");
        var playerTwo = new TestComputerPlayer('O', "PC1");
        playerOne.addInputs(0, 0, 0, 1, 1, 2, 1, 1, 2, 0);
        playerTwo.addInputs(0, 2, 1, 0, 2, 2, 2, 1);
        var game = new GameTest(playerOne, playerTwo);
        game.start();
        assertEquals(Results.DRAW, game.getResult());
    }
}