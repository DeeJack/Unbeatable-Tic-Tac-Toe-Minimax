package me.deejack.tris.game;

import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.LocalPlayer;
import me.deejack.tris.players.Player;

public class LocalMultiplayerGame extends Game {
    private final LocalPlayer[] players = new LocalPlayer[2];

    public LocalMultiplayerGame(int columns) {
        super(new LocalPlayer(0), new LocalPlayer(1), new DefaultGameLogic(columns), columns);
        players[0] = (LocalPlayer) getPlayers()[0];
        players[1] = (LocalPlayer) getPlayers()[1];
    }

    @Override
    protected void beforeStart() {
        System.out.println("------------P1---------------");
        players[0].askName();
        players[0].askSymbol();
        System.out.println("------------P2---------------");
        players[1].askName();
        players[1].askSymbol();
    }

    @Override
    protected void beforeTurn() {

    }

    @Override
    protected void onFinish(Results result) {
        System.out.println(result);
    }

    @Override
    protected void afterTurn() {
        System.out.println(getBoard().toString());
    }
}