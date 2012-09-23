package com.mazebuilder.gameplay;

import com.google.common.collect.Multiset;
import com.mazebuilder.renderer.Renderable;

public interface Player extends Renderable {

    String getName();

    boolean canJump();

    /** Should be called every turn -- will handle matters such as generating movement bonuses etc. **/
    void executeTurn();

    /** Returns the set of bonuses for the player. **/
    Multiset<Direction> getBonuses();

    /** Returns the number of bonuses the player needs to spend to jump a wall **/
    int bonusesToJump();

    /** Returns true if the bonuses spent to jump should be equal, or false otherwise. **/
    boolean bonusesEqual();

    /** Removes a bonus from the player. Returns true if the bonus was successfully removed, or false otherwise. **/
    boolean spendBonus(Direction d);

}
