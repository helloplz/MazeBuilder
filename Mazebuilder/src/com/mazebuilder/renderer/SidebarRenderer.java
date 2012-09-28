package com.mazebuilder.renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;

import com.mazebuilder.gameplay.players.ChaserPlayer;
import com.mazebuilder.gameplay.players.Player;
import com.mazebuilder.gameplay.players.RunnerPlayer;

public class SidebarRenderer {

    static final int infoRectHeight = 150;
    static final int infoRectWidth = 360;
    static final int forfeitOffsetX = 15,
                     forfeitOffsetY = 90,
                     forfeitHeight = 45,
                     forfeitWidth = 120;
    static final int playerX = 15,
                     playerY = 15,
                     playerHeight = 64,
                     playerWidth = 64;
    static final int moveBarX = 150,
                     moveBarY = 15,
                     moveBarHeight = 45,
                     moveBarWidth = 150;
    static final int actionBarX = moveBarX,
                     actionBarY = 75,
                     actionBarHeight = moveBarHeight,
                     actionBarWidth = moveBarWidth;
                     
    
    ChaserPlayer chaser;
    RunnerPlayer runner;
    
    Rectangle runnerInfoRect = new RoundedRectangle(0, 0, infoRectWidth, infoRectHeight, 4);
    Rectangle runnerMoveBar = new RoundedRectangle(moveBarX, moveBarY, moveBarWidth, moveBarHeight,3);
    Rectangle runnerActionBar = new RoundedRectangle(actionBarX, actionBarY, actionBarWidth, actionBarHeight,3);
    Rectangle runnerQuit = new RoundedRectangle(forfeitOffsetX, forfeitOffsetY, forfeitWidth, forfeitHeight, 3);
    Rectangle chaserInfoRect = new RoundedRectangle(0, 0, infoRectWidth, infoRectHeight, 4);
    Rectangle chaserMoveBar = new RoundedRectangle(actionBarX, actionBarY, actionBarWidth, actionBarHeight,3);
    Rectangle chaserQuit = new RoundedRectangle(forfeitOffsetX, forfeitOffsetY, forfeitWidth, forfeitHeight, 3);
    
    public SidebarRenderer (ChaserPlayer chaser, RunnerPlayer runner) {
        this.chaser = chaser;
        this.runner = runner;
    }
    
    public void drawSidebar(Graphics g) {
        g.setColor(Color.green);
        
            g.setLineWidth(4);
            g.draw(runnerInfoRect);
            g.setLineWidth(3);
            
            runner.render(g, playerX, playerY, playerWidth, playerHeight);
            g.drawString("CLICK\nto forfeit", forfeitOffsetX+10, forfeitOffsetY+5);
            g.draw(runnerMoveBar);
            g.draw(runnerActionBar);
            g.draw(runnerQuit);
        
        g.setColor(Color.blue);
        g.pushTransform();
        g.translate(0, infoRectHeight +25);
        
            g.draw(chaserInfoRect);
        
        g.popTransform();
    }
    
    public void render(Graphics g, int xOffset, int yOffset) {
        g.pushTransform();
        g.translate(650, 64);
        g.popTransform();
    }

}
