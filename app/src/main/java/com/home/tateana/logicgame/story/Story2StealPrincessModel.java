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
public class Story2StealPrincessModel extends StoryModel {

    public Story2StealPrincessModel(int level, SoundPlayer soundPlayer, ViewLocationCalculator locationCalc) {
        super(level, soundPlayer, locationCalc);
        textReadingId = R.raw.text01;
        textId = R.string.text2;

        storyAnimationList = new ArrayList();
        storyAnimationList.add(new FadeInAnimation(R.id.wizard, this, this));
        storyAnimationList.add(new FadeInAnimation(animDragonLeftViewId, this, this));
        storyAnimationList.add(new FadeOutAnimation(R.id.prince, this, this));

        storyAnimationList.add(new FlyAnimation(animDragonLeftViewId, R.id.princess, locationCalc, this, this));
        storyAnimationList.add(new FadeOutAnimation(R.id.princess, this, this));
        storyAnimationList.add(new FlyAnimation(animDragonLeftViewId, R.id.dragon, locationCalc, this, this));
        storyAnimationList.add(new FadeInAnimation(R.id.prince, this, this));
    }

    @Override
    protected void customInit(Activity context) {
        characters.put(R.id.princess, initPrincess(context));
        characters.get(R.id.princess).setVisibility(View.VISIBLE);

        characters.put(R.id.wizard, initWizard(context));

        characters.put(R.id.prince, initPrince(context));
        characters.get(R.id.prince).setVisibility(View.VISIBLE);

        characters.put(R.id.dragon, initDragon(context));
        characters.put(animDragonLeftViewId, initAnimDragon(context));
    }
}
