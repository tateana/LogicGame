package com.home.tateana.logicgame.story;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tateana on 03-Sep-15.
 */
public class DrawableAnimationBuilder {
    private ArrayList<Integer> resources = new ArrayList();
    private ArrayList<Integer> durations = new ArrayList();

    private Drawable firstDrawable;

    public DrawableAnimationBuilder() {
    }

    public DrawableAnimationBuilder(Drawable firstFrame, int firstResId) {
        this.firstDrawable = firstFrame;
        addFrame(firstResId, 1);
    }

    public void addFrame(int resId, int duration) {
        resources.add(resId);
        durations.add(duration);
    }

    public AnimationDrawable build(Context context) {
        AnimationDrawable animation = new AnimationDrawable();
        HashMap<Integer, Drawable> drawables = new HashMap<Integer, Drawable>();

        if(firstDrawable != null) {
            drawables.put(resources.get(0), firstDrawable);
            firstDrawable = null;
            //set the same last frame
            addFrame(resources.get(0), 1);
        }

        for(int i = 0; i < resources.size(); i++) {
            Integer resId = resources.get(i);
            Integer duration = durations.get(i);
            Drawable frame;
            if(drawables.containsKey(resId)) {
                frame = drawables.get(resId);
            } else {
                frame = context.getResources().getDrawable(resId);
                drawables.put(resId, frame);
            }
            animation.addFrame(frame,duration);
        }

        return animation;
    }

    public void setFirstDrawable(Drawable firstDrawable) {
        this.firstDrawable = firstDrawable;
    }
}
