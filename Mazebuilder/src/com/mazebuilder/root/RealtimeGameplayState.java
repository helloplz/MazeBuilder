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

    private static final int RUNNER_MOVE_MILLIS = 50;
    private static final int RUNNER_WALL_MILLIS = 50;
    private static final int CHASER_MOVE_MILLIS = 50;
    private static final int CHASER_JUMP_MILLIS = 200;

    private int chaserMoveTimer = 0;
    private int chaserJumpTimer = 0;
    private int runnerMoveTimer = 0;
    private int runnerWallTimer = 0;

    protected RealtimeGameplayState() {
        super(new RunnerPlayer(new RunnerPlayerRenderer(), "A", true, true), new ChaserPlayer(new ChaserPlayerRenderer(), "B", false,
                Integer.MAX_VALUE, 0, true));
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
        System.out.println(dir.toString());
        if (dir != null) {
            if (canChaserMove() && board.movePlayer(chaser, dir)) {
                chaserMove();
            } else if (canChaserJump() && board.jumpPlayer(chaser, dir)) {
                chaserJump();
            }
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
        // no-op
    }

}
