package me.deejack.tris.players;

public interface Player {
    public PlayerSymbol getSymbol();

    public String getName();

    public int getIntInput(String question);

    public String getInput(String question);

    public int getWins();

    public int getLosses();

    public int getDraws();

    public int getIndex();

    /**
     * -1 = loss
     * 0 = draw
     * 1 = win
     */
    public void addResult(int result);

    public void sendMessage(String message);
}