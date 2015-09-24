package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class FairyListener extends Listener {

    public FairyListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.magic, context);
    }

    protected void playSound() {
        playSound(R.raw.magic);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_fairy_1);
        animBuilder.addFrame(R.drawable.ig_fairy_2, 400);
        animBuilder.addFrame(R.drawable.ig_fairy_3, 400);
        animBuilder.addFrame(R.drawable.ig_fairy_1, 400);
        animBuilder.addFrame(R.drawable.ig_fairy_2, 400);
        animBuilder.addFrame(R.drawable.ig_fairy_3, 400);
        animBuilder.addFrame(R.drawable.ig_fairy_1, 400);
        animBuilder.addFrame(R.drawable.ig_fairy_2, 400);
        animBuilder.addFrame(R.drawable.ig_fairy_3, 400);
        return animBuilder;
    }
}
