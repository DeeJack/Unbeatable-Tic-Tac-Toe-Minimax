package me.deejack.tris.players;

public interface Player {
    public PlayerSymbol getSymbol();

    public String getName();

    public int getIntInput(String question);

    public String getInput(String question);

    public int getWins();

    public int getTotalPlays();

    public void sendMessage(String message);
}