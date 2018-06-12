package com.aa;

import java.util.HashSet;

public class Player {

    private String name;
    private int health;
    private int damage;

    private HashSet<String> keyIds;

    private Room room;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public HashSet<String> getKeyIds() {
        return keyIds;
    }

    public void setKeyIds(HashSet<String> keyIDs) {
        this.keyIds = keyIDs;
    }

    public void addKeyID(String keyID) {
        if (getKeyIds() == null) {
            setKeyIds(new HashSet<String>());
        }
        getKeyIds().add(keyID);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

}