package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class OceanListener extends Listener {

    public OceanListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.sea, context);
    }

    protected void playSound() {
        playSound(R.raw.sea);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ocean);
        animBuilder.addFrame(R.drawable.ocean_2, 500);
        animBuilder.addFrame(R.drawable.ocean, 500);
        animBuilder.addFrame(R.drawable.ocean_2, 500);
        animBuilder.addFrame(R.drawable.ocean, 500);
        animBuilder.addFrame(R.drawable.ocean_2, 500);
        animBuilder.addFrame(R.drawable.ocean_2, 500);
        animBuilder.addFrame(R.drawable.ocean, 500);
        animBuilder.addFrame(R.drawable.ocean_2, 500);
        return animBuilder;
    }
}
