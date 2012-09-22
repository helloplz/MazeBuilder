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
    
}
