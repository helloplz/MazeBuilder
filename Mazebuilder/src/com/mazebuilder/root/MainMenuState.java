package com.mazebuilder.root;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
        g.drawString("Hello, Mazebuilder! [MainMenu]", 50, 100);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		game.enterState(GameplayState.ID);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
