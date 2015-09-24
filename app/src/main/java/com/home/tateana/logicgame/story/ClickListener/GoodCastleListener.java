package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class GoodCastleListener extends Listener {

    public GoodCastleListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.fanfare, context);
    }

    protected void playSound() {
        playSound(R.raw.fanfare);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_good_castle);
        animBuilder.addFrame(R.drawable.ig_small_firework, 500);
        animBuilder.addFrame(R.drawable.ig_big_firework, 500);
        animBuilder.addFrame(R.drawable.ig_small_firework, 500);
        animBuilder.addFrame(R.drawable.ig_big_firework, 500);
        animBuilder.addFrame(R.drawable.ig_good_castle, 500);
        animBuilder.addFrame(R.drawable.ig_small_firework, 500);
        animBuilder.addFrame(R.drawable.ig_big_firework, 500);
        animBuilder.addFrame(R.drawable.ig_good_castle, 500);
        animBuilder.addFrame(R.drawable.ig_small_firework, 500);
        animBuilder.addFrame(R.drawable.ig_big_firework, 500);
        return animBuilder;
    }
}
