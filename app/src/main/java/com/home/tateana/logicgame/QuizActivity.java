package com.home.tateana.logicgame;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.home.tateana.logicgame.quiz.QuizModel;
import java.util.ArrayList;



public class QuizActivity extends FragmentActivity implements QuizModel.ViewProvider {

    private static final String TAG_MODEL = "TAG_QUIZ_MODEL";

    private MediaPlayer successSound;
    private MediaPlayer verySuccessSound;
    private MediaPlayer failedSound;
    private MediaPlayer veryFailedSound;

    private RatingBar scoreView;
    private ArrayList<View> answerButtons = new ArrayList<View>();
    private TextView questionView;
    private TextView descriptionView;
    private ImageButton okView;

    private QuizModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ModelFragment modelFragment = (ModelFragment) getFragmentManager().findFragmentByTag(TAG_MODEL);
        if (modelFragment == null) {
            modelFragment = new ModelFragment();
            getFragmentManager().beginTransaction()
                    .add(modelFragment, TAG_MODEL)
                    .commit();

            Intent intent = getIntent();
            int level = intent.getIntExtra(Game.STATE_LEVEL, Game.getGame().getSettings(this).getLastRequestedLevel());
            int score = Game.getGame().getSettings(this).getLastScore();

            QuizModel newModel = Game.getGame().getTaskModel(level, this);
            newModel.setScore(score);
            modelFragment.setModel(newModel);
        }
        model = (QuizModel) modelFragment.getModel();
        model.setViewProvider(this);

        scoreView = (RatingBar) findViewById(R.id.score);
        LayerDrawable stars = (LayerDrawable) scoreView.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(getResources().getColor(R.color.brown), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.brown), PorterDuff.Mode.SRC_ATOP);
        scoreView.setStepSize(1);
        scoreView.setNumStars(model.getSize());
        scoreView.setRating(model.getScore());

        ViewStub viewStub = (ViewStub) findViewById(R.id.game_content);
        viewStub.setLayoutResource(model.getContentLayoutId());
        viewStub.inflate();

        ImageButton homeButton = (ImageButton) findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        successSound = MediaPlayer.create(this, R.raw.success);
        verySuccessSound = MediaPlayer.create(this, R.raw.anger);
        failedSound = MediaPlayer.create(this, R.raw.oops);
        veryFailedSound = MediaPlayer.create(this, R.raw.devil_laugh);

        questionView = (TextView) findViewById(R.id.question);
        descriptionView = (TextView) findViewById(R.id.description);

        okView = (ImageButton) findViewById(R.id.ok);
        View.OnClickListener okClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOkClick();

            }
        };
        okView.setOnClickListener(okClickListener);

        View.OnClickListener answerClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerClick(v);
            }
        };

        initAnswerView(R.id.answer1, answerClickListener);
        initAnswerView(R.id.answer2, answerClickListener);
        initAnswerView(R.id.answer3, answerClickListener);
        initAnswerView(R.id.answer4, answerClickListener);
        initAnswerView(R.id.answer5, answerClickListener);
        initAnswerView(R.id.answer6, answerClickListener);
        initAnswerView(R.id.answer7, answerClickListener);
        initAnswerView(R.id.answer8, answerClickListener);
        initAnswerView(R.id.answer9, answerClickListener);

        model.showNextTask();
    }

    protected void initAnswerView(int id, View.OnClickListener listener) {
        View answerButton = findViewById(id);
        answerButtons.add(answerButton);
        answerButton.setOnClickListener(listener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        GameSettings gameSettings = Game.getGame().getSettings(this);
        gameSettings.setLastScore(model.getScore());
        gameSettings.save();
    }

    public void onOkClick() {
        QuizModel.Result resultQuality = model.checkAnswers();
        scoreView.setRating(model.getScore());

        int layoutId = R.layout.dialog_failed;
        switch (resultQuality) {
            case FAILED:
                layoutId = R.layout.dialog_failed;
                failedSound.start();
                break;
            case VERY_FAILED:
                layoutId = R.layout.dialog_very_failed;
                veryFailedSound.start();
                break;
            case SUCCESS:
                successSound.start();
                if(!model.showNextTask()){
                    navigate();
                    return;
                }
                layoutId = R.layout.dialog_success;
                break;
            case VERY_SUCCESS:
                verySuccessSound.start();
                if(!model.showNextTask()){
                    navigate();
                    return;
                }
                layoutId = R.layout.dialog_very_success;
                break;
        }

        View layout = getLayoutInflater().inflate(layoutId, null);
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
        deselectAllAnswerButtons();
    }

    protected void deselectAllAnswerButtons() {
        for (View button : answerButtons) {
            button.setSelected(false);
        }
    }

    public void onAnswerClick(View selectedButton) {
        selectedButton.setSelected(!selectedButton.isSelected());

        okView.setEnabled(false);
        for(View button : answerButtons) {
            if(button.isSelected()) {
                okView.setEnabled(true);
                break;
            }
        }
    }

    @Override
    public TextView questionView() {
        return questionView;
    }

    @Override
    public TextView descriptionView() {
        return descriptionView;
    }

    @Override
    public ArrayList<View> answerButtons() {
        return answerButtons;
    }

    protected void navigate() {
        int nextLevel = model.getLevel() + 1;
        Intent goToNextActivity = new Intent(this, Game.getGame().getClass(nextLevel));
        goToNextActivity.putExtra(Game.STATE_LEVEL, nextLevel);
        startActivity(goToNextActivity);
        finish();
    }

    protected void onDestroy() {
        Log.i(Game.LOG_TAG, "onDestroy quiz activity");
        model.unAttachDependencies();
        super.onDestroy();
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
