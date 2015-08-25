package com.home.tateana.logicgame.quiz;

import android.content.res.Resources;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.utils.DifficultyConfiguration;
import com.home.tateana.logicgame.utils.RandomNumberGenerator;
import com.home.tateana.logicgame.utils.TaskFactory;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by tateana on 15-Jul-15.
 */
public class WordCategoryTaskFactory implements TaskFactory {
    private RandomNumberGenerator numberGenerator;
    private Resources resources;
    private DifficultyConfiguration difficultyConfiguration;
    private WordCategoriesStorage storage;

    public WordCategoryTaskFactory(RandomNumberGenerator numberGenerator, DifficultyConfiguration difficultyConfiguration, Resources resources, WordCategoriesStorage storage) {
        this.numberGenerator = numberGenerator;
        this.resources = resources;
        this.storage = storage;
        this.difficultyConfiguration = difficultyConfiguration;
    }

    public LinkedList<Task> createTasks() {
        int numberOfTasks = difficultyConfiguration.numberOfTasks();
        int numberOfAnswers = difficultyConfiguration.numberOfAnswers();
        LinkedList<Task> tasks = new LinkedList<Task>();
        String questionDescription = resources.getString(R.string.task_question_find_words_from_category);
        ArrayList<Integer> categoryIndexes = numberGenerator.generateUniqueIntegers(0, storage.getNumberOfCategories()-1, numberOfTasks);
        int maxNumberOfAnswersFromOneCategoryInEachTask = 3;
        for (int i = 0; i < categoryIndexes.size();  i++) {
            Integer categoryIndex = categoryIndexes.get(i);
            ArrayList<Item> answers = new ArrayList<Item>();

            int leftNumberOfAnswers = numberOfAnswers;
            addAnswers(answers, categoryIndex, maxNumberOfAnswersFromOneCategoryInEachTask, true);
            int nextI = i;
            while(leftNumberOfAnswers > 0) {
                nextI = (nextI+1) % categoryIndexes.size();
                int incorrectCategoryIndex = categoryIndexes.get(nextI);

                if(leftNumberOfAnswers > maxNumberOfAnswersFromOneCategoryInEachTask) {
                    leftNumberOfAnswers = leftNumberOfAnswers - maxNumberOfAnswersFromOneCategoryInEachTask;
                    addAnswers(answers, incorrectCategoryIndex, maxNumberOfAnswersFromOneCategoryInEachTask, false);
                    continue;
                }

                addAnswers(answers, incorrectCategoryIndex, leftNumberOfAnswers, false);
                leftNumberOfAnswers = 0;
            }
            String question = resources.getString(storage.getCategoryByIndex(categoryIndex));
            Task task = new Task(questionDescription, question, answers);
            tasks.add(task);

        }

        return tasks;
    }

    protected void addAnswers(ArrayList<Item> answers, int categoryIndex, int numberOfAnswers, boolean isCorrect) {
        ArrayList<Integer> answerIndexes = numberGenerator.generateUniqueIntegers(0, storage.getCategorySizeByIndex(categoryIndex)-1, numberOfAnswers);
        for(Integer answerIndex : answerIndexes) {
            int answerId = storage.getWordByIndexAndCategoryIndex(categoryIndex, answerIndex);
            String answer = resources.getString(answerId);
            TextItem item = new TextItem(answer, isCorrect);
            answers.add(item);
        }
    }
}
