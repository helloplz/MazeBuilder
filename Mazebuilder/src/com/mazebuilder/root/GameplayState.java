package com.mazebuilder.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.google.common.collect.Multiset;
import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.gameplay.Location;
import com.mazebuilder.gameplay.SimpleLocation;
import com.mazebuilder.gameplay.board.Board;
import com.mazebuilder.gameplay.board.DefaultBoard;
import com.mazebuilder.gameplay.players.ChaserPlayer;
import com.mazebuilder.gameplay.players.Player;
import com.mazebuilder.gameplay.players.RunnerPlayer;
import com.mazebuilder.renderer.ChaserPlayerRenderer;
import com.mazebuilder.renderer.RunnerPlayerRenderer;
import com.mazebuilder.renderer.SimpleBoardRenderer;

/**
 * This is the main gameplay loop - gameboard, player movement, etc, should all be in here.
 * 
 * @author dxiao
 */
public class GameplayState extends BasicGameState {

    static public final int ID = 1;

    private static final int BOARD_WIDTH = 7;
    private static final int BOARD_HEIGHT = 7;
    private static final int RUNNER_XPOS = 3;
    private static final int RUNNER_YPOS = 0;
    private static final int CHASER_XPOS = 3;
    private static final int CHASER_YPOS = 6;
    private static final int INITIAL_WALLS_XPOS = 3;
    private static final int INITIAL_WALLS_YPOS = 3;
    //private static final int CHASER_TURN_MILLIS = 5000;
    
    private static Direction jumping = null;
    
    private GameContainer gameContainer;
    private final Board board = new DefaultBoard(new SimpleBoardRenderer(), BOARD_WIDTH, BOARD_HEIGHT);
    private final RunnerPlayer runner = new RunnerPlayer(new RunnerPlayerRenderer(), "A");
    private final ChaserPlayer chaser = new ChaserPlayer(new ChaserPlayerRenderer(), "B");
    
    private int chaserTurnTimer;
    

    /** Write initialization for the board here */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        board.addPlayerAtLocation(runner, new SimpleLocation(RUNNER_YPOS, RUNNER_XPOS));
        board.addPlayerAtLocation(chaser, new SimpleLocation(CHASER_YPOS, CHASER_XPOS));
        board.putWall(new SimpleLocation(INITIAL_WALLS_YPOS, INITIAL_WALLS_XPOS), Direction.LEFT);
        board.putWall(new SimpleLocation(INITIAL_WALLS_YPOS, INITIAL_WALLS_XPOS), Direction.RIGHT);
        runner.startTurn();
        gameContainer = container;
    }

    /** Each rendering step, draw the game here */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        board.render(g, 64, 64);
        // g.drawString("Hello, Mazebuilder! [Gameplay]", 50, 100);
    }

    /** Each logic step, poll inputs and compute game logic here */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        /* For real time updates
        chaserTurnTimer -= delta;
        if (chaserTurnTimer < 0) {
            chaser.startTurn();
            System.out.println("chaser turn");
            chaserTurnTimer = CHASER_TURN_MILLIS;
        }
        */
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
	public void keyPressed(int key, char c) {
        //JUMPing key presses
        if (jumping != null){
            switch (c){
            // Regular Moves
            case 'w':
                board.jumpPlayer(chaser, jumping, Direction.UP, Direction.UP);
                break;
            case 'a':
                board.jumpPlayer(chaser, jumping, Direction.LEFT, Direction.LEFT);
                break;
            case 's':
                board.jumpPlayer(chaser, jumping, Direction.DOWN, Direction.DOWN);
                break;
            case 'd':
                board.jumpPlayer(chaser, jumping, Direction.RIGHT, Direction.RIGHT);
                break;
            case ' ':
                System.out.println("Jump Cancelled");
                jumping = null;
            }
            jumping = null;            
        }
        //NON-JUMP key presses
        else{
            switch (c){
            // Regular Moves
            case 'w':
                if (chaser.canMove()){
                    if(!board.movePlayer(chaser, Direction.UP)){
                        jumping = Direction.UP;
                        handleJump();
                    }
                }
                break;
            case 'a':
                if (chaser.canMove()){
                    if(!board.movePlayer(chaser, Direction.LEFT)){
                        jumping = Direction.LEFT;
                        handleJump();
                    }
                }
                break;
            case 's':
                if (chaser.canMove()){
                    if(!board.movePlayer(chaser, Direction.DOWN)){
                        jumping = Direction.DOWN;
                        handleJump();
                    }
                }
                break;
            case 'd':
                if (chaser.canMove()){
                    if(!board.movePlayer(chaser, Direction.RIGHT)){
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
                if(!runner.canMove() && !runner.canWall()){
                    runner.startTurn();
                }
            }
        }
  	}
    
    public void handleJump(){
        Multiset<Direction> m = chaser.getBonuses(); 
        int up = m.count(Direction.UP);
        int down = m.count(Direction.DOWN);
        int left = m.count(Direction.LEFT);
        int right = m.count(Direction.RIGHT);
        boolean hasJump = false;
        
        if (up >= chaser.bonusesToJump()){
            System.out.println("Press W to use 2 bonus \"UP\" movements to jump the wall");
            hasJump = true;
        }
        if (down >= chaser.bonusesToJump()){
            System.out.println("Press S to use 2 bonus \"DOWN\" movements to jump the wall");
            hasJump = true;
        }
        if (left >= chaser.bonusesToJump()){
            System.out.println("Press A to use 2 bonus \"LEFT\" movements to jump the wall");
            hasJump = true;
        }
        if (right >= chaser.bonusesToJump()){
            System.out.println("Press D to use 2 bonus \"RIGHT\" movements to jump the wall");
            hasJump = true;
        }
        if (!hasJump){
            System.out.println("You do not have matching bonuses. You cannot jump the wall");
        }
        System.out.println("Press SPACE to quit without jumping");

    }
    
    @Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
        if (runner.canMove()) {
            Location loc = board.getTile(x-64,  y-64);
            if (loc != null) {
                Direction dir = board.getPlayerLocation(runner).isAdjacent(loc);
                if (dir != null) {
                    board.movePlayer(runner, dir);
                }
            }
        } else if (runner.canWall()) {
            Direction dir = board.getWallDirection(x-64, y-64);
            Location loc = board.getTile(x-64,  y-64);
            if (dir != null && loc != null) {
                if (board.putWall(loc, dir)) {
                    if (runner.spendWall() == 0) {
                        chaser.startTurn();
                    }
                }
            }
        }
    }
    
    private void movePlayer(Player player, Direction dir) {
        if (board.movePlayer(player, dir)) {
            player.startTurn();
        }
    }
}
