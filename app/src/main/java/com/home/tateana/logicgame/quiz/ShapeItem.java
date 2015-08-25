package com.home.tateana.logicgame.quiz;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

import com.home.tateana.logicgame.R;

/**
 * Created by tateana on 08-Jul-15.
 */
 abstract class ShapeItem implements Item {

    final public static char SIZE_BIG = 1;
    final public static char SIZE_SMALL = 2;

    protected int color;
    protected int size = SIZE_BIG;
    protected ShapeDrawable shape;

    private boolean correct = false;


    public ShapeItem (int color, int size, boolean correct) {
        this(color, correct);
    }

    public ShapeItem (int color, boolean correct) {
        this(color);
        this.correct = correct;
    }

    public ShapeItem (int color) {
        this.color = color;
    }

    public boolean isCorrect(boolean isSelected) {
        return isSelected == correct;
    }

    public abstract int getName();

    public Drawable getValue(){
        if(shape == null) {
            shape = createShape();

            shape.getPaint().setStyle(Paint.Style.FILL);
            shape.getPaint().setColor(color);
            if(shape.getIntrinsicHeight() == 0) {
                shape.setIntrinsicHeight(100);
                shape.setIntrinsicWidth(100);
            }

            if(size == SIZE_BIG) {
                return shape;
            }

            Drawable[] arr = {shape};
            LayerDrawable layer = new LayerDrawable(arr);
            layer.setLayerInset(1, 0, 5, 0, 5);
            return layer;
        }

        return shape;
    }

    protected abstract ShapeDrawable createShape();
}
