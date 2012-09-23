package com.mazebuilder.gameplay;

import java.util.Map;

import org.newdawn.slick.Graphics;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mazebuilder.renderer.BoardRenderer;

public final class DefaultBoard implements Board {

    private final BoardRenderer renderer;
    private final int tilesAcross, tilesDown;
    private final Map<Player, Location> players = Maps.newIdentityHashMap();
    private final WallContainer walls;

    public DefaultBoard(BoardRenderer renderer, int tilesAcross, int tilesDown) {
        this.renderer = renderer;
        this.tilesAcross = tilesAcross;
        this.tilesDown = tilesDown;
        walls = new BitmaskWallContainer(tilesDown, tilesAcross);
    }

    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        int x, y;
        x = xOffset;
        y = yOffset;

        renderer.drawTile(g, x, y);
        x += renderer.tileWidth();
        for (int j = 1; j < tilesAcross; j++) {
            if (walls.isWall(new SimpleLocation(0, j), Direction.LEFT)) {
                renderer.drawWall(g, x, y, false);
            } else {
                renderer.drawNoWall(g, x, y, false);
            }
            x += renderer.wallShortSideLength();
            renderer.drawTile(g, x, y);
            x += renderer.tileWidth();
        }
        y += renderer.tileHeight();
        x = xOffset;

        for (int i = 1; i < tilesDown; i++) {
            if (walls.isWall(new SimpleLocation(i - 1, 0), Direction.DOWN)) {
                renderer.drawWall(g, x, y, true);
            } else {
                renderer.drawNoWall(g, x, y, true);
            }
            x += renderer.tileWidth();
            for (int j = 1; j < tilesAcross; j++) {
                renderer.drawCorner(g, x, y);
                x += renderer.wallShortSideLength();
                if (walls.isWall(new SimpleLocation(i - 1, j), Direction.DOWN)) {
                    renderer.drawWall(g, x, y, true);
                } else {
                    renderer.drawNoWall(g, x, y, true);
                }
                x += renderer.tileWidth();
            }
            y += renderer.wallShortSideLength();
            x = xOffset;

            renderer.drawTile(g, x, y);
            x += renderer.tileWidth();
            for (int j = 1; j < tilesAcross; j++) {
                if (walls.isWall(new SimpleLocation(i, j), Direction.LEFT)) {
                    renderer.drawWall(g, x, y, false);
                } else {
                    renderer.drawNoWall(g, x, y, false);
                }
                x += renderer.wallShortSideLength();
                renderer.drawTile(g, x, y);
                x += renderer.tileWidth();
            }
            y += renderer.tileHeight();
            x = xOffset;
        }
    }

    @Override
    public int tilesAcross() {
        return tilesAcross;
    }

    @Override
    public int tilesDown() {
        return tilesDown;
    }

    @Override
    public Location getPlayerLocation(Player p) {
        return Preconditions.checkNotNull(players.get(p), "Player not found.");
    }

    @Override
    public Location movePlayer(Player p, Direction d) {
        Location l = getPlayerLocation(p);
        if (!walls.isWall(l, d)) {
            Location moved = l.move(d);
            if (isBoardLocation(moved)) {
                l = moved;
                players.put(p, l);
            }
        }
        return l;
    }

    @Override
    public Location jumpPlayer(Player p, Direction d, Direction... bonusesToSpend) {
        Preconditions.checkArgument(p.canJump(), "Player " + p.getName() + " cannot jump walls.");
        Location l = getPlayerLocation(p);
        if (walls.isWall(l, d)) {
            Location moved = l.move(d);
            if (isBoardLocation(moved)) {
                Preconditions.checkArgument(bonusesToSpend.length == p.bonusesToJump());
                if (p.bonusesEqual() && bonusesToSpend.length > 0) {
                    Preconditions.checkNotNull(bonusesToSpend[0]);
                    Direction b = bonusesToSpend[0];
                    for (Direction bonus : bonusesToSpend) {
                        if (!b.equals(bonus)) {
                            return l;
                        }
                    }
                }

                for (Direction bonus : bonusesToSpend) {
                    p.spendBonus(bonus);
                }
                l = moved;
                players.put(p, l);
            }
        }
        return l;
    }

    @Override
    public boolean putWall(Location l, Direction d) {
        boolean alreadySet = walls.isWall(l, d);
        if (!alreadySet) {
            walls.putWall(l, d);
        }
        return !alreadySet;
    }

    private boolean isBoardLocation(Location l) {
        int r, c;
        r = l.getRow();
        c = l.getColumn();
        return (r >= 0 && c >= 0 && r < tilesDown && c < tilesAcross);
    }

}
