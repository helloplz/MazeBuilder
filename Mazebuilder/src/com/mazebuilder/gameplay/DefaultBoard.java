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
        throw new RuntimeException("Not implemented.");
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
        if(!walls.isWall(l, d)) {
            Location moved = l.move(d);
            if(isBoardLocation(moved)) {
                l = moved;
                players.put(p, l);
            }
        }
        return l;
    }
    
    @Override
    public Location jumpPlayer(Player p, Direction d, Direction... bonusesToSpend) {
        throw new RuntimeException("Not implemented.");
    }
    
    @Override
    public boolean putWall(Location l, Direction d) {
        boolean alreadySet = walls.isWall(l, d);
        if(!alreadySet) {
            walls.putWall(l, d);
        }
        return !alreadySet;
    }
    
    private boolean isBoardLocation(Location l) {
        int r,c;
        r = l.getRow();
        c = l.getColumn();
        return (r>=0 && c>=0 && r<tilesDown && c<tilesAcross);
    }
    
}
