package com.mazebuilder.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class RealTimeHelpState extends BasicGameState {
    static public final int ID = 8;

    private StateBasedGame game;

    private Rectangle MenuButton;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Game Paused! Click Anywhere to Continue", 50, 100);
        g.drawString("INSTRUCTIONS:\n\n" + "Each player has a 'move bar' and an\n" + "'ability bar' which will fill up over time.\n"
                + "The players can move when their move bars\n" + "are full, and can use 'abilities' when\n" + "their ability bars are full.\n\n"
                + "--Runner (starts at top):\n" + "Get to the bottom of the board\n" + "without the chaser touching you!\n"
                + "Runner's special ability is to destroy bridges.\n" + "1) Click on a platform to move there\n"
                + "2) Click on a bridge to destroy it\n" + "   (Water is blue and impedes movement\n" + "    for both players)\n\n"
                + "--Chaser (starts at bottom)\n" + "Catch the runner!\n" + "Chaser's special ability is to 'jump' over water.\n"
                + "1) w/a/s/d to move up/left/down/right\n" + "2) You can jump over water if your ability\n" + "   bar is full\n", 50, 150);
        g.draw(MenuButton = new Rectangle(48, 638, 100, 48));
        g.drawString("Main Menu", 50, 640);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (MenuButton.contains(x, y)) {
            game.enterState(MainMenuState.ID);
        } else {
            game.enterState(RealtimeGameplayState.ID);
        }
    }

    @Override
    public int getID() {
        return ID;
    }
}
