package me.deejack.tris.game.modes;

import me.deejack.tris.game.Game;
import me.deejack.tris.game.logic.DefaultGameLogic;
import me.deejack.tris.game.logic.Results;
import me.deejack.tris.players.PlayerSymbol;
import me.deejack.tris.players.types.LocalPlayer;

import static java.util.concurrent.CompletableFuture.completedFuture;

import java.util.concurrent.CompletableFuture;

public class LocalMultiplayerGame extends Game {
    private final LocalPlayer[] players = new LocalPlayer[2];

    public LocalMultiplayerGame(int columns) {
        super(new LocalPlayer(0), new LocalPlayer(1), new DefaultGameLogic(columns), columns);
        players[0] = (LocalPlayer) getPlayers()[0];
        players[1] = (LocalPlayer) getPlayers()[1];
    }

    @Override
    protected CompletableFuture<Void> beforeStart() {
        System.out.println("------------P1---------------");
        players[0].askName().join();
        players[0].setSymbol(new PlayerSymbol('X'));
        //players[0].askSymbol();
        System.out.println("------------P2---------------");
        players[1].askName().join();
        players[0].setSymbol(new PlayerSymbol('O'));
        //players[1].askSymbol();
        return completedFuture(null);
    }

    @Override
    protected CompletableFuture<Void> beforeTurn() {
        return completedFuture(null);
    }

    @Override
    protected CompletableFuture<Void> onFinish(Results result) {
        System.out.println(result);
        return completedFuture(null);
    }

    @Override
    protected CompletableFuture<Void> afterTurn() {
        System.out.println(getBoard().toString());
        return completedFuture(null);
    }
}