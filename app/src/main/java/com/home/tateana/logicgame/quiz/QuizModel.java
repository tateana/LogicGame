package com.home.tateana.logicgame.quiz;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.home.tateana.logicgame.Game;
import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by tateana on 18-Jul-15.
 */
public class QuizModel {

    private int level;
    private int score = 0;
    private int failedAttempts = 0;

    private Task currentTask;
    private HashMap<View, Item> buttonToAnswerItemMap;
    private LinkedList<Task> tasks;
    private int size;

    private RandomNumberGenerator numberGenerator;
    private ViewProvider viewProvider;

    public QuizModel(int level, RandomNumberGenerator numberGenerator) {
        this.level = level;
        this.numberGenerator = numberGenerator;
    }

    public void setTasks(LinkedList<Task> tasks) {
        this.tasks = tasks;
        this.size = tasks.size();
    }

    public int getContentLayoutId() {
        return R.layout.quiz_text;
    }


    public void setViewProvider(ViewProvider viewProvider) {
        this.viewProvider = viewProvider;
    }

    public void unAttachDependencies() {
        this.viewProvider = null;
    }

    public int getLevel() {
        return level;
    }

    public void setScore(int score) {
        if(score <= this.score) {
            return;
        }
        while (this.score < score) {
            this.score++;
            tasks.remove();
        }
    }

    public int getScore() {
        return score;
    }

    public int getSize() {
        return size;

    }

    public boolean showNextTask() {
        if(tasks.size() == 0) {
            return false;
        }
        currentTask = tasks.poll();
        showTask(currentTask);
        return true;
    }

    protected void showTask(Task task) {
        Log.d(Game.LOG_TAG, "next task");
        viewProvider.questionView().setText(task.getQuestion());
        viewProvider.descriptionView().setText(task.getDescription());
        buttonToAnswerItemMap = new HashMap<View, Item>();
        ArrayList<View> buttons = viewProvider.answerButtons();

        ArrayList<Integer> indexes = numberGenerator.generateUniqueIntegers(0, buttons.size()-1, buttons.size());
        for (View button : buttons) {
            Item answer = task.getAnswerByIndex(indexes.get(0));
            indexes.remove(0);
            button.setSelected(false);
            buttonToAnswerItemMap.put(button, answer);
            showOptionAnswer(answer, button);
        }
    }

    protected void showOptionAnswer(Item answer, View button) {
        showOptionAnswer((TextItem) answer, (Button) button);
    }

    protected void showOptionAnswer(TextItem answer, Button button) {
        button.setText(answer.getValue());
    }

    public Result checkAnswers() {
        for(Map.Entry<View, Item> answerButton : buttonToAnswerItemMap.entrySet()) {
            View button = answerButton.getKey();
            Item answer = answerButton.getValue();

            if(!answer.isCorrect(button.isSelected())) {
                failedAttempts ++;
                //prevent many insertions of the same task
                if(currentTask != null) {
                    tasks.add(currentTask);
                    currentTask = null;
                }

                if(failedAttempts == 4) {
                    failedAttempts = 0;
                    return Result.VERY_FAILED;
                }
                return Result.FAILED;
            }
        }

        if(currentTask != null) {
            score++;
            currentTask = null;
        }
        failedAttempts = 0;
        if(score == 3 || score == 8) {
            return Result.VERY_SUCCESS;
        } else {
            return Result.SUCCESS;
        }
    }

    public interface ViewProvider {
        public TextView questionView();
        public TextView descriptionView();
        public ArrayList<View> answerButtons();
    }

    public static enum Result { VERY_FAILED, FAILED, SUCCESS, VERY_SUCCESS }
}
