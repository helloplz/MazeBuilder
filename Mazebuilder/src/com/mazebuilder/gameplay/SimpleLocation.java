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

    @Override
    public Direction isAdjacent(Location loc) {
        if (loc.getRow() == this.getRow()) {
            if (loc.getColumn() - 1 == this.getColumn()) {
                return Direction.RIGHT;
            } else if (loc.getColumn() + 1 == this.getColumn()) {
                return Direction.LEFT;
            }
        } else if (loc.getColumn() == this.getColumn()) {
            if (loc.getRow() - 1 == this.getRow()) {
                return Direction.DOWN;
            } else if (loc.getRow() + 1 == this.getRow()) {
                return Direction.UP;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "SimpleLocation [row=" + row + ", column=" + column + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SimpleLocation other = (SimpleLocation) obj;
        if (column != other.column)
            return false;
        if (row != other.row)
            return false;
        return true;
    }
}
