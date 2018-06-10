package com.aa;

public class Enemy {

    private int id;
    private String type;
    private int hp;
    private int damage;
    private boolean alive;
    private int experience;

    public Enemy(int i, String t, int h, int d, boolean a, int e) {
        id = i;
        type = t;
        hp = h;
        damage = d;
        alive = a;
        experience = e;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    Enemy a = new Enemy(1, "Brittle Skeleton", 5, 2, true, 5);

    Enemy b = new Enemy(2, "Skeleton", 10, 4, true, 10);

    Enemy c = new Enemy(3, "Armoured Skeleton", 15, 5, true, 15);

    Enemy d = new Enemy(4, "Rotting Zombie", 2, 1, true, 5);

    Enemy e = new Enemy(5, "Zombified Knight", 10, 5, true, 10);

    Enemy f = new Enemy(6, "Large Rat",2, 2, true, 5);

    Enemy g = new Enemy(7, "Lich", 12, 10, true, 20);

    Enemy h = new Enemy(8, "Goblin", 5, 3, true, 5);

    Enemy i = new Enemy(9, "Goblin Archer", 5, 4, true, 5);

    Enemy j = new Enemy(10, "Goblin Champion", 7, 5, true, 10);

    Enemy k = new Enemy(11, "Troll", 25, 10, true, 20);

    Enemy l = new Enemy(12, "Giant Spider", 10, 3, true, 10);

    Enemy m = new Enemy(13, "Monk of Ashardalon", 5, 5, true, 5);

    Enemy n = new Enemy(14, "Sentinel of Ashardalon", 15, 6, true, 15);

    Enemy o = new Enemy(15, "Orc Warrior", 10, 4, true, 10);

    Enemy p = new Enemy(16, "Orc Archer", 10, 5, true, 10);

    Enemy q = new Enemy(17, "Orc Captain", 15, 7, true, 15);

    Enemy r = new Enemy(18, "Cockatrice", 15, 10, true, 20);

    Enemy s = new Enemy(19, "Basalisk", 20, 10, true, 20);

    Enemy t = new Enemy(20, "Wyvern", 20, 15, true, 25);

    Enemy u = new Enemy(21, "Werewolf", 10, 10,true, 15);

    Enemy v = new Enemy(22, "Water Elemental", 5, 10, true, 10);

    Enemy w = new Enemy(23, "Fire Elemental", 5, 15, true, 15);

    Enemy x = new Enemy(24, "Mimic", 20, 15, true, 30);

    Enemy y = new Enemy(25, "Manticore", 15, 20, true, 25);

    Enemy z = new Enemy(26, "Haunting Spirit", 10, 5, true, 10);

}
