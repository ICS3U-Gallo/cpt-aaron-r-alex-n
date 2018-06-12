package com.aa;

public class Enemy {

    private String type;
    private int hp;
    private int currentHp;
    private int damage;
    private boolean alive;


    public Enemy(String t, int h, int d) {
        type = t;
        hp = h;
        currentHp = h;
        damage = d;
        alive = true;
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

    public int getCurrentHp() { return currentHp; }

    public void setCurrentHp(int currentHp) { this.currentHp = currentHp; }

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

    public void reset() {
        setAlive(true);
        setCurrentHp(getHp());
    }

}
