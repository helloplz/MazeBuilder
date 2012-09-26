package com.mazebuilder.gameplay.players;

import java.util.Random;

import org.newdawn.slick.Graphics;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.renderer.PlayerRenderer;
import com.mazebuilder.sound.SoundEffects;

public final class ChaserPlayer implements Player {

    private static final boolean BONUSES_EQUAL = true;
    private static final int BONUS_INTERVAL = 2;
    private static final int BONUSES_TO_JUMP = 2;
    private static final int MOVEMENTS_PER_TURN = 2;

    private final PlayerRenderer renderer;
    private final String name;
    private final Multiset<Direction> bonuses;
    private final Random rand;

    private int turnsToBonus = BONUS_INTERVAL;
    private int remainingMoves;

    public ChaserPlayer(PlayerRenderer renderer, String name) {
        this.renderer = renderer;
        this.name = name;
        this.bonuses = EnumMultiset.create(Direction.class);
        this.rand = new Random();
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
            turnsToBonus = BONUS_INTERVAL;
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
        System.out.println(getBonuses());
    }

    @Override
    public Multiset<Direction> getBonuses() {
        return Multisets.unmodifiableMultiset(bonuses);
    }

    @Override
    public int bonusesToJump() {
        return BONUSES_TO_JUMP;
    }

    @Override
    public boolean bonusesEqual() {
        return BONUSES_EQUAL;
    }

    @Override
    public boolean spendBonus(Direction d) {
        return bonuses.remove(d);
    }

    @Override
    public boolean canMove() {
        return remainingMoves > 0;
    }

    @Override
    public int spendMove() {
        SoundEffects.playChaserMove();
        return --remainingMoves;
    }

}
