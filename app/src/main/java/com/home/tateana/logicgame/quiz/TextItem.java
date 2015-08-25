package com.home.tateana.logicgame.quiz;

/**
 * Created by tateana on 15-Jun-15.
 */
public class TextItem implements Item {

    private String value;
    private boolean correct = false;

    public TextItem (String value) {
        this.value = value;
    }

    public TextItem (String value, boolean correct) {
        this.value = value;
        this.correct = correct;
    }

    public String getValue(){
        return this.value;
    }

    public boolean isCorrect(boolean isSelected) {
        return isSelected == correct;
    }
}
