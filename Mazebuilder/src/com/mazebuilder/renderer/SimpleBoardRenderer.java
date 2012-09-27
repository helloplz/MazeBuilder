package com.mazebuilder.renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.mazebuilder.gameplay.Location;

public final class SimpleBoardRenderer implements BoardRenderer {

    private static final int TILE_HEIGHT = 50;
    private static final int TILE_WIDTH = 50;
    private static final int WALL_SHORT_SIDE = 30;
    private Image boardPiece; 
    private Image lakePiece;
    private Image bridgePiece;

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
    public void drawTile(Graphics g, int x, int y) {
        try {
            boardPiece = new Image("./assets/crateside.bmp");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
        g.setColor(Color.red);
        g.drawImage(boardPiece, x, y, Color.white);
    }

    @Override
    public void drawWall(Graphics g, int x, int y, boolean horizontal) {
        g.setColor(Color.lightGray);
        if (horizontal) {
            try {
                lakePiece = new Image("./assets/WaterHorz.jpg");
            } catch (SlickException e) {
                throw new RuntimeException(e);
            }
            g.drawImage(lakePiece, x, y, Color.white);
        } else {
            try {
                lakePiece = new Image("./assets/WaterVert.jpg");
            } catch (SlickException e) {
                throw new RuntimeException(e);
            }
            g.drawImage(lakePiece, x, y, Color.white);
        }    
    }
    @Override
    public void drawNoWall(Graphics g, int x, int y, boolean horizontal) {
        if (horizontal) {
            try {
                lakePiece = new Image("./assets/WaterWithBridgeHorz.jpg");
            } catch (SlickException e) {
                throw new RuntimeException(e);
            }
            g.drawImage(lakePiece, x, y, Color.white);
        } else {
            try {
                lakePiece = new Image("./assets/WaterWithBridgeVert.jpg");
            } catch (SlickException e) {
                throw new RuntimeException(e);
            }
            g.drawImage(lakePiece, x, y, Color.white);
        }
    }

    @Override
    public void drawCorner(Graphics g, int x, int y) {
//        g.setColor(Color.green);
//        g.fillRoundRect(x, y, WALL_SHORT_SIDE, WALL_SHORT_SIDE, 8);
        try {
            lakePiece = new Image("./assets/WaterEdge.jpg");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(lakePiece, x, y, Color.white);
    }
}
