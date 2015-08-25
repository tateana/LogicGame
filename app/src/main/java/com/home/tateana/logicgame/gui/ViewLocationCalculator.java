package com.home.tateana.logicgame.gui;

import android.view.View;
import android.widget.ImageView;

import com.home.tateana.logicgame.R;

/**
 * Created by tateana on 25-Jul-15.
 */
public class ViewLocationCalculator {

    public int getViewX(View view) {
        final int[] location = new int[2];
        view.getLocationOnScreen(location);
        int leftMargin = (int) view.getContext().getResources().getDimension(R.dimen.activity_horizontal_margin);
        int result = location[0];// - leftMargin;
        return result;
    }

    public int getViewY(View view) {
        final int[] location = new int[2];
        view.getLocationOnScreen(location);
        int topMargin = (int) view.getContext().getResources().getDimension(R.dimen.activity_vertical_margin);
        int h = view.getHeight();
        int result = location[1] - h/2;// - topMargin;
        return result;
    }

    public int getXChangeBetween(View startView, View endView) {
        int[] startLocation = new int[2];
        startView.getLocationOnScreen(startLocation);

        int[] endLocation = new int[2];
        endView.getLocationOnScreen(endLocation);

        int leftMargin = (int) startView.getContext().getResources().getDimension(R.dimen.activity_horizontal_margin);

        int result = endLocation[0] - startLocation[0];
        return result;
    }

    public int getYChangeBetween(View startView, View endView) {
        int[] startLocation = new int[2];
        startView.getLocationOnScreen(startLocation);

        int[] endLocation = new int[2];
        endView.getLocationOnScreen(endLocation);

        int result = endLocation[1] - startLocation[1];
        return result;
    }
}
