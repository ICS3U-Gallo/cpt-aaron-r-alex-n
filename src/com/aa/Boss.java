/*
  Object that represents a boss enemy.
  This class inherits most of its behaviour from the Enemy class, the only
  difference is that Boss objects are treated differently by the game as
  well as having an indicator used for the final boss.
 */

package com.aa;

class Boss extends Enemy {

    private boolean last;

    Boss(boolean last, String type, int maximumHp, int damage, int attackRating, int healingPotionDrop, String keyDrop, int goldDrop) {
        super(type, maximumHp, damage, attackRating, healingPotionDrop, keyDrop, goldDrop);
        setLast(last);
    }

    /* isLast() is used to check if that boss is the last boss of the game, this is used
    because after defeating the last boss, the game automatically ends.
     */
    boolean isLast() {
        return last;
    }

    private void setLast(boolean last) {
        this.last = last;
    }

    // getTitle() prints the name of the boss
    String getTitle() {
        return getType() == null ? "" : getType();
    }
}
