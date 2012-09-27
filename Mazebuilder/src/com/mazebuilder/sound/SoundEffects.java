package com.mazebuilder.sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.Music;

public final class SoundEffects {

    private SoundEffects() {
        // never instantiated
    }

    private static final Sound putWall;
    private static final Sound runnerMove;
    private static final Sound chaserMove;
    private static final Sound chaserWin;
    private static final Sound chaserGetBonus;
//    private static final Music backGroundMusic;

    static {
        try {
            putWall = new Sound("./assets/sounds/DropWall.wav");
            runnerMove = new Sound("./assets/sounds/PlayerOneMove.wav");
            chaserMove = new Sound("./assets/sounds/PlayerTwoMove.wav");
            chaserWin = new Sound("./assets/sounds/PlayerOneDies.wav");
            chaserGetBonus = new Sound("./assets/sounds/PlayerTwoGetPower.wav");
//            backGroundMusic = new Music("./assets/sounds/mischief2.ogg");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void playBGM() {
//        backGroundMusic.play();
//    }
    
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
