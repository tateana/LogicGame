package com.home.tateana.logicgame.story;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.home.tateana.logicgame.gui.ViewLocationCalculator;

/**
 * Created by tateana on 04-Sep-15.
 */
public class ChangePropertyAnimation extends StoryAnimation {

    private int  animViewId;
    private int  targetViewId;
    private int  newDrawableResId;

    private ViewLocationCalculator locationCalc;

    public ChangePropertyAnimation(int animViewId, int locationViewId, int newDrawableResId,  ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider, StoryAnimationEndListener listener) {
        super(provider, listener);
        this.animViewId = animViewId;
        this.targetViewId = targetViewId;
        this.locationCalc = locationCalc;
    }


    public ChangePropertyAnimation(int animViewId, int targetViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider, StoryAnimationEndListener listener) {
        super(provider, listener);
        this.animViewId = animViewId;
        this.targetViewId = targetViewId;
        this.locationCalc = locationCalc;
    }

    @Override
    public void start() {
        super.start();
        final Interpolator interpolator = new LinearInterpolator();
        ImageView animView = provider.getCharacters().get(animViewId);
        View targetView = provider.getCharacters().get(targetViewId);
        int toX = locationCalc.getXChangeBetween(animView, targetView);
        int toY = locationCalc.getYChangeBetween(animView, targetView);

        TranslateAnimation animation = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, toX,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, toY);

        animation.setAnimationListener(this);
        animation.setZAdjustment(Animation.ZORDER_TOP);
        animation.setDuration(10);
        animation.setInterpolator(interpolator);
        animView.startAnimation(animation);
    }

    @Override
    public void end() {
        super.end();
        ImageView animView = provider.getCharacters().get(animViewId);
        View targetView = provider.getCharacters().get(targetViewId);
        int x = locationCalc.getViewX(targetView, animView);
        int y = locationCalc.getViewY(targetView);
        animView.layout(x, y, x + animView.getWidth(), y + animView.getHeight());

        Drawable drawable =  animView.getDrawable();
        if(drawable instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = ((AnimationDrawable) drawable);
            animationDrawable.stop();
            animView.setImageDrawable(animationDrawable.getFrame(0));
        }
    }

}
