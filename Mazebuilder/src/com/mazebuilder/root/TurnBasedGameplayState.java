package com.mazebuilder.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.google.common.collect.Multiset;
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
public class TurnBasedGameplayState extends AbstractMazebuilderGameState {

    static public final int ID = 1;

    private Direction jumping = null;
    private boolean showMoves = false;

    public TurnBasedGameplayState() {
        super(new RunnerPlayer(new RunnerPlayerRenderer(), "A"), new ChaserPlayer(new ChaserPlayerRenderer(), "B"));
    }

    @Override
    public int getID() {
        return ID;
    }

    /** Each logic step, poll inputs and compute game logic here */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        // no-op
    }

    @Override
    public void keyPressed(int key, char c) {
        if (c == 'q') {
            showMoves = !showMoves;
        }
        // JUMPing key presses
        if (jumping != null) {
            switch (c) {
            // Regular Moves
            case 'w':
                if (chaser.hasBonuses(Direction.UP, Direction.UP)) {
                    board.jumpPlayer(chaser, jumping, Direction.UP, Direction.UP);
                }
                break;
            case 'a':
                if (chaser.hasBonuses(Direction.LEFT, Direction.LEFT)) {
                    board.jumpPlayer(chaser, jumping, Direction.LEFT, Direction.LEFT);
                }
                break;
            case 's':
                if (chaser.hasBonuses(Direction.DOWN, Direction.DOWN)) {
                    board.jumpPlayer(chaser, jumping, Direction.DOWN, Direction.DOWN);
                }
                break;
            case 'd':
                if (chaser.hasBonuses(Direction.RIGHT, Direction.RIGHT)) {
                    board.jumpPlayer(chaser, jumping, Direction.RIGHT, Direction.RIGHT);
                }
                break;
            case ' ':
                System.out.println("Jump Cancelled");
            }
            jumping = null;
        }
        // NON-JUMP key presses
        else {
            switch (c) {
            // Regular Moves
            case 'w':
                if (chaser.canMove()) {
                    if (!board.movePlayer(chaser, Direction.UP)) {
                        jumping = Direction.UP;
                        handleJump();
                    }
                }
                break;
            case 'a':
                if (chaser.canMove()) {
                    if (!board.movePlayer(chaser, Direction.LEFT)) {
                        jumping = Direction.LEFT;
                        handleJump();
                    }
                }
                break;
            case 's':
                if (chaser.canMove()) {
                    if (!board.movePlayer(chaser, Direction.DOWN)) {
                        jumping = Direction.DOWN;
                        handleJump();
                    }
                }
                break;
            case 'd':
                if (chaser.canMove()) {
                    if (!board.movePlayer(chaser, Direction.RIGHT)) {
                        jumping = Direction.RIGHT;
                        handleJump();
                    }
                }
                break;
            // Special Moves
            case 'W':
                board.movePlayerWithBonus(chaser, Direction.UP);
                break;
            case 'A':
                board.movePlayerWithBonus(chaser, Direction.LEFT);
                break;
            case 'S':
                board.movePlayerWithBonus(chaser, Direction.DOWN);
                break;
            case 'D':
                board.movePlayerWithBonus(chaser, Direction.RIGHT);
                break;
            case ' ':
                if (!runner.canMove() && !runner.canWall()) {
                    chaser.endTurn();
                    checkRunnerWin();
                    runner.startTurn();
                }
            }
        }
        checkChaserWin();
    }

    private void handleJump() {
        Multiset<Direction> m = chaser.getBonuses();
        int up = m.count(Direction.UP);
        int down = m.count(Direction.DOWN);
        int left = m.count(Direction.LEFT);
        int right = m.count(Direction.RIGHT);
        boolean hasJump = false;

        if (up >= chaser.bonusesToJump()) {
            System.out.println("Press W to use 2 bonus \"UP\" movements to jump the wall");
            hasJump = true;
        }
        if (down >= chaser.bonusesToJump()) {
            System.out.println("Press S to use 2 bonus \"DOWN\" movements to jump the wall");
            hasJump = true;
        }
        if (left >= chaser.bonusesToJump()) {
            System.out.println("Press A to use 2 bonus \"LEFT\" movements to jump the wall");
            hasJump = true;
        }
        if (right >= chaser.bonusesToJump()) {
            System.out.println("Press D to use 2 bonus \"RIGHT\" movements to jump the wall");
            hasJump = true;
        }
        if (!hasJump) {
            System.out.println("You do not have matching bonuses. You cannot jump the wall");
            jumping = null;
        } else {
            System.out.println("Press SPACE to quit without jumping");
        }

    }

    @Override
    protected boolean canRunnerMove() {
        return runner.canMove();
    }

    @Override
    protected boolean canRunnerWall() {
        return runner.canWall();
    }

    @Override
    protected boolean canChaserMove() {
        return chaser.canMove();
    }

    @Override
    protected boolean canChaserJump() {
        return chaser.canJump();
    }

    @Override
    protected void postRender(GameContainer container, StateBasedGame game, Graphics g) {
        if (showMoves) {
            g.drawString(chaser.getBonuses().toString(), 650, 640);
        }

        if (canRunnerMove() || canRunnerWall()) {
            g.drawString("Runner's Turn!", 625, 64);
        } else {
            g.drawString("Chaser's Turn!", 625, 64);
        }
    }

    @Override
    protected void runnerMove() {
        // no-op
    }

    @Override
    protected void runnerWall() {
        // no-op
    }

}
