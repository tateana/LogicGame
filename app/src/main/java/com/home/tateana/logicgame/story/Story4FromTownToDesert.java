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
public class Story4FromTownToDesert extends StoryModel {

    public Story4FromTownToDesert(int level, SoundPlayer soundPlayer, ViewLocationCalculator locationCalc) {
        super(level, soundPlayer, locationCalc);
        textReadingId = R.raw.text01;
        textId = R.string.text3;

        storyAnimationList = new ArrayList();
        storyAnimationList.add(new FadeInAnimation(animKnightViewId, this, this));
        storyAnimationList.add(new RelocationAnimation(animKnightViewId, R.id.spider, locationCalc, this, this));
        storyAnimationList.add(new FadeInAnimation(R.id.spider, this, this));
    }

    @Override
    protected void customInit(Activity context) {
        characters.put(R.id.wizard, initWizard(context));
        characters.get(R.id.wizard).setVisibility(View.VISIBLE);

        characters.put(R.id.knight, initKnight(context));
        characters.put(animKnightViewId, initAnimKnight(context, R.id.town));


        characters.put(R.id.spider, initSpider(context));
    }
}
