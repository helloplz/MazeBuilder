package com.mazebuilder.gameplay.players;

import org.newdawn.slick.Graphics;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;
import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.renderer.PlayerRenderer;

public final class RunnerPlayer implements Player {

    public static final int WALLS_PER_TURN = 1;
    public static final int MOVES_PER_TURN = 1;

    private final PlayerRenderer renderer;
    private final String name;
    private final String type = "runner";
    private final Multiset<Direction> bonuses;

    private int remainingMoves;
    private int remainingWalls;

    public RunnerPlayer(PlayerRenderer renderer, String name) {
        this.renderer = renderer;
        this.name = name;
        this.bonuses = EnumMultiset.create(Direction.class);
    }

    @Override
    public void render(Graphics g, int x, int y, int tileWidth, int tileHeight) {
        renderer.drawPlayer(g, x, y, tileWidth, tileHeight);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean canJump() {
        return false;
    }

    @Override
    public void startTurn() {
        System.out.println("Runner's turn!");
        remainingWalls = WALLS_PER_TURN;
        remainingMoves = MOVES_PER_TURN;
    }

    @Override
    public Multiset<Direction> getBonuses() {
        return bonuses;
    }

    @Override
    public int bonusesToJump() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean bonusesEqual() {
        return true;
    }

    @Override
    public boolean spendBonus(Direction d) {
        throw new RuntimeException("Cannot spend bonus - RunnerPlayer is not allowed to have bonuses.");
    }

    @Override
    public boolean canMove() {
        return remainingMoves > 0;
    }

    @Override
    public int spendMove() {
        return --remainingMoves;
    }

    public boolean canWall() {
        return remainingWalls > 0;
    }

    public int spendWall() {
        return --remainingWalls;
    }
}
