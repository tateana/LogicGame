package com.home.tateana.logicgame.story;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;

/**
 * Created by tateana on 25-Jul-15.
 */
public class FlyBackFromPrincessAnimation extends RelocationAnimation {

    public FlyBackFromPrincessAnimation(int animDragonViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider) {
        super(animDragonViewId, R.id.dragon, locationCalc, provider);
    }

    public FlyBackFromPrincessAnimation(int animDragonViewId, ViewLocationCalculator locationCalc, StoryAnimationCharactersProvider provider, StoryAnimationEndListener listener) {
        super(animDragonViewId, R.id.dragon, locationCalc, provider, listener);
    }
}
