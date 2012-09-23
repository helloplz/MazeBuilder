package com.mazebuilder.renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public final class RunnerPlayerRenderer implements PlayerRenderer {

    private final Image player;

    public RunnerPlayerRenderer() {
        try {
            player = new Image("\\assets\\Character Cat Girl.png");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void drawPlayer(Graphics g, int x, int y, int tileWidth, int tileHeight) {
        g.setColor(Color.green);
        g.drawImage(player, x, y, x + tileWidth, y + tileHeight, 0, 50, 101, 151);
        // g.fillRoundRect(x, y, tileWidth, tileHeight, Math.min(tileHeight, tileWidth) / 2);
    }
}
