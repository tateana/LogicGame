package com.home.tateana.logicgame.gui;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.home.tateana.logicgame.R;

/**
 * Created by tateana on 14-Aug-15.
 */
public class LevelAdapter extends BaseAdapter {

    private int mTotal;
    private int mAvailable;
    private int mPassed;
    private SparseIntArray imageResArray;
    private Context mContext;

    public LevelAdapter(int mTotal, int mAvailable, int mPassed, SparseIntArray imageResArray) {
        this.mTotal = mTotal;
        this.mAvailable = mAvailable;
        this.mPassed = mPassed;
        this.imageResArray = imageResArray;
        //this.mContext = mContext;
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
        ImageView imageView;
        int level = position + 1;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(parent.getContext());
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }

        if(level > mAvailable) {
            imageView.setImageResource(R.mipmap.icon_lock);
            imageView.setBackgroundResource(R.drawable.box_grey);
        } else if(imageResArray.get(level) == 0) {
            imageView.setImageResource(R.mipmap.ic_question);
            imageView.setBackgroundResource(R.drawable.box_green);
        } else {
            imageView.setImageResource(imageResArray.get(level));
            imageView.setBackgroundResource(R.drawable.box_blue);
        }

        return imageView;
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
