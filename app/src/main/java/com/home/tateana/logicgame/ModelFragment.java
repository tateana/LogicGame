package com.home.tateana.logicgame;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.home.tateana.logicgame.story.StoryModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ModelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModelFragment extends Fragment {
    private Object model;

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public static ModelFragment newInstance(int level) {
        ModelFragment fragment = new ModelFragment();
        Bundle args = new Bundle();
        args.putInt(Game.STATE_LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


}
