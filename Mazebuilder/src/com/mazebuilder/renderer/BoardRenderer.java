package com.mazebuilder.renderer;

import org.newdawn.slick.Graphics;

import com.mazebuilder.gameplay.Player;

public interface BoardRenderer {

    int tileHeight();

    int tileWidth();

    int wallShortSideLength();

    /**
     * Draw the sprite for a tile.
     */
    void drawTile(Graphics g, int x, int y);

    /**
     * Draw the sprite for a wall.
     * 
     * If horizontal is set to true, then width and height are specified by tileWidth() and wallHeight(). Otherwise, width and height are specified by
     * wallWidth() and tileHeight().
     */
    void drawWall(Graphics g, int x, int y, boolean horizontal);

    /**
     * Draw the sprite for an empty (potential) wall location.
     * 
     * If horizontal is set to true, then width and height are specified by tileWidth() and wallHeight(). Otherwise, width and height are specified by
     * wallWidth() and tileHeight().
     */
    void drawNoWall(Graphics g, int x, int y, boolean horizontal);

    /**
     * Draw the sprite for a corner (the intersection of walls). Width and height are specified by wallWidth() and wallHeight(), respectively.
     */
    void drawCorner(Graphics g, int x, int y);

    /**
     * Draw the sprite for the given player.
     */
    void drawPlayer(Player p, Graphics g, int x, int y);
}
