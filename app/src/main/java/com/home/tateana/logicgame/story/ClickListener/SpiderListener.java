package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class SpiderListener extends Listener {

    public SpiderListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.spider, context);
    }

    protected void playSound() {
        playSound(R.raw.spider);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_spider_seat);
        animBuilder.addFrame(R.drawable.ig_spider_stand, 500);
        animBuilder.addFrame(R.drawable.ig_spider_seat, 500);
        animBuilder.addFrame(R.drawable.ig_spider_stand, 500);
        animBuilder.addFrame(R.drawable.ig_spider_seat, 500);
        return animBuilder;
    }
}
