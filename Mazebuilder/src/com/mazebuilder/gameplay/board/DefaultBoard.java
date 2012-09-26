package com.mazebuilder.gameplay.board;

import java.util.Map;

import org.newdawn.slick.Graphics;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.gameplay.Location;
import com.mazebuilder.gameplay.SimpleLocation;
import com.mazebuilder.gameplay.players.Player;
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
    public Location getTile(int x, int y) {
        x /= renderer.tileWidth() + renderer.wallShortSideLength();
        y /= renderer.tileHeight() + renderer.wallShortSideLength();
        if (x < 0 || y < 0 || x >= tilesAcross || y >= tilesDown) {
            return null;
        }
        return new SimpleLocation(y, x);
    }

    @Override
    public Direction getWallDirection(int x, int y) {
        x %= renderer.tileWidth() + renderer.wallShortSideLength();
        y %= renderer.tileHeight() + renderer.wallShortSideLength();
        if (x > renderer.tileWidth() && x > y) {
            return Direction.RIGHT;
        }
        if (y > renderer.tileHeight() && y > x) {
            return Direction.DOWN;
        }
        return null;
    }

    private void renderTileRow(Graphics g, int x, int y, int row) {
        renderer.drawTile(g, x, y);
        x += renderer.tileWidth();
        for (int j = 1; j < tilesAcross; j++) {
            if (walls.isWall(new SimpleLocation(row, j), Direction.LEFT)) {
                renderer.drawWall(g, x, y, false);
            } else {
                renderer.drawNoWall(g, x, y, false);
            }
            x += renderer.wallShortSideLength();
            renderer.drawTile(g, x, y);
            x += renderer.tileWidth();
        }
    }

    private void renderWallRow(Graphics g, int x, int y, int rowAbove) {
        if (walls.isWall(new SimpleLocation(rowAbove, 0), Direction.DOWN)) {
            renderer.drawWall(g, x, y, true);
        } else {
            renderer.drawNoWall(g, x, y, true);
        }
        x += renderer.tileWidth();
        for (int j = 1; j < tilesAcross; j++) {
            renderer.drawCorner(g, x, y);
            x += renderer.wallShortSideLength();
            if (walls.isWall(new SimpleLocation(rowAbove, j), Direction.DOWN)) {
                renderer.drawWall(g, x, y, true);
            } else {
                renderer.drawNoWall(g, x, y, true);
            }
            x += renderer.tileWidth();
        }
    }

    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        int x, y;
        x = xOffset;
        y = yOffset;

        renderTileRow(g, x, y, 0);
        y += renderer.tileHeight();
        x = xOffset;

        for (int i = 1; i < tilesDown; i++) {
            renderWallRow(g, x, y, i - 1);
            y += renderer.wallShortSideLength();
            x = xOffset;

            renderTileRow(g, x, y, i);
            y += renderer.tileHeight();
            x = xOffset;
        }

        for (Map.Entry<Player, Location> e : players.entrySet()) {
            int row = e.getValue().getRow();
            int column = e.getValue().getColumn();
            x = xOffset + (column * (renderer.tileWidth() + renderer.wallShortSideLength()));
            y = yOffset + (row * (renderer.tileHeight() + renderer.wallShortSideLength()));
            e.getKey().render(g, x, y, renderer.tileWidth(), renderer.tileHeight());
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
    public boolean movePlayer(Player p, Direction d) {
        Location l = getPlayerLocation(p);
        if (p.canMove() && !walls.isWall(l, d)) {
            Location moved = l.move(d);
            if (isBoardLocation(moved)) {
                players.put(p, moved);
                p.spendMove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean movePlayerWithBonus(Player p, Direction d) {
        Preconditions.checkArgument(p.canJump(), "Player " + Strings.nullToEmpty(p.getName()) + " cannot jump.");
        Location l = getPlayerLocation(p);
        if (!walls.isWall(l, d)) {
            Location moved = l.move(d);
            if (isBoardLocation(moved) && p.spendBonus(d)) {
                players.put(p, moved);
                return true;
            }
        }
        return false;
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

    @Override
    public void addPlayerAtLocation(Player p, Location l) {
        Preconditions.checkNotNull(p, "Player cannot be null.");
        Preconditions.checkArgument(!players.containsKey(p), "Player " + Strings.emptyToNull(p.getName()) + " already exists on this board.");
        players.put(p, l);
    }

    public int[][] getWallLocations() {
        return walls.getWalls();
    }

    /**
     * Log important Board State information for testing purposes.
     * 
     * @return String boardState, which lists the player names followed by a coordinate, and walls in the form of (x,y,direction binary).
     */
    public String logBoardState() {
        String boardState = new String();
        for (Player player : this.players.keySet()) {
            Location l = this.getPlayerLocation(player);
            int x = l.getColumn();
            int y = l.getRow();
            boardState = boardState + player.getName() + " (" + x + ", " + y + ") ";
        }
        for (int i = 0; i < this.getWallLocations().length; i++) {
            for (int j = 0; j < this.getWallLocations()[i].length; j++) {
                boardState = boardState + " (" + j + ", " + i + ", " + this.getWallLocations()[i][j] + ") ";
            }
        }
        return boardState;
    }

}
