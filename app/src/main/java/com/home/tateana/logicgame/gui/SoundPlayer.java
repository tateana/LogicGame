package com.home.tateana.logicgame.gui;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.SparseIntArray;

/**
 * Created by tateana on 22-Jul-15.
 */
public interface SoundPlayer {

    public void release();

    public void loadSound(int id, Context context);

    public void loadSound(int id, int loop, Context context);

    public void loadText(int id, Context context);

    public void playSound(int id);

    public void playText();

    public void pause();

    public void playButtonClick();

}
