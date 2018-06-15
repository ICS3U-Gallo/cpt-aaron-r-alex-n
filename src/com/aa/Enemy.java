package com.aa;

import java.util.ArrayList;
import static com.aa.GameUtils.BoundaryScale;

public class Enemy {

    private String type;
    private int maximumHp;
    private int currentHp;
    private int damage;
    private int attackRating;
    private int healingPotionDrop;
    private String keyDrop;
    private ArrayList<Enemy> spawn;


    public Enemy(String type, int maximumHp, int damage, int attackRating, int healingPotionDrop, String keyDrop) {
        setType(type);
        setMaximumHp(maximumHp);
        setCurrentHp(maximumHp);
        setDamage(damage);
        setAttackRating(attackRating);
        setHealingPotionDrop(healingPotionDrop);
        setKeyDrop(keyDrop);
        spawn = new ArrayList<Enemy>();
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private int getMaximumHp() { return maximumHp; }

    public void setMaximumHp(int maximumHp) { this.maximumHp = maximumHp; }

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

    public int getHealingPotionDrop() { return healingPotionDrop; }

    public void setHealingPotionDrop(int healingPotionDrop) { this.healingPotionDrop = healingPotionDrop; }

    public String getKeyDrop() { return keyDrop; }

    public void setKeyDrop(String keyDrop) { this.keyDrop = keyDrop; }

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

    public boolean isDead() { return ! isAlive(); }

    public void reset() {
        setCurrentHp(getMaximumHp());
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

    public int attemptAttack(Player p, boolean playerIsBlocking) {
        int val = GameUtils.getRandomBoundedValue();

        if (val < getAttackRating()) {
            return p.beingAttacked(this, playerIsBlocking);
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

    public boolean hasHealingPotionDrop() {
        return getHealingPotionDrop() > 0;
    }

    public boolean hasKeyDrop() {
        return getKeyDrop() != null && (! getKeyDrop().isEmpty());
    }

}
