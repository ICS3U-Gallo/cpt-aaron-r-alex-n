package com.aa;

import java.util.ArrayList;

class Enemy {

    private String type;
    private boolean boss;
    private int maximumHp;
    private int currentHp;
    private int damage;
    private int attackRating;
    private int healingPotionDrop;
    private String keyDrop;
    private int goldDrop;
    private ArrayList<Enemy> spawn;


    Enemy(String type, int maximumHp, int damage, int attackRating, int healingPotionDrop, String keyDrop, int goldDrop) {
        setType(type);
        setMaximumHp(maximumHp);
        setCurrentHp(maximumHp);
        setDamage(damage);
        setAttackRating(attackRating);
        setHealingPotionDrop(healingPotionDrop);
        setKeyDrop(keyDrop);
        setGoldDrop(0);
        setSpawn(new ArrayList<Enemy>());
    }


    String getType() { return type; }

    private void setType(String type) { this.type = type; }

    boolean isBoss() { return boss; }

    void setBoss(boolean boss) { this.boss = boss; }

    private int getMaximumHp() { return maximumHp; }

    private void setMaximumHp(int maximumHp) { this.maximumHp = maximumHp; }

    int getCurrentHp() { return currentHp; }

    private void setCurrentHp(int currentHp) { this.currentHp = currentHp; }

    int getDamage() { return damage; }

    private void setDamage(int damage) { this.damage = damage; }

    private int getAttackRating() { return attackRating; }

    private void setAttackRating(int attackRating) { this.attackRating = attackRating; }

    int getHealingPotionDrop() { return healingPotionDrop; }

    private void setHealingPotionDrop(int healingPotionDrop) { this.healingPotionDrop = healingPotionDrop; }

    String getKeyDrop() { return keyDrop; }

    private void setKeyDrop(String keyDrop) { this.keyDrop = keyDrop; }

    int getGoldDrop() { return goldDrop; }

    private void setGoldDrop(int goldDrop) { this.goldDrop = goldDrop; }

    ArrayList<Enemy> getSpawn() { return spawn; }

    private void setSpawn(ArrayList<Enemy> spawn) { this.spawn = spawn; }

    boolean hasSpawn() {
        return getSpawn() != null && ! getSpawn().isEmpty();
    }

    void addSpawn(Enemy e) {
        getSpawn().add(e);
    }

    boolean isAlive() {
        return getCurrentHp() > 0;
    }

    boolean isDead() { return ! isAlive(); }

    void reset() {
        setCurrentHp(getMaximumHp());
        if (hasSpawn()) {
            for (Enemy s : getSpawn()) {
                s.reset();
            }
        }
    }

    void takeDamage(int d) {
        setCurrentHp(getCurrentHp() - d);
    }

    int attemptAttack(Player p, boolean playerIsBlocking) {
        int val = GameUtils.getRandomBoundedValue();

        if (val < getAttackRating()) {
            return p.beingAttacked(this, playerIsBlocking);
        }
        return 0;
    }

    ArrayList<Enemy> asFoes() {
        ArrayList<Enemy> result = new ArrayList<Enemy>();
        result.add(this);
        if (hasSpawn())
            result.addAll(getSpawn());

        return result;
    }

    boolean hasHealingPotionDrop() {
        return getHealingPotionDrop() > 0;
    }

    boolean hasKeyDrop() {
        return getKeyDrop() != null && (! getKeyDrop().isEmpty());
    }
    boolean hasGoldDrop() {
        return getGoldDrop() > 0;
    }

    String getTitle(boolean capitalized) {

        String t = (getType() == null || getType().isEmpty()) ? "Monster" : getType();
        if (isBoss()) {
            return t;
        }
        String vowels = "AaEeIiOoUu";
        String prefix;
        if (vowels.contains(getType().substring(0, 1))) {
            prefix = capitalized ? "An " : "an ";
        }
        else {
            prefix = capitalized ? "A " : "a ";
        }

        return prefix + t;
    }

}
