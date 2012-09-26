package com.mazebuilder.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ChaserWinState extends BasicGameState {

    static public final int ID = 2;
    private StateBasedGame game;
    private GameContainer gameContainer;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        // TODO Auto-generated method stub
        this.game = game;
        this.gameContainer = container;

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Chasing Player Wins!", 50, 100);
    }

    @Override
    public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        game.enterState(GameplayState.ID);
    }

    @Override
    public int getID() {
        return ID;
    }

}
