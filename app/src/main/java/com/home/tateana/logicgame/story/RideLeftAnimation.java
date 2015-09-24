package com.home.tateana.logicgame.story;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;

/**
 * Created by tateana on 25-Jul-15.
 */
public class RideLeftAnimation extends RideAnimation {

    public RideLeftAnimation(int animViewId, int targetViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider) {
        super(animViewId, targetViewId, locationCalc, provider);
    }

    public RideLeftAnimation(int animViewId, int targetViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider, StoryAnimationEndListener listener) {
        super(animViewId, targetViewId, locationCalc, provider, listener);
    }

    protected void setupDrawableBuilder() {
        DrawableAnimationBuilder knightRideBuilder = new DrawableAnimationBuilder();
        knightRideBuilder.addFrame(R.drawable.ig_knight_1, 200);
        knightRideBuilder.addFrame(R.drawable.ig_knight_2, 200);

        this.setAnimationBuilder(knightRideBuilder);
    }
}
