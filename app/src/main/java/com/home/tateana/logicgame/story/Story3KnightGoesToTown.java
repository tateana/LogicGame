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
public class Story3KnightGoesToTown extends StoryModel {

    public Story3KnightGoesToTown(int level, SoundPlayer soundPlayer, ViewLocationCalculator locationCalc) {
        super(level, soundPlayer, locationCalc);
        textReadingId = R.raw.text01;
        textId = R.string.text3;

        storyAnimationList = new ArrayList();
        storyAnimationList.add(new FadeOutAnimation(R.id.prince, this, this));
        storyAnimationList.add(new FadeInAnimation(animKnightRightViewId, this, this));
        storyAnimationList.add(new RideAnimation(animKnightRightViewId, R.id.town, locationCalc, this));
    }

    @Override
    protected void customInit(Activity context) {
        characters.put(R.id.prince, initPrince(context));
        characters.get(R.id.prince).setVisibility(View.VISIBLE);

        characters.put(R.id.wizard, initWizard(context));
        characters.get(R.id.wizard).setVisibility(View.VISIBLE);

        characters.put(R.id.knight, initKnight(context));
        characters.put(animKnightRightViewId, initAnimRightKnight(context, R.id.knight));
    }
}
