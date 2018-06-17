/*
  Class representing a chest that can contain a healing potion and/or gold.
  This class also has an indicator that allows the game to launch a random
  encounter with an enemy if it is set to true.
 */
package com.aa;

//This creates and gives values to chests.
class Chest {
    private int gold;
    private int healingPotion;
    private boolean randomEncounter;

    //If the chest has a random encounter (meaning that boolean is true), then it causes a random enemy encounter.
    Chest(int gold, int healingPotion, boolean randomEncounter) {
        setGold(gold);
        setHealingPotion(healingPotion);
        setRandomEncounter(randomEncounter);
    }


    /* Now we use getters and setters for each variable in order to retrieve and give values to each variable,
    for each separate object */
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


    // The chest once it has been looted by the player.
    void empty() {
        setGold(0);
        setHealingPotion(0);
        setRandomEncounter(false);
    }

    // Returns if the chest still has things in it.
    boolean notEmpty() {
        return getGold() > 0 || getHealingPotion() > 0;
    }
}
