package com.mazebuilder.gameplay;

import com.google.common.collect.Multiset;
import com.mazebuilder.renderer.Renderable;

public interface Player extends Renderable {
	
	String getName();
	
	boolean canJump();
	
	/** Should be called every turn -- will handle matters such as drawing movement bonuses etc. **/
	boolean executeTurn();
	
    /** Returns the set of bonuses for the player. **/
    Multiset<Direction> getBonuses();
    
    /** Returns the number of bonuses the player needs to spend to jump a wall **/
    int bonusesToJump();
    
    /** Removes a bonus from the player. **/
    boolean spendBonus(Direction d);
	
}
