package com.mazebuilder.gameplay.board;

import com.mazebuilder.gameplay.Direction;
import com.mazebuilder.gameplay.Location;

public final class BitmaskWallContainer implements WallContainer {
    
    private final int height, width;
    private final int[][] walls;
    
    public BitmaskWallContainer(int height, int width) {
        this.height = height;
        this.width = width;
        walls = new int[height][width];
    }
    
    @Override
    public void putWall(Location l, Direction d) {
        putWallInternal(l,d);
        putWallInternal(l.move(d),d.reverse());
    }
    
    private void putWallInternal(Location l, Direction d) {
        int x,y;
        y = l.getRow();
        x = l.getColumn();
        if(x>=0 && y>=0 && x<width && y<height) {
            walls[y][x] |= d.value();
        }
    }
    
    @Override
    public boolean isWall(Location l, Direction d) {
        int x,y;
        y = l.getRow();
        x = l.getColumn();
        return (walls[y][x] & d.value()) != 0;
    }
    
}
