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
import com.mazebuilder.renderer.SidebarRenderer;
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

    protected final Board board = new DefaultBoard(new SimpleBoardRenderer(), BOARD_WIDTH, BOARD_HEIGHT);
    protected final RunnerPlayer runner;
    protected final ChaserPlayer chaser;
    
    SidebarRenderer sidebar;

    protected AbstractMazebuilderGameState(RunnerPlayer runner, ChaserPlayer chaser) {
        this.runner = runner;
        this.chaser = chaser;
        sidebar = new SidebarRenderer(chaser, runner);
    }

    private Music backGroundMusic;

    /** Write initialization for the board here */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
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
        if (canRunnerMove()) {
            Location loc = board.getTile(x - BOARD_XOFFSET, y - BOARD_YOFFSET);
            if (loc != null) {
                Direction dir = board.getPlayerLocation(runner).isAdjacent(loc);
                if (dir != null) {
                    if (board.movePlayer(runner, dir)) {
                        runnerMove();
                    }
                }
            }
        } else if (canRunnerWall()) {
            Direction dir = board.getWallDirection(x - BOARD_XOFFSET, y - BOARD_YOFFSET);
            Location loc = board.getTile(x - BOARD_XOFFSET, y - BOARD_YOFFSET);
            if (dir != null && loc != null) {
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

    protected abstract void runnerMove();

    protected abstract void runnerWall();

    protected abstract boolean canRunnerMove();

    protected abstract boolean canRunnerWall();

    protected abstract boolean canChaserMove();

    protected abstract boolean canChaserJump();

    protected abstract void postRender(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;

}
