package com.home.tateana.logicgame.story;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;

/**
 * Created by tateana on 25-Jul-15.
 */
public class RelocationAnimation extends StoryAnimation {

    private int  animViewId;
    private int  duration = 5000;
    private int  targetViewId;

    private DrawableAnimationBuilder animationBuilder;
    private ViewLocationCalculator locationCalc;

    public RelocationAnimation(int animViewId, int targetViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider) {
        super(provider);
        this.animViewId = animViewId;
        this.targetViewId = targetViewId;
        this.locationCalc = locationCalc;
    }

    public RelocationAnimation(int animViewId, int targetViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider, StoryAnimationEndListener listener) {
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
        animation.setDuration(duration);
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

    public void onAnimationStart(Animation animation) {
        super.onAnimationStart(animation);
        ImageView view = provider.getCharacters().get(animViewId);

        Drawable drawable =  view.getDrawable();
        AnimationDrawable animationDrawable = null;
        if(drawable instanceof AnimationDrawable) {
            animationDrawable = ((AnimationDrawable) drawable);
        } else if(animationBuilder != null) {
            animationDrawable = animationBuilder.build(view.getContext());
            view.setImageDrawable(animationDrawable);
        }

        if(animationDrawable != null) {
            animationDrawable.setOneShot(false);
            animationDrawable.stop();
            animationDrawable.start();
        }
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAnimationBuilder(DrawableAnimationBuilder animationBuilder) {
        this.animationBuilder = animationBuilder;
    }
}
