package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class LeftDragonListener extends Listener {

    public LeftDragonListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.dragon_voice, context);
    }

    protected void playSound() {
        playSound(R.raw.dragon_voice);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_dragon_1);
        animBuilder.addFrame(R.drawable.ig_dragon_2, 200);
        animBuilder.addFrame(R.drawable.ig_dragon_3, 200);
        animBuilder.addFrame(R.drawable.ig_dragon_2, 200);
        animBuilder.addFrame(R.drawable.ig_dragon_1, 200);
        animBuilder.addFrame(R.drawable.ig_dragon_2, 200);
        animBuilder.addFrame(R.drawable.ig_dragon_3, 200);
        animBuilder.addFrame(R.drawable.ig_dragon_2, 200);
        return animBuilder;
    }
}
