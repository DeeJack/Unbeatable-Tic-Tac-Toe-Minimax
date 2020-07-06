package me.deejack.tris.game;

import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.Player;

public class LocalMultiplayerGame extends Game {

    public LocalMultiplayerGame(Player firstPlayer, Player secondPlayer, int columns) {
        super(firstPlayer, secondPlayer, new DefaultGameLogic(columns), columns);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void beforeStart() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void beforeTurn() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onFinish(Results results) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void afterTurn() {
        // TODO Auto-generated method stub

    }
    
}