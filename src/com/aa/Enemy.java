/*
  Class that represents an enemy in the game.
  It has variables that hold different attributes that allow for different
  results when dealing with enemies by the game. Enemies can have spawn,
  which is a collection of more Enemy objects that are used in a random encounter.
 */
package com.aa;

import java.util.ArrayList;


class Enemy {
    private String type;
    private int maximumHp;
    private int currentHp;
    private int damage;
    private int attackRating;
    private int healingPotionDrop;
    private String keyDrop;
    private int goldDrop;
    private ArrayList<Enemy> spawn;

    /*
    Creates an Enemy instance with all the required parameters.
    The array list are the summones of the enemy, meaning that the enemy can spawn in other enemies.
     */
    Enemy(String type, int maximumHp, int damage, int attackRating, int healingPotionDrop, String keyDrop, int goldDrop) {
        setType(type);
        setMaximumHp(maximumHp);
        setCurrentHp(maximumHp);
        setDamage(damage);
        setAttackRating(attackRating);
        setHealingPotionDrop(healingPotionDrop);
        setKeyDrop(keyDrop);
        setGoldDrop(goldDrop);
        setSpawn(new ArrayList<>());
    }

    /* Now we use getters and setters for each variable in order to retrieve and give values to each variable,
    for each separate object */
    String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    private int getMaximumHp() {
        return maximumHp;
    }

    private void setMaximumHp(int maximumHp) {
        this.maximumHp = maximumHp;
    }

    int getCurrentHp() {
        return currentHp;
    }

    private void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    int getDamage() {
        return damage;
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }

    private int getAttackRating() {
        return attackRating;
    }

    private void setAttackRating(int attackRating) {
        this.attackRating = attackRating;
    }

    int getHealingPotionDrop() {
        return healingPotionDrop;
    }

    private void setHealingPotionDrop(int healingPotionDrop) {
        this.healingPotionDrop = healingPotionDrop;
    }

    String getKeyDrop() {
        return keyDrop;
    }

    private void setKeyDrop(String keyDrop) {
        this.keyDrop = keyDrop;
    }

    int getGoldDrop() {
        return goldDrop;
    }

    private void setGoldDrop(int goldDrop) {
        this.goldDrop = goldDrop;
    }

    ArrayList<Enemy> getSpawn() {
        return spawn;
    }

    private void setSpawn(ArrayList<Enemy> spawn) {
        this.spawn = spawn;
    }


    // Returns true if the enemy has summons.
    boolean hasSpawn() {
        return getSpawn() != null && ! getSpawn().isEmpty();
    }

    // Add the parameter "enemy" as one of the spawns or summons.
    void addSpawn(Enemy e) {
        getSpawn().add(e);
    }


    // Returns true if is alive (if hp > 0)
    boolean isAlive() {
        return getCurrentHp() > 0;
    }


    boolean isDead() {
        return ! isAlive();
    }



    /*
    Sets the health to maximum health, as well as for spawns, if they exist.
    This method is useful because the list of enemies in the GameUtils class is reused.
     */
    void reset() {
        setCurrentHp(getMaximumHp());
        if (hasSpawn()) {
            for (Enemy s : getSpawn()) {
                s.reset();
            }
        }
    }



    // Reduces the current health of the enemy by the amount of damage being passed.
    void takeDamage(int d) {
        setCurrentHp(getCurrentHp() - d);
    }


    // The enemy attepts an attack, that will only pass if it is below the the attack rating.
    int attemptAttack(Player p, boolean playerIsBlocking) {
        int val = GameUtils.getRandomBoundedValue(99);

        if (val < getAttackRating()) {
            // Returns the player being attacked.
            return p.beingAttacked(this, playerIsBlocking);
        }
        return 0;
    }


    /*
    Returns the enemy and its spawn in one collection.
     */
    ArrayList<Enemy> asFoes() {
        ArrayList<Enemy> result = new ArrayList<>();
        result.add(this);
        if (hasSpawn())
            result.addAll(getSpawn());

        return result;
    }


    // Returns true if it drops a healing potion which has to be greater than 0.
    boolean hasHealingPotionDrop() {
        return getHealingPotionDrop() > 0;
    }



    // Returns true if the enemy drops a key, as long as the key isn't null or empty string.
    boolean hasKeyDrop() {
        return getKeyDrop() != null && (! getKeyDrop().isEmpty());
    }



    // Drops gold which is then picked up by the player.
    boolean hasGoldDrop() {
        return getGoldDrop() > 0;
    }

    /*
    This prints the way to describe the enemy.
    If the enemy contains a vowel as the first letter in it's name, the game prints out "an", for example "an orc".
    Otherwise, it prints out an "a", like "a skeleton".
     */
    String getTitle(boolean capitalized) {
        String t;
        if (getType() == null || getType().isEmpty()) {
            t = "Monster";
        } else {
            t = getType();
        }
        String prefix;
        if ("AaEeIiOoUu".contains(t.substring(0, 1))) {
            if (capitalized) {
                prefix = "An ";
            } else {
                prefix = "an ";
            }
        }
        else {
            if (capitalized) {
                prefix = "A ";
            } else {
                prefix = "a ";
            }
        }
        return prefix + t;
    }

}
