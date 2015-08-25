package com.home.tateana.logicgame.quiz;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.home.tateana.logicgame.Game;
import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by tateana on 18-Jul-15.
 */
public class QuizImageModel extends QuizModel {

    public QuizImageModel(int level, RandomNumberGenerator numberGenerator) {
        super(level, numberGenerator);
    }

    public int getContentLayoutId() {
        return R.layout.quiz_images;
    }

    protected void showOptionAnswer(Item answer, View button) {
        showOptionAnswer((ShapeItem) answer, (ImageButton) button);
    }

    protected void showOptionAnswer(ShapeItem answer, ImageButton button) {
        button.setImageDrawable(answer.getValue());
    }
}
