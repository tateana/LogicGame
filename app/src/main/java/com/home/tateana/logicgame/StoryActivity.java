package com.home.tateana.logicgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.home.tateana.logicgame.story.StoryModel;

import android.os.Handler;


public class StoryActivity extends Activity {

    private static final String TAG_MODEL = "TAG_STORY_MODEL";
    private StoryModel model;
    private Handler delayHandler;
    private Runnable delayExecutor;

    private View.OnTouchListener buttonTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(Game.LOG_TAG, "click button ");
                model.getSoundPlayer().playButtonClick();
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Game.LOG_TAG, "create activity " + this.getClass().toString());
        setContentView(R.layout.activity_story);

        ImageButton homeButton = (ImageButton) findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               finish();
                                           }
                                       }

        );

        ImageButton nextView = (ImageButton) findViewById(R.id.next);
        ImageButton backView = (ImageButton) findViewById(R.id.back);
        View.OnClickListener navigateListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.next) {
                    navigate(1);
                } else {
                    navigate(-1);
                }
            }
        };
        nextView.setOnClickListener(navigateListener);
        backView.setOnClickListener(navigateListener);

        ModelFragment modelFragment = (ModelFragment) getFragmentManager().findFragmentByTag(TAG_MODEL);

        if (modelFragment == null) {
            Log.d(Game.LOG_TAG, "create a new model fragment");
            modelFragment = new ModelFragment();
            getFragmentManager().beginTransaction()
                    .add(modelFragment, TAG_MODEL)
                    .commit();

            int level;
            Intent intent = getIntent();
            level = intent.getIntExtra(Game.STATE_LEVEL, 0);
            if(level == 0 && savedInstanceState != null) {
                level = savedInstanceState.getInt(Game.STATE_LEVEL, 0);
            }
            if(level == 0) {
                level = Game.LEVEL_STORY_1;
            }

            StoryModel model = Game.getGame().getStoryModel(level, this);
            modelFragment.setModel(model);
        }
        model = (StoryModel) modelFragment.getModel();
        model.init(this);

        ImageButton showTextButton = (ImageButton) findViewById(R.id.showText);
        showTextButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Intent showTextActivity = new Intent(StoryActivity.this, StoryTextActivity.class);
                                              showTextActivity.putExtra(StoryTextActivity.TAG_TEXT_ID, model.getTextId());
                                              startActivity(showTextActivity);
                                              overridePendingTransition(R.anim.slide_up_info,R.anim.abc_slide_out_bottom);
                                          }
                                      }

        );
    }

    @Override
    protected void onPause() {
        Log.d(Game.LOG_TAG, "pause activity " + this.getClass().toString());
        super.onPause();
        model.pause();
    }

    @Override
    protected void onResume() {
        Log.d(Game.LOG_TAG, "resume activity " + this.getClass().toString());
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && delayHandler == null) {
            Log.d(Game.LOG_TAG, "window on focus start delay " + this.getClass().toString());
            delayHandler = new Handler();
            delayExecutor = new Runnable() {
                @Override
                public void run() {
                    Log.d(Game.LOG_TAG, "window on focus finish delay " + this.getClass().toString());
                    model.start();
                }
            };
            delayHandler.postDelayed(delayExecutor, 2000L);
        } else if(hasFocus) {
            Log.d(Game.LOG_TAG, "window on focus without delay " + this.getClass().toString());
            model.start();
        } else {
            Log.d(Game.LOG_TAG, "window off focus " + this.getClass().toString());
            model.pause();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        delayHandler.removeCallbacks(delayExecutor);
        if (isFinishing()) {
            Log.d(Game.LOG_TAG, " finish and destroy activity " + this.getClass().toString());
            model.destroy();
        } else {
            Log.d(Game.LOG_TAG, " destroy activity " + this.getClass().toString());
            model.stop();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(Game.LOG_TAG, "save activity state " + this.getClass().toString());
        int level = model.getLevel();
        outState.putInt(Game.STATE_LEVEL, level);
        super.onSaveInstanceState(outState);
    }

    protected void navigate(int direction) {
        int nextLevel = (model.getLevel() + direction);
        Intent goToNextActivity = new Intent(this, Game.getGame().getClass(nextLevel));
        goToNextActivity.putExtra(Game.STATE_LEVEL, nextLevel);
        startActivity(goToNextActivity);
        finish();
    }
}
