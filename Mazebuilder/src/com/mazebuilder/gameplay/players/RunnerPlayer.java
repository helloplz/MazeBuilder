package com.mazebuilder.gameplay.players;

import org.newdawn.slick.Graphics;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;
import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.renderer.PlayerRenderer;

public final class RunnerPlayer implements Player {

    private final PlayerRenderer renderer;
    private final String name;
    private final Multiset<Direction> bonuses;

    public RunnerPlayer(PlayerRenderer renderer, String name) {
        this.renderer = renderer;
        this.name = name;
        this.bonuses = EnumMultiset.create(Direction.class);
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
        return false;
    }

    @Override
    public void executeTurn() {
        // no-op
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

}
