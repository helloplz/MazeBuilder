package com.mazebuilder.gameplay;

public interface WallContainer {
    
    public void putWall(Location l, Direction d);
    
    /**
     * Returns if there is a wall at the specified location and direction, or false otherwise.
     */
    public boolean isWall(Location l, Direction d);
    
}
