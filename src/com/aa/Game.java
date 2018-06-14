package com.aa;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

import static com.aa.GameUtils.*;

public class Game {
    private boolean finished = false;
    private Player player;
    private ArrayList<Enemy> enemies;

    private static void messageln(String m) {
        System.out.println(m);
    }

    private static void message(String m) {
        System.out.print(m);
    }

    private boolean isFinished() {
        return finished;
    }
    private Player getPlayer() { return player; }
    private void setPlayer(Player player) { this.player = player; }
    private ArrayList<Enemy> getEnemies() { return enemies; }
    private void setEnemies(ArrayList<Enemy> enemies) { this.enemies = enemies; }
    private void finish() {
        finished = true;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setPlayer(GameUtils.createPlayer());
        game.setEnemies(GameUtils.createEnemies());
        game.play();
    }

    private void endGame(String m) {
        if (m != null) {
            messageln(m);
        }
        messageln("The End!");
        finish();
    }

    private void attemptToQuitGame(Scanner s) {
        String input;
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

    private void fightFoes(ArrayList<Enemy> foes, Scanner s) {

    }

    private void dealWithRandomEncounter(Scanner s) {
        if (GameUtils.getFiftyFiftyChance()) return;


        // Encounter!
        Random rand = new Random();
        Enemy e;
        e = getEnemies().get(rand.nextInt(getEnemies().size()));
        e.reset();

        messageln("An enemy faces you: " + e.getType() + "!");
        if (e.hasSpawn()) {
            messageln("And it has companions: ");
            for (Enemy c : e.getSpawn()) {
                messageln(e.getType());
            }
        }

        fightFoes(e.asFoes(), s);

    }

    private void movePlayerToRoom(String dir, Room r, Scanner s) {
        if (r == null) {
            messageln("Nowhere to go " + dir);
            return;
        }

        getPlayer().setRoom(r);
        messageln(getPlayer().getRoomMessage());
        dealWithRandomEncounter(s);
    }

    private void dealWithDoor(String dir, Room door, Room nextRoom, Scanner s) {
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

            String answer;
            messageln("To unlock this door, you need to enter the answer to the following riddle.");
            messageln("Be careful, you only have " + DoorUnlockChances + " chances. If you fail... certain death! : ");
            for (int i=0; i < DoorUnlockChances; i++) {
                message(door.getRiddleQuestion() + "(" + (i+1) + " of " + DoorUnlockChances + ")");
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

        }
    }


    private void attemptToMovePlayer(String dir, Room nextRoom, Room afterRoom, Scanner s) {
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


    private void getUserInput(Scanner s) {
        String input = s.next();

        messageln("---------------------------------------------------------\n");
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
        }
    }

    private void play() {
        Scanner s = new Scanner(System.in);

        messageln("Welcome to Dungeon Brawler\n");
        messageln("You are a drifter, from a far off land.\nFor many years, you took odd jobs from time to time\nhowever recently, you caught wind of a huge bounty, set up by a king.\nYour task is simple: Kill the red dragon Ashardalon, and free the townfolk of it's terror.\nThe dragon has taken residence in an ancient Dwarven castle, deep underground.\nAs you walk in the endless caverns, you misstep, the floor below you breaks\nand fall down into a room...\n\n");
        messageln(getPlayer().getRoomMessage());

        while (! isFinished()) {
            message("Where would you like to go? (n)orth, (e)ast, (s)outh, (w)est: ");
            getUserInput(s);
        }
    }
}
