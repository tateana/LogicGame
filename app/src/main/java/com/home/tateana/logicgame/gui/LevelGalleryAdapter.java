package com.home.tateana.logicgame.gui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.home.tateana.logicgame.Game;
import com.home.tateana.logicgame.R;

/**
 * Created by tateana on 27-Jul-15.
 */
public class LevelGalleryAdapter extends RecyclerView.Adapter {
    private int size = Game.LEVEL_STORY_11;
    private int maxAvailableLevel = 20;
    private SparseIntArray imageResArray;
    private onItemClickListener itemClickListener;

    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public LevelGalleryAdapter(int size, SparseIntArray imageResArray) {
        this.size = size;
        this.imageResArray = imageResArray;
    }

    public void setMaxAvailableLevel(int maxAvailableLevel) {
        this.maxAvailableLevel = maxAvailableLevel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Log.v(Game.LOG_TAG, "createViewHolder " + i);
        ImageView imageView = new ImageView(parent.getContext());
        ViewHolder holder = new ViewHolder(imageView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Log.v(Game.LOG_TAG, "bindViewHolder " + position + " " + viewHolder);
        ViewHolder holder = (ViewHolder) viewHolder;
        ImageView imageView = (ImageView) holder.itemView;
        int level = position + 1;

        if(level > maxAvailableLevel) {
            imageView.setImageResource(R.mipmap.icon_lock);
            imageView.setBackgroundResource(R.drawable.box_grey);
        } else if (imageResArray.get(level) == 0) {
            imageView.setImageResource(R.mipmap.ic_question);
            imageView.setBackgroundResource(R.drawable.box_green);
        } else {
            imageView.setImageResource(imageResArray.get(level));
            imageView.setBackgroundResource(R.drawable.box_green);
        }

        //(ImageView) holder.itemView).setText("Item "+position);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //params.setMargins(20, 20, 20, 20);
        holder.itemView.setLayoutParams(params);

        if(level <= maxAvailableLevel) {
            holder.itemView.setOnClickListener(holder);
        }

        ((ImageView) holder.itemView).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(View v) {
            super(v);

            //v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.v(Game.LOG_TAG, " Select item with position " + String.valueOf(getPosition()));
            if(itemClickListener != null) {
                itemClickListener.onItemSelected(v, getPosition() + 1);
            }
        }
    }

    public interface onItemClickListener {
        public void onItemSelected(View v, int level);
    }
}
