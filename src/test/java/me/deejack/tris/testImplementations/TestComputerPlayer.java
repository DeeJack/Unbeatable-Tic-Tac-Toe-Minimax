package me.deejack.tris.testImplementations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import me.deejack.tris.players.ComputerPlayer;
import me.deejack.tris.players.PlayerSymbol;

public class TestComputerPlayer extends ComputerPlayer {
    private Queue<Integer> inputs = new LinkedList<Integer>();

    public TestComputerPlayer(char symbol, String name) {
        super(new PlayerSymbol(symbol), name);
    }

    public void addInputs(Integer... inputsToAdd) {
        inputs.addAll(Arrays.asList(inputsToAdd));
    }

    @Override
    public int getIntInput(String question) {
        var input = inputs.poll();
        System.out.println("Choosen: " + input);
        return input;
    }
}