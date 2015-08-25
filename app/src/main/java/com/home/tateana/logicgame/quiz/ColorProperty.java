package com.home.tateana.logicgame.quiz;

/**
 * Created by tateana on 08-Jul-15.
 */
public class ColorProperty {
    int nameResId;
    int codeResId;

    public ColorProperty(int nameResId, int codeResId) {
        this.nameResId = nameResId;
        this.codeResId = codeResId;
    }

    public int getNameResId() {
        return nameResId;
    }

    public int getCodeResId() {
        return codeResId;
    }
}
