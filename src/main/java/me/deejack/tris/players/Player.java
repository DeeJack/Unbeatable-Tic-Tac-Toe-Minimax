package me.deejack.tris.players;

import java.util.concurrent.CompletableFuture;

import me.deejack.tris.board.Board;
import me.deejack.tris.board.Cell;

public interface Player {
    public PlayerSymbol getSymbol();

    public void setSymbol(PlayerSymbol symbol);

    public String getName();

    public int getWins();

    public int getLosses();

    public int getDraws();

    public int getIndex();

    /**
     * -1 = loss
     * 0 = draw
     * 1 = win
     */
    public void addResult(int result);

    public CompletableFuture<Void> sendMessage(String message);

    public CompletableFuture<String> getInput(String question);

    public CompletableFuture<Cell> getNextMove(Board board);
}