package com.aa;

import java.sql.Time;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    private boolean finished = false;
    private Player player;
    private ArrayList<Enemy> enemies;


    public boolean isFinished() {
        return finished;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
    public ArrayList<Enemy> getEnemies() { return enemies; }
    public void setEnemies(ArrayList<Enemy> enemies) { this.enemies = enemies; }

    public static void main(String[] args) {

        Game game = new Game();
        game.setPlayer(Initializer.createPlayer());
        game.setEnemies(Initializer.createEnemies());
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
            message("Are you sure you want to quit? (y/n): ");
            input = s.next();
            if ("n".equalsIgnoreCase(input)) {
                return;
            }
            if ("y".equalsIgnoreCase(input)) {
                endGame("You lose yourself in the infinite dark...");
                return;
            }
        }
    }

    public void printCurrentRoomMessage() {
        if (getPlayer().getRoomMessage() != null) {
            messageln(getPlayer().getRoomMessage());
        }
    }

    public void fightFoes(ArrayList<Enemy> foes, Scanner s) {
        messageln("You are fighting the following: ");
        for (Enemy e : foes) {
            messageln(e.getType());
        }
        messageln("");
    }

    public void dealWithRandomEncounter(Scanner s) {
        Random rand = new Random();
        int r = rand.nextInt(100);

        if (r > 49) {
            return;
        }
        // Encounter!
        Enemy e = getEnemies().get(rand.nextInt(getEnemies().size()));
        e.reset();

        messageln("An enemy faces you: " + e.getType() + "!");

        ArrayList<Enemy> foes = new ArrayList<Enemy>();
        foes.add(e);
        if (e.hasSpawn()) {
            foes.addAll(e.getSpawn());
        }

        fightFoes(foes, s);

    }

    public void movePlayerToRoom(String dir, Room r, Scanner s) {
        if (r == null) {
            messageln("There is nowhere to go!");
            return;
        }

        getPlayer().setRoom(r);
        printCurrentRoomMessage();

        /** Guess what?
            Good news and bad news.
            Good news, the playr has moved to the next room.
            Bad news, there might be a random enemy encounter!
         */

        dealWithRandomEncounter(s);
    }

    public void dealWithDoor(String dir, Room door, Room nextRoom, Scanner s) {
        if (door == null) {
            return;
        }

        messageln("You are facing a door now.");

        if (! door.isLocked()) {
            messageln("The door is unlocked and you gor through to the next room.");
            movePlayerToRoom(dir, nextRoom, s);
            return;
        }

        if (door.isLockTypeRiddle()) {
            if (door.getRiddleQuestion() == null || (door.getRiddleQuestion().isEmpty())) {
                messageln("Huh! This door used to require answering a riddle to open... it is your lucky day and the door unlocks and you proceed to the next room.");
                door.setLocked(false);
                movePlayerToRoom(dir, nextRoom, s);
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
                    movePlayerToRoom(dir, nextRoom, s);
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
                    movePlayerToRoom(dir, nextRoom, s);
                }
                else {
                    messageln("You don't have the key for this door. Go somewhere else.");
                }
                return;
            }
            messageln("Huh!, it seems this door does not need a key anymore. It unlocks and you proceed to the next room.");
            door.setLocked(false);
            movePlayerToRoom(dir, nextRoom, s);
            return;

        }
    }


    public void attemptToMovePlayer(String dir, Room nextRoom, Room afterRoom, Scanner s) {
        if (nextRoom == null) {
            messageln("You cannot go through walls!");
            return;
        }

        if (nextRoom.isDoor()) {
            dealWithDoor(dir, nextRoom, afterRoom, s);
            return;
        }

        movePlayerToRoom(dir, nextRoom, s);
    }


    public void getUserInput(Scanner s) {
        String input = s.next();
        if ("x".equalsIgnoreCase(input)) {
            attemptToQuitGame(s);
            return;
        }
        if ("n".equalsIgnoreCase(input)) {
            attemptToMovePlayer("North", getPlayer().getRoomNorth(), getPlayer().getAfterRoomNorth(), s);
            return;
        }
        if ("s".equalsIgnoreCase(input)) {
            attemptToMovePlayer("South", getPlayer().getRoomSouth(), getPlayer().getAfterRoomSouth(), s);
            return;
        }
        if ("e".equalsIgnoreCase(input)) {
            attemptToMovePlayer("East", getPlayer().getRoomEast(), getPlayer().getAfterRoomEast(), s);
            return;
        }
        if ("w".equalsIgnoreCase(input)) {
            attemptToMovePlayer("West", getPlayer().getRoomWest(), getPlayer().getAfterRoomWest(), s);
            return;
        }


    }

    public void play() {

        Scanner owl = new Scanner(System.in);

        messageln("Welcome to Dungeon Brawler\n");
        messageln("You are a drifter, from a far off land.\nFor many years, you took odd jobs from time to time\nhowever recently, you caught wind of a huge bounty, set up by a king.\nYour task is simple: Kill the red dragon Ashardalon, and free the townfolk of it's terror.\nThe dragon has taken residence in an ancient Dwarven castle, deep underground.\nAs you walk in the endless caverns, you misstep, the floor below you breaks\nand fall down into a room...\n\n");
        printCurrentRoomMessage();

        while (! isFinished()) {
            message("Where would you like to go? (n)orth, (e)ast, (s)outh, (w)est: ");
            getUserInput(owl);
        }
    }
}
