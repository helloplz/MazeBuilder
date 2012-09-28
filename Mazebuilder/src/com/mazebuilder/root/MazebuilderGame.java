package com.mazebuilder.root;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.mazebuilder.gameplay.players.Player;

/**
 * 
 * @author dxiao
 */
public class MazebuilderGame extends StateBasedGame {

    private Player winner;

    public MazebuilderGame() {
        super("Mazebuilder v0.0");
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        // temporarily disabled for testing of GameplayState
        addState(new MainMenuState());
        addState(new GameplayState());
        addState(new RunnerWinState());
        addState(new ChaserWinState());
        addState(new CreditsGameState());
        addState(new TurnBasedHelpState());
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player player) {
        winner = player;
    }

    public static void main(String args[]) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MazebuilderGame());

        app.setDisplayMode(1024, 768, false);
        app.setMaximumLogicUpdateInterval(100);
        app.setMinimumLogicUpdateInterval(10);
        app.setTargetFrameRate(60);
        // app.setSmoothDeltas(true);

        app.start();

    }
}
