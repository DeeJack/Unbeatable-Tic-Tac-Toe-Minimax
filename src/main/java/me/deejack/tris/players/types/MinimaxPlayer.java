package me.deejack.tris.players.types;

import me.deejack.tris.players.DefaultPlayer;
import me.deejack.tris.players.PlayerSymbol;

public class MinimaxPlayer extends DefaultPlayer {
    private final int depth;

    public MinimaxPlayer(int depth) {
        super(1);
        this.depth = depth;
    }

    public MinimaxPlayer() {
        this(Integer.MAX_VALUE);
    }

    @Override
    public PlayerSymbol getSymbol() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getIntInput(String question) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getInput(String question) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendMessage(String message) {
        // TODO Auto-generated method stub

    }
    
}