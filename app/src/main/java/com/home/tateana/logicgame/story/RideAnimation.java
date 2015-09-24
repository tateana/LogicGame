package com.home.tateana.logicgame.story;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;

/**
 * Created by tateana on 25-Jul-15.
 */
public class RideAnimation extends RelocationAnimation {

    public RideAnimation(int animViewId, int targetViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider) {
        super(animViewId, targetViewId, locationCalc, provider);
        setupDrawableBuilder();
    }

    public RideAnimation(int animViewId, int targetViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider, StoryAnimationEndListener listener) {
        super(animViewId, targetViewId, locationCalc, provider, listener);
        setupDrawableBuilder();
    }

    protected void setupDrawableBuilder() {
        DrawableAnimationBuilder knightRideBuilder = new DrawableAnimationBuilder();
        knightRideBuilder.addFrame(R.drawable.ig_knight_right_1, 200);
        knightRideBuilder.addFrame(R.drawable.ig_knight_right_2, 200);

        this.setAnimationBuilder(knightRideBuilder);
    }
}
