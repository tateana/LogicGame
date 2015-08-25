package com.home.tateana.logicgame.quiz;

/**
 * Created by tateana on 15-Jun-15.
 */
public abstract class Itemd {

    private Object value;
    private boolean correct = false;

    public Itemd(Object value) {
        this.value = value;
    }

    public Itemd(Object value, boolean correct) {
        this.value = value;
        this.correct = correct;
    }

    public Object getValue(){
        return this.value;
    }

    public boolean isCorrect(boolean isSelected) {
        return isSelected == correct;
    }
}