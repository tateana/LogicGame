package com.home.tateana.logicgame.utils;

import java.util.HashMap;

/**
 * Created by tateana on 18-Jul-15.
 */
public class DifficultyConfiguration {

    private DifficultyLevel difficultyLevel = DifficultyLevel.NORMAL;
    HashMap<DifficultyLevel, Configuration> configurations;

    public DifficultyConfiguration() {
        configurations = new HashMap<DifficultyLevel, Configuration>(3);
        configurations.put(DifficultyLevel.EASY, new Configuration(1, 9, 10));
        configurations.put(DifficultyLevel.NORMAL, new Configuration(2, 9, 10));
        configurations.put(DifficultyLevel.HARD, new Configuration(3, 9, 10));
    }

    public int numberOfTasks() {
        return configurations.get(difficultyLevel).numberOfTasks;
    }

    public int numberOfAnswers() {
        return configurations.get(difficultyLevel).numberOfAnswers;
    }

    public int maxKnownDigit() {
        return configurations.get(difficultyLevel).maxKnownDigit;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public static enum DifficultyLevel { EASY, NORMAL, HARD }

    private static class Configuration {
        int numberOfTasks;
        int numberOfAnswers;
        int maxKnownDigit;

        private Configuration(int numberOfTasks, int numberOfAnswers, int maxKnownDigit) {
            this.numberOfTasks = numberOfTasks;
            this.numberOfAnswers = numberOfAnswers;
            this.maxKnownDigit = maxKnownDigit;
        }
    }
}
