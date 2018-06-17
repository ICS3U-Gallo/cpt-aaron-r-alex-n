/*
 * Object that represents a boss enemy.
 * This class inherits most of its behaviour from the Enemy class, the only
 * difference is that Boss objects are treated differently by the game as
 * well as having an indicator used for the final boss.
 */

package com.aa;

class Boss extends Enemy {

    private boolean last;

    Boss(boolean last, String type, int maximumHp, int damage, int attackRating, int healingPotionDrop, String keyDrop, int goldDrop) {
        super(type, maximumHp, damage, attackRating, healingPotionDrop, keyDrop, goldDrop);
        setLast(last);
    }

    boolean isLast() {
        return last;
    }

    private void setLast(boolean last) {
        this.last = last;
    }

    String getTitle() {
        return getType() == null ? "" : getType();
    }
}
