package com.home.tateana.logicgame.quiz;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

import com.home.tateana.logicgame.R;

/**
 * Created by tateana on 08-Jul-15.
 */
public class OvalItem extends ShapeItem {
    public OvalItem(int color, int size, boolean correct) {
        super(color, size, correct);
    }

    public OvalItem(int color, boolean correct) {
        super(color, correct);
    }

    public OvalItem(int color) {
        super(color);
    }

    public int getName() {
        return R.string.material_shape_oval;
    }

    @Override
    protected ShapeDrawable createShape() {
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.setIntrinsicHeight(80);
        drawable.setIntrinsicWidth(100);
        return drawable;
    }
}
