package com.mazebuilder.gameplay;

import com.mazebuilder.renderer.Renderable;

public interface Board extends Renderable {

    /** Number of tiles in the horizontal board direction. **/
    int tilesAcross();

    /** Number of tiles in the vertical board direction. **/
    int tilesDown();

    /** Returns the location of the given player. Uses identity (==) comparisons. **/
    Location getPlayerLocation(Player p);

    /**
     * Returns the new location of the given player. Will return the same location if the move is impossible. Does not jump walls. Uses identity (==)
     * comparisons.
     **/
    Location movePlayer(Player p, Direction d);

    /**
     * Returns the new location of the given player. Will return the same location if the move is impossible. Does not jump walls. Uses identity (==)
     * comparisons.
     **/
    Location movePlayerWithBonus(Player p, Direction d);

    /**
     * Returns the new location of the given player. Will return the same location if the move is impossible (player cannot jump or no wall to jump).
     * Uses identity (==) comparisons.
     **/
    Location jumpPlayer(Player p, Direction d, Direction... bonusesToSpend);

    /**
     * Sets a wall on the given tile, in the direction given. Returns true if the wall was successfully set, false otherwise (if a wall is already
     * there).
     */
    boolean putWall(Location l, Direction d);

}
