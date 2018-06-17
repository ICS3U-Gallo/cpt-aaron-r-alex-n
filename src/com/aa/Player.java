/*
  This is the class that represents the main character of the game.
  It has variables representing different attributes of the main
  character. Objects of this class have a property that indicates
  the current room where the character is located. The game
  initializes the player and the rooms structure setting the initial
  room of the player to the first room.
 */
package com.aa;

import java.util.ArrayList;
import java.util.HashSet;

import static com.aa.GameUtils.*;

class Player {
    private int maximumHp;
    private int currentHp;
    private int damage;
    private int attackRating;
    private int blockRating;
    private ArrayList<Integer> healingPotions;
    private HashSet<String> keys;
    private int gold;
    private Room room;


    /*
     Constructs the player based by the global variables defined in the GameUtils class.
     For the keys and the healing potions, it creates empty collections.
     The gold is set to 0.
      */
    Player() {
        setMaximumHp(PlayerHp);
        setCurrentHp(PlayerHp);
        setDamage(PlayerDamage);
        setAttackRating(PlayerAttackRating);
        setBlockRating(PlayerBlockRating);
        setHealingPotions(new ArrayList<>(MaxNumberOfHealingPotions));
        setKeys(new HashSet<>());
        setGold(0);
    }


    /* Now we use getters and setters for each variable in order to retrieve and give values to each variable,
    for each separate object */
    int getMaximumHp() {
        return maximumHp;
    }

    private void setMaximumHp(int maximumHp) {
        this.maximumHp = maximumHp;
    }

    int getCurrentHp() {
        return currentHp;
    }

    private void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    private int getDamage() {
        return damage;
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }

    private int getAttackRating() {
        return attackRating;
    }

    private void setAttackRating(int attackRating) {
        this.attackRating = attackRating;
    }

    private int getBlockRating() {
        return blockRating;
    }

    private ArrayList<Integer> getHealingPotions() {
        return healingPotions;
    }

    private void setHealingPotions(ArrayList<Integer> healingPotions) {
        this.healingPotions = healingPotions;
    }

    private void setBlockRating(int blockRating) {
        this.blockRating = blockRating;
    }

    private HashSet<String> getKeys() {
        return keys;
    }

    private void setKeys(HashSet<String> keys) {
        this.keys = keys;
    }

    int getGold() {
        return gold;
    }

    private void setGold(int gold) {
        this.gold = gold;
    }

    private Room getRoom() {
        return room;
    }

    void setRoom(Room room) {
        this.room = room;
    }

    boolean isAlive() {
        return getCurrentHp() > 0;
    }

    boolean isDead() {
        return !isAlive();
    }

    void addKey(String key) {
        getKeys().add(key);
    }

    private boolean hasRoom() {
        return getRoom() != null;
    }


    /*
     Returns the message from the room where the player is.
     If there is no room, or the message on the room is null, it returns empty string.
      */
    String getRoomMessage() {
        if (hasRoom()) {
            return getRoom().getName() + ": " + getRoom().getMessage();
        }
        return "";
    }


    /*
    From the room the player is in, returns north south east or west depending on the method.
    If the room where the player is in is null, returns null.
     */
    Room getRoomNorth() {
        if (hasRoom()) {
            return getRoom().getNorth();
        }
        return null;
    }

    Room getRoomSouth() {
        if (hasRoom()) {
            return getRoom().getSouth();
        }
        return null;
    }

    Room getRoomEast() {
        if (hasRoom()) {
            return getRoom().getEast();
        }
        return null;
    }

    Room getRoomWest() {
        if (hasRoom()) {
            return getRoom().getWest();
        }
        return null;
    }



    /*
    Only for doors.
    Gets the room after a door going in the same direction of that door.
     */
    // In this case, it gets the room after a north door.
    Room getAfterRoomNorth() {
        Room r = getRoomNorth();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getNorth();
    }



    // This is for south.
    Room getAfterRoomSouth() {
        Room r = getRoomSouth();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getSouth();
    }


    // East.
    Room getAfterRoomEast() {
        Room r = getRoomEast();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getEast();
    }


    // And west.
    Room getAfterRoomWest() {
        Room r = getRoomWest();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getWest();
    }


    // If the player has a key. It's only true if the key is not null, or not empty string.
    boolean hasKey(String id) {
        return getKeys() != null && getKeys().contains(id);
    }

    /*
        Attempt an attack on an enemy by getting a random number from 0 - 99.
        If the number is less than the attack rating, then the attack succeeds and returns the damage done to the enemy.
        If it fails, return 0.
        The damage done to the enemy is discounted to the enemy health, by calling the method takeDamage() from the Enemy class.
     */
    int attemptAttack(Enemy enemy) {
        int chance = GameUtils.getRandomBoundedValue(99);
        if (chance < getAttackRating()) {
            enemy.takeDamage(getDamage());
            return getDamage();
        }
        return 0;
    }



    // Reduce the health of the player by the damage represent by the parameter being passed.
    private void takeDamage(int d) {
        setCurrentHp(getCurrentHp() - d);
    }


    // Returns true if the current Hp is equal to the maximum Hp.
    private boolean isHpMaximum() {
        return getMaximumHp() == getCurrentHp();
    }



    // Returns true if the player has at least one healing potion.
    boolean hasHealingPotions() {
        return getHealingPotions() != null && ! getHealingPotions().isEmpty();
    }


    /*
     The player uses one healing potion and recovers health based on the value of that healing potion.
     Return 0 if there are no potions or the player health is at its maximum already.
     Returns -1 if for some reason the potion is of 0 value.
     Otherwise returns the number of healing points consumed by the player.
      */
    int useHealingPotion() {
        if (isHpMaximum() || (! hasHealingPotions())) return 0;

        Integer potion = getHealingPotions().remove(0);
        int increment;
        if (potion > 0) {
            // The increment cannot exceed the maximum player health.
            increment = Math.min(getMaximumHp() - getCurrentHp(), potion);
            setCurrentHp(getCurrentHp() + increment);
            return increment;
        }
        return -1;
    }


    /*
     Add a healing potion to the player, only if it doesn't exceed the maximum healing potions defined in the GameUtils class.
     And if the potion doesn't have a value of 0.
      */
    boolean addHealingPotion(int potion) {
        if (potion == 0 || (getHealingPotions().size() == MaxNumberOfHealingPotions)) return false;
        getHealingPotions().add(potion);
        return true;
    }



    /*
    Checks if you're blocking.
    If the random number chosen is below the block rating, then the block is successful.
    Otherwise, the player takes damage.
    If the player isn't blocking, it moves on to the "takeDamage" phase of combat.
     */
    int beingAttacked(Enemy foe, boolean blocking) {
        int blockChance;
        if (blocking) {
            blockChance = GameUtils.getRandomBoundedValue(99);
            if (blockChance < getBlockRating()) {
                return 0;
            }
        }


        // ZThe player takes damage equal to the amount of damage the enemy does.
        takeDamage(foe.getDamage());
        return foe.getDamage();
    }



    // Add the gold that is being passed as a parameter.
    void addGold(int g) {
        setGold(getGold() + g);
    }
}