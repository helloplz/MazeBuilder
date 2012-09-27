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
    private Rectangle PlayButton;
    private Rectangle HelpButton;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Hello, Mazebuilder! [MainMenu]", 50, 100);
        g.draw(PlayButton = new Rectangle(48, 148, 100, 48));
        g.draw(HelpButton = new Rectangle(48, 198, 100, 48));
        g.drawString("Play", 50, 150);
        g.drawString("Help", 50, 200);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (PlayButton.contains(x, y)) {
            game.enterState(GameplayState.ID);
        }
        if (HelpButton.contains(x, y)) {
            game.enterState(HelpState.ID);
        }

    }

    @Override
    public int getID() {
        return ID;
    }

}
