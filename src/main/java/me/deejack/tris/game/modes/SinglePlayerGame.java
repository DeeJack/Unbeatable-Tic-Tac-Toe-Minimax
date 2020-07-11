package me.deejack.tris.game.modes;

import me.deejack.tris.game.Game;
import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.types.RandomComputerPlayer;
import me.deejack.tris.players.types.LocalPlayer;

public class SinglePlayerGame extends Game {
    private final LocalPlayer player;

    public SinglePlayerGame(int columns) {
        super(new LocalPlayer(0), new RandomComputerPlayer(), new DefaultGameLogic(columns), columns);
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