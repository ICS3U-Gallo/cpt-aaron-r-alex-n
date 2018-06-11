package com.aa;

public class Room {

    // We need to declare private variables, that way they can only be called in this class

    private String name;
    private String type;
    private Room north;
    private Room east;
    private Room south;
    private Room west;
    private String message;
    private boolean locked;
    private String lockType;
    private String keyId;
    private String riddleQuestion;
    private String riddleAnswer;

    /* Now we use getters and setters for each variable in order to retrive and give values to each variable,
    for each separate object */

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLockType() {
        return lockType;
    }

    public void setLockType(String lockType) {
        this.lockType = lockType;
    }

    public boolean isLocked() { return locked; }

    public void setLocked(boolean locked) { this.locked = locked; }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getRiddleQuestion() {
        return riddleQuestion;
    }

    public void setRiddleQuestion(String riddleQuestion) {
        this.riddleQuestion = riddleQuestion;
    }

    public String getRiddleAnswer() {
        return riddleAnswer;
    }

    public void setRiddleAnswer(String riddleAnswer) {
        this.riddleAnswer = riddleAnswer;
    }

    public boolean isDoor() {
        return getType().equals("door");
    }

    public boolean isChamber() {
        return getType().equals("chamber");
    }

    public boolean isCorridor() {
        return getType().equals("corridor");
    }

    public boolean isLockTypeRiddle() {
        if (! isDoor()) {
            return false;
        }
        return "riddle".equals(getLockType());
    }
    public boolean isLockTypeKey() {
        if (! isDoor()) {
            return false;
        }
        return "key".equals(getLockType());
    }
}
