package com.home.tateana.logicgame.story;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.home.tateana.logicgame.Game;
import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.SoundPlayer;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;
import com.home.tateana.logicgame.story.ClickListener.BadCastleListener;
import com.home.tateana.logicgame.story.ClickListener.FairyListener;
import com.home.tateana.logicgame.story.ClickListener.FlameListener;
import com.home.tateana.logicgame.story.ClickListener.GoodCastleListener;
import com.home.tateana.logicgame.story.ClickListener.LeftDragonListener;
import com.home.tateana.logicgame.story.ClickListener.LeftKnightListener;
import com.home.tateana.logicgame.story.ClickListener.OceanListener;
import com.home.tateana.logicgame.story.ClickListener.PrinceListener;
import com.home.tateana.logicgame.story.ClickListener.PrincessListener;
import com.home.tateana.logicgame.story.ClickListener.RightDragonListener;
import com.home.tateana.logicgame.story.ClickListener.RightKnightListener;
import com.home.tateana.logicgame.story.ClickListener.SpiderListener;
import com.home.tateana.logicgame.story.ClickListener.TownListener;
import com.home.tateana.logicgame.story.ClickListener.WhaleListener;
import com.home.tateana.logicgame.story.ClickListener.WizardListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tateana on 17-Jul-15.
 */
public abstract class StoryModel implements StoryAnimation.StoryAnimationEndListener, StoryAnimation.StoryAnimationCharactersProvider {

    final static int STATE_NEW = 1;
    final static int STATE_READY = 2;
    final static int STATE_RUNNING = 3;
    final static int STATE_PAUSED = 4;

    protected int state = STATE_NEW;
    protected boolean areViewsReady = false;
    protected boolean isWaitingViews = false;
    protected int curPos = -1;

    protected ViewLocationCalculator locationCalc;
    protected SoundPlayer soundPlayer;
    protected ArrayList<StoryAnimation> storyAnimationList;
    protected Map<Integer, ImageView> characters = new HashMap();

    private int level;
    protected int textReadingId;
    protected int textId;
    protected int animDragonLeftViewId = 1;
    protected int animKnightRightViewId = 2;
    protected int animWhaleViewId = 3;
    protected int animKnightLeftViewId = 4;
    protected int animDragonRightViewId = 5;

    protected AnimationDrawable currentAnimation;
    protected Drawable currentStaticDrawable;

    public ImageView getCurrentAnimatedView() {
        return currentAnimatedView;
    }

    public void setCurrentAnimatedView(ImageView currentAnimatedView) {
        this.currentAnimatedView = currentAnimatedView;
    }

    protected ImageView currentAnimatedView;

    public StoryModel(int level, SoundPlayer soundPlayer,  ViewLocationCalculator locationCalc) {
        this.level = level;
        this.soundPlayer = soundPlayer;
        this.locationCalc = locationCalc;
    }

    public int getLevel() {
        return level;
    }

    public int getTextId() {
        return textId;
    }

    public Map<Integer, ImageView> getCharacters() {
        return characters;
    }

    public void init(Activity context) {
        if(state != STATE_NEW) {
            return;
        }
        Log.d(Game.LOG_TAG, "init "+this.getClass().toString());

        characters.put(R.id.oceanRight, initOcean(context));
        characters.put(R.id.goodCastle, initGoodCastle(context));
        characters.put(R.id.badCastle, initBadCastle(context));
        characters.put(R.id.town, initTown(context));

        hideView(R.id.prince, context);
        hideView(R.id.princess, context);
        hideView(R.id.knight, context);
        hideView(R.id.spider, context);
        hideView(R.id.flame, context);
        hideView(R.id.whale, context);
        hideView(R.id.wizard, context);
        hideView(R.id.dragon, context);
        hideView(R.id.fairy, context);

        soundPlayer.loadText(textReadingId, context);
        customInit(context);
        state = STATE_READY;
        onViewIsReady(R.id.town);
    }

    protected abstract void customInit(Activity context);

    public void start() {
        if(state != STATE_READY) {
            resume();
        }

        if(!areViewsReady) {
            Log.d(Game.LOG_TAG, "wait for views ");
            isWaitingViews = true;
            return;
        }

        Log.d(Game.LOG_TAG, "start "+this.getClass().toString() + " from position " + String.valueOf(curPos));

        if(curPos > -1) {
            storyAnimationList.get(curPos).enable();
            for (int i=0; i <= curPos; i++) {
                storyAnimationList.get(i).end();
            }
        }
        soundPlayer.playText();
        state = STATE_RUNNING;
        startNextStoryAnimation();
    }

