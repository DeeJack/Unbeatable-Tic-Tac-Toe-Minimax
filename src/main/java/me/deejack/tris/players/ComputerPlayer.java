package me.deejack.tris.players;

import java.util.Random;

public class ComputerPlayer extends DefaultPlayer {
    private PlayerSymbol symbol = new PlayerSymbol('O');
    private String name;

    public ComputerPlayer(PlayerSymbol symbol, String name) {
        super(1);
        this.symbol = symbol;
        this.name = name;
    }

    public ComputerPlayer() {
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