package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class TownListener extends Listener {

    public TownListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.cows, context);
    }

    protected void playSound() {
        playSound(R.raw.cows);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_mill_wings_1);
        animBuilder.addFrame(R.drawable.ig_mill_wings_2, 500);
        animBuilder.addFrame(R.drawable.ig_mill_wings_1, 500);
        animBuilder.addFrame(R.drawable.ig_mill_wings_2, 500);
        animBuilder.addFrame(R.drawable.ig_mill_wings_1, 500);
        animBuilder.addFrame(R.drawable.ig_mill_wings_2, 500);
        return animBuilder;
    }
}
