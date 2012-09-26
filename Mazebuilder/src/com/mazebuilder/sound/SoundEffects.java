package com.mazebuilder.sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public final class SoundEffects {

    private SoundEffects() {
        // never instantiated
    }

    private static final Sound putWall;
    private static final Sound runnerMove;
    private static final Sound chaserMove;
    private static final Sound chaserWin;
    private static final Sound chaserGetBonus;

    static {
        try {
            putWall = new Sound("\\assets\\sounds\\DropWall.wav");
            runnerMove = new Sound("\\assets\\sounds\\PlayerOneMove.wav");
            chaserMove = new Sound("\\assets\\sounds\\PlayerTwoMove.wav");
            chaserWin = new Sound("\\assets\\sounds\\PlayerOneDies.wav");
            chaserGetBonus = new Sound("\\assets\\sounds\\PlayerTwoGetPower.wav");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playPutWall() {
        putWall.play();
    }

    public static void playRunnerMove() {
        runnerMove.play();
    }

    public static void playChaserMove() {
        chaserMove.play();
    }

    public static void playChaserWin() {
        chaserWin.play();
    }

    public static void playChaserGetBonus() {
        chaserGetBonus.play();
    }

}
