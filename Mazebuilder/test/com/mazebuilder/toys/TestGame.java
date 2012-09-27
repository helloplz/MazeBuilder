package com.mazebuilder.toys;

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

public class TestGame {
    /**
     * TESTING STRATEGY:
     * 
     * 1. Test basic features of player input individually (i.e. Player A move, player B move, player A place wall, player B special move)
     * 
     * 2. Test boundary conditions associated with player input (players can't leave board or move through walls, etc)
     * 
     * 3. Test win conditions for both players
     * 
     * 4. Test full game scenarios for expected progression of game states.
     * 
     * Tests will be done using a combination of error messages to check for illegal moves, and board state logging to check for the correct
     * progression of game states.
     * 
     * Player input for tests will be simulated through hard-coding sequences of inputs.
     */

    /**
     * Helper function for setting up tests
     */
    private static void setUpBoard() {
        Board board = new DefaultBoard(new SimpleBoardRenderer(), 7, 7);
        Player runner = new RunnerPlayer(new RunnerPlayerRenderer(), "A");
        Player chaser = new ChaserPlayer(new ChaserPlayerRenderer(), "B");
        board.addPlayerAtLocation(runner, new SimpleLocation(0, 3));
        board.addPlayerAtLocation(chaser, new SimpleLocation(6, 3));
        board.putWall(new SimpleLocation(3, 3), Direction.LEFT);
        board.putWall(new SimpleLocation(3, 3), Direction.RIGHT);
    }
}
