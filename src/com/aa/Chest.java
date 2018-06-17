/*
 * Class representing a chest that can contain a healing potion and/or gold.
 * This class also has an indicator that allows the game to launch a random
 * encounter with an enemy if it is set to true.
 */
package com.aa;

class Chest {
    private int gold;
    private int healingPotion;
    private boolean randomEncounter;

    Chest(int gold, int healingPotion, boolean randomEncounter) {
        setGold(gold);
        setHealingPotion(healingPotion);
        setRandomEncounter(randomEncounter);
    }

    int getGold() {
        return gold;
    }

    private void setGold(int gold) {
        this.gold = gold;
    }

    int getHealingPotion() {
        return healingPotion;
    }

    private void setHealingPotion(int healingPotion) {
        this.healingPotion = healingPotion;
    }

    boolean isRandomEncounter() {
        return randomEncounter;
    }

    private void setRandomEncounter(boolean randomEncounter) {
        this.randomEncounter = randomEncounter;
    }


    boolean hasGold() {
        return getGold() > 0;
    }

    boolean hasHealingPotion() {
        return getHealingPotion() > 0;
    }

    void empty() {
        setGold(0);
        setHealingPotion(0);
        setRandomEncounter(false);
    }

    boolean notEmpty() {
        return getGold() > 0 || getHealingPotion() > 0;
    }
}
