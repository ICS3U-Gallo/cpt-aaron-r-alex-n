package com.aa;

import java.util.HashSet;

import static com.aa.GameUtils.*;

public class Player {

    private String name;
    private int maximumHp;
    private int currentHp;
    private int damage;
    private int attackRating;
    private int blockRating;
    private int[] healingPotions;
    private HashSet<String> keyIds;
    private Room room;

    public Player() {
        setMaximumHp(PlayerHp);
        setCurrentHp(PlayerHp);
        setDamage(PlayerDamage);
        setAttackRating(PlayerAttackRating);
        setBlockRating(PlayerBlockRating);
        setHealingPotions(new int[MaxNumberOfHealingPotions]);
        setKeyIds(new HashSet<String>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaximumHp() {
        return maximumHp;
    }

    private void setMaximumHp(int maximumHp) { this.maximumHp = maximumHp; }

    public int getCurrentHp() { return currentHp; }

    public void setCurrentHp(int currentHp) { this.currentHp = currentHp; }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAttackRating() { return attackRating; }

    public void setAttackRating(int attackRating) { this.attackRating = attackRating; }

    public int getBlockRating() { return blockRating; }

    public int[] getHealingPotions() { return healingPotions; }

    public void setHealingPotions(int[] healingPotions) { this.healingPotions = healingPotions; }

    public void setBlockRating(int blockRating) { this.blockRating = blockRating; }

    public HashSet<String> getKeyIds() {
        return keyIds;
    }

    public void setKeyIds(HashSet<String> keyIDs) {
        this.keyIds = keyIDs;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isAlive() {
        return getCurrentHp() > 0;
    }

    public void addKeyID(String keyID) {
        getKeyIds().add(keyID);
    }

    public boolean hasRoom() {
        return getRoom() != null;
    }

    public String getRoomMessage() {
        if (hasRoom()) {
            return getRoom().getMessage();
        }
        return "";
    }


    public Room getRoomNorth() {
        if (hasRoom()) {
            return getRoom().getNorth();
        }
        return null;
    }

    public Room getRoomSouth() {
        if (hasRoom()) {
            return getRoom().getSouth();
        }
        return null;
    }

    public Room getRoomEast() {
        if (hasRoom()) {
            return getRoom().getEast();
        }
        return null;
    }

    public Room getRoomWest() {
        if (hasRoom()) {
            return getRoom().getWest();
        }
        return null;
    }

    public Room getAfterRoomNorth() {
        Room r = getRoomNorth();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getNorth();
    }

    public Room getAfterRoomSouth() {
        Room r = getRoomSouth();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getSouth();
    }

    public Room getAfterRoomEast() {
        Room r = getRoomEast();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getEast();
    }

    public Room getAfterRoomWest() {
        Room r = getRoomWest();
        if (r == null || ! r.isDoor()) {
            return null;
        }
        return r.getWest();
    }

    public boolean hasKeyId(String id) {
        return getKeyIds() == null && getKeyIds().contains(id);
    }

    public int attemptAttack(Enemy e) {
        int val = GameUtils.getRandomBoundedValue();
        if (val < getAttackRating()) {
            e.takeDamage(getDamage());
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


    public int getHealingPotionIndex() {
        int each;
        for (int i = 0; i < getHealingPotions().length; i ++) {
            each = getHealingPotions()[i];
            if (each > 0) return i;
        }
        return -1;
    }

    public int useHealingPotion() {
        if (isHpMaximum()) return 0;
        int potion;
        int increment;
        for (int i = 0; i < getHealingPotions().length; i ++) {
            potion = getHealingPotions()[i];
            if (potion > 0) {
                increment = Math.min(getMaximumHp() - getCurrentHp(), potion);
                setCurrentHp(getCurrentHp() + increment);
                getHealingPotions()[i] = 0;
                return increment;
            }
        }
        return -1;
    }

    public boolean addHealthPotion(int potion) {
        if (potion == 0) return false;
        int each;
        for (int i = 0; i < getHealingPotions().length; i ++) {
            each = getHealingPotions()[i];
            if (each == 0) {
                getHealingPotions()[i] = potion;
                return true;
            }
        }
        return false;
    }

    public int beingAttacked(Enemy foe, boolean blocking) {
        int blockChance;
        if (blocking) {
            blockChance = GameUtils.getRandomBoundedValue();
            if (blockChance < getBlockRating()) {
                return 0;
            }
        }

        takeDamage(foe.getDamage());
        return foe.getDamage();
    }
}