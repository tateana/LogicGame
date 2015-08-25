package com.home.tateana.logicgame.story;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.home.tateana.logicgame.Game;
import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.gui.SoundPlayer;
import com.home.tateana.logicgame.gui.ViewLocationCalculator;

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
    protected int animDragonViewId = 1;
    protected int animKnightViewId = 2;

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
        storyAnimationList.get(curPos).cancel();
        characters = new HashMap();
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

    protected ImageView initPrincess(Activity context) {
        if (characters.get(R.id.princess) != null){
            return characters.get(R.id.princess);
        }
        final ImageView view = (ImageView) context.findViewById(R.id.princess);
        soundPlayer.loadSound(R.raw.lala, 3, context);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run princess animation");
                AnimationDrawable animation = (AnimationDrawable) view.getDrawable();
                animation.stop();
                animation.start();
                soundPlayer.playSound(R.raw.lala);
            }
        };
        view.setOnClickListener(onClickListener);
        return view;
    }

    protected ImageView initWizard(Activity context) {
        if (characters.get(R.id.wizard) != null){
            return characters.get(R.id.wizard);
        }
        final ImageView view = (ImageView) context.findViewById(R.id.wizard);
        soundPlayer.loadSound(R.raw.devil_laugh, context);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run wizard animation");
                AnimationDrawable animation = (AnimationDrawable) view.getDrawable();
                animation.stop();
                animation.start();
                soundPlayer.playSound(R.raw.devil_laugh);
            }
        };
        view.setOnClickListener(clickListener);
        return view;
    }

    protected ImageView initFlame(Activity context) {
        if (characters.get(R.id.flame) != null){
            return characters.get(R.id.flame);
        }
        final ImageView view = (ImageView) context.findViewById(R.id.flame);
        soundPlayer.loadSound(R.raw.fire, 2, context);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run flame animation");
                AnimationDrawable animation = (AnimationDrawable) view.getDrawable();
                animation.stop();
                animation.start();
                soundPlayer.playSound(R.raw.fire);
            }
        };
        view.setOnClickListener(clickListener);
        return view;
    }

    protected ImageView initWhale(Activity context) {
        if (characters.get(R.id.whale) != null){
            return characters.get(R.id.whale);
        }
        final ImageView view = (ImageView) context.findViewById(R.id.whale);
        soundPlayer.loadSound(R.raw.whale, context);
        View.OnClickListener whaleClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationDrawable whaleAnimation = (AnimationDrawable) view.getDrawable();
                whaleAnimation.stop();
                whaleAnimation.start();
                soundPlayer.playSound(R.raw.whale);
            }
        };
        view.setOnClickListener(whaleClickListener);
        return view;
    }

    protected ImageView initDragon(Activity context) {
        if (characters.get(R.id.dragon) != null){
            return characters.get(R.id.dragon);
        }
        ImageView view = (ImageView) context.findViewById(R.id.dragon);
        return view;
    }

    protected ImageView initAnimDragon(Activity context) {
        if (characters.get(animDragonViewId) != null){
            return characters.get(animDragonViewId);
        }

        final ImageView dragonView = initDragon(context);
        dragonView.setVisibility(View.INVISIBLE);

        final RelativeLayout parentLayout = (RelativeLayout) context.findViewById(R.id.main);
        final ImageView animView = new ImageView(context);
        animView.setScaleType(dragonView.getScaleType());
        animView.setImageResource(R.drawable.anim_dragon);
        animView.setVisibility(View.INVISIBLE);

        dragonView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dragonView.getWidth(), dragonView.getHeight());
                parentLayout.addView(animView, params);
                animView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        int x = locationCalc.getViewX(dragonView);
                        int y = locationCalc.getViewY(dragonView);
                        Log.d(Game.LOG_TAG, "set up x " + String.valueOf(x) +" and y " + String.valueOf(y) + " with width " + String.valueOf(animView.getWidth()) + " and height " + String.valueOf(animView.getHeight()));
                        animView.layout(x, y, x+animView.getWidth(), y+animView.getHeight());
                        onViewIsReady(animDragonViewId);
                        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
                            animView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        else
                            animView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });

                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
                    dragonView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                else
                    dragonView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        soundPlayer.loadSound(R.raw.dragon_voice, context);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run dragon animation");
                AnimationDrawable dragonAnimation = (AnimationDrawable) animView.getDrawable();
                dragonAnimation.stop();
                dragonAnimation.start();
                soundPlayer.playSound(R.raw.dragon_voice);
            }
        };
        animView.setOnClickListener(onClickListener);

        return animView;
    }

    protected ImageView initSpider(Activity context) {
        if (characters.get(R.id.spider) != null){
            return characters.get(R.id.spider);
        }
        final ImageView view = (ImageView) context.findViewById(R.id.spider);
        view.setImageResource(R.drawable.anim_spider);
        soundPlayer.loadSound(R.raw.spider, context);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run spider animation");
                AnimationDrawable animation = (AnimationDrawable) view.getDrawable();
                animation.stop();
                animation.start();
                soundPlayer.playSound(R.raw.spider);
            }
        };
        view.setOnClickListener(onClickListener);
        return view;
    }

    protected ImageView initFairy(Activity context) {
        if (characters.get(R.id.fairy) != null){
            return characters.get(R.id.fairy);
        }
        final ImageView view = (ImageView) context.findViewById(R.id.fairy);
        soundPlayer.loadSound(R.raw.magic, context);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run fairy animation");
                AnimationDrawable animation = (AnimationDrawable) view.getDrawable();
                animation.stop();
                animation.start();
                soundPlayer.playSound(R.raw.magic);
            }
        };
        view.setOnClickListener(onClickListener);
        return view;
    }

    protected ImageView initGoodCastle(Activity context) {
        if (characters.get(R.id.goodCastle) != null){
            return characters.get(R.id.goodCastle);
        }
        final ImageView view = (ImageView) context.findViewById(R.id.salut);
        ((AnimationDrawable) view.getDrawable()).jumpToCurrentState();
        soundPlayer.loadSound(R.raw.fanfare, context);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run Good Castle animation");
                soundPlayer.playSound(R.raw.fanfare);
                AnimationDrawable animation = (AnimationDrawable) view.getDrawable();
                animation.stop();
                animation.start();
            }
        };
        view.setOnClickListener(clickListener);
        return view;
    }

    protected ImageView initBadCastle(Activity context) {
        if (characters.get(R.id.badCastle) != null){
            return characters.get(R.id.badCastle);
        }
        final ImageView view = (ImageView) context.findViewById(R.id.badCastle);
        ((AnimationDrawable) view.getDrawable()).jumpToCurrentState();
        soundPlayer.loadSound(R.raw.storm, context);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run Bad Castle animation");
                soundPlayer.playSound(R.raw.storm);
                AnimationDrawable animation = (AnimationDrawable) view.getDrawable();
                animation.stop();
                animation.start();
            }
        };
        view.setOnClickListener(clickListener);
        return view;
    }

    protected ImageView initTown(Activity context) {
        if (characters.get(R.id.town) != null){
            return characters.get(R.id.town);
        }

        final ImageView view = (ImageView) context.findViewById(R.id.town);
        ((AnimationDrawable) view.getDrawable()).jumpToCurrentState();
        soundPlayer.loadSound(R.raw.cows, context);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run town animation");
                AnimationDrawable animation = (AnimationDrawable) view.getDrawable();
                animation.stop();
                animation.start();
                soundPlayer.playSound(R.raw.cows);
            }
        };
        view.setOnClickListener(onClickListener);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                onViewIsReady(R.id.town);
                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                else
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        return view;
    }

    protected ImageView initOcean(Activity context) {
        if (characters.get(R.id.oceanRight) != null){
            return characters.get(R.id.oceanRight);
        }

        final ImageView view = (ImageView) context.findViewById(R.id.oceanRight);
        ((AnimationDrawable) view.getDrawable()).jumpToCurrentState();
        soundPlayer.loadSound(R.raw.sea, context);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run ocean animation");
                AnimationDrawable animation = (AnimationDrawable) view.getDrawable();
                animation.stop();
                animation.start();
                soundPlayer.playSound(R.raw.sea);
            }
        };
        view.setOnClickListener(onClickListener);
        return view;
    }

    protected ImageView initPrince(Activity context) {
        if (characters.get(R.id.prince) != null){
            return characters.get(R.id.prince);
        }
        final ImageView view = (ImageView) context.findViewById(R.id.prince);
        soundPlayer.loadSound(R.raw.vot_tak, context);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run prince animation");
                AnimationDrawable animation = (AnimationDrawable) view.getDrawable();
                animation.stop();
                animation.start();
                soundPlayer.playSound(R.raw.vot_tak);
            }
        };
        view.setOnClickListener(onClickListener);
        return view;
    }

    protected ImageView initKnight(Activity context) {
        if (characters.get(R.id.knight) != null){
            return characters.get(R.id.knight);
        }
        ImageView view = (ImageView) context.findViewById(R.id.knight);
        return view;
    }

    protected ImageView initAnimKnight(Activity context, int locationViewId) {
        if (characters.get(animKnightViewId) != null){
            return characters.get(animKnightViewId);
        }
        final ImageView knightView = initKnight(context);
        final ImageView locationView = characters.get(locationViewId);
        final RelativeLayout parentLayout = (RelativeLayout) context.findViewById(R.id.main);
        final ImageView animView = new ImageView(context);
        animView.setScaleType(knightView.getScaleType());
        animView.setImageResource(R.drawable.ig_knight);
        animView.setVisibility(View.INVISIBLE);

        knightView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(knightView.getWidth(), knightView.getHeight());
                parentLayout.addView(animView, params);

                animView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        int x = locationCalc.getViewX(locationView);
                        int y = locationCalc.getViewY(locationView);
                        Log.d(Game.LOG_TAG, "setup knight x " + String.valueOf(x) +" and y " + String.valueOf(y) + " with width " + String.valueOf(animView.getWidth()) + " and height " + String.valueOf(animView.getHeight()));
                        animView.layout(x, y, x+animView.getWidth(), y+animView.getHeight());
                        onViewIsReady(animKnightViewId);
                        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
                            animView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        else
                            animView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });

                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
                    knightView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                else
                    knightView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        soundPlayer.loadSound(R.raw.horse, 3, context);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Game.LOG_TAG, "run knight animation");
                /*AnimationDrawable drawableAnimation = (AnimationDrawable) animView.getDrawable();
                drawableAnimation.stop();
                drawableAnimation.start();*/
                soundPlayer.playSound(R.raw.horse);
            }
        };
        animView.setOnClickListener(onClickListener);

        return animView;
    }

    protected void hideView(int resId, Activity context) {
        View view = context.findViewById(resId);
        view.setVisibility(View.INVISIBLE);
    }

    protected void onViewIsReady(int viewResId){
        if(characters.containsKey(animKnightViewId) && viewResId!=animKnightViewId) {
            return;
        }

        if(characters.containsKey(animDragonViewId) && viewResId!=animDragonViewId) {
            return;
        }

        areViewsReady = true;
        if(isWaitingViews) {
            isWaitingViews = false;
            start();
        }
    }

    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }
}
