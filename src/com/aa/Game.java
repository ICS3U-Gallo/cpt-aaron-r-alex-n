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

    public static void messageln(String m) {
        System.out.println(m);
    }

    public static void message(String m) {
        System.out.print(m);
    }

    public void endGame(String m) {
        if (m != null) {
            messageln(m);
        }
        messageln("The End!");
        setFinished(true);
    }

    public void attemptToQuitGame(Scanner s) {
        String input = null;
        while (true) {
            messageln("Are you sure you want to quit? (y/n) ");
            input = s.next();
            if ("n".equalsIgnoreCase(input)) {
                return;
            }
            if ("y".equalsIgnoreCase(input)) {
                endGame("You lose yourself in the infinite dark...");
            }
        }
    }

    public void printCurrentRoomMessage() {
        if (getPlayer().getRoomMessage() != null) {
            messageln(getPlayer().getRoomMessage());
        }
    }

    public void movePlayerToRoom(Room r) {
        if (r == null) {
            messageln("There is nowhere to go!");
            return;
        }

        getPlayer().setRoom(r);
        printCurrentRoomMessage();
    }

    public void dealWithDoor(Room door, Room nextRoom, Scanner s) {
        if (door == null) {
            return;
        }

        messageln("You are facing a door now.");

        if (! door.isLocked()) {
            messageln("The door is unlocked and you gor through to the next room.");
            movePlayerToRoom(nextRoom);
            return;
        }

        if (door.isLockTypeRiddle()) {
            if (door.getRiddleQuestion() == null || (door.getRiddleQuestion().isEmpty())) {
                messageln("Huh! This door used to require answering a riddle to open... it is your lucky day and the door unlocks and you proceed to the next room.");
                door.setLocked(false);
                movePlayerToRoom(nextRoom);
                return;
            }
            int limit = 3;
            String answer;
            messageln("To unlock this door, you need to enter the answer to the following riddle.");
            messageln("Be careful, you only have " + limit + " chances. If you fail... certain death! : ");
            for (int i=0; i < limit; i++) {
                message(door.getRiddleQuestion() + "(" + (i+1) + " of " + limit + ")");
                answer = s.next();
                if (answer.equalsIgnoreCase(door.getRiddleAnswer())) {
                    messageln("You have answered the riddle! The door unlocks and you proceed to the following room.");
                    door.setLocked(false);
                    movePlayerToRoom(nextRoom);
                    return;
                }
                else {
                    messageln("Wrong answer!");
                }
            }
            endGame("You have failed all the attempts to open this door. Now you die!");
            return;
        }

        if (door.isLockTypeKey()) {
            if (door.getKeyId() != null) {
                messageln("To unlock this door, you need a key.");
                if (getPlayer().hasKeyId(door.getKeyId())) {
                    messageln("You use the key for this door to open it. You proceed to the next room. ");
                    door.setLocked(false);
                    movePlayerToRoom(nextRoom);
                }
                else {
                    messageln("You don't have the key for this door. Go somewhere else.");
                }
                return;
            }
            messageln("Huh!, it seems this door does not need a key anymore. It unlocks and you proceed to the next room.");
            door.setLocked(false);
            movePlayerToRoom(nextRoom);
            return;

        }
    }

    public void movePlayerNorth(Scanner s) {
        Room r = getPlayer().getRoomNorth();
        if (r != null) {
            if (r.isDoor()) {
                dealWithDoor(r, r.getNorth(), s);
            }
            else {
                movePlayerToRoom(r);
            }
            return;
        }
        messageln("You cannot go through walls!");
    }


    public void movePlayerSouth(Scanner s) {
        Room r = getPlayer().getRoomSouth();
        if (r != null) {
            if (r.isDoor()) {
                dealWithDoor(r, r.getSouth(), s);
            }
            else {
                movePlayerToRoom(r);
            }
            return;
        }
        messageln("You cannot go through walls!");
    }
    
    public void movePlayerEast(Scanner s) {

    }
    public void movePlayerWest(Scanner s) {

    }


    public void movePlayer(Scanner s) {
        String input = s.next();
        if ("x".equalsIgnoreCase(input)) {
            attemptToQuitGame(s);
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

        messageln("Welcome to Dungeon Brawler\n");
        messageln("You are a drifter, from a far off land.\nFor many years, you took odd jobs from time to time\nhowever recently, you caught wind of a huge bounty, set up by a king.\nYour task is simple: Kill the red dragon Ashardalon, and free the townfolk of it's terror.\nThe dragon has taken residence in an ancient Dwarven castle, deep underground.\nAs you walk in the endless caverns, you misstep, the floor below you breaks\nand fall down into a room...\n\n");
        printCurrentRoomMessage();

        while (! isFinished()) {
            message("Where would you like to go? (n)orth, (e)ast, (s)outh, (w)est: ");
            movePlayer(owl);
        }
    }
}
