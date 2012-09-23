package com.mazebuilder.renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public final class RunnerPlayerRenderer implements PlayerRenderer {

    @Override
    public void drawPlayer(Graphics g, int x, int y, int tileWidth, int tileHeight) {
        g.setColor(Color.green);
        g.fillRoundRect(x, y, tileWidth, tileHeight, Math.min(tileHeight, tileWidth) / 2);
    }
}
