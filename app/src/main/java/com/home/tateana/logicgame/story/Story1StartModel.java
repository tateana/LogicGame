package com.home.tateana.logicgame.story;

import android.app.Activity;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.SoundPlayer;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;

import java.util.ArrayList;

/**
 * Created by tateana on 17-Jul-15.
 */
public class Story1StartModel extends StoryModel {

    public Story1StartModel(int level, SoundPlayer soundPlayer, ViewLocationCalculator locationCalc) {
        super(level, soundPlayer, locationCalc);
        textReadingId = R.raw.text01;
        textId = R.string.text1;

        storyAnimationList = new ArrayList();
        storyAnimationList.add(new FadeInAnimation(R.id.prince, this, this));
        storyAnimationList.add(new FadeInAnimation(R.id.princess, this));

    }

    public void customInit(Activity context) {
        characters.put(R.id.prince, initPrince(context));
        characters.put(R.id.princess, initPrincess(context));
    }
}
