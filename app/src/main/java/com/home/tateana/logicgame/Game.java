package com.home.tateana.logicgame;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.util.SparseIntArray;
import android.widget.ListAdapter;

import com.home.tateana.logicgame.gui.LevelAdapter;
import com.home.tateana.logicgame.gui.SoundPlayerDefault;
import com.home.tateana.logicgame.gui.SoundPlayerNoText;
import com.home.tateana.logicgame.gui.SoundPlayer;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;
import com.home.tateana.logicgame.quiz.ExpressionTaskFactory;
import com.home.tateana.logicgame.quiz.QuizImageModel;
import com.home.tateana.logicgame.quiz.QuizModel;
import com.home.tateana.logicgame.quiz.ShapeColorTaskFactory;
import com.home.tateana.logicgame.quiz.Task;
import com.home.tateana.logicgame.quiz.WordCategoriesStorage;
import com.home.tateana.logicgame.quiz.WordCategoryTaskFactory;
import com.home.tateana.logicgame.story.Story10EndFightWithDragon;
import com.home.tateana.logicgame.story.Story1StartModel;
import com.home.tateana.logicgame.story.Story2StealPrincessModel;
import com.home.tateana.logicgame.story.Story3KnightGoesToTown;
import com.home.tateana.logicgame.story.Story4FromTownToDesert;
import com.home.tateana.logicgame.story.Story5InDesert;
import com.home.tateana.logicgame.story.Story6FromDesertToForest;
import com.home.tateana.logicgame.story.Story7FromForestToWhale;
import com.home.tateana.logicgame.story.Story8RideOnWhale;
import com.home.tateana.logicgame.story.Story9StartFightWithDragon;
import com.home.tateana.logicgame.story.StoryModel;
import com.home.tateana.logicgame.utils.DifficultyConfiguration;
import com.home.tateana.logicgame.utils.RandomNumberGenerator;
import com.home.tateana.logicgame.utils.TaskFactory;

import java.util.LinkedList;

/**
 * Created by tateana on 06-Jul-15.
 */
public class Game {
    final public static String LOG_TAG = "MY_GAME";

    final public static int LEVEL_STORY_1 = 1;
    final public static int LEVEL_TASK_1 = 2;
    final public static int LEVEL_STORY_2 = 3;
    final public static int LEVEL_TASK_2 = 4;
    final public static int LEVEL_STORY_3 = 5;
    final public static int LEVEL_TASK_3 = 6;
    final public static int LEVEL_STORY_4 = 7;
    final public static int LEVEL_TASK_4 = 8;
    final public static int LEVEL_STORY_5 = 9;
    final public static int LEVEL_TASK_5 = 10;
    final public static int LEVEL_STORY_6 =11;
    final public static int LEVEL_TASK_6 = 12;
    final public static int LEVEL_STORY_7 = 13;
    final public static int LEVEL_TASK_7 = 14;
    final public static int LEVEL_STORY_8 = 15;
    final public static int LEVEL_TASK_8 = 16;
    final public static int LEVEL_STORY_9 = 17;
    final public static int LEVEL_TASK_9 = 18;
    final public static int LEVEL_STORY_10 = 19;
    final public static int LEVEL_TASK_10 = 20;
    final public static int LEVEL_STORY_11 = 21;

    final public static String STATE_LEVEL = "playerLevel";

    private GameSettings settings;
    private DifficultyConfiguration difficultyConfiguration;
    private MediaPlayer backgroundMusic;
    private int binds = 0;
    
    private static Game instance;

    public static void setGame(Game game) {
        instance = game;
    }

    public static Game getGame() {
        if(instance == null) {
            //return new Game();
            instance = new Game();
        }
        return instance;
    }

    public StoryModel getStoryModel(int level, Context context){
        getSettings(context).setLastRequestedLevel(level);
        getSettings(context).setAvailableLevel(level);
        getSettings(context).setPassedLevel(level-1);

        ViewLocationCalculator viewLocationCalculator = getViewLocationCalculator();
        SoundPlayer soundPlayer = getSoundPlayer(context);

        switch (level) {
            case LEVEL_STORY_1:
                return new Story1StartModel(level, soundPlayer, viewLocationCalculator);
            case LEVEL_STORY_2:
                return new Story2StealPrincessModel(level,  soundPlayer, viewLocationCalculator);
            case LEVEL_STORY_3:
                return new Story3KnightGoesToTown(level,  soundPlayer, viewLocationCalculator);
            case LEVEL_STORY_4:
                return new Story4FromTownToDesert(level,  soundPlayer, viewLocationCalculator);
            case LEVEL_STORY_5:
                return new Story5InDesert(level,  soundPlayer, viewLocationCalculator);
            case LEVEL_STORY_6:
                return new Story6FromDesertToForest(level, soundPlayer, viewLocationCalculator);
            case LEVEL_STORY_7:
                return new Story7FromForestToWhale(level, soundPlayer, viewLocationCalculator);
            case LEVEL_STORY_8:
                return new Story8RideOnWhale(level, soundPlayer, viewLocationCalculator);
            case LEVEL_STORY_9:
                return new Story9StartFightWithDragon(level, soundPlayer, viewLocationCalculator);
            case LEVEL_STORY_10:
                return new Story10EndFightWithDragon(level, soundPlayer, viewLocationCalculator);
            default:
                throw new RuntimeException("Not existing story level "+String.valueOf(level));
          }
    }

