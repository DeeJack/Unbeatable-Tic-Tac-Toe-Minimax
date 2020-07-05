package me.deejack.tris.game;

public class PlayerSymbol {
    private final char symbol;

    public PlayerSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PlayerSymbol &&
            ((PlayerSymbol) obj).symbol == this.symbol;
    }
}