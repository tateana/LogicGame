package com.home.tateana.logicgame.quiz;

/**
 * Created by tateana on 22-Jun-15.
 */
public class ImageItem extends Itemd {

    public ImageItem (Integer value) {
        super(value);
    }

    public ImageItem (Integer value, boolean correct) {
        super(value, correct);
    }

    public Integer getValue(){
        return (Integer) super.getValue();
    }
}
