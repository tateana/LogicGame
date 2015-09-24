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
public class Story8RideOnWhale extends StoryModel {

    public Story8RideOnWhale(int level, SoundPlayer soundPlayer, ViewLocationCalculator locationCalc) {
        super(level, soundPlayer, locationCalc);
        textReadingId = R.raw.text01;
        textId = R.string.text8;
        locationCalc.setAlignRight(true);

        storyAnimationList = new ArrayList();
        storyAnimationList.add(new FadeOutAnimation(animKnightRightViewId, this, this));
        storyAnimationList.add(new SwimAnimation(animWhaleViewId, R.id.fairy, locationCalc, this, this));
        storyAnimationList.add(new FadeInAnimation(animKnightLeftViewId, this, this));
        storyAnimationList.add(new FadeOutAnimation(animWhaleViewId, this, this));
        storyAnimationList.add(new FadeInAnimation(R.id.fairy, this, this));
    }

    @Override
    protected void customInit(Activity context) {
        characters.put(R.id.wizard, initWizard(context));
        characters.get(R.id.wizard).setVisibility(View.VISIBLE);

        characters.put(R.id.fairy, initFairy(context));

        characters.put(animWhaleViewId, initAnimWhale(context));
        characters.get(animWhaleViewId).setVisibility(View.VISIBLE);

        characters.put(animKnightRightViewId, initAnimRightKnight(context, animWhaleViewId));
        characters.get(animKnightRightViewId).setVisibility(View.VISIBLE);

        characters.put(animKnightLeftViewId, initAnimLeftKnight(context, R.id.fairy));
    }
}
