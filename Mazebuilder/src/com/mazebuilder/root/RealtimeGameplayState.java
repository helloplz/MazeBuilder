package com.mazebuilder.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.gameplay.players.ChaserPlayer;
import com.mazebuilder.gameplay.players.RunnerPlayer;
import com.mazebuilder.renderer.ChaserPlayerRenderer;
import com.mazebuilder.renderer.RunnerPlayerRenderer;

/**
 * This is the main gameplay loop - gameboard, player movement, etc, should all be in here.
 * 
 * @author dxiao
 */
public class RealtimeGameplayState extends AbstractMazebuilderGameState {

    static public final int ID = 6;

    private static final int MAX_RUNNER_MOVE_MILLIS = 5000;
    private static final int MAX_RUNNER_WALL_MILLIS = 6500;
    private static final int MAX_CHASER_MOVE_MILLIS = 4000;
    private static final int MAX_CHASER_JUMP_MILLIS = 25000;

    private static final int CHASER_INVERSE_SCALING = 7;

    private double RUNNER_MOVE_MILLIS = MAX_RUNNER_MOVE_MILLIS;
    private double RUNNER_WALL_MILLIS = MAX_RUNNER_WALL_MILLIS;
    private double CHASER_MOVE_MILLIS = MAX_CHASER_MOVE_MILLIS;
    private double CHASER_JUMP_MILLIS = MAX_CHASER_JUMP_MILLIS;

    private int chaserMoveTimer;
    private int chaserJumpTimer;
    private int runnerMoveTimer;
    private int runnerWallTimer;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        super.init(container, game);
        chaserMoveTimer = 0;
        chaserJumpTimer = 0;
        runnerMoveTimer = 0;
        runnerWallTimer = (int) RUNNER_WALL_MILLIS;
    }

    @Override
    protected RunnerPlayer getRunner() {
        return new RunnerPlayer(new RunnerPlayerRenderer(), "A", true, true);
    }

    @Override
    protected ChaserPlayer getChaser() {
        return new ChaserPlayer(new ChaserPlayerRenderer(), "B", false, Integer.MAX_VALUE, 0, true);
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        // System.out.println(chaserMoveTimer + "\t" + runnerWallTimer);
        chaserMoveTimer += delta;
        chaserJumpTimer += delta;
        runnerMoveTimer += delta;
        runnerWallTimer += delta;

        CHASER_JUMP_MILLIS -= delta / CHASER_INVERSE_SCALING;

        checkRunnerWin();
    }

    /**
     * @returns the Direction corresponding to the char, or null if it doesn't match a known direction
     */
    private Direction extractDirectionFromChar(char c) {
        switch (c) {
        case 'a':
            return Direction.LEFT;
        case 'w':
            return Direction.UP;
        case 'd':
            return Direction.RIGHT;
        case 's':
            return Direction.DOWN;
        default:
            return null;
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        Direction dir = extractDirectionFromChar(c);
        if (dir != null) {
            if (canChaserMove() && board.movePlayer(chaser, dir)) {
                chaserMove();
            } else if (canChaserJump() && board.jumpPlayer(chaser, dir)) {
                chaserJump();
            }
            checkChaserWin();
        } else if (c == 't') {
            game.enterState(RunnerWinState.ID);
        }
    }

    @Override
    protected void runnerMove() {
        runnerMoveTimer = 0;
    }

    @Override
    protected void runnerWall() {
        runnerWallTimer = 0;
    }

    private void chaserMove() {
        chaserMoveTimer = 0;
    }

    private void chaserJump() {
        chaserJumpTimer = 0;
    }

    @Override
    protected boolean canRunnerMove() {
        return (runnerMoveTimer >= RUNNER_MOVE_MILLIS);
    }

    @Override
    protected boolean canRunnerWall() {
        return (runnerWallTimer >= RUNNER_WALL_MILLIS);
    }

    @Override
    protected boolean canChaserMove() {
        return (chaserMoveTimer >= CHASER_MOVE_MILLIS);
    }

    @Override
    protected boolean canChaserJump() {
        return (chaserJumpTimer >= CHASER_JUMP_MILLIS);
    }

    @Override
    protected void postRender(GameContainer container, StateBasedGame game, Graphics g) {
        g.pushTransform();
        g.translate(650, 34);
        sidebar.drawSidebar(g, (runnerMoveTimer) / RUNNER_MOVE_MILLIS, (runnerWallTimer) / RUNNER_WALL_MILLIS,
                (chaserMoveTimer) / CHASER_MOVE_MILLIS, (chaserJumpTimer) / CHASER_JUMP_MILLIS);
        g.popTransform();
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);
        if (sidebar.runnerForfeitButtonClicked(x - 650, y - 34)) {
            game.enterState(ChaserWinState.ID);
        } else if (sidebar.helpButtonClicked(x - 650, y - 34)) {
            game.enterState(RealTimeHelpState.ID);
        } else if (sidebar.quitButtonClicked(x - 650, y - 34)) {
            game.enterState(MainMenuState.ID);
        }
    }
}
