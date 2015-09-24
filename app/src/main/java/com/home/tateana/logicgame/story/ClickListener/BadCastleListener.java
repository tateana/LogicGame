package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class BadCastleListener extends Listener {

    public BadCastleListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.storm, context);
    }

    protected void playSound() {
        playSound(R.raw.storm);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_bad_castle);
        animBuilder.addFrame(R.drawable.ig_bad_castle, 500);
        animBuilder.addFrame(R.drawable.ig_bad_castle_with_lights, 1500);
        return animBuilder;
    }
}
