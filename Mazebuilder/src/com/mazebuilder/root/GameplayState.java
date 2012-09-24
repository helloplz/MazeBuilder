package com.mazebuilder.root;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.mazebuilder.gameplay.Direction;
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
    
    private final Board board = new DefaultBoard(new SimpleBoardRenderer(), BOARD_WIDTH, BOARD_HEIGHT);
    private final Player runner = new RunnerPlayer(new RunnerPlayerRenderer(), "A");
    private final Player chaser = new ChaserPlayer(new ChaserPlayerRenderer(), "B");
    
    private int chaserTurnTimer;
    

    /** Write initialization for the board here */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        board.addPlayerAtLocation(runner, new SimpleLocation(RUNNER_YPOS, RUNNER_XPOS));
        board.addPlayerAtLocation(chaser, new SimpleLocation(CHASER_YPOS, CHASER_XPOS));
        board.putWall(new SimpleLocation(INITIAL_WALLS_YPOS, INITIAL_WALLS_XPOS), Direction.LEFT);
        board.putWall(new SimpleLocation(INITIAL_WALLS_YPOS, INITIAL_WALLS_XPOS), Direction.RIGHT);
        runner.startTurn();
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
        if (chaser.canMove()){
            switch (c){
            case 'w':
                board.movePlayer(chaser, Direction.UP);
                break;
            case 'a':
                board.movePlayer(chaser, Direction.LEFT);
                break;
            case 's':
                board.movePlayer(chaser, Direction.DOWN);
                break;
            case 'd':
                board.movePlayer(chaser, Direction.RIGHT);
                break;
            }
        } else if (c == ' ') {
            runner.startTurn();
        } else if (c == '\n' || c == '\r') {
            chaser.startTurn();
        }
	}
    
    private void movePlayer(Player player, Direction dir) {
        if (board.movePlayer(player, dir)) {
            player.startTurn();
        }
    }
}
