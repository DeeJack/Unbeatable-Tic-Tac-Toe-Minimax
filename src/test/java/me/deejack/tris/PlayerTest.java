package me.deejack.tris;

import org.junit.Test;

import me.deejack.tris.players.LocalPlayer;
import me.deejack.tris.players.PlayerSymbol;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PlayerTest {
    @Test
    public void testHumanPlayer() {
        String input = "AsD\nx\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        var player = new LocalPlayer(0);
        player.askName();
        player.askSymbol();

        assertEquals(player.getName(), "AsD");
        assertNotEquals("asd", player.getName());
        assertEquals(player.getSymbol().getSymbol(), 'x');
        assertNotEquals(player.getSymbol().getSymbol(), 'y');
    }

    @Test
    public void testHumanPlayerInput() {
        var humanPlayer = new LocalPlayer("asd", new PlayerSymbol('x'), 0);
        
    }
}