package com.mazebuilder.gameplay;

import java.util.Random;

import org.newdawn.slick.Graphics;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.mazebuilder.renderer.PlayerRenderer;

public final class ChaserPlayer implements Player {

    private final PlayerRenderer renderer;
    private final String name;
    private final Multiset<Direction> bonuses;
    private final Random rand;

    private int turnsToBonus = BONUS_INTERVAL;

    private static final boolean BONUSES_EQUAL = true;
    private static final int BONUS_INTERVAL = 2;
    private static final int BONUSES_TO_JUMP = 2;

    public ChaserPlayer(PlayerRenderer renderer, String name) {
        this.renderer = renderer;
        this.name = name;
        this.bonuses = EnumMultiset.create(Direction.class);
        this.rand = new Random();
    }

    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        renderer.drawPlayer(g, xOffset, yOffset);
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
    public void executeTurn() {
        turnsToBonus--;
        if (turnsToBonus == 0) {
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

}
