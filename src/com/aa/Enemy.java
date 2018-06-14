package com.aa;

import java.util.ArrayList;
import static com.aa.GameUtils.BoundaryScale;

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

    private int getHp() { return hp; }

    public void setHp(int hp) { this.hp = hp; }

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

    public boolean takeDamage(int d) {
        setCurrentHp(getCurrentHp() - d);
        return getCurrentHp() > 0;
    }

    public int attemptAttack(Player p) {
        int val = GameUtils.getRandomBoundedValue();

        if (val < getAttackRating()) {
            p.takeDamage(getDamage());
            return getDamage();
        }
        return 0;
    }

    public ArrayList<Enemy> asFoes() {
        ArrayList<Enemy> result = new ArrayList<Enemy>();
        result.add(this);
        if (hasSpawn())
            result.addAll(getSpawn());

        return result;
    }

}
