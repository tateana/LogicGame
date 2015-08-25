package com.home.tateana.logicgame.quiz;

import android.content.res.Resources;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.utils.DifficultyConfiguration;
import com.home.tateana.logicgame.utils.RandomNumberGenerator;
import com.home.tateana.logicgame.utils.TaskFactory;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by tateana on 08-Jul-15.
 */
public class ExpressionTaskFactory implements TaskFactory {

    final public static char SIGN_PLUS = '+';
    final public static char SIGN_MINUS = '-';

    private RandomNumberGenerator numberGenerator;
    private Resources resources;
    private DifficultyConfiguration difficultyConfiguration;

    public ExpressionTaskFactory(RandomNumberGenerator numberGenerator, DifficultyConfiguration difficultyConfiguration, Resources resources) {
        this.numberGenerator = numberGenerator;
        this.resources = resources;
        this.difficultyConfiguration = difficultyConfiguration;
    }

    public LinkedList<Task> createTasks() {
        int maxNumeric = difficultyConfiguration.maxKnownDigit();
        int numberOfTasks = difficultyConfiguration.numberOfTasks();
        int numberOfAnswers = difficultyConfiguration.numberOfAnswers();
        char sign = SIGN_PLUS;
        String questionDescription = resources.getString(R.string.task_question_solve_expression);
        LinkedList<Task> tasks = new LinkedList<Task>();

        for (int i = 0; i < numberOfTasks; i++) {
            Integer firstTerm = numberGenerator.generateInteger(1, maxNumeric-1);
            Integer secondTerm = numberGenerator.generateInteger(1, (maxNumeric - firstTerm));
            StringBuilder question = new StringBuilder(5);
            question.append(firstTerm).append(" ").append(sign).append(" ").append(secondTerm);

            ArrayList<Item> answers = new ArrayList<Item>();
            String correctAnswer = String.valueOf(firstTerm + secondTerm);
            answers.add(new TextItem(correctAnswer, true));

            ArrayList<Integer> incorrectAnswers = numberGenerator.generateUniqueIntegers(0, maxNumeric, numberOfAnswers, firstTerm + secondTerm);
            for (int j = 0; j < numberOfAnswers; j++) {
                String incorrectAnswer = String.valueOf(incorrectAnswers.get(j));
                answers.add(new TextItem(incorrectAnswer, false));
            }

            Task task = new Task(questionDescription, question.toString(), answers);
            tasks.add(task);
        }

        return tasks;
    }
}
