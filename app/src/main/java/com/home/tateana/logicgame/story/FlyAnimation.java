package com.home.tateana.logicgame.story;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;

/**
 * Created by tateana on 25-Jul-15.
 */
public class FlyAnimation extends RelocationAnimation {

    public FlyAnimation(int animViewId, int targetViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider) {
        super(animViewId, targetViewId, locationCalc, provider);
        setupDrawableBuilder();
    }

    public FlyAnimation(int animViewId, int targetViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider, StoryAnimationEndListener listener) {
        super(animViewId, targetViewId, locationCalc, provider, listener);
        setupDrawableBuilder();
    }

    protected void setupDrawableBuilder() {
        DrawableAnimationBuilder dragonFlyBuilder = new DrawableAnimationBuilder();
        dragonFlyBuilder.addFrame(R.drawable.ig_dragon_1, 200);
        dragonFlyBuilder.addFrame(R.drawable.ig_dragon_2, 200);
        dragonFlyBuilder.addFrame(R.drawable.ig_dragon_3, 200);
        dragonFlyBuilder.addFrame(R.drawable.ig_dragon_2, 200);

        this.setAnimationBuilder(dragonFlyBuilder);
    }
}
