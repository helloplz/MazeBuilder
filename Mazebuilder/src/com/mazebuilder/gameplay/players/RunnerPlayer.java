package com.mazebuilder.gameplay.players;

import org.newdawn.slick.Graphics;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;
import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.renderer.PlayerRenderer;
import com.mazebuilder.sound.SoundEffects;

public final class RunnerPlayer implements Player {

    public static final int WALLS_PER_TURN = 1;
    public static final int MOVES_PER_TURN = 1;

    private final PlayerRenderer renderer;
    private final String name;
    private final String type = "runner";
    private final Multiset<Direction> bonuses;
    private final boolean overrideMoveCounter;
    private final boolean overrideWallCounter;

    private int remainingMoves;
    private int remainingWalls;

    public RunnerPlayer(PlayerRenderer renderer, String name) {
        this(renderer, name, false, false);
    }

    public RunnerPlayer(PlayerRenderer renderer, String name, boolean overrideMoveCounter, boolean overrideWallCounter) {
        this.renderer = renderer;
        this.name = name;
        this.bonuses = EnumMultiset.create(Direction.class);
        this.overrideMoveCounter = overrideMoveCounter;
        this.overrideWallCounter = overrideWallCounter;
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
        return overrideMoveCounter || remainingMoves > 0;
    }

    @Override
    public int spendMove() {
        SoundEffects.playRunnerMove();
        return --remainingMoves;
    }

    public boolean canWall() {
        return overrideWallCounter || remainingWalls > 0;
    }

    public int spendWall() {
        SoundEffects.playPutWall();
        return --remainingWalls;
    }
}
