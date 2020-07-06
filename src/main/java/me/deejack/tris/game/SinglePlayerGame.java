package me.deejack.tris.game;

import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.ComputerPlayer;
import me.deejack.tris.players.LocalPlayer;

public class SinglePlayerGame extends Game {
    private final LocalPlayer player;

    public SinglePlayerGame(int columns) {
        super(new LocalPlayer(), new ComputerPlayer(), new DefaultGameLogic(columns), columns);
        this.player = (LocalPlayer) super.getPlayers()[0];
    }

    @Override
    public void beforeStart() {
        player.askName();
        player.askSymbol();
    }

    @Override
    public void beforeTurn() {

    }

    @Override
    public void onFinish(Results result) { 
        System.out.println(result);
    }

    @Override
    protected void afterTurn() {
        System.out.println(getBoard().toString());
    }
}