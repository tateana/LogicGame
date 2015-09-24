package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class WhaleListener extends Listener {

    public WhaleListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.whale, context);
    }

    protected void playSound() {
        playSound(R.raw.whale);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_whale_tail_down);
        animBuilder.addFrame(R.drawable.ig_whale_tail_up, 500);
        animBuilder.addFrame(R.drawable.ig_whale_tail_down, 500);
        animBuilder.addFrame(R.drawable.ig_whale_tail_up, 500);
        return animBuilder;
    }
}