    public void resume() {
        if(state != STATE_PAUSED) {
            return;
        }
        soundPlayer.playText();
        state = STATE_RUNNING;
        startNextStoryAnimation();
    }

    public void pause () {
        if(state != STATE_RUNNING) {
            return;
        }
        soundPlayer.pause();
        state = STATE_PAUSED;
    }

    public void stop () {
        Log.d(Game.LOG_TAG, "stop "+this.getClass().toString());
        state = STATE_NEW;
        isWaitingViews = false;
        areViewsReady = false;
        storyAnimationList.get(curPos).cancel();
        characters = new HashMap();
        soundPlayer.pause();
    }

    public void destroy () {
        Log.d(Game.LOG_TAG, "destroy "+this.getClass().toString());
        state = STATE_NEW;
        areViewsReady = false;
        isWaitingViews = false;
        if(curPos > -1) {
            storyAnimationList.get(curPos).cancel();
        }
        characters = new HashMap();
        currentAnimation = null;
        currentAnimatedView = null;
        currentStaticDrawable = null;
        soundPlayer.release();
        curPos = -1;
    }

    protected void startNextStoryAnimation() {
        Log.d(Game.LOG_TAG, " next for cur pos "+String.valueOf(curPos));
        if(state != STATE_RUNNING) {
            Log.d(Game.LOG_TAG, " stopped as not running state "+String.valueOf(state));
            return;
        }

        if(storyAnimationList.size() <= curPos+1) {
            Log.d(Game.LOG_TAG, " stopped as out of animation tasks "+String.valueOf(storyAnimationList.size()));
            return;
        }
        curPos++;
        StoryAnimation story = storyAnimationList.get(curPos);
        story.start();
    }

    @Override
    public void onStoryAnimationEnd(StoryAnimation animation) {
        startNextStoryAnimation();
    }

    protected ImageView initMountains(Activity context) {
        if (characters.get(R.id.highMountains) != null){
            return characters.get(R.id.highMountains);
        }
        ImageView view = (ImageView) context.findViewById(R.id.highMountains);
        return view;
    }

    protected ImageView initForest(Activity context) {
        if (characters.get(R.id.forest) != null){
            return characters.get(R.id.forest);
        }
        ImageView view = (ImageView) context.findViewById(R.id.forest);
        return view;
    }

    protected ImageView initPrincess(Activity context) {
        if (characters.get(R.id.princess) != null){
            return characters.get(R.id.princess);
        }
        ImageView view = (ImageView) context.findViewById(R.id.princess);
        view.setImageResource(R.drawable.ig_princess_1);
        view.setOnClickListener(new PrincessListener(this, context));
        return view;
    }

    protected ImageView initWizard(Activity context) {
        if (characters.get(R.id.wizard) != null){
            return characters.get(R.id.wizard);
        }
        ImageView view = (ImageView) context.findViewById(R.id.wizard);
        view.setImageResource(R.drawable.ig_wizard_2);
        view.setOnClickListener(new WizardListener(this, context));
        return view;
    }

    protected ImageView initFlame(Activity context) {
        if (characters.get(R.id.flame) != null){
            return characters.get(R.id.flame);
        }
        ImageView view = (ImageView) context.findViewById(R.id.flame);
        view.setImageResource(R.drawable.ig_flame_middle);
        view.setOnClickListener(new FlameListener(this, context));
        return view;
    }

    protected ImageView initSpider(Activity context) {
        if (characters.get(R.id.spider) != null){
            return characters.get(R.id.spider);
        }
        ImageView view = (ImageView) context.findViewById(R.id.spider);
        view.setImageResource(R.drawable.ig_spider_seat);
        soundPlayer.loadSound(R.raw.spider, context);
        view.setOnClickListener(new SpiderListener(this, context));
        return view;
    }

    protected ImageView initFairy(Activity context) {
        if (characters.get(R.id.fairy) != null){
            return characters.get(R.id.fairy);
        }
        final ImageView view = (ImageView) context.findViewById(R.id.fairy);
        view.setImageResource(R.drawable.ig_fairy_1);
        soundPlayer.loadSound(R.raw.magic, context);
        view.setOnClickListener(new FairyListener(this, context));
        return view;
    }

