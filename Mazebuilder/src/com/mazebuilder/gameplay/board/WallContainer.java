package com.mazebuilder.gameplay.board;

import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.gameplay.Location;

public interface WallContainer {

    public void putWall(Location l, Direction d);

    /**
     * Returns if there is a wall at the specified location and direction, or false otherwise.
     */
    public boolean isWall(Location l, Direction d);

    public int[][] getWalls();

}
