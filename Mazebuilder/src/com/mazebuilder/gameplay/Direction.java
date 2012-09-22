package com.mazebuilder.gameplay;

public enum Direction {
    
    LEFT(1),
    UP(2),
    RIGHT(4),
    DOWN(8);
    
    private int value;
    
    private Direction(int value) {
        this.value = value;
    }
    
    public int value() {
        return value;
    }
    
    public Direction reverse() {
        switch(value) {
            case 1:
                return RIGHT;
            case 2:
                return DOWN;
            case 4:
                return LEFT;
            case 8:
                return UP;
            default:
                throw new RuntimeException("Unknown value " + value);
        }
    }
    
}
