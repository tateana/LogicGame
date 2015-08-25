package com.home.tateana.logicgame.story;

import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.home.tateana.logicgame.Game;

import java.util.Map;

/**
 * Created by tateana on 25-Jul-15.
 */
public  abstract class StoryAnimation implements Animation.AnimationListener {

    protected StoryAnimationEndListener listener;
    protected StoryAnimationCharactersProvider provider;
    protected boolean isCanceled = false;

    public StoryAnimation(StoryAnimationCharactersProvider provider) {
        super();
        this.provider = provider;
    }

    public StoryAnimation(StoryAnimationCharactersProvider provider, StoryAnimationEndListener listener) {
        this(provider);
        this.listener = listener;
    }

    public void start() {
        Log.d(Game.LOG_TAG, "start story animation " + this.getClass().toString());
    }

    public void end() {
        Log.d(Game.LOG_TAG, "end story animation " + this.getClass().toString());
    }

    public void cancel() {
        Log.d(Game.LOG_TAG, "cancel story animation " + this.getClass().toString());
        isCanceled = true;
    }

    public void enable() {
        Log.d(Game.LOG_TAG, "enable story animation " + this.getClass().toString());
        isCanceled = false;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        animation.setAnimationListener(null);

        if(!isCanceled) {
            end();
        }

        if(listener != null) {
            listener.onStoryAnimationEnd(this);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public interface StoryAnimationEndListener {
        public void onStoryAnimationEnd(StoryAnimation animation);
    }

    public interface StoryAnimationCharactersProvider {
        public Map<Integer, ImageView>  getCharacters();
    }
}
