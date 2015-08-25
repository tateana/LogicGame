package com.home.tateana.logicgame.gui;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by tateana on 10-Jul-15.
 */
public class TriangleShape extends Shape {

    Path path = new Path();

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawPath(path, paint);
    }


    @Override
    protected void onResize(float width, float height) {
        super.onResize(width, height);
        path.reset();
        path.moveTo(0, height);
        path.lineTo(width / 2, 0);
        path.lineTo(width, height);
    }

 }
