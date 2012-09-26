package com.mazebuilder.gameplay.board;

import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.gameplay.Location;
import com.mazebuilder.gameplay.players.Player;
import com.mazebuilder.renderer.Renderable;

public interface Board extends Renderable {

    /** Number of tiles in the horizontal board direction. **/
    int tilesAcross();

    /** Number of tiles in the vertical board direction. **/
    int tilesDown();

    /** Adds a new player at the given location. Will throw exceptions if adding an existing player. **/
    void addPlayerAtLocation(Player p, Location l);

    /** Returns the location of the given player. Uses identity (==) comparisons. **/
    Location getPlayerLocation(Player p);

    /**
     * Will return true if the move was completed. Does not jump walls. Uses identity (==)
     * comparisons.
     **/
    boolean movePlayer(Player p, Direction d);

    /**
     * Will return true if the move was completed. Does not jump walls. Uses identity (==)
     * comparisons.
     **/
    boolean movePlayerWithBonus(Player p, Direction d);

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

    /**
     * Return the location of the nearest tile to the pixel coordinates given
     * @return null if the cursor position is out of bounds
     */
    Location getTile(int x, int y);

    /**
     * Returns either the wall direction for the location given by getTile, or null if pixel location is not on a wall tile.
     */
    Direction getWallDirection(int x, int y);

}
