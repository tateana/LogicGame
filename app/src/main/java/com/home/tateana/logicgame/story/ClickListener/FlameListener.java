package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class FlameListener extends Listener {

    public FlameListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.fire, context);
    }

    protected void playSound() {
        playSound(R.raw.fire);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_flame_middle);
        animBuilder.addFrame(R.drawable.ig_flame_small, 200);
        animBuilder.addFrame(R.drawable.ig_flame_middle, 200);
        animBuilder.addFrame(R.drawable.ig_flame, 500);
        animBuilder.addFrame(R.drawable.ig_flame_middle, 200);
        animBuilder.addFrame(R.drawable.ig_flame, 700);
        return animBuilder;
    }
}
