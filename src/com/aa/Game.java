/*
This is the class that contains the main() method which sets up an instance of this class
and launches the game play.
This class has a Player object, representing the main character of the game.
It also has a list of enemies that are used for random encounters.
And it holds an instance of Scanner, to process user input.
 */

package com.aa;

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

    private void setFinished(boolean finished) {
        this.finished = finished;
    }

    private Player getPlayer() {
        return player;
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    private Scanner getInput() {
        return input;
    }

    private void setInput(Scanner input) {
        this.input = input;
    }

    private ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    private void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.setPlayer(GameUtils.createPlayer());
        game.setEnemies(GameUtils.createEnemies());
        game.play();
    }

    private Enemy getRandomEnemy() {
        if (GameUtils.getFiftyFiftyChance()) return null;
        Enemy foe = getEnemies().get(GameUtils.getRandomBoundedValue(getEnemies().size()));
        foe.reset();
        return foe;
    }

    private String prompt() {
        String ui = getInput().next();
        message("\n");
        return ui;
    }

    private void endGame(String m) {
        if (m != null) {
            messageln(m);
        }
        messageln("The End!\n\n");
        setFinished(true);
    }

    private void playerDies() {
        messageln("========\nYOU DIED\n========");
        setFinished(true);
    }

    private void attemptToQuitGame() {
        String input;
        while (true) {
            message("Are you sure you want to quit? (y/n): ");
            input = prompt();
            if ("n".equalsIgnoreCase(input)) {
                return;
            }
            if ("y".equalsIgnoreCase(input)) {
                endGame("You lose yourself in the infinite dark...");
                return;
            }
        }
    }

    /* A group of enemies is attacking the player. Return true if the player survives the onslaught, and false if the player dies.
     */
    private boolean foesAttackPlayer(ArrayList<Enemy> foes, boolean blocking) {
        int dam;
        for (Enemy foe : foes) {
            if (foe.isDead()) continue;

            dam = foe.attemptAttack(getPlayer(), blocking);
            if (dam > 0) {
                messageln("The " + foe.getType() + " has reduced your health by " + dam + "!\n");

                if (getPlayer().isDead()) {
                    playerDies();
                    return false;
                }
            }
            else {
                messageln("The " + foe.getType() + " failed the attack!\n");
            }
        }
        return true;
    }


    private boolean playerAttemptsToKillFoe(Enemy foe) {
        int atk = getPlayer().attemptAttack(foe);
        if (atk == 0) {
            messageln("Your attack on the " + foe.getType() + " failed!\n");
            return false;
        }
        messageln("Your attack on the " + foe.getType() + " succeeded! (HP: " + foe.getCurrentHp() + ")\n");
        if (foe.isDead()) {
            messageln("You killed the " + foe.getType() + "!\n");
            return true;
        }
        return false;
    }

    private void playerLootsFoe(Enemy foe) {
        if (foe.hasGoldDrop()) {
            getPlayer().addGold(foe.getGoldDrop());
            messageln("You've looted " + foe.getGoldDrop() +
                    " gold from the " + foe.getType() +
                    " now you have " + getPlayer().getGold() + " gold.\n");
        }
        if (foe.hasKeyDrop()) {
            getPlayer().addKey(foe.getKeyDrop());
            messageln("You've looted the " + foe.getKeyDrop() + " from the " + foe.getType() + ".\n");
        }
        if (foe.hasHealingPotionDrop()) {
            if (getPlayer().addHealingPotion(foe.getHealingPotionDrop())) {
                messageln("You've looted a healing potion of " + foe.getHealingPotionDrop() +
                        " strength from the " + foe.getType() + ".\n");
            }
            else {
                messageln("You couldn't carry the " + foe.getHealingPotionDrop() +
                        " healing potion dropped by the " + foe.getType() + ".\n");
            }
        }
    }

    private void playerLootsChest(Chest chest) {
        if (chest.hasGold()) {
            getPlayer().addGold(chest.getGold());
            messageln("You've looted " + chest.getGold() +
                    " gold from the this chest. Now you have " + getPlayer().getGold() + " gold.\n");
        }
        if (chest.hasHealingPotion()) {
            if (getPlayer().addHealingPotion(chest.getHealingPotion())) {
                messageln("You've looted a healing potion of " + chest.getHealingPotion() +
                        " strenght from this chest.\n");
            }
        }
        chest.empty();
    }

    private void playerUsesHealingPotion() {
        messageln("You use a healing potion and recover " + getPlayer().useHealingPotion() + " health.\n");
    }

    /*
      Turn for player to decide what to do when facing the list of foes.
      Return 0 if all enemies are killed by player (if the player decided to attack).
      Return > 0 if not all the enemies have been killed by player, (if the player decided to attack);
      Return the enemies group size if the player decided to take a potion.
      Return -1 if the player decides to block.
      Return -2 if the player used a healing potion.
      Return -3 if the player did not enter a valid command.

     */
    private int playerTurnWithFoes(ArrayList<Enemy> foes, boolean withPotions) {
        String encounter = "Your health is " + getPlayer().getCurrentHp() +
                " of " + getPlayer().getMaximumHp() +
                ". What are you going to do? (a)ttack, (b)lock";

        if (withPotions) {
            encounter = encounter + ", Use (h)ealing potion: ";
        }
        else {
            encounter = encounter + ": ";
        }
        message(encounter);

        String action = prompt();
        int foesSize = foes.size();
        int bodyCount = 0;
        if ("a".equalsIgnoreCase(action)) {
            for (Enemy foe : foes) {
                if (foe.isAlive()) {
                    if (playerAttemptsToKillFoe(foe)) {
                        bodyCount++;
                        playerLootsFoe(foe);
                    }
                }
                else {
                    bodyCount++;
                }
                if (bodyCount == foesSize) {
                    messageln("Victory! All enemies are dead!\n");
                    return 0;
                }
            }
            return foesSize - bodyCount;
        }


        if ("b".equalsIgnoreCase(action)) {
            return -1;
        }

        if (withPotions && "h".equalsIgnoreCase(action)) {
            playerUsesHealingPotion();
            return -2;
        }
        return -3;
    }


    private void dealWithEncounter(Enemy foe) {
        messageln("You encounter " + foe.getTitle(false) + "! (HP: " + foe.getCurrentHp() + ")");

        if (foe.hasSpawn()) {
            messageln("And it has summoned help:");
            for (Enemy spawn : foe.getSpawn()) {
                messageln(spawn.getTitle(true));
            }
        }

        ArrayList<Enemy> foes = foe.asFoes();
        boolean ffc = GameUtils.getFiftyFiftyChance();
        if (ffc) {
            messageln("\nYou are being surprise-attacked!\n");
            if (!(foesAttackPlayer(foes, false))) {
                return;
            }
        }

        boolean block;

        int currentFoes = foes.size();
        int outcome = currentFoes;
        while(outcome > 0) {
            block = false;
            outcome = playerTurnWithFoes(foes, (getPlayer().hasHealingPotions()));

            if (outcome == 0) {
                currentFoes = 0;
                continue;
            }
            if (outcome > 0) {
                currentFoes = outcome;
            }

            if (outcome < 0) { // -1, -2, -3
                if (outcome == -1) {
                    block = true;
                }
                outcome = currentFoes;
            }

            messageln("Now, it is the enemies turn... \n");

            if (!foesAttackPlayer(foes, block))
                outcome = -4;

        }
    }

    private void dealWithBossEncounter(Boss boss) {
        dealWithEncounter(boss);

        if (boss.isDead() && boss.isLast()) {
            endGame("Congratulations! You have killed the mastr of this dungeon: " + boss.getTitle() +
                    "\nGold: " + getPlayer().getGold());

        }
    }

    private void dealWithChest(Chest chest) {
        boolean prompt = true;
        String action;
        while(prompt) {
            message("There is a chest. What do you want to do? (o)pen or (l)eave?: ");
            action = prompt();
            if ("o".equalsIgnoreCase(action)) {
                if (chest.isRandomEncounter()) {
                    Enemy foe = getRandomEnemy();
                    if (foe != null) {
                        messageln("The chest has a trap and magically summons a monster!");
                        dealWithEncounter(foe);
                    }
                }
                if (getPlayer().isAlive()) {
                    playerLootsChest(chest);
                }
                prompt = false;
            }
            if ("l".equalsIgnoreCase(action)) {
                messageln("You leave the chest alone...");
                prompt = false;
            }
        }
    }

    private void movePlayerToRoom(String dir, Room room) {
        if (room == null) {
            messageln("There is no path " + dir + ".");
            return;
        }

        getPlayer().setRoom(room);
        messageln(getPlayer().getRoomMessage());
        if (room.hasBoss())
            dealWithBossEncounter(room.getBoss());
        else {
            Enemy foe = getRandomEnemy();
            if (foe != null) {
                dealWithEncounter(foe);
            }
        }

        if (getPlayer().isAlive() && room.hasChest()) {
            dealWithChest(room.getChest());
        }

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
                door.unlock();
                movePlayerToRoom(dir, nextRoom);
                return;
            }

            String answer;
            messageln("To unlock this door, you need to enter the answer to the following riddle.");
            messageln("You only have " + DoorUnlockChances + "chances.  If you fail, you die!: \n");
            for (int i=0; i < DoorUnlockChances; i++) {
                message(door.getRiddleQuestion() + " (" + (i+1) + " of " + DoorUnlockChances + "): ");
                answer = prompt();
                if (answer.equalsIgnoreCase(door.getRiddleAnswer())) {
                    messageln("You have successfully answered the riddle.\nThe door unlocks and you proceed to the following room.");
                    door.unlock();
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
            if (door.hasKey()) {
                messageln("You require a key to open this door.");
                if (getPlayer().hasKey(door.getKey())) {
                    messageln("You use the key you found to unlock the door. You proceed to the next room. ");
                    door.unlock();
                    movePlayerToRoom(dir, nextRoom);
                }
                else {
                    messageln("Go find the key.");
                }
                return;
            }
            messageln("The lock to this door crumbles easily, you proceed to the next room.");
            door.unlock();
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


    private void playerTurn(boolean withPotions) {

        messageln("HP: " + getPlayer().getCurrentHp() + " of " + getPlayer().getMaximumHp() + " Gold: " + getPlayer().getGold());

        if (withPotions) {
            message("\nWhat would you like to do? Go (n)orth, (e)ast, (s)outh, (w)est, or use a (h)ealing potion?: ");
        }
        else {
            message("\nWhat would you like to do? Go (n)orth, (e)ast, (s)outh or (w)est?: ");
        }
        String playerInput = prompt();
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
        if(withPotions && "h".equalsIgnoreCase(playerInput)) {
            playerUsesHealingPotion();
        }
    }

    private void play() {
        for (int i = 0; i < 100; i ++) message("\n");

        messageln("Welcome to Dungeon Brawler!\n");
        messageln("You are a drifter, from a far off land.\nFor many years, you took odd jobs from time to time\nhowever recently, you caught wind of a huge bounty, set up by a king.\nYour task is simple: Kill the red dragon Ashardalon, and free the townfolk of it's terror.\nThe dragon has taken residence in an ancient Dwarven castle, deep underground.\nAs you walk in the endless caverns, you misstep, the floor below you breaks\nand fall down into a room...\n\n");
        messageln(getPlayer().getRoomMessage());

        while (! isFinished()) {
            playerTurn(getPlayer().hasHealingPotions());
        }
    }
}
