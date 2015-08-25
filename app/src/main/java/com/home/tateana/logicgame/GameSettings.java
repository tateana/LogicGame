package com.home.tateana.logicgame;

import android.content.Context;
import android.content.SharedPreferences;

import com.home.tateana.logicgame.utils.DifficultyConfiguration;

/**
 * Created by tateana on 19-Aug-15.
 */
public class GameSettings {

    final public static String PREFS_NAME = "GAME_PREFS";

    final private static String STATE_MUSIC = "playMusic";
    final private static String STATE_READING = "readText";
    final private static String STATE_DIFFICULTY = "difficultyLevel";
    final private static String STATE_AVAILABLE_LEVEL = "availableLevel";
    final private static String STATE_PASSED_LEVEL = " passedLevel";
    final private static String STATE_LAST_LEVEL = " lastRequestedLevel";

    private boolean isSaved = true;
    private SharedPreferences sharedPreferences;

    private int lastRequestedLevel = Game.LEVEL_STORY_1;



    private int lastScore = 0;
    private int availableLevel = Game.LEVEL_TASK_6;
    private int passedLevel = Game.LEVEL_TASK_6;
    private DifficultyConfiguration.DifficultyLevel difficultyLevel = DifficultyConfiguration.DifficultyLevel.NORMAL;
    private boolean playMusic = false;
    private boolean readText = false;

    public GameSettings(Context context) {
        this(context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE));
    }

    public GameSettings(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;

        lastRequestedLevel = this.sharedPreferences.getInt(STATE_LAST_LEVEL, lastRequestedLevel);
        availableLevel = this.sharedPreferences.getInt(STATE_AVAILABLE_LEVEL, availableLevel);
        passedLevel = this.sharedPreferences.getInt(STATE_PASSED_LEVEL, passedLevel);
        playMusic = this.sharedPreferences.getBoolean(STATE_MUSIC, playMusic);
        readText = this.sharedPreferences.getBoolean(STATE_READING, readText);
        String difficultyLevelString = this.sharedPreferences.getString(STATE_DIFFICULTY, difficultyLevel.toString());
        difficultyLevel = DifficultyConfiguration.DifficultyLevel.valueOf(difficultyLevelString);
    }

    public void reset() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }


    public void save() {
        if(isSaved) {
            return;
        }

        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt(STATE_LAST_LEVEL, lastRequestedLevel);
        editor.putInt(STATE_AVAILABLE_LEVEL, availableLevel);
        editor.putInt(STATE_PASSED_LEVEL, passedLevel);
        editor.putBoolean(STATE_MUSIC, playMusic);
        editor.putBoolean(STATE_READING, readText);
        editor.putString(STATE_DIFFICULTY, difficultyLevel.toString());
        editor.commit();
    }

    public int getLastRequestedLevel() {
        return lastRequestedLevel;
    }

    public void setLastRequestedLevel(int lastRequestedLevel) {
        if (lastRequestedLevel > availableLevel) {
            return;
        }

        if(lastRequestedLevel == this.lastRequestedLevel) {
            return;
        }

        if(lastRequestedLevel >= Game.LEVEL_STORY_1 && lastRequestedLevel <= Game.LEVEL_STORY_11) {
            this.lastRequestedLevel = lastRequestedLevel;
            isSaved = false;
            lastScore = 0;
        }
    }

    public int getAvailableLevel() {
        return availableLevel;
    }

    public void setAvailableLevel(int availableLevel) {
        if(availableLevel <= this.availableLevel) {
            return;
        }

        if(availableLevel > Game.LEVEL_STORY_11) {
            availableLevel = Game.LEVEL_STORY_11;
        }

        if(availableLevel >= Game.LEVEL_STORY_2) {
            this.availableLevel = availableLevel;
            isSaved = false;
        }
    }

    public int getPassedLevel() {
        return passedLevel;
    }

    public void setPassedLevel(int passedLevel) {
        if(passedLevel <= this.passedLevel) {
            return;
        }

        if(passedLevel > availableLevel) {
            return;
        }

        if(passedLevel >= Game.LEVEL_STORY_1 && passedLevel <= Game.LEVEL_STORY_11) {
            this.passedLevel = passedLevel;
            isSaved = false;
        }
    }

    public boolean isPlayMusic() {
        return playMusic;
    }

    public void setPlayMusic(boolean playMusic) {
        this.playMusic = playMusic;
        isSaved = false;
    }

    public boolean isReadText() {
        return readText;
    }

    public void setReadText(boolean readText) {
        this.readText = readText;
        isSaved = false;
    }

    public void setDifficultyLevel(DifficultyConfiguration.DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        isSaved = false;
    }

    public DifficultyConfiguration.DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
    }
}
