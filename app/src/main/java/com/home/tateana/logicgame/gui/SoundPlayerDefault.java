package com.home.tateana.logicgame.gui;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.util.SparseIntArray;

import com.home.tateana.logicgame.Game;
import com.home.tateana.logicgame.GameSettings;
import com.home.tateana.logicgame.R;

/**
 * Created by tateana on 22-Jul-15.
 */
public class SoundPlayerDefault extends SoundPlayerNoText {

    private MediaPlayer textReading;
    private int currentTextPosition = 0;

    public SoundPlayerDefault(AudioManager audioManager, Context context) {
        super(audioManager, context);
    }


    public void release() {
        if(textReading != null) {
            textReading.release();
        }

        super.release();
    }

    public void loadText(int id, Context context) {
        if(textReading == null) {
            textReading = MediaPlayer.create(context, id);
            textReading.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
    }

    public void playText() {
        if(textReading.isPlaying()) {
            return;
        }

        if(currentTextPosition > 0) {
            textReading.seekTo(currentTextPosition);
            currentTextPosition = 0;
        }
        textReading.start();
    }

    public void pause() {
        super.pause();
        if (textReading.isPlaying()) {
            currentTextPosition = textReading.getCurrentPosition();
            textReading.pause();
        }
    }
}
