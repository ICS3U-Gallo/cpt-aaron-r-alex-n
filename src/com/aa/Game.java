package com.aa;

import java.util.Scanner;

public class Game {
    private boolean finished = false;
    private Player player;


    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public static void main(String[] args) {

        Game game = new Game();
        game.play();

    }

    public boolean quitGame(Scanner s) {
        String input = null;
        while (true) {
            System.out.print("Are you sure you want to quit? (y/n) ");
            input = s.next();
            if ("n".equalsIgnoreCase(input)) {
                return false;
            }
            if ("y".equalsIgnoreCase(input)) {
                System.out.println("You lose yourself in the infinite dark...");
                return true;
            }
        }
    }

    public void printCurrentRoomMessage() {
        if (getPlayer().getRoomMessage() != null) {
            System.out.println(getPlayer().getRoomMessage());
        }
    }

    public void dealWithDoorNorth(Room door, Scanner s) {

    }

    public void movePlayerNorth(Scanner s) {
        if (getPlayer().canMoveNorth()) {
            Room north = getPlayer().getRoom().getNorth();
            if (north.isDoor()) {
                dealWithDoorNorth(north, s);
            }
            else {
                getPlayer().setRoom(north);
                printCurrentRoomMessage();
            }
        }
        System.out.println("You cannot go through walls!");
    }

    // Aaron, complete the rest of the methods for south, east and west.
    // You are going to need to create the "dealWithDoorXXXX()" methods, but for now
    // just create those empty.

    public void movePlayerSouth(Scanner s) {

    }
    public void movePlayerEast(Scanner s) {

    }
    public void movePlayerWest(Scanner s) {

    }


    public void movePlayer(Scanner s) {
        String input = s.next();
        if ("x".equalsIgnoreCase(input)) {
            setFinished(quitGame(s));
            return;
        }
        if ("n".equalsIgnoreCase(input)) {
            movePlayerNorth(s);
            return;
        }
        if ("s".equalsIgnoreCase(input)) {
            movePlayerSouth(s);
            return;
        }
        if ("e".equalsIgnoreCase(input)) {
            movePlayerEast(s);
            return;
        }
        if ("w".equalsIgnoreCase(input)) {
            movePlayerWest(s);
            return;
        }


    }

    public void play() {

        Scanner owl = new Scanner(System.in);
        setPlayer(Initializer.createPlayer());


        System.out.println("Welcome to Dungeon Brawler\n");
        System.out.println("You are a drifter, from a far off land.\nFor many years, you took odd jobs from time to time\nhowever recently, you caught wind of a huge bounty, set up by a king.\nYour task is simple: Kill the red dragon Ashardalon, and free the townfolk of it's terror.\nThe dragon has taken residence in an ancient Dwarven castle, deep underground.\nAs you walk in the endless caverns, you misstep, the floor below you breaks\nand fall down into a room...\n\n");
        printCurrentRoomMessage();

        while (! isFinished()) {
            System.out.print("Where would you like to go? (n)orth, (e)ast, (s)outh, (w)est: ");
            movePlayer(owl);
        }
    }
}