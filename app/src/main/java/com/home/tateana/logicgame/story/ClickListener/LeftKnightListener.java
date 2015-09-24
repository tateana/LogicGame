package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class LeftKnightListener extends Listener {

    public LeftKnightListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.horse, 2, context);
    }

    protected void playSound() {
        playSound(R.raw.horse);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_knight_1);
        animBuilder.addFrame(R.drawable.ig_knight_2, 300);
        animBuilder.addFrame(R.drawable.ig_knight_1, 300);
        animBuilder.addFrame(R.drawable.ig_knight_2, 300);
        animBuilder.addFrame(R.drawable.ig_knight_1, 300);
        animBuilder.addFrame(R.drawable.ig_knight_2, 300);
        animBuilder.addFrame(R.drawable.ig_knight_1, 300);
        animBuilder.addFrame(R.drawable.ig_knight_2, 300);
        animBuilder.addFrame(R.drawable.ig_knight_1, 300);
        animBuilder.addFrame(R.drawable.ig_knight_2, 300);
        animBuilder.addFrame(R.drawable.ig_knight_1, 300);
        animBuilder.addFrame(R.drawable.ig_knight_2, 300);
        return animBuilder;
    }
}
