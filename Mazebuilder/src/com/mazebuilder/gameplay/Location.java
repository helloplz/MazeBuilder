package com.mazebuilder.gameplay;

public interface Location {
	
	int getRow();
	
	int getColumn();
	
	Location move(Direction d);
	
}
