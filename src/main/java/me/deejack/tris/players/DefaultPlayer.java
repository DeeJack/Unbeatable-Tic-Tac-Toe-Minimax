package me.deejack.tris.players;

public abstract class DefaultPlayer implements Player {
    private int wins = 0;
    private int losses = 0;
    private int draws = 0;
    private int index = 0;
    private String name;
    private PlayerSymbol symbol;

    public DefaultPlayer(int index) {
        this.index = index;
    }

    @Override
    public void addResult(int result) {
        switch (result) {
            case -1:
                losses++;
                break;
            case 0:
                draws++;
                break;
            case 1:
                wins++;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public int getDraws() {
        return draws;
    }

    @Override
    public int getLosses() {
        return losses;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PlayerSymbol getSymbol() {
        return symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(PlayerSymbol symbol) {
        this.symbol = symbol;
    }
}