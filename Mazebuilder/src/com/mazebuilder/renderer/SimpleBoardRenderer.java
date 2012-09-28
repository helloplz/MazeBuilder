package com.mazebuilder.renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;

import com.mazebuilder.gameplay.players.ChaserPlayer;
import com.mazebuilder.gameplay.players.RunnerPlayer;

public final class SimpleBoardRenderer implements BoardRenderer {

    private static final int TILE_HEIGHT = 50;
    private static final int TILE_WIDTH = 50;
    private static final int WALL_SHORT_SIDE = 30;
    private static final int BOARDER_PLACE = 34;

    private static final Image boardPiece;
    private static final Image lakePieceHorizontal, lakePieceVertical;
    private static final Image bridgePieceHorizontal, bridgePieceVertical;
    private static final Image cornerPiece;
    private static final Image boarder;

    static {
        try {
            boardPiece = new Image("./assets/crateside.bmp");
            lakePieceHorizontal = new Image("./assets/WaterHorz.jpg");
            lakePieceVertical = new Image("./assets/WaterVert.jpg");
            bridgePieceHorizontal = new Image("./assets/WaterWithBridgeHorz.jpg");
            bridgePieceVertical = new Image("./assets/WaterWithBridgeVert.jpg");
            cornerPiece = new Image("./assets/WaterEdge.jpg");
            boarder = new Image("./assets/boarder.png");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }

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
        g.setColor(Color.red);
        g.drawImage(boardPiece, x, y, Color.white);
    }

    @Override
    public void drawWall(Graphics g, int x, int y, boolean horizontal) {
        g.setColor(Color.lightGray);
        if (horizontal) {
            g.drawImage(lakePieceHorizontal, x, y, Color.white);
        } else {
            g.drawImage(lakePieceVertical, x, y, Color.white);
        }
    }

    @Override
    public void drawNoWall(Graphics g, int x, int y, boolean horizontal) {
        if (horizontal) {
            g.drawImage(bridgePieceHorizontal, x, y, Color.white);
        } else {
            g.drawImage(bridgePieceVertical, x, y, Color.white);
        }
    }

    @Override
    public void drawCorner(Graphics g, int x, int y) {
        // g.setColor(Color.green);
        // g.fillRoundRect(x, y, WALL_SHORT_SIDE, WALL_SHORT_SIDE, 8);
        g.drawImage(cornerPiece, x, y, Color.white);
    }
    
    @Override
    public void drawBoarder(Graphics g) {
        g.drawImage(boarder, BOARDER_PLACE, BOARDER_PLACE,Color.white);
    }
    
}
