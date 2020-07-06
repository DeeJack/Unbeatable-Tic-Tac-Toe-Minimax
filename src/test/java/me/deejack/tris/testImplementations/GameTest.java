package me.deejack.tris.testImplementations;

import me.deejack.tris.game.Game;
import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.GameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.Player;

public class GameTest extends Game {

    public GameTest(Player firstPlayer, Player secondPlayer) {
        super(firstPlayer, secondPlayer, new DefaultGameLogic(3), 3);
    }

    @Override
    protected void beforeStart() {
    }

    @Override
    protected void beforeTurn() {
    }

    @Override
    protected void afterTurn() {
        System.out.println(getBoard().toString());
    }

    @Override
    protected void onFinish(Results result) {
        
    }
    
}