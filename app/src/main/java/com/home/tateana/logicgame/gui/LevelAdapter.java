package com.home.tateana.logicgame.gui;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.tateana.logicgame.Game;
import com.home.tateana.logicgame.QuizActivity;
import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.StoryActivity;

/**
 * Created by tateana on 14-Aug-15.
 */
public class LevelAdapter extends BaseAdapter {

    private int mTotal;
    private int mAvailable;
    private int mPassed;

    private Drawable lockDrawable;
    private Drawable storyDrawable;
    private Drawable quizDrawable;
    private GridView.LayoutParams layoutParams;

    public LevelAdapter(int mTotal, int mAvailable, int mPassed) {
        this.mTotal = mTotal;
        this.mAvailable = mAvailable;
        this.mPassed = mPassed;

        this.layoutParams = new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return mTotal == mAvailable;
    }

    @Override
    public boolean isEnabled(int position) {
        return position < mAvailable;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mTotal;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position+1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        int level = position + 1;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(parent.getContext());
            textView.setLayoutParams(layoutParams);
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            textView.setPadding(2, 2, 2, 0);
            textView.setGravity(Gravity.RIGHT | Gravity.BOTTOM);
            textView.setSingleLine(true);
            textView.setTextAppearance(parent.getContext(), R.style.LevelNumber);
        } else {
            textView = (TextView) convertView;
        }

        Drawable bg;
        if(level > mAvailable) {
            bg = getLockDrawable(parent.getContext());
        } else if(Game.getGame().getClass(level) == QuizActivity.class) {
            //textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_question, 0, 0, 0);
            bg = getQuizDrawable(parent.getContext());
        } else {
            bg = getStoryDrawable(parent.getContext());
            //textView.setCompoundDrawablesWithIntrinsicBounds(imageResArray.get(level),0, 0, 0);
        }

        textView.setBackgroundDrawable(bg);
        textView.setText(String.valueOf(level));
        return textView;
    }

    public Drawable getLockDrawable(Context context) {
        if(lockDrawable == null) {
            lockDrawable = context.getResources().getDrawable(R.drawable.level_lock);
        }
        return lockDrawable;
    }

    public Drawable getStoryDrawable(Context context) {
        if(storyDrawable == null) {
            storyDrawable = context.getResources().getDrawable(R.drawable.level_story);
        }
        return storyDrawable;
    }

    public Drawable getQuizDrawable(Context context) {
        if(quizDrawable == null) {
            quizDrawable = context.getResources().getDrawable(R.drawable.level_quiz);
        }
        return quizDrawable;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
