package com.home.tateana.logicgame.story;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.home.tateana.logicgame.R;

/**
 * Created by tateana on 25-Jul-15.
 */
public class FadeInAnimation extends StoryAnimation {

    private int  viewId;

    public FadeInAnimation(int viewId, StoryAnimationCharactersProvider provider) {
        super(provider);
        this.viewId = viewId;
    }

    public FadeInAnimation(int viewId, StoryAnimationCharactersProvider provider, StoryAnimationEndListener listener) {
        super(provider, listener);
        this.viewId = viewId;
    }

    @Override
    public void start() {
        super.start();
        View view = provider.getCharacters().get(viewId);
        Context context = view.getContext();
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        anim.setFillAfter(false);
        anim.setAnimationListener(this);
        view.startAnimation(anim);
    }

    @Override
    public void end() {
        super.end();
        ImageView view = provider.getCharacters().get(viewId);
        view.setVisibility(View.VISIBLE);
        Drawable drawable = view.getDrawable();
        if(drawable instanceof AnimationDrawable) {
            drawable.jumpToCurrentState();
        }
        view.clearAnimation();
    }
}
