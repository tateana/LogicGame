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
public class Story6FromDesertToForest extends StoryModel {

    public Story6FromDesertToForest(int level, SoundPlayer soundPlayer, ViewLocationCalculator locationCalc) {
        super(level, soundPlayer, locationCalc);
        textReadingId = R.raw.text01;
        textId = R.string.text6;

        storyAnimationList = new ArrayList();
        storyAnimationList.add(new RideAnimation(animKnightRightViewId, R.id.flame, locationCalc, this, this));
        storyAnimationList.add(new FadeInAnimation(R.id.flame, this, this));
    }

    @Override
    protected void customInit(Activity context) {
        characters.put(R.id.wizard, initWizard(context));
        characters.get(R.id.wizard).setVisibility(View.VISIBLE);

        characters.put(R.id.flame, initFlame(context));

        characters.put(R.id.spider, initSpider(context));
        characters.get(R.id.spider).setVisibility(View.VISIBLE);

        characters.put(animKnightRightViewId, initAnimRightKnight(context, R.id.spider));
        characters.get(animKnightRightViewId).setVisibility(View.VISIBLE);
    }
}
