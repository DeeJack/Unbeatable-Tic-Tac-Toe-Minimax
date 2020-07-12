package me.deejack.tris.testImplementations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

import me.deejack.tris.players.types.RandomComputerPlayer;
import me.deejack.tris.board.Board;
import me.deejack.tris.board.Cell;
import me.deejack.tris.players.PlayerSymbol;

import static com.ea.async.Async.await;
import static java.util.concurrent.CompletableFuture.completedFuture;

public class TestComputerPlayer extends RandomComputerPlayer {
    private Queue<Integer> inputs = new LinkedList<Integer>();

    public TestComputerPlayer(char symbol, String name) {
        super(new PlayerSymbol(symbol), name);
    }

    public void addInputs(Integer... inputsToAdd) {
        inputs.addAll(Arrays.asList(inputsToAdd));
    }

    @Override
    public CompletableFuture<Cell> getNextMove(Board board) {
        var row = inputs.poll();
        var column = inputs.poll();
        System.out.println("Choosen: " + row + " - " + column);
        return completedFuture(new Cell(row, column));
    }
}