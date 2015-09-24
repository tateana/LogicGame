package com.home.tateana.logicgame.story.ClickListener;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.home.tateana.logicgame.Game;
import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public abstract class Listener implements View.OnClickListener {

    protected StoryModel model;

    public Listener(StoryModel model) {
        this.model = model;
    }

    @Override
    public void onClick(View v) {
        Log.d(Game.LOG_TAG, "run animation " + this.getClass());
        ImageView view = (ImageView) v;
        Drawable image = view.getDrawable();
        if(image instanceof AnimationDrawable == false) {
            DrawableAnimationBuilder animBuilder = createAnimationBuilder(image);
            image = animBuilder.build(v.getContext());
        }
        runAnimation(view, (AnimationDrawable) image);
        playSound();
    }

    abstract protected void playSound();

    protected void playSound(int soundResId) {
        model.getSoundPlayer().playSound(soundResId);
    }

    abstract protected DrawableAnimationBuilder createAnimationBuilder(Drawable image);

    protected void runAnimation(ImageView view, AnimationDrawable animation) {
        if(view == model.getCurrentAnimatedView()) {
            animation.stop();
            animation.start();
            return;
        }
        if(model.getCurrentAnimatedView() != null) {
            AnimationDrawable currentAnimation = (AnimationDrawable)model.getCurrentAnimatedView().getDrawable();
            currentAnimation.stop();
            model.getCurrentAnimatedView().setImageDrawable(currentAnimation.getFrame(0));
        }
        model.setCurrentAnimatedView(view);
        view.setImageDrawable(animation);
        animation.start();
    }
}
