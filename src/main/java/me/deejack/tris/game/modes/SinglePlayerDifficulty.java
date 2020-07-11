package me.deejack.tris.game.modes;

import me.deejack.tris.players.Player;
import me.deejack.tris.players.types.*;

public enum SinglePlayerDifficulty {
    EASY,
    NORMAL,
    HARD;
    
    Player getPlayer() {
        switch (this) {
            case EASY:
                return new RandomComputerPlayer();
            case NORMAL:
                return new MinimaxPlayer(1);
            case HARD:
                return new MinimaxPlayer();
            default:
                throw new AssertionError("It shoudn't be happening, non-existent difficulty");
        }
    }
}