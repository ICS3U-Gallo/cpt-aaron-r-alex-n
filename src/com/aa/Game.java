package com.aa;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

import static com.aa.GameUtils.*;

public class Game {
    private boolean finished;
    private Scanner input;
    private Player player;
    private ArrayList<Enemy> enemies;

    private static void messageln(String m) {
        System.out.println(m);
    }

    private static void message(String m) {
        System.out.print(m);
    }

    private Game() {
        setFinished(false);
        setInput(new Scanner(System.in));
    }

    private boolean isFinished() {
        return finished;
    }
    private void setFinished(boolean finished) { this.finished = finished; }
    private Player getPlayer() { return player; }
    private void setPlayer(Player player) { this.player = player; }
    private Scanner getInput() { return input; }
    private void setInput(Scanner input) { this.input = input; }


    private ArrayList<Enemy> getEnemies() { return enemies; }
    private void setEnemies(ArrayList<Enemy> enemies) { this.enemies = enemies; }

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
        messageln("The End!\n\n");
        setFinished(true);
    }

    private void attemptToQuitGame() {
        String input;
        while (true) {
            message("Are you sure you want to quit? (y/n): ");
            input = getInput().next();
            if ("n".equalsIgnoreCase(input)) {
                return;
            }
            if ("y".equalsIgnoreCase(input)) {
                endGame("You lose yourself in the infinite dark...");
                return;
            }
        }
    }

    private boolean foesAttackPlayer(ArrayList<Enemy> foes, boolean blocking) {
        messageln("You are being attacked!");
        int dam;
        for (Enemy foe : foes) {
            if (foe.isDead()) continue;

            dam = foe.attemptAttack(getPlayer(), blocking);
            if (dam > 0) {
                messageln(foe.getType() + " has reduced your health by " + dam + "!");
                if (getPlayer().isAlive()) {
                    messageln("Your health is " + getPlayer().getCurrentHp() + " of " + getPlayer().getMaximumHp() + ".");
                }
                else {
                    return false;
                }
            }
            else {
                messageln(foe.getType() + " missed the attack!");
            }
        }
        return true;
    }

    private boolean fightFoesWithHealingPotions(ArrayList<Enemy> foes) {
        message("Your health is " + getPlayer().getCurrentHp() +
                " of " + getPlayer().getMaximumHp() +
                ". What are you going to do? (A)ttack, (B)lock, Use (H)ealing potion: ");

        String action = getInput().next();
        if ("a".equalsIgnoreCase(action)) {
            int atk;
            for (Enemy foe : foes) {
                if (foe.isAlive()) {
                    atk = getPlayer().attemptAttack(foe);
                    if (atk == 0) {
                        messageln("");
                    }
                }
            }
        }
        if ("b".equalsIgnoreCase(action)) {
            if (! foesAttackPlayer(foes, true)); {
                return false;
            }
        }
        if ("h".equalsIgnoreCase(action)) {
            messageln("You use a healing potion and recover " + getPlayer().useHealingPotion() + " health.");
        }
        return true;
    }


    /**
     * TODO
     * @param foes
     * @return
     */
    private boolean fightFoesWithoutHealingPotions(ArrayList<Enemy> foes) {
        return true;
    }

    private void fightFoes(ArrayList<Enemy> foes) {

        boolean fighting = true;
        boolean ffc = GameUtils.getFiftyFiftyChance();

        if (ffc) {
            if (!(foesAttackPlayer(foes, false))) {
                messageln("========\nYOU DIED\n========");
                setFinished(true);
                return;
            }
        }

        String action;
        int potionIndex;
        while (fighting) {
            potionIndex = getPlayer().getHealingPotionIndex();
            if (potionIndex > -1) {
                fightFoesWithoutHealingPotions(foes);
            }
            else {
                fightFoesWithHealingPotions(foes);
            }
        }
    }

    private void dealWithRandomEncounter() {
        if (GameUtils.getFiftyFiftyChance()) return;

        // Encounter
        Random rand = new Random();
        Enemy e;
        e = getEnemies().get(rand.nextInt(getEnemies().size()));
        e.reset();

        messageln("You encounter a " + e.getType() + "!");
        if (e.hasSpawn()) {
            messageln("And it has summoned help:");
            for (Enemy spawn : e.getSpawn()) {
                messageln(spawn.getType());
            }
            messageln("Prepare for combat!");
        }

        fightFoes(e.asFoes());

    }

    private void movePlayerToRoom(String dir, Room r) {
        if (r == null) {
            messageln("There is no path " + dir + ".");
            return;
        }

        getPlayer().setRoom(r);
        messageln(getPlayer().getRoomMessage());
        dealWithRandomEncounter();
    }

    private void dealWithDoor(String dir, Room door, Room nextRoom) {
        if (door == null) {
            return;
        }

        messageln("You face a door.");

        if (! door.isLocked()) {
            messageln("You've unlocked the door and proceed to the next room.");
            movePlayerToRoom(dir, nextRoom);
            return;
        }

        if (door.isLockTypeRiddle()) {
            if (door.getRiddleQuestion() == null || (door.getRiddleQuestion().isEmpty())) {
                messageln("The doorkeeper forgot the riddle.\nHe allows you to continue to save him the embarrassment.");
                door.setLocked(false);
                movePlayerToRoom(dir, nextRoom);
                return;
            }

            String answer;
            messageln("To unlock this door, you need to enter the answer to the following riddle.");
            messageln("You only have " + DoorUnlockChances + " if you fail, you die : ");
            for (int i=0; i < DoorUnlockChances; i++) {
                message(door.getRiddleQuestion() + "(" + (i+1) + " of " + DoorUnlockChances + ")");
                answer = getInput().next();
                if (answer.equalsIgnoreCase(door.getRiddleAnswer())) {
                    messageln("You have successfully answered the riddle.\nThe door unlocks and you proceed to the following room.");
                    door.setLocked(false);
                    movePlayerToRoom(dir, nextRoom);
                    return;
                }
                else {
                    messageln("No.");
                }
            }
            endGame("Your futile attempt to open the door has failed.\nThe walls quickly close in on you,\nand you die a slow,\npainful death.");
            return;
        }

        if (door.isLockTypeKey()) {
            if (door.getKeyId() != null) {
                messageln("You require a key to open this door.");
                if (getPlayer().hasKeyId(door.getKeyId())) {
                    messageln("You use the key you found to unlock the door. You proceed to the next room. ");
                    door.setLocked(false);
                    movePlayerToRoom(dir, nextRoom);
                }
                else {
                    messageln("Your attempt to open the door with your mind has failed.\Go find the key.");
                }
                return;
            }
            messageln("The lock to this door crumbles easily, you proceed to the next room.");
            door.setLocked(false);
            movePlayerToRoom(dir, nextRoom);

        }
    }


    private void attemptToMovePlayer(String dir, Room nextRoom, Room afterRoom) {
        if (nextRoom == null) {
            messageln("There is nowhere you can go in that direction.");
            return;
        }

        if (nextRoom.isDoor()) {
            dealWithDoor(dir, nextRoom, afterRoom);
            return;
        }

        movePlayerToRoom(dir, nextRoom);
    }


    private void getUserInput() {
        String playerInput = getInput().next();
        messageln("\n");
        if ("x".equalsIgnoreCase(playerInput)) {
            attemptToQuitGame();
            return;
        }
        if ("n".equalsIgnoreCase(playerInput)) {
            attemptToMovePlayer("North", getPlayer().getRoomNorth(), getPlayer().getAfterRoomNorth());
            return;
        }
        if ("s".equalsIgnoreCase(playerInput)) {
            attemptToMovePlayer("South", getPlayer().getRoomSouth(), getPlayer().getAfterRoomSouth());
            return;
        }
        if ("e".equalsIgnoreCase(playerInput)) {
            attemptToMovePlayer("East", getPlayer().getRoomEast(), getPlayer().getAfterRoomEast());
            return;
        }
        if ("w".equalsIgnoreCase(playerInput)) {
            attemptToMovePlayer("West", getPlayer().getRoomWest(), getPlayer().getAfterRoomWest());
        }
    }

    private void play() {
        messageln(LotsOfln);
        messageln("Welcome to Dungeon Brawler\n");
        messageln("You are a drifter, from a far off land.\nFor many years, you took odd jobs from time to time\nhowever recently, you caught wind of a huge bounty, set up by a king.\nYour task is simple: Kill the red dragon Ashardalon, and free the townfolk of it's terror.\nThe dragon has taken residence in an ancient Dwarven castle, deep underground.\nAs you walk in the endless caverns, you misstep, the floor below you breaks\nand fall down into a room...\n\n");
        messageln(getPlayer().getRoomMessage());

        while (! isFinished()) {
            message("\nWhere would you like to go? (n)orth, (e)ast, (s)outh, (w)est: ");
            getUserInput();
        }
    }
}
