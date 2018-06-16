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

    String getRoomMessage() {
        if (hasRoom()) {
            return getRoom().getName() + ": " + getRoom().getMessage();
        }
        return "";
    }

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

    Room getAfterRoomNorth() {
        Room r = getRoomNorth();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getNorth();
    }

    Room getAfterRoomSouth() {
        Room r = getRoomSouth();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getSouth();
    }

    Room getAfterRoomEast() {
        Room r = getRoomEast();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getEast();
    }

    Room getAfterRoomWest() {
        Room r = getRoomWest();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getWest();
    }

    boolean hasKey(String id) {
        return getKeys() != null && getKeys().contains(id);
    }

    /*
        If the attack succeeds, return the damage done to the enemy, if it fails, return zero.
     */
    int attemptAttack(Enemy enemy) {
        int chance = GameUtils.getRandomBoundedValue(BoundaryScale);
        if (chance < getAttackRating()) {
            enemy.takeDamage(getDamage());
            return getDamage();
        }
        return 0;
    }


    private void takeDamage(int d) {
        setCurrentHp(getCurrentHp() - d);
        //return getCurrentHp() > 0;
    }

    private boolean isHpMaximum() {
        return getMaximumHp() == getCurrentHp();
    }


    boolean hasHealingPotions() {
        return getHealingPotions() != null && ! getHealingPotions().isEmpty();
    }

    int useHealingPotion() {
        if (isHpMaximum() || (! hasHealingPotions())) return 0;

        Integer potion = getHealingPotions().remove(0);
        int increment;
        if (potion > 0) {
            increment = Math.min(getMaximumHp() - getCurrentHp(), potion);
            setCurrentHp(getCurrentHp() + increment);
            return increment;
        }
        return -1;
    }

    boolean addHealingPotion(int potion) {
        if (potion == 0 || (getHealingPotions().size() == MaxNumberOfHealingPotions)) return false;
        getHealingPotions().add(potion);
        return true;
    }

    int beingAttacked(Enemy foe, boolean blocking) {
        int blockChance;
        if (blocking) {
            blockChance = GameUtils.getRandomBoundedValue(BoundaryScale);
            if (blockChance < getBlockRating()) {
                return 0;
            }
        }

        takeDamage(foe.getDamage());
        return foe.getDamage();
    }

    void addGold(int g) {
        setGold(getGold() + g);
    }
}