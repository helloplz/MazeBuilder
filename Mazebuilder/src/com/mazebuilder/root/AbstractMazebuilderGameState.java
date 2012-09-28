package com.mazebuilder.root;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.gameplay.Location;
import com.mazebuilder.gameplay.SimpleLocation;
import com.mazebuilder.gameplay.board.Board;
import com.mazebuilder.gameplay.board.DefaultBoard;
import com.mazebuilder.gameplay.players.ChaserPlayer;
import com.mazebuilder.gameplay.players.RunnerPlayer;
import com.mazebuilder.renderer.SimpleBoardRenderer;

public abstract class AbstractMazebuilderGameState extends BasicGameState {

    protected static final int BOARD_WIDTH = 7;
    protected static final int BOARD_HEIGHT = 7;
    protected static final int RUNNER_XPOS = 3;
    protected static final int RUNNER_YPOS = 0;
    protected static final int CHASER_XPOS = 3;
    protected static final int CHASER_YPOS = 6;
    protected static final int INITIAL_WALLS_XPOS = 3;
    protected static final int INITIAL_WALLS_YPOS = 3;

    protected static final int BOARD_XOFFSET = 64;
    protected static final int BOARD_YOFFSET = 64;

    protected GameContainer gameContainer;
    protected StateBasedGame game;

    protected Board board;
    protected RunnerPlayer runner;
    protected ChaserPlayer chaser;

    private Music backGroundMusic;

    /** Write initialization for the board here */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        runner = getRunner();
        chaser = getChaser();
        board = new DefaultBoard(new SimpleBoardRenderer(), BOARD_WIDTH, BOARD_HEIGHT);
        board.addPlayerAtLocation(runner, new SimpleLocation(RUNNER_YPOS, RUNNER_XPOS));
        board.addPlayerAtLocation(chaser, new SimpleLocation(CHASER_YPOS, CHASER_XPOS));
        board.putWall(new SimpleLocation(INITIAL_WALLS_YPOS, INITIAL_WALLS_XPOS), Direction.LEFT);
        board.putWall(new SimpleLocation(INITIAL_WALLS_YPOS, INITIAL_WALLS_XPOS), Direction.RIGHT);
        runner.startTurn();
        gameContainer = container;

        backGroundMusic = new Music("./assets/sounds/mischief2.wav");
        backGroundMusic.loop();
    }

    /** Each rendering step, draw the game here */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        board.render(g, BOARD_XOFFSET, BOARD_YOFFSET);
        // g.drawString("Hello, Mazebuilder! [Gameplay]", 50, 100);
        g.setColor(Color.white);

        g.drawString("INSTRUCTIONS:\n\n" + "--Runner (starts at top):\n" + "Get to the bottom of the board\n" + "without the chaser touching you!\n"
                + "1) Click on a square to move there\n" + "2) THEN click on an edge to place a wall\n" + "   (Walls are red and impede movement\n"
                + "    for both players)\n\n" + "--Chaser (starts at bottom)\n" + "Catch the runner!\n" + "(Press q to see your movement cards\n"
                + " q again to hide them)\n" + "1) w/a/s/d to move up/left/down/right\n" + "   (you can move twice per turn)\n"
                + "2) W/A/S/D to use special movement card,\n" + "   which instantly moves you \n" + "   up/left/down/right\n"
                + "3) You can jump over a wall if you have\n" + "   any two matching special movement cards.\n"
                + "   To do this, move regularly into the wall\n" + "   then press the type of special movement\n" + "   card that you want to use\n"
                + "4) Space to end your turn\n", 625, 100);

        postRender(container, game, g);
    }

    protected void checkChaserWin() {
        if (board.getPlayerLocation(chaser).equals(board.getPlayerLocation(runner))) {
            game.enterState(ChaserWinState.ID);
        }
    }

    protected void checkRunnerWin() {
        if (BOARD_HEIGHT - 1 == board.getPlayerLocation(runner).getRow()) {
            game.enterState(RunnerWinState.ID);
        }
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        Direction dir = board.getWallDirection(x - BOARD_XOFFSET, y - BOARD_YOFFSET);
        if (dir == null && canRunnerMove()) {
            Location loc = board.getTile(x - BOARD_XOFFSET, y - BOARD_YOFFSET);
            if (loc != null) {
                Direction dirc = board.getPlayerLocation(runner).isAdjacent(loc);
                if (dirc != null) {
                    if (board.movePlayer(runner, dirc)) {
                        runnerMove();
                    }
                }
            }
        } else if (dir != null && canRunnerWall()) {
            Location loc = board.getTile(x - BOARD_XOFFSET, y - BOARD_YOFFSET);
            if (loc != null) {
                if (board.putWall(loc, dir)) {
                    if (runner.spendWall() == 0) {
                        chaser.startTurn();
                    }
                    runnerWall();
                }
            }
        }
        checkChaserWin();
    }

    protected abstract RunnerPlayer getRunner();
    
    protected abstract ChaserPlayer getChaser();
    
    protected abstract void runnerMove();

    protected abstract void runnerWall();

    protected abstract boolean canRunnerMove();

    protected abstract boolean canRunnerWall();

    protected abstract boolean canChaserMove();

    protected abstract boolean canChaserJump();

    protected abstract void postRender(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;

}
