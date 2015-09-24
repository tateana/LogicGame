package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class PrinceListener extends Listener {

    public PrinceListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.vot_tak, context);
    }

    protected void playSound() {
        playSound(R.raw.vot_tak);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_prince_1);
        animBuilder.addFrame(R.drawable.ig_prince_2, 200);
        animBuilder.addFrame(R.drawable.ig_prince_3, 1000);
        animBuilder.addFrame(R.drawable.ig_prince_2, 200);
        return animBuilder;
    }
}
