package com.home.tateana.logicgame.story;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.SoundPlayer;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;

import java.util.ArrayList;

/**
 * Created by tateana on 17-Jul-15.
 */
public class Story10EndFightWithDragon extends StoryModel {

    public Story10EndFightWithDragon(int level, SoundPlayer soundPlayer, ViewLocationCalculator locationCalc) {
        super(level, soundPlayer, locationCalc);
        textReadingId = R.raw.text01;
        textId = R.string.text10;
        locationCalc.setAlignRight(true);
        storyAnimationList = new ArrayList();
        storyAnimationList.add(new FadeOutAnimation(animDragonRightViewId, this, this));
        storyAnimationList.add(new FadeInAnimation(animDragonLeftViewId, this, this));

        storyAnimationList.add(new FadeInAnimation(animDragonLeftViewId, this, this));

        //storyAnimationList.add(new FadeOutAnimation(animKnightLeftViewId, this, this));
        //storyAnimationList.add(new FlyAnimation(animDragonLeftViewId, R.id.wizard, locationCalc, this, this));
        //storyAnimationList.add(new FadeOutAnimation(animDragonLeftViewId, this, this));
        //storyAnimationList.add(new FadeInAnimation(animKnightRightViewId, this, this));
    }

    @Override
    protected void customInit(Activity context) {
        characters.put(R.id.wizard, initWizard(context));
        characters.get(R.id.wizard).setVisibility(View.VISIBLE);

        characters.put(R.id.highMountains, initMountains(context));

        characters.put(animDragonRightViewId, initAnimRightDragon(context, R.id.highMountains));
        characters.get(animDragonRightViewId).setVisibility(View.VISIBLE);

        characters.put(animKnightLeftViewId, initAnimLeftKnight(context, R.id.highMountains));
        characters.get(animKnightLeftViewId).setVisibility(View.VISIBLE);

        characters.put(animDragonLeftViewId, initAnimDragon(context));
        //characters.put(animKnightRightViewId, initAnimRightKnight(context, R.id.badCastle));
        //characters.get(animKnightRightViewId).setScaleType(ImageView.ScaleType.FIT_END);



    }
}