    protected ImageView initGoodCastle(Activity context) {
        if (characters.get(R.id.goodCastle) != null){
            return characters.get(R.id.goodCastle);
        }
        ImageView view = (ImageView) context.findViewById(R.id.salut);
        ImageView backgroundView = (ImageView) context.findViewById(R.id.goodCastle);
        view.setImageDrawable(backgroundView.getDrawable());
        view.setOnClickListener(new GoodCastleListener(this, context));
        return view;
    }

    protected ImageView initBadCastle(Activity context) {
        if (characters.get(R.id.badCastle) != null){
            return characters.get(R.id.badCastle);
        }
        ImageView view = (ImageView) context.findViewById(R.id.badCastle);
        view.setOnClickListener(new BadCastleListener(this, context));
        return view;
    }

    protected ImageView initTown(Activity context) {
        if (characters.get(R.id.town) != null){
            return characters.get(R.id.town);
        }

        ImageView view = (ImageView) context.findViewById(R.id.town);
        view.setOnClickListener(new TownListener(this, context));
        return view;
    }

    protected ImageView initOcean(Activity context) {
        if (characters.get(R.id.oceanRight) != null){
            return characters.get(R.id.oceanRight);
        }

        ImageView view = (ImageView) context.findViewById(R.id.oceanRight);
        view.setOnClickListener(new OceanListener(this, context));
        return view;
    }

    protected ImageView initPrince(Activity context) {
        if (characters.get(R.id.prince) != null){
            return characters.get(R.id.prince);
        }
        ImageView view = (ImageView) context.findViewById(R.id.prince);
        view.setImageResource(R.drawable.ig_prince_1);
        view.setOnClickListener(new PrinceListener(this, context));
        return view;
    }

    protected ImageView initWhale(Activity context) {
        if (characters.get(R.id.whale) != null){
            return characters.get(R.id.whale);
        }
        ImageView view = (ImageView) context.findViewById(R.id.whale);
        return view;
    }

    protected ImageView initAnimWhale(Activity context) {
        if (characters.get(animWhaleViewId) != null){
            return characters.get(animWhaleViewId);
        }
        ImageView whaleView = initWhale(context);
        whaleView.setImageResource(R.drawable.ig_whale_tail_down);
        ImageView animView = initAnimView(context, whaleView, whaleView, animWhaleViewId);
        animView.setOnClickListener(new WhaleListener(this, context));
        return animView;
    }

    protected ImageView initDragon(Activity context) {
        if (characters.get(R.id.dragon) != null){
            return characters.get(R.id.dragon);
        }
        ImageView view = (ImageView) context.findViewById(R.id.dragon);
        return view;
    }

    protected ImageView initAnimRightDragon(Activity context, int locationViewId) {
        if (characters.get(animDragonRightViewId) != null){
            return characters.get(animDragonRightViewId);
        }

        ImageView dragonView = initDragon(context);
        dragonView.setImageResource(R.drawable.ig_dragon_right_1);
        dragonView.setVisibility(View.INVISIBLE);

        soundPlayer.loadSound(R.raw.dragon_voice, context);
        ImageView animDragonView = initAnimView(context, characters.get(locationViewId), dragonView, animDragonRightViewId);
        animDragonView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        animDragonView.setOnClickListener(new RightDragonListener(this, context));
        return animDragonView;
    }

    protected ImageView initAnimDragon(Activity context) {
        if (characters.get(animDragonLeftViewId) != null){
            return characters.get(animDragonLeftViewId);
        }

        ImageView dragonView = initDragon(context);
        dragonView.setImageResource(R.drawable.ig_dragon_1);
        dragonView.setVisibility(View.INVISIBLE);

        soundPlayer.loadSound(R.raw.dragon_voice, context);
        ImageView animDragonView = initAnimView(context, dragonView, dragonView, animDragonLeftViewId);
        animDragonView.setOnClickListener(new LeftDragonListener(this, context));
        return animDragonView;
    }

    protected ImageView initKnight(Activity context) {
        if (characters.get(R.id.knight) != null){
            return characters.get(R.id.knight);
        }
        ImageView view = (ImageView) context.findViewById(R.id.knight);
        return view;
    }

