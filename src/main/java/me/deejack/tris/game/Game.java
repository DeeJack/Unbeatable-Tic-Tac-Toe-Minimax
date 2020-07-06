package me.deejack.tris.game;

import me.deejack.tris.board.Board;
import me.deejack.tris.game.logic.GameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.Player;

public abstract class Game {
    private final Board board;
    private final Player[] players = new Player[2];
    private final GameLogic logic;
    private int round = 0;
    private boolean finished = false;
    private Results result = Results.NONE;

    public Game(Player firstPlayer, Player secondPlayer, GameLogic logic, int columns) {
        if (firstPlayer == null || secondPlayer == null)
            throw new IllegalArgumentException("Player must not be null");
        players[0] = firstPlayer;
        players[1] = secondPlayer;
        this.logic = logic;
        this.board = new Board(columns);
    }

    protected abstract void beforeStart();

    protected abstract void beforeTurn();

    protected void onTurn() {
        var currentPlayer = players[round % 2];
        currentPlayer.sendMessage("It's your turn, " + currentPlayer.getName());
        int row;
        int column; 
        boolean result;
        do {
            row = currentPlayer.getIntInput("Row: ");
            column = currentPlayer.getIntInput("Column: ");
            result = board.changeCellStatus(row, column, currentPlayer.getSymbol());
            if (!result)
                currentPlayer.sendMessage("Incorrect data, please try again");
        } while (!result);
        var gameResult = logic.checkWin(row, column, round % 2);
        if (gameResult != Results.NONE) {
            onFinish(gameResult);
            this.result = gameResult;
            finished = true;
        }
        round++;
    }

    protected abstract void afterTurn();

    public void start() {
        beforeStart();
        if (players[0].getSymbol().equals(players[1].getSymbol())) {
            players[0].sendMessage("You can't have the same symbol!");
            players[1].sendMessage("You can't have the same symbol!");
            return;
        }
        do {
            beforeTurn();
            onTurn();
            afterTurn();
        } while (!finished);
    }

    protected abstract void onFinish(Results result);

    public void afterFinish(Results result) {
        int playerOne = 0; // draw
        switch (result) {
            case WIN_P1:
                playerOne = 1;
                break;
            case WIN_P2:
                playerOne = -1;
                break;
            default:
                throw new IllegalStateException();
        }
        players[0].addResult(playerOne);
        players[1].addResult(playerOne * -1);
    }

    protected Player[] getPlayers() {
        return players;
    }
    
    public int getRound() {
        return round;
    }
    
    public Board getBoard() {
        return board;
    }

    public Results getResult() {
        return result;
    }
}