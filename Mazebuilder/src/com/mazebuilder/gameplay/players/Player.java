package com.mazebuilder.gameplay.players;

import org.newdawn.slick.Graphics;

import com.google.common.collect.Multiset;
import com.mazebuilder.gameplay.Direction;

public interface Player {

    String getName();

    String getType();

    boolean canJump();

    /** Should be called every turn -- will handle matters such as generating movement bonuses etc. **/
    void startTurn();

    /** Returns the set of bonuses for the player. **/
    Multiset<Direction> getBonuses();

    /** Returns the number of bonuses the player needs to spend to jump a wall **/
    int bonusesToJump();

    /** Returns true if the bonuses spent to jump should be equal, or false otherwise. **/
    boolean bonusesEqual();

    /** Removes a bonus from the player. Returns true if the bonus was successfully removed, or false otherwise. **/
    boolean spendBonus(Direction d);

    void render(Graphics g, int xOffset, int yOffset, int tileWidth, int tileHeight);

    boolean canMove();

    int spendMove();
}
