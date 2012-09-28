package com.mazebuilder.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CreditsGameState extends BasicGameState {

    static public final int ID = 5;
    private StateBasedGame game;
    private GameContainer gameContainer;
    private Rectangle MenuButton;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        this.gameContainer = container;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("CREDITS", 50, 75);
        g.drawString("Character assets are used with permission from the PlanetCutePNG library made by Daniel Cook", 50, 100);
        g.drawString("Music was made by Zachary Segal and Kyle Wong", 50, 125);
        g.drawString("Sound effects were made by Zachary Segal", 50, 150);
        g.drawString("Game design by Kevin Yue, David Xiao and Predrag Gruevski", 50, 175);
        g.drawString("Lead Game Programmers were Predrag Gruevski and David Xiao", 50, 200);
        g.drawString("Programmers were Marco Alagna and Kevin Yue", 50, 225);
        g.drawString("Head of art and miscellaneous assets by Zachary Segal", 50, 250);
        g.drawString("Most beautiful unicorn was Marco Alagna", 50, 275);
        g.drawString("Assets from 'The Independent Gaming Source's Assemblee Competition' were used with permission from the authors.", 50, 300);

        g.draw(MenuButton = new Rectangle(48, 323, 100, 48));
    }

    @Override
    public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
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
