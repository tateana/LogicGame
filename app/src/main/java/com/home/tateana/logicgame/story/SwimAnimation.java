package com.home.tateana.logicgame.story;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;

/**
 * Created by tateana on 25-Jul-15.
 */
public class SwimAnimation extends RelocationAnimation {

    public SwimAnimation(int animViewId, int targetViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider) {
        super(animViewId, targetViewId, locationCalc, provider);
        setupDrawableBuilder();
    }

    public SwimAnimation(int animViewId, int targetViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider, StoryAnimationEndListener listener) {
        super(animViewId, targetViewId, locationCalc, provider, listener);
        setupDrawableBuilder();
    }

    protected void setupDrawableBuilder() {
        DrawableAnimationBuilder knightRideBuilder = new DrawableAnimationBuilder();
        knightRideBuilder.addFrame(R.drawable.ig_whale_tail_up_fontain, 400);
        knightRideBuilder.addFrame(R.drawable.ig_whale_tail_up, 200);
        knightRideBuilder.addFrame(R.drawable.ig_whale_tail_down, 200);

        this.setAnimationBuilder(knightRideBuilder);
    }
}
