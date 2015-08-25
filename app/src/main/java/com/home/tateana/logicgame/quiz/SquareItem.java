package com.home.tateana.logicgame.quiz;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;

import com.home.tateana.logicgame.R;

/**
 * Created by tateana on 08-Jul-15.
 */
public class SquareItem extends ShapeItem {
    public SquareItem(int color, int size, boolean correct) {
        super(color, size, correct);
    }

    public SquareItem(int color, boolean correct) {
        super(color, correct);
    }

    public SquareItem(int color) {
        super(color);
    }

    public int getName() {
        return R.string.material_shape_square;
    }

    @Override
    protected ShapeDrawable createShape() {
        ShapeDrawable drawable = new ShapeDrawable(new RectShape());
        return drawable;
    }
}
