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
public class CircleItem extends ShapeItem {
    public CircleItem(int color, int size, boolean correct) {
        super(color, size, correct);
    }

    public CircleItem(int color, boolean correct) {
        super(color, correct);
    }

    public CircleItem(int color) {
        super(color);
    }

    public int getName() {
        return R.string.material_shape_circle;
    }

    @Override
    protected ShapeDrawable createShape() {
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        return drawable;
    }
}
