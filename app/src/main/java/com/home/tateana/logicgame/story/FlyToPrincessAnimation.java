package com.home.tateana.logicgame.story;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;

/**
 * Created by tateana on 25-Jul-15.
 */
public class FlyToPrincessAnimation extends RelocationAnimation {

    public FlyToPrincessAnimation(int animDragonViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider) {
        super(animDragonViewId, R.id.princess, locationCalc, provider);
    }

    public FlyToPrincessAnimation(int animDragonViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider, StoryAnimationEndListener listener) {
        super(animDragonViewId, R.id.princess, locationCalc, provider, listener);
    }

}
