package com.aa;

public class Boss extends Enemy {

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
