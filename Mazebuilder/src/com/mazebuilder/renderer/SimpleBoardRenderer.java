package com.mazebuilder.renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.mazebuilder.gameplay.Location;

public final class SimpleBoardRenderer implements BoardRenderer {

    private static final int TILE_HEIGHT = 64;
    private static final int TILE_WIDTH = 64;
    private static final int WALL_SHORT_SIDE = 16;
    private static final int PLAYER_OVERFILL = 7;

    @Override
    public int tileHeight() {
        return TILE_HEIGHT;
    }

    @Override
    public int tileWidth() {
        return TILE_WIDTH;
    }

    @Override
    public int wallShortSideLength() {
        return WALL_SHORT_SIDE;
    }
    
    @Override
    public int playerOverfill() {
        return PLAYER_OVERFILL;
    }

    @Override
    public void drawTile(Graphics g, int x, int y) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
    }

    @Override
    public void drawWall(Graphics g, int x, int y, boolean horizontal) {
        g.setColor(Color.red);
        if (horizontal) {
            g.fillRoundRect(x, y, TILE_WIDTH, WALL_SHORT_SIDE, 4);
        } else {
            g.fillRoundRect(x, y, WALL_SHORT_SIDE, TILE_HEIGHT, 4);
        }
    }

    @Override
    public void drawNoWall(Graphics g, int x, int y, boolean horizontal) {
        g.setColor(Color.lightGray);
        if (horizontal) {
            g.fillRoundRect(x, y, TILE_WIDTH, WALL_SHORT_SIDE, 4);
        } else {
            g.fillRoundRect(x, y, WALL_SHORT_SIDE, TILE_HEIGHT, 4);
        }
    }

    @Override
    public void drawCorner(Graphics g, int x, int y) {
        g.setColor(Color.green);
        g.fillRoundRect(x, y, WALL_SHORT_SIDE, WALL_SHORT_SIDE, 8);
    }
}
