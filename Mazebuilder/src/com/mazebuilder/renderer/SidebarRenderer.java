package com.mazebuilder.renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.Image;

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
    static final int moveBarX = 195,
                     moveBarY = 15,
                     moveBarHeight = 45,
                     moveBarWidth = 150;
    static final int actionBarX = moveBarX,
                     actionBarY = moveBarY + moveBarHeight + 30,
                     actionBarHeight = moveBarHeight,
                     actionBarWidth = moveBarWidth;
    static final int mainHelpY = 375,
                     mainHelpHeight = 60,
                     mainHelpWidth = 360;
    static final int mainQuitY = 465,
                     mainQuitHeight = 60,
                     mainQuitWidth = 360;
    
    private static final Image mouse;
    private static final Image WASD;
    private static final Image destroyBridge;
    private static final Image jumpWater;
    
    static {
        try {
            mouse = new Image("./assets/Move.jpg");
            WASD = new Image("./assets/WASD.png");
            destroyBridge = new Image("./assets/DestroyBridge.jpg");
            jumpWater = new Image("./assets/Jump.png");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }
    
    static final Color darkGreen = new Color(0, 0.5f, 0);
    static final Color darkBlue = new Color(0, 0, 0.5f);
                     
    
    
    ChaserPlayer chaser;
    RunnerPlayer runner;
    
    Rectangle runnerInfoRect = new RoundedRectangle(0, 0, infoRectWidth, infoRectHeight, 4);
    Rectangle runnerMoveBar = new RoundedRectangle(moveBarX, moveBarY, moveBarWidth, moveBarHeight,3);
    Rectangle runnerActionBar = new RoundedRectangle(actionBarX, actionBarY, actionBarWidth, actionBarHeight,3);
    Rectangle runnerQuit = new RoundedRectangle(forfeitOffsetX-10, forfeitOffsetY, forfeitWidth, forfeitHeight, 3);
    Rectangle chaserInfoRect = new RoundedRectangle(0, 0, infoRectWidth, infoRectHeight, 4);
    Rectangle chaserMoveBar = new RoundedRectangle(moveBarX, moveBarY, moveBarWidth, moveBarHeight,3);
    Rectangle chaserActionBar = new RoundedRectangle(actionBarX, actionBarY, actionBarWidth, actionBarHeight,3);
    Rectangle chaserQuit = new RoundedRectangle(forfeitOffsetX-10, forfeitOffsetY, forfeitWidth, forfeitHeight, 3);
    
    public SidebarRenderer (ChaserPlayer chaser, RunnerPlayer runner) {
        this.chaser = chaser;
        this.runner = runner;
    }
    
    public void drawSidebar(Graphics g, double runnerMove, double runnerAction, double chaserMove, double chaserAction) {
        g.setColor(new Color(0.5f, 1.0f, 0.5f));
        
            g.setLineWidth(4);
            g.fill(runnerInfoRect);
            
            g.setLineWidth(1);
            runner.render(g, playerX, playerY, playerWidth, playerHeight);
            if (runnerMove > 1) {
                g.setColor(Color.yellow);
                g.fillRect(moveBarX, moveBarY, moveBarWidth, moveBarHeight);
            } else {
                g.setColor(Color.gray);
                g.fillRect(moveBarX, moveBarY, moveBarWidth*(float)runnerMove, moveBarHeight);
            }
            if (runnerAction > 1) {
                g.setColor(Color.yellow);
                g.fillRect(actionBarX, actionBarY, actionBarWidth, actionBarHeight);
            } else {
                g.setColor(Color.gray);
                g.fillRect(actionBarX, actionBarY, actionBarWidth*(float)runnerAction, actionBarHeight);
            }
            g.setColor(darkGreen);
            g.drawString("CLICK\nto forfeit", forfeitOffsetX, forfeitOffsetY+5);
            g.setLineWidth(2);
            g.draw(runnerMoveBar);
            g.draw(runnerActionBar);
            g.draw(runnerQuit);
            
            g.drawImage(mouse, (moveBarX-3*forfeitOffsetX), moveBarY-1);
            g.drawImage(destroyBridge, (moveBarX-3*forfeitOffsetX), actionBarY-1);

            
        g.setColor(new Color(0.5f, 0.5f, 1.0f));
        g.pushTransform();
        g.translate(0, infoRectHeight +25);
        
            g.setLineWidth(4);
            g.fill(chaserInfoRect);
            
            g.setLineWidth(1);
            chaser.render(g, playerX, playerY, playerWidth, playerHeight);
            if (chaserMove > 1) {
                g.setColor(Color.yellow);
                g.fillRect(moveBarX, moveBarY, moveBarWidth, moveBarHeight);
            } else {
                g.setColor(Color.gray);
                g.fillRect(moveBarX, moveBarY, moveBarWidth*(float)chaserMove, moveBarHeight);
            }
            if (chaserAction > 1) {
                g.setColor(Color.yellow);
                g.fillRect(actionBarX, actionBarY, actionBarWidth, actionBarHeight);
            } else {
                g.setColor(Color.gray);
                g.fillRect(actionBarX, actionBarY, actionBarWidth*(float)chaserAction, actionBarHeight);
            }
            g.setColor(darkBlue);
            g.drawString("PRESS 't'\nto forfeit", forfeitOffsetX, forfeitOffsetY+5);
            g.setLineWidth(2);
            g.draw(chaserMoveBar);
            g.draw(chaserActionBar);
            g.draw(chaserQuit);
        
            g.drawImage(WASD, (moveBarX-3*forfeitOffsetX)-30, moveBarY-1);
            g.drawImage(jumpWater,(moveBarX-3*forfeitOffsetX)-20, actionBarY-1);
        g.popTransform();
        
            g.setColor(Color.lightGray);
            g.fillRoundRect(0, mainHelpY, mainHelpWidth, mainHelpHeight, 4);
            g.fillRoundRect(0, mainQuitY, mainQuitWidth, mainQuitHeight, 4);
            g.setColor(Color.darkGray);
            g.drawString("HELP / PAUSE", 120, mainHelpY+20);
            g.drawString("MENU / QUIT", 125, mainQuitY+20);
        g.setColor(Color.white);
    }
    
    public boolean runnerForfeitButtonClicked(int x, int y) {
        return x >= runnerQuit.getMinX() && x <= runnerQuit.getMaxX() &&
               y >= runnerQuit.getMinY() && y <= runnerQuit.getMaxY();
    }
    
    public boolean helpButtonClicked(int x, int y) {
        return x >= 0 && x <= mainHelpWidth && 
               y >= mainHelpY && y <= mainHelpY + mainHelpHeight;
    }
    
    public boolean quitButtonClicked(int x, int y) {
        return x >= 0 && x <= mainQuitWidth && 
               y >= mainQuitY && y <= mainQuitY + mainQuitHeight;
    }
}
