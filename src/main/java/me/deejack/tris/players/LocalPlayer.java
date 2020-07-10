package me.deejack.tris.players;

import java.util.Scanner;

public class LocalPlayer extends DefaultPlayer {
    private final Scanner scanner = new Scanner(System.in);
    private String name;
    private PlayerSymbol symbol;

    public LocalPlayer(int index) {
        this("Unknown", new PlayerSymbol('?'), index);
    }

    public LocalPlayer(String name, PlayerSymbol symbol, int index) {
        super(index);
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public PlayerSymbol getSymbol() {
        return symbol;
    }

    @Override
    public String getInput(String question) {
        System.out.print(question + " ");
        return scanner.nextLine();
    }

    @Override
    public int getIntInput(String question) {
        boolean ok = false;
        do {
            System.out.print(question + " ");
            ok = scanner.hasNextInt();
            if (!ok) {
                System.out.println("\nInsert a valid int");
                scanner.next();
            }
        } while (!ok);
        return scanner.nextInt();
    }

    @Override
    public String getName() {
        return name;
    }

    public void askName() {
        String name;
        do {
            System.out.print("Insert your name: ");
            name = scanner.nextLine().trim();
        } while (name.length() == 0);
        System.out.println("Your name is: " + name);
        this.name = name;
    }

    public void askSymbol() {
        char[] input;
        do {
            System.out.print("Insert your symbol: ");
            input = scanner.nextLine().toCharArray(); 
        } while (input.length == 0);
        
        char symbol = input[0];
        System.out.println("Your symbol is: " + symbol);
        this.symbol = new PlayerSymbol(symbol);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}