package com.home.tateana.logicgame.utils;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import com.home.tateana.logicgame.GameSettings;
import com.home.tateana.logicgame.R;

/**
 * Created by tateana on 20-Aug-15.
 */
public class BackgroundMusic  {

    private MediaPlayer music;
    private int binds = 0;
    private GameSettings settings;

    public BackgroundMusic(MediaPlayer music, GameSettings settings) {
        this.music = music;
        this.settings = settings;
    }

    public void bind() {
        binds++;
        if(binds > 0) {
            start();
        }
    }

    public void unbind() {
        binds = Math.min(binds, binds-1);
        if(binds == 0) {
            release();
        }
    }

    public void  start() {
        if(!settings.isPlayMusic()) {
            return;
        }

        if(music.isPlaying()) {
            return;
        }

        music.start();
    }

    public void pause() {
        if(music.isPlaying()) {
            music.stop();
        }
    }

    public void release() {
        music.release();
    }

}
