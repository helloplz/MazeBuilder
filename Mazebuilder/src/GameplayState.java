import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This is the main gameplay loop - gameboard, player movement, etc, should all be in here.
 * 
 * @author dxiao
 */
public class GameplayState extends BasicGameState {
	
	static public final int ID = 0;

	/** Write initialization for the board here */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

	}

	/** Each rendering step, draw the game here */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
        g.drawString("Hello, Mazebuilder!", 0, 100);
	}

	/** Each logic step, poll inputs and compute game logic here */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}

	@Override
	public int getID() {
		return ID;
	}

}
