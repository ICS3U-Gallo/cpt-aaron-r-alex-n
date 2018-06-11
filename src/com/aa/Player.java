package com.aa;

import java.util.HashSet;

public class Player {

    private String name;
    private int health;
    private int damage;
    private int attack;
    private int armour;
    private int agility;
    private int perception;
    private int sneak;
    private int level;
    private int currentExperience;
    private int experienceNeeded;
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getPerception() {
        return perception;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public int getSneak() {
        return sneak;
    }

    public void setSneak(int sneak) {
        this.sneak = sneak;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public void setCurrentExperience(int currentExperience) {
        this.currentExperience = currentExperience;
    }

    public int getExperienceNeeded() {
        return experienceNeeded;
    }

    public void setExperienceNeeded(int experienceNeeded) {
        this.experienceNeeded = experienceNeeded;
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

    public boolean hasKeyId(String id) {
        return getKeyIds() == null && getKeyIds().contains(id);
    }

}