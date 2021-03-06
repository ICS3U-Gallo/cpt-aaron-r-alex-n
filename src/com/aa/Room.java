/*
  This class represents a room on the map of the game.
  Rooms can be doors, chambers and corridors. Chambers and corridors
  are treated pretty much the same way, but doors are treated
  differently. Doors have locks and different means of unlocking.
  Rooms can hold chest objects as well as have Boss objects.
  All that is setup upon initialization of the room structure by the game.
 */
package com.aa;

// The North South East and West are the variables that allow movement.
class Room {
    private String name;
    private String type;
    private Room north;
    private Room east;
    private Room south;
    private Room west;
    private String message;
    private Chest chest;
    private Boss boss;
    private boolean locked;
    private String lockType;
    private String key;
    private String riddleQuestion;
    private String riddleAnswer;

    private Room() {
        super();
    }

    static Room getNewChamber(String name) {
        Room r = new Room();
        r.setType("chamber");
        r.setName(name);
        return r;
    }

    static Room getNewCorridor(String name) {
        Room r = new Room();
        r.setType("corridor");
        r.setName(name);
        return r;
    }

    static Room getNewLockedDoor(String name) {
        Room r = new Room();
        r.setType("door");
        r.setName(name);
        r.setLocked(true);
        return r;
    }

    /* Now we use getters and setters for each variable in order to retrieve and give values to each variable,
    for each separate object */
    String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    Room getNorth() {
        return north;
    }

    void setNorth(Room north) {
        this.north = north;
    }

    Room getEast() {
        return east;
    }

    void setEast(Room east) {
        this.east = east;
    }

    Room getSouth() {
        return south;
    }

    void setSouth(Room south) {
        this.south = south;
    }

    Room getWest() {
        return west;
    }

    void setWest(Room west) {
        this.west = west;
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    Chest getChest() {
        return chest;
    }

    void setChest(Chest chest) {
        this.chest = chest;
    }

    Boss getBoss() {
        return boss;
    }

    void setBoss(Boss boss) {
        this.boss = boss;
    }

    private String getLockType() {
        return lockType;
    }

    private void setLockType(String lockType) {
        this.lockType = lockType;
    }

    boolean isLocked() {
        return locked;
    }

    private void setLocked(boolean locked) {
        this.locked = locked;
    }

    String getKey() {
        return key;
    }

    void setKey(String key) {
        this.key = key;
    }

    String getRiddleQuestion() {
        return riddleQuestion;
    }

    void setRiddleQuestion(String riddleQuestion) {
        this.riddleQuestion = riddleQuestion;
    }

    String getRiddleAnswer() {
        return riddleAnswer;
    }

    void setRiddleAnswer(String riddleAnswer) {
        this.riddleAnswer = riddleAnswer;
    }

    boolean isDoor() {
        return getType().equals("door");
    }

    boolean isChamber() {
        return getType().equals("chamber");
    }

    boolean isCorridor() {
        return getType().equals("corridor");
    }



    // For doors only. Make the door have a lock type of riddle.
    void setLockTypeRiddle() {
        if (isDoor()) {
            setLockType("riddle");
        }
    }


    // For doors only. Make the door have a lock type of key.
    void setLockTypeKey() {
        if (isDoor()) {
            setLockType("key");
        }
    }


    // If it's a door and the ;ock type is riddle.
    boolean isLockTypeRiddle() {
        if (! isDoor()) {
            return false;
        }
        return "riddle".equals(getLockType());
    }


    // If it's a door and the lock type is key.
    boolean isLockTypeKey() {
        if (! isDoor()) {
            return false;
        }
        return "key".equals(getLockType());
    }


    // Only for doors. Sets the value of unlock to false.
    void unlock() {
        if (!isDoor()) return;
        setLocked(false);
    }


    // If the room has a chest, and the chest is not empty, returns true, otherwise return false.
    boolean hasChest() {
        return getChest() != null && getChest().notEmpty();
    }

    // If the room has a boss, and the boss is alive, returns true, otherwise returns false.
    boolean hasBoss() {
        return getBoss() != null && getBoss().isAlive();
    }

    // Only for doors. If the door requires a key, checks if the key exists.
    boolean hasKey() {
        return isDoor() && isLockTypeKey() && (getKey() != null);
    }
}
