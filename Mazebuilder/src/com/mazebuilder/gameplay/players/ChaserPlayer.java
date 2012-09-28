package com.mazebuilder.gameplay.players;

import java.util.Arrays;
import java.util.Random;

import org.newdawn.slick.Graphics;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.renderer.PlayerRenderer;
import com.mazebuilder.sound.SoundEffects;

public final class ChaserPlayer implements Player {

    public static final boolean DEFAULT_BONUSES_EQUAL = true;
    public static final int DEFAULT_BONUS_INTERVAL = 2;
    public static final int DEFAULT_BONUSES_TO_JUMP = 2;

    private static final int MOVEMENTS_PER_TURN = 2;
    private static final int STARTING_BONUS_MOVEMENTS = 1;

    private final PlayerRenderer renderer;
    private final String name;
    private final String type = "chaser";
    private final Multiset<Direction> bonuses;
    private final Random rand;

    private final boolean bonusesEqual;
    private final int bonusInterval;
    private final int bonusesToJump;
    private final boolean overrideMoveCounter;

    private int turnsToBonus;
    private int remainingMoves;

    public ChaserPlayer(PlayerRenderer renderer, String name) {
        this(renderer, name, DEFAULT_BONUSES_EQUAL, DEFAULT_BONUS_INTERVAL, DEFAULT_BONUSES_TO_JUMP, false);
    }

    public ChaserPlayer(PlayerRenderer renderer, String name, boolean bonusesEqual, final int bonusInterval, int bonusesToJump,
            boolean overrideMoveCounter) {
        this.renderer = renderer;
        this.name = name;
        this.bonuses = EnumMultiset.create(Direction.class);
        this.rand = new Random();

        for (int i = 0; i < STARTING_BONUS_MOVEMENTS; i++) {
            newBonus();
        }
        this.bonusesEqual = bonusesEqual;
        this.bonusesToJump = bonusesToJump;
        this.bonusInterval = bonusInterval;
        this.turnsToBonus = bonusInterval + 1;
        this.overrideMoveCounter = overrideMoveCounter;
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
        return true;
    }

    @Override
    public void startTurn() {
        System.out.println("Chaser's turn!");
        remainingMoves = MOVEMENTS_PER_TURN;
        turnsToBonus--;
        if (turnsToBonus == 0) {
            SoundEffects.playChaserGetBonus();
            newBonus();
        }
        System.out.println("CHASER PLAYER HAS THE FOLLOWING BONUSES:");
        System.out.println(getBonuses());
    }

    private void newBonus() {
        turnsToBonus = bonusInterval;
        switch (rand.nextInt(4)) {
        case 0:
            bonuses.add(Direction.LEFT);
            break;
        case 1:
            bonuses.add(Direction.UP);
            break;
        case 2:
            bonuses.add(Direction.RIGHT);
            break;
        case 3:
            bonuses.add(Direction.DOWN);
            break;
        }
    }

    @Override
    public Multiset<Direction> getBonuses() {
        return Multisets.unmodifiableMultiset(bonuses);
    }

    @Override
    public int bonusesToJump() {
        return bonusesToJump;
    }

    @Override
    public boolean bonusesEqual() {
        return bonusesEqual;
    }

    @Override
    public boolean spendBonus(Direction d) {
        if (bonuses.contains(d)) {
            System.out.println("Chaser has used a bonus \"" + d.toString() + "\" move");
        }
        return bonuses.remove(d);
    }

    @Override
    public boolean canMove() {
        return overrideMoveCounter || remainingMoves > 0;
    }

    @Override
    public int spendMove() {
        SoundEffects.playChaserMove();
        return --remainingMoves;
    }

    public boolean hasBonuses(Direction... directions) {
        return bonuses.containsAll(Arrays.asList(directions));
    }

}
