package com.home.tateana.logicgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.home.tateana.logicgame.gui.SoundPlayer;
import com.home.tateana.logicgame.utils.DifficultyConfiguration;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;


public class MainActivity extends Activity  {

    private SoundPlayer soundPlayer;
    private HashMap<Integer, DifficultyConfiguration.DifficultyLevel> radioButtonLevelMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Game.LOG_TAG, "create activity_main activity");
        setContentView(R.layout.activity_main);

        final Game game = Game.getGame();
        soundPlayer = game.getSoundPlayer(this);


        Picasso.with(this).load(R.drawable.ig_main_bg_left).into((ImageView)findViewById(R.id.main_bg_left));
        Picasso.with(this).load(R.drawable.ig_main_bg_right).into((ImageView)findViewById(R.id.main_bg_right));


        ImageButton startButton = (ImageButton) findViewById(R.id.start);
        ImageView logoView = (ImageView) findViewById(R.id.logo);
        Picasso.with(this).load(R.drawable.ig_main_logo).into(logoView);

        //startButton.setOnTouchListener(buttonTouchListener);
        View.OnClickListener startClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlayer.playButtonClick();
                startLevel(game.getSettings(MainActivity.this).getLastRequestedLevel());
            }
        };
        startButton.setOnClickListener(startClickListener);
        logoView.setOnClickListener(startClickListener);


        ImageButton restartButton = (ImageButton) findViewById(R.id.restart);
        restartButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               soundPlayer.playButtonClick();
                                               game.getSettings(MainActivity.this).reset();
                                               startLevel(Game.LEVEL_STORY_1);
                                           }
                                       }
        );


        ImageButton readingButton = (ImageButton) findViewById(R.id.reading);
        readingButton.setSelected(game.getSettings(this).isReadText());
        readingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlayer.playButtonClick();
                v.setSelected(!v.isSelected());
                game.getSettings(MainActivity.this).setReadText(v.isSelected());
            }
        });


        ImageButton musicButton = (ImageButton) findViewById(R.id.music);
        musicButton.setSelected(game.getSettings(this).isPlayMusic());
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlayer.playButtonClick();
                v.setSelected(!v.isSelected());
                game.getSettings(MainActivity.this).setPlayMusic(v.isSelected());
                if(v.isSelected()) {
                    game.getBackgroundMusic(MainActivity.this).start();
                } else {
                    game.getBackgroundMusic(MainActivity.this).pause();
                }
            }
        });

        initDifficultyLevelRadioGroup();

        ImageButton showLevelsButton = (ImageButton) findViewById(R.id.showLevels);
        showLevelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlayer.playButtonClick();
                Intent showSelectLevelActivity = new Intent(MainActivity.this, SelectLevelActivity.class);
                startActivity(showSelectLevelActivity);
                overridePendingTransition(R.anim.slide_up_info,R.anim.abc_slide_out_bottom);
            }
        });


    }

    protected void initDifficultyLevelRadioGroup() {
        radioButtonLevelMap = new HashMap<Integer, DifficultyConfiguration.DifficultyLevel>();
        radioButtonLevelMap.put(R.id.easy, DifficultyConfiguration.DifficultyLevel.EASY);
        radioButtonLevelMap.put(R.id.normal, DifficultyConfiguration.DifficultyLevel.NORMAL);
        radioButtonLevelMap.put(R.id.hard, DifficultyConfiguration.DifficultyLevel.HARD);

        HashMap<DifficultyConfiguration.DifficultyLevel, Integer> levelRadioButtonMap = new HashMap<DifficultyConfiguration.DifficultyLevel, Integer>();
        levelRadioButtonMap.put(DifficultyConfiguration.DifficultyLevel.EASY, R.id.easy);
        levelRadioButtonMap.put(DifficultyConfiguration.DifficultyLevel.NORMAL, R.id.normal);
        levelRadioButtonMap.put(DifficultyConfiguration.DifficultyLevel.HARD, R.id.hard);

        RadioGroup difficultyLevelRadioGroup = (RadioGroup)findViewById(R.id.difficultyLevel);
        DifficultyConfiguration.DifficultyLevel  difficultyLevel = Game.getGame().getSettings(this).getDifficultyLevel();
        RadioButton radioButton = (RadioButton) findViewById(levelRadioButtonMap.get(difficultyLevel));
        radioButton.setChecked(true);
        difficultyLevelRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                soundPlayer.playButtonClick();
                onDifficultyLevelChange(checkedId);
            }
        });
    }

    protected void onDifficultyLevelChange(int checkedId) {
        Log.d(Game.LOG_TAG, " change difficulty to " + String.valueOf(checkedId));
        DifficultyConfiguration.DifficultyLevel  difficultyLevel = radioButtonLevelMap.get(checkedId);
        Game.getGame().getSettings(this).setDifficultyLevel(difficultyLevel);
    }

    protected void startLevel(int level) {
        Intent startLevelIntent = new Intent(this, Game.getGame().getClass(level));
        startLevelIntent.putExtra(Game.STATE_LEVEL, level);
        startActivity(startLevelIntent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Game.getGame().getSettings(this).save();
    }


    @Override
    protected void onStop() {
        Game.getGame().unbind(this);
        super.onStop();
    }

    @Override
    protected void onStart() {
        Game.getGame().bind(this);
        super.onStart();
    }
}