    protected ImageView initAnimLeftKnight(Activity context, int locationViewId) {
        if (characters.get(animKnightLeftViewId) != null){
            return characters.get(animKnightLeftViewId);
        }
        ImageView knightView = initKnight(context);
        ImageView locationView = characters.get(locationViewId);

        knightView.setImageResource(R.drawable.ig_knight_1);
        soundPlayer.loadSound(R.raw.horse, 2, context);
        ImageView animKnightView = initAnimView(context, locationView, knightView, animKnightLeftViewId);
        animKnightView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        animKnightView.setOnClickListener(new LeftKnightListener(this, context));
        return animKnightView;
    }

    protected ImageView initAnimRightKnight(Activity context, int locationViewId) {
        if (characters.get(animKnightRightViewId) != null){
            return characters.get(animKnightRightViewId);
        }
        ImageView knightView = initKnight(context);
        ImageView locationView = knightView;
        if(locationViewId != R.id.knight) {
            locationView = characters.get(locationViewId);
        }

        knightView.setImageResource(R.drawable.ig_knight_right_1);
        soundPlayer.loadSound(R.raw.horse, 2, context);
        ImageView animKnightView = initAnimView(context, locationView, knightView, animKnightRightViewId);
        animKnightView.setOnClickListener(new RightKnightListener(this, context));
        return animKnightView;
    }

    protected ImageView initAnimView(Activity context, final ImageView locationView, final ImageView sampleView, final int animViewId) {
        final RelativeLayout parentLayout = (RelativeLayout) context.findViewById(R.id.main);
        final ImageView animView = new ImageView(context);
        animView.setScaleType(sampleView.getScaleType());
        animView.setVisibility(View.INVISIBLE);

        sampleView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
                    sampleView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                else
                    sampleView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(sampleView.getWidth(), sampleView.getHeight());
                parentLayout.addView(animView, params);
                animView.setImageDrawable(sampleView.getDrawable());
                animView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
                            animView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        else
                            animView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                        int x = locationCalc.getViewX(locationView, animView);
                        int y = locationCalc.getViewY(locationView);

                        Log.d(Game.LOG_TAG, "setup moving view " + animViewId + " x " + String.valueOf(x) +" and y " + String.valueOf(y) + " with width " + String.valueOf(animView.getWidth()) + " and height " + String.valueOf(animView.getHeight()));
                        animView.layout(x, y, x + animView.getWidth(), y + animView.getHeight());
                        onViewIsReady(animViewId);
                    }
                });
            }
        });

        return animView;
    }

    protected void hideView(int resId, Activity context) {
        View view = context.findViewById(resId);
        view.setVisibility(View.INVISIBLE);
    }

    protected void onViewIsReady(int viewResId){

        if(viewResId== animKnightRightViewId || viewResId== animDragonLeftViewId || viewResId == animKnightLeftViewId ) {
            //just miss elseif block
        } else if(characters.containsKey(animKnightRightViewId) || characters.containsKey(animDragonLeftViewId) || characters.containsKey(animKnightLeftViewId)) {
            return;
        }

        areViewsReady = true;
        if(isWaitingViews) {
            isWaitingViews = false;
            start();
        }
    }

    protected void runAnimation(ImageView view, AnimationDrawable animation) {
        if(view == currentAnimatedView) {
            currentAnimation.stop();
            currentAnimation.start();
            return;
        }
        if(currentAnimatedView != null) {
            currentAnimation.stop();
            currentAnimatedView.setImageDrawable(currentStaticDrawable);
        }
        currentAnimatedView = view;
        currentStaticDrawable = view.getDrawable();

        view.setImageDrawable(animation);
        currentAnimation = (AnimationDrawable)view.getDrawable();

        currentAnimation.start();
    }

    protected void runAnimation(ImageView view, int animResId) {
        if(view == currentAnimatedView) {
            currentAnimation.stop();
            currentAnimation.start();
            return;
        }
        if(currentAnimatedView != null) {
            currentAnimation.stop();
            currentAnimatedView.setImageDrawable(currentStaticDrawable);
        }
        currentAnimatedView = view;
        currentStaticDrawable = view.getDrawable();

        view.setImageResource(animResId);
        currentAnimation = (AnimationDrawable)view.getDrawable();

        //set static drawable as last frame to stop animation on the same state
        if(currentStaticDrawable != null) {
            currentAnimation.addFrame(currentStaticDrawable, 1);
        }

        currentAnimation.start();
    }

    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }
}
