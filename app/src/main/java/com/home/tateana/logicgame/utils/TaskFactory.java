package com.home.tateana.logicgame.utils;

import com.home.tateana.logicgame.quiz.Task;

import java.util.LinkedList;

/**
 * Created by tateana on 18-Jul-15.
 */
public interface TaskFactory {
    public LinkedList<Task> createTasks();
}