    public QuizModel getTaskModel(int level, Context context){

        int score = 0;
        if(level == getSettings(context).getLastRequestedLevel()) {
            score = getSettings(context).getLastScore();
        }

        getSettings(context).setLastRequestedLevel(level);
        getSettings(context).setPassedLevel(level-1);

        Resources resources = context.getResources();
        RandomNumberGenerator numberGenerator = this.getRandomNumberGenerator();
        DifficultyConfiguration difficultyConfiguration = getDifficultyConfiguration();
        difficultyConfiguration.setDifficultyLevel(getSettings(context).getDifficultyLevel());
        LinkedList<Task> tasks;
        TaskFactory taskFactory;
        QuizModel model;
        switch (level) {
            case LEVEL_TASK_1:
            case LEVEL_TASK_2:
            case LEVEL_TASK_4:
            case LEVEL_TASK_10:
                taskFactory = new ShapeColorTaskFactory(numberGenerator, difficultyConfiguration, resources);
                model =  new QuizImageModel(level, numberGenerator);
                break;
            case LEVEL_TASK_3:
            case LEVEL_TASK_8:
                taskFactory = new WordCategoryTaskFactory(numberGenerator, difficultyConfiguration, resources, new WordCategoriesStorage());
                model =  new QuizModel(level, numberGenerator);
                break;
            case LEVEL_TASK_5:
            case LEVEL_TASK_6:
            case LEVEL_TASK_7:
            case LEVEL_TASK_9:
                taskFactory = new ExpressionTaskFactory(numberGenerator, difficultyConfiguration, resources);
                model =  new QuizModel(level, numberGenerator);
                break;
            default:
                throw new RuntimeException("Not existing task level "+String.valueOf(level));
        }

        tasks = taskFactory.createTasks();
        model.setTasks(tasks);
        model.setScore(score);
        return model;
    }

    public Class<?> getClass(int level) {
        switch (level) {
            case LEVEL_STORY_1:
            case LEVEL_STORY_2:
            case LEVEL_STORY_3:
            case LEVEL_STORY_4:
            case LEVEL_STORY_5:
            case LEVEL_STORY_6:
            case LEVEL_STORY_7:
            case LEVEL_STORY_8:
            case LEVEL_STORY_9:
            case LEVEL_STORY_10:
                return StoryActivity.class;
            case LEVEL_TASK_1:
            case LEVEL_TASK_2:
            case LEVEL_TASK_3:
            case LEVEL_TASK_4:
                return QuizActivity.class;
            default:
                return MainActivity.class;
        }
    }

    private RandomNumberGenerator getRandomNumberGenerator() {
        return new RandomNumberGenerator();
    }

    private DifficultyConfiguration getDifficultyConfiguration() {
        if(difficultyConfiguration == null) {
            difficultyConfiguration = new DifficultyConfiguration();
        }

        return difficultyConfiguration;
    }

    public GameSettings getSettings(Context context) {
        if(settings == null) {
            settings = new GameSettings(context);
        }

        return settings;
    }


    public SoundPlayer getSoundPlayer(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        GameSettings settings = getSettings(context);

        if(getSettings(context).isReadText()) {
            return new SoundPlayerDefault(audioManager, context);
        }
        return new SoundPlayerNoText(audioManager, context);
    }

    public ViewLocationCalculator getViewLocationCalculator() {
        return new ViewLocationCalculator();
    }

    public ListAdapter getLevelAdapter(Context context) {
        //LevelGalleryAdapter adapter = new LevelGalleryAdapter(LEVEL_STORY_11 - 1, imageResArray);
        LevelAdapter adapter = new LevelAdapter(LEVEL_STORY_11, getSettings(context).getAvailableLevel(), getSettings(context).getPassedLevel());
        //adapter.setItemClickListener(activity);
        //adapter.setMaxAvailableLevel(availableLevel);
        return adapter;
    }

    public MediaPlayer getBackgroundMusic(Context context) {
        if(backgroundMusic == null) {
            backgroundMusic = MediaPlayer.create(context, R.raw.background_music);
            backgroundMusic.setAudioStreamType(AudioManager.STREAM_MUSIC);
            backgroundMusic.setLooping(true);
            backgroundMusic.setVolume(0.2f, 0.2f);
        }

        return backgroundMusic;
    }


    public void bind(Activity activity) {
        Log.d(Game.LOG_TAG, "start activity " + activity.toString());
        binds++;
        if(binds > 0 && getSettings(activity).isPlayMusic() && !getBackgroundMusic(activity).isPlaying()) {
            getBackgroundMusic(activity).start();
        }
    }
    
    public void unbind(Activity activity) {
        Log.d(Game.LOG_TAG, "stop activity " + activity.getClass().toString());
        binds = Math.max(0, binds - 1);
        if(binds == 0 && backgroundMusic != null) {
            backgroundMusic.release();
            backgroundMusic = null;
        }
    }

}
