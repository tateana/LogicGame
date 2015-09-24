package com.home.tateana.logicgame.gui;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.util.SparseIntArray;

import com.home.tateana.logicgame.Game;
import com.home.tateana.logicgame.R;

/**
 * Created by tateana on 22-Jul-15.
 */
public class SoundPlayerNoText implements SoundPlayer {

    private SparseIntArray sounds = new SparseIntArray();
    private SparseIntArray loops = new SparseIntArray();
    private SoundPool player;
    protected AudioManager audioManager;


    public SoundPlayerNoText(AudioManager audioManager, Context context) {
        super();
        this.audioManager = audioManager;

        player = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        loadSound(R.raw.magic, context);
    }

    public void release() {
        Log.d(Game.LOG_TAG, "release sound player");
        if(player != null) {
            player.release();
        }
    }

    public void loadSound(int id, Context context) {
        sounds.append(id, player.load(context, id, 1));
        loops.append(id, 0);
    }

    public void loadSound(int id, int loop, Context context) {
        loadSound(id, context);
        player.setLoop(sounds.get(id), loop);
        loops.append(id, loop);
    }

    @Override
    public void loadText(int id, Context context) {

    }


    public void playSound(int id) {
        play(id, 0);
    }

    @Override
    public void playText() {

    }


    public void pause() {
        if(player != null) {
            player.autoPause();
        }
    }

    public void playButtonClick() {
        play(R.raw.magic, 1);
    }

    private void play(int id, int priority) {
        float curStreamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        player.play(sounds.get(id), curStreamVolume, curStreamVolume, priority, loops.get(id), 1.0f);
    }
}
