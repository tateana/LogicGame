package com.home.tateana.logicgame.quiz;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageButton;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.utils.RandomNumberGenerator;

import java.util.LinkedList;

/**
 * Created by tateana on 13-Jul-15.
 */
public class QuizImagesController extends QuizController {

    public QuizImagesController(FragmentActivity context, LinkedList<Task> tasks, int level, int subLevel, RandomNumberGenerator numberGenerator) {
        super(context, tasks, level, subLevel, numberGenerator);
    }

    protected void initContentLayout() {
        initContentLayout(R.layout.quiz_images);
    }
    protected void showOptionAnswer(Item answer, View button) {
        showOptionAnswer((ShapeItem) answer, (ImageButton) button);
    }

    protected void showOptionAnswer(ShapeItem answer, ImageButton button) {
        button.setImageDrawable(answer.getValue());
    }
}
