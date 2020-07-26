package me.deejack.tris;

import org.junit.Test;

import me.deejack.tris.players.types.LocalPlayer;

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
        player.askName().join();
        //player.askSymbol();

        assertEquals("AsD", player.getName());
        assertNotEquals("asd", player.getName());
        assertEquals('X', player.getSymbol().getSymbol());
        assertNotEquals('Y', player.getSymbol().getSymbol());
    }

    @Test
    public void testHumanPlayerInput() {
        
    }
}