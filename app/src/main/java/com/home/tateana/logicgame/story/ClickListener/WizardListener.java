package com.home.tateana.logicgame.story.ClickListener;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.story.DrawableAnimationBuilder;
import com.home.tateana.logicgame.story.StoryModel;

/**
 * Created by tateana on 04-Sep-15.
 */
public class WizardListener extends Listener {

    public WizardListener(StoryModel model, Context context) {
        super(model);
        model.getSoundPlayer().loadSound(R.raw.devil_laugh, context);
    }

    protected void playSound() {
        playSound(R.raw.devil_laugh);
    }

    protected DrawableAnimationBuilder createAnimationBuilder(Drawable image) {
        DrawableAnimationBuilder animBuilder = new DrawableAnimationBuilder(image, R.drawable.ig_wizard_2);
        animBuilder.addFrame(R.drawable.ig_wizard, 3000);
        return animBuilder;
    }
}
