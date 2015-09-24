package com.home.tateana.logicgame.story;

import android.app.Activity;
import android.view.View;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.SoundPlayer;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;

import java.util.ArrayList;

/**
 * Created by tateana on 17-Jul-15.
 */
public class Story9StartFightWithDragon extends StoryModel {

    public Story9StartFightWithDragon(int level, SoundPlayer soundPlayer, ViewLocationCalculator locationCalc) {
        super(level, soundPlayer, locationCalc);
        textReadingId = R.raw.text01;
        textId = R.string.text9;
        locationCalc.setAlignRight(true);
        storyAnimationList = new ArrayList();
        storyAnimationList.add(new RideLeftAnimation(animKnightLeftViewId, R.id.highMountains, locationCalc, this, this));
        storyAnimationList.add(new FadeInAnimation(animDragonRightViewId, this, this));
        storyAnimationList.add(new FlyRightAnimation(animDragonRightViewId, R.id.highMountains, locationCalc, this, this));
    }

    @Override
    protected void customInit(Activity context) {
        characters.put(R.id.wizard, initWizard(context));
        characters.get(R.id.wizard).setVisibility(View.VISIBLE);

        characters.put(R.id.highMountains, initMountains(context));
        characters.put(R.id.forest, initForest(context));

        characters.put(animDragonRightViewId, initAnimRightDragon(context, R.id.wizard));

        characters.put(animKnightLeftViewId, initAnimLeftKnight(context, R.id.forest));
        characters.get(animKnightLeftViewId).setVisibility(View.VISIBLE);
    }
}
