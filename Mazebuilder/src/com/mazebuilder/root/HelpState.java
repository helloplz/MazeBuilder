package com.mazebuilder.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HelpState extends BasicGameState {
    static public final int ID = 4;

    private StateBasedGame game;

    private Rectangle MenuButton;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("HINT: the cake is a lie...", 50, 100);
        g.draw(MenuButton = new Rectangle(48, 148, 100, 48));
        g.drawString("Main Menu", 50, 150);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (MenuButton.contains(x, y)) {
            game.enterState(MainMenuState.ID);
        }
    }

    @Override
    public int getID() {
        return ID;
    }
}
