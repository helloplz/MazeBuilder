import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class MazebuilderGame extends StateBasedGame {

	public MazebuilderGame() {
		super("Mazebuilder v0.0");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new GameplayState());
	}

	public static void main (String args[]) throws SlickException {
		AppGameContainer app = new AppGameContainer(new MazebuilderGame());

		app.setDisplayMode(800, 600, false);
		//app.setSmoothDeltas(true);

		app.start();
	}
}
