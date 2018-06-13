package com.aa;

import java.util.ArrayList;
import java.util.Random;

public class Enemy {

    private String type;
    private int hp;
    private int currentHp;
    private int damage;
    private int attackRating;
    private ArrayList<Enemy> spawn;


    public Enemy(String t, int h, int d, int ar) {
        type = t;
        hp = h;
        currentHp = h;
        damage = d;
        attackRating = ar;
        spawn = new ArrayList<Enemy>();
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

    public int getAttackRating() { return attackRating; }

    public void setAttackRating(int attackRating) { this.attackRating = attackRating; }

    public ArrayList<Enemy> getSpawn() { return spawn; }

    public void setSpawn(ArrayList<Enemy> spawn) { this.spawn = spawn; }

    public boolean hasSpawn() {
        return getSpawn() != null && ! getSpawn().isEmpty();
    }

    public void addSpawn(Enemy e) {
        getSpawn().add(e);
    }

    public boolean isAlive() {
        return getCurrentHp() > 0;
    }

    public void reset() {
        setCurrentHp(getHp());
        if (hasSpawn()) {
            for (Enemy s : getSpawn()) {
                s.reset();
            }
        }
    }

    public void takeDamage(int d) {
        setCurrentHp(getCurrentHp() - d);
        if (getCurrentHp() < 1) {
            /**
             * TODO
             */
        }
    }

    public int attemptAttack(Player p) {
        Random rand = new Random();
        int val = rand.nextInt(100) + 1;

        if (val > (100 - getAttackRating())) {
            p.takeDamage(getDamage());
            return getDamage();
        }
        else {
            return 0;
        }
    }

}
