package com.home.tateana.logicgame.quiz;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.home.tateana.logicgame.Controller;
import com.home.tateana.logicgame.Game;
import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.utils.RandomNumberGenerator;
import com.home.tateana.logicgame.gui.ResultDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by tateana on 07-Jul-15.
 */
public class QuizController implements Controller {

    protected FragmentActivity context;
    protected int level;
    protected int score = 0;
    protected int failedAttempts = 0;
    protected RandomNumberGenerator numberGenerator;

    protected LinkedList<Task> tasks;
    private HashMap<View, Item> answerButtons;
    private ArrayList<View> buttons = new ArrayList<View>();
    private Task currentTask;

    private TextView questionView;
    private TextView descriptionView;
    private ImageButton okView;
    private RatingBar scoreView;

    private MediaPlayer successSound;
    private MediaPlayer verySuccessSound;
    private MediaPlayer failedSound;
    private MediaPlayer veryFailedSound;

    public QuizController(FragmentActivity context, LinkedList<Task> tasks, int level, int score, RandomNumberGenerator numberGenerator) {
        this.context = context;
        this.tasks = tasks;
        this.level = level;
        this.score = score;
        this.numberGenerator = numberGenerator;

        setupScoreView();
        initContentLayout();
        initSounds();

        currentTask = tasks.poll();
        initViewElements(currentTask);
        showTask(currentTask);
    }

    public int getLevel() {
        return level;
    }

    public int getSubLevel() {
        return score;
    }

    protected void initSounds() {
        successSound = MediaPlayer.create(context, R.raw.success);
        verySuccessSound = MediaPlayer.create(context, R.raw.anger);
        failedSound = MediaPlayer.create(context, R.raw.oops);
        veryFailedSound = MediaPlayer.create(context, R.raw.devil_laugh);
    }

    protected void setupScoreView() {
        if(scoreView == null) {
            scoreView = (RatingBar) context.findViewById(R.id.score);
            LayerDrawable stars = (LayerDrawable) scoreView.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(1).setColorFilter(context.getResources().getColor(R.color.brown), PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(0).setColorFilter(context.getResources().getColor(R.color.brown), PorterDuff.Mode.SRC_ATOP);
            scoreView.setStepSize(1.0f);
            Integer numStarts = score + tasks.size();
            scoreView.setNumStars(numStarts);
        }

        scoreView.setRating(score);
    }

    protected void initContentLayout() {
        initContentLayout(R.layout.quiz_text);
    }

    protected void initContentLayout(int contentRecourseId) {
        ViewStub viewStub = (ViewStub)context.findViewById(R.id.game_content);
        if(viewStub == null) {
            return;
        }
        viewStub.setLayoutResource(contentRecourseId);
        viewStub.inflate();
    }

    protected void initViewElements(Task task) {
        questionView = (TextView) context.findViewById(R.id.question);
        descriptionView = (TextView) context.findViewById(R.id.description);

        okView = (ImageButton) context.findViewById(R.id.ok);
        View.OnClickListener okClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOkClick(v);
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
    }

    protected void initAnswerView(int id, View.OnClickListener listener) {
        View answerButton = context.findViewById(id);
        buttons.add(answerButton);
        answerButton.setOnClickListener(listener);
    }

    protected void showTask(Task task) {
        Log.d(Game.LOG_TAG, "next task");
        questionView.setText(task.getQuestion());
        descriptionView.setText(task.getDescription());
        answerButtons = new HashMap<View, Item>();
        okView.setEnabled(false);

        ArrayList<Integer> indexes = numberGenerator.generateUniqueIntegers(0, buttons.size()-1, buttons.size());
        for (View button : buttons) {
            Item answer = task.getAnswerByIndex(indexes.get(0));
            indexes.remove(0);
            button.setSelected(false);
            answerButtons.put(button, answer);

            showOptionAnswer(answer, button);
        }
    }

    protected void showOptionAnswer(Item answer, View button) {
        showOptionAnswer((TextItem) answer, (Button) button);
    }

    protected void showOptionAnswer(TextItem answer, Button button) {
        button.setText(answer.getValue());
    }

    public void onAnswerClick(View selectedButton) {
        Log.d(Game.LOG_TAG, "onAnswerClick");

        selectedButton.setSelected(!selectedButton.isSelected());

        okView.setEnabled(false);
        for(Map.Entry<View, Item> answerButton : answerButtons.entrySet()) {
            View button = answerButton.getKey();
            if(button.isSelected()) {
                okView.setEnabled(true);
                break;
            }
        }
    }



    public void onOkClick(View okButton) {
        for(Map.Entry<View, Item> answerButton : answerButtons.entrySet()) {
            View button = answerButton.getKey();
            Item answer = answerButton.getValue();

            if(!answer.isCorrect(button.isSelected())) {
                deselectAllButtons();

                failedAttempts ++;
                showFailedDialog();
                //prevent many insertions of the same task
                if(currentTask != null) {
                    tasks.add(currentTask);
                }
                currentTask = null;
                return;
            }
        }

        failedAttempts = 0;
        showSuccessDialog();
        if(currentTask != null) {
            score++;
            setupScoreView();
        }


        if(tasks.size() == 0) {
            okView.setEnabled(false);
            navigateNext();
            return;
        }
        currentTask = tasks.poll();
        showTask(currentTask);
    }

    protected void deselectAllButtons() {
        for (View button : buttons) {
            button.setSelected(false);
        }
    }

    private void showSuccessDialog(){
        if(score == 3 || score == 8) {
            showResultDialog(R.layout.dialog_very_success);
            verySuccessSound.start();
            return;
        }
        showResultDialog(R.layout.dialog_success);
        successSound.start();
    }

    private void showFailedDialog(){
        // Inflate the Layout
        if(failedAttempts == 4) {
            failedAttempts = 0;
            showResultDialog(R.layout.dialog_very_failed);
            veryFailedSound.start();
            return;
        }
        showResultDialog(R.layout.dialog_failed);
        failedSound.start();
    }

    private void showResultDialog(int layoutId) {
        View layout = context.getLayoutInflater().inflate(layoutId, null);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

        /*FragmentManager fm = context.getSupportFragmentManager();
        ResultDialogFragment dialog = ResultDialogFragment.newInstance(status);
        dialog.show(fm, "result_dialog");*/
    }

    protected void navigateNext() {
        int nextLevel = getLevel() + 1;
        Intent goToNextActivity = new Intent(context.getApplicationContext(), Game.getGame().getClass(nextLevel));
        goToNextActivity.putExtra(Game.STATE_LEVEL, nextLevel);
        context.startActivity(goToNextActivity);
        context.finish();
    }
}
