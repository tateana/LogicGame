package com.home.tateana.logicgame.story.ClickListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class PrincessListener extends Listener {

    public PrincessListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.lala, 3, context);
    }

    protected void playSound() {
        playSound(R.raw.lala);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_princess_1);
        animBuilder.addFrame(R.drawable.ig_princess_2, 1000);
        animBuilder.addFrame(R.drawable.ig_princess_1, 1000);
        animBuilder.addFrame(R.drawable.ig_princess_2, 1000);
        return animBuilder;
    }
}
