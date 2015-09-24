package com.home.tateana.logicgame.gui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by tateana on 03-Sep-15.
 */
public class PicassoTarget implements Target {

    private TextView view;

    public PicassoTarget(TextView view) {
        this.view = view;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        BitmapDrawable drawable = new BitmapDrawable(view.getContext().getResources(), bitmap);
        view.setCompoundDrawables(drawable, null, null, null);
        view = null;
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        view = null;
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }

    private void release() {
        view = null;
    }
}
