package com.mazebuilder.gameplay;

public interface Location {
	
	int getRow();
	
	int getColumn();
	
	Location move(Direction d);

	/**
	 * Tests if the given location is orthogonally adjacent to this location.
	 * @return the direction that the other location is with respect to this one, null if not adjacent or is equal
	 */
    Direction isAdjacent(Location loc);
	
}
