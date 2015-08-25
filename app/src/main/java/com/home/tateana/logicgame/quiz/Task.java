package com.home.tateana.logicgame.quiz;

import java.util.ArrayList;

/**
 * Created by tateana on 15-Jun-15.
 */
public class Task {

    private ArrayList<Item> answers;
    private String question;
    private String description;

    public Task(String description, String question, ArrayList<Item> answers) {
        this.question = question;
        this.answers = answers;
        this.description = description;
    }

    public String getQuestion() {
       return this.question;
    }

    public String getDescription() {
        return this.description;
    }

    public Item getAnswerByIndex(int index) {
        return answers.get(index);
    }

}
