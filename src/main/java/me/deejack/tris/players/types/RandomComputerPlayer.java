package me.deejack.tris.players.types;

import java.util.Random;

import me.deejack.tris.players.DefaultPlayer;
import me.deejack.tris.players.PlayerSymbol;

public class RandomComputerPlayer extends DefaultPlayer {
    private PlayerSymbol symbol = new PlayerSymbol('O');
    private String name;

    public RandomComputerPlayer(PlayerSymbol symbol, String name) {
        super(1);
        this.symbol = symbol;
        this.name = name;
    }

    public RandomComputerPlayer() {
        this(new PlayerSymbol('O'), "PC0");
    }

    @Override
    public PlayerSymbol getSymbol() {
        return symbol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getIntInput(String question) {
        return new Random().nextInt(10);
    }

    @Override
    public String getInput(String question) {
        return "";
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
    
}