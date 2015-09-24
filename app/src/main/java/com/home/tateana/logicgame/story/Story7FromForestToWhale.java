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
public class Story7FromForestToWhale extends StoryModel {

    public Story7FromForestToWhale(int level, SoundPlayer soundPlayer, ViewLocationCalculator locationCalc) {
        super(level, soundPlayer, locationCalc);
        textReadingId = R.raw.text01;
        textId = R.string.text7;

        storyAnimationList = new ArrayList();
        storyAnimationList.add(new FadeOutAnimation(R.id.flame, this, this));
        storyAnimationList.add(new RideAnimation(animKnightRightViewId, animWhaleViewId, locationCalc, this, this));
        storyAnimationList.add(new FadeInAnimation(animWhaleViewId, this, this));
    }

    @Override
    protected void customInit(Activity context) {
        characters.put(R.id.wizard, initWizard(context));
        characters.get(R.id.wizard).setVisibility(View.VISIBLE);

        characters.put(R.id.flame, initFlame(context));
        characters.get(R.id.flame).setVisibility(View.VISIBLE);

        characters.put(R.id.knight, initKnight(context));
        characters.put(animKnightRightViewId, initAnimRightKnight(context, R.id.flame));
        characters.get(animKnightRightViewId).setVisibility(View.VISIBLE);

        characters.put(animWhaleViewId, initAnimWhale(context));
    }
}
