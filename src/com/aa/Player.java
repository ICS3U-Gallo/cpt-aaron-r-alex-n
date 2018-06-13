package com.aa;

import java.util.HashSet;
import java.util.Random;

public class Player {

    private String name;
    private int hp;
    private int damage;
    private int attackRating;
    private int blockRating;

    private HashSet<String> keyIds;

    private Room room;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAttackRating() { return attackRating; }

    public void setAttackRating(int attackRating) { this.attackRating = attackRating; }

    public int getBlockRating() { return blockRating; }

    public void setBlockRating(int blockRating) {
        this.blockRating = blockRating;
    }

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
        return getHp() > 0;
    }

    public void addKeyID(String keyID) {
        if (getKeyIds() == null) {
            setKeyIds(new HashSet<String>());
        }
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
        Random rand = new Random();
        int val = rand.nextInt(100) + 1;

        if (val > (100 - getAttackRating())) {
            e.takeDamage(getDamage());
            return getDamage();
        }
        else {
            return 0;
        }
    }

    public void takeDamage(int d) {
        setHp(getHp() - d);
        if (getHp() < 1) {
            /**
             * TODO
             */
        }
    }


}