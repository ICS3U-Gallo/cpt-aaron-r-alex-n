package com.aa;

public class Boss {

    private int id;
    private String type;
    private int hp;
    private int damage;
    private boolean alive;
    private int experience;
    private String loot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getLoot() {
        return loot;
    }

    public void setLoot(String loot) {
        this.loot = loot;
    }

    public Boss(int i, String t, int h, int d, boolean a, int e, String l) {
        id = i;
        type = t;
        hp = h;
        damage = d;
        alive = a;
        experience = e;
        loot = l;
    }
}