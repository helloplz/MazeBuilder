package com.mazebuilder.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The main menu loop.
 * 
 * @author dxiao
 */
public class MainMenuState extends BasicGameState {

    static public final int ID = 0;

    private StateBasedGame game;
    private Rectangle realTimeMode;
    private Rectangle turnBasedMode;
    private Rectangle credits;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Hello, Mazebuilder! [MainMenu]", 50, 100);
        g.draw(realTimeMode = new Rectangle(48, 148, 200, 48));
        g.draw(turnBasedMode = new Rectangle(48, 198, 200, 48));
        g.draw(turnBasedMode = new Rectangle(48, 248, 200, 48));
        g.drawString("Play Real Time Mode", 50, 150);
        g.drawString("Play Turn Based Mode", 50, 200);
        g.drawString("Credits", 50, 250);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (realTimeMode.contains(x, y)) {
            game.enterState(GameplayState.ID);
        }
        if (turnBasedMode.contains(x, y)) {
            game.enterState(HelpState.ID);
        }
        
        //TODO: "Credits Page"

    }

    @Override
    public int getID() {
        return ID;
    }

}
