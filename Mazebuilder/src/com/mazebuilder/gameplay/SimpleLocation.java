package com.mazebuilder.gameplay;

public final class SimpleLocation implements Location {

    private final int row, column;

    public SimpleLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public Location move(Direction d) {
        switch (d) {
        case DOWN:
            return new SimpleLocation(row + 1, column);
        case UP:
            return new SimpleLocation(row - 1, column);
        case LEFT:
            return new SimpleLocation(row, column - 1);
        case RIGHT:
            return new SimpleLocation(row, column + 1);
        default:
            throw new RuntimeException("Unknown direction " + d.toString());
        }
    }

}
