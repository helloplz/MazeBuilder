package com.mazebuilder.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TurnBasedHelpState extends BasicGameState {
    static public final int ID = 4;

    private StateBasedGame game;

    private Rectangle MenuButton;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Game Paused! Click Anywhere to Continue", 50, 100);
        g.drawString("INSTRUCTIONS:\n\n" + "--Runner (starts at top):\n" + "Get to the bottom of the board\n" + "without the chaser touching you!\n"
                + "1) Click on a platform to move there\n" + "2) Click on a bridge to destroy it\n" + "   (Water is blue and impedes movement\n"
                + "    for both players)\n\n" + "--Chaser (starts at bottom)\n" + "Catch the runner!\n" + "(Press q to see your movement cards\n"
                + " q again to hide them)\n" + "1) w/a/s/d to move up/left/down/right\n" + "   (you can move twice per turn)\n"
                + "2) W/A/S/D to use special movement card,\n" + "   which instantly moves you \n" + "   up/left/down/right\n"
                + "3) You can jump over a wall if you have\n" + "   any two matching special movement cards.\n"
                + "   To do this, move regularly into the wall\n" + "   then press the type of special movement\n" + "   card that you want to use\n"
                + "4) Space to end your turn\n", 50, 150);
        g.draw(MenuButton = new Rectangle(48, 678, 100, 48));
        g.drawString("Main Menu", 50, 680);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (MenuButton.contains(x, y)) {
            game.enterState(MainMenuState.ID);
        } else {
            game.enterState(TurnBasedGameplayState.ID);
        }
    }

    @Override
    public int getID() {
        return ID;
    }
}
