package com.home.tateana.logicgame.quiz;

import android.content.res.Resources;

import com.home.tateana.logicgame.R;
import com.home.tateana.logicgame.utils.DifficultyConfiguration;
import com.home.tateana.logicgame.utils.RandomNumberGenerator;
import com.home.tateana.logicgame.utils.TaskFactory;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by tateana on 08-Jul-15.
 */
public class ShapeColorTaskFactory implements TaskFactory {
    final public static int SHAPE_CIRCLE = 1;
    final public static int SHAPE_SQUARE = 2;
    final public static int SHAPE_OVAL = 3;
    final public static int SHAPE_TRIANGLE = 4;

    private  ArrayList<ColorProperty> colors = new ArrayList<ColorProperty>();

    private RandomNumberGenerator numberGenerator;
    private Resources resources;
    private DifficultyConfiguration difficultyConfiguration;

    public ShapeColorTaskFactory(RandomNumberGenerator numberGenerator, DifficultyConfiguration difficultyConfiguration, Resources resources) {
        this.numberGenerator = numberGenerator;
        this.resources = resources;
        this.difficultyConfiguration = difficultyConfiguration;

        colors.add(new ColorProperty(R.string.material_red, R.color.material_red));
        colors.add(new ColorProperty(R.string.material_purple, R.color.material_purple));
        colors.add(new ColorProperty(R.string.material_blue, R.color.material_blue));
        colors.add(new ColorProperty(R.string.material_light_blue, R.color.material_light_blue));
        colors.add(new ColorProperty(R.string.material_green, R.color.material_green));
        colors.add(new ColorProperty(R.string.material_yellow, R.color.material_yellow));
        colors.add(new ColorProperty(R.string.material_orange, R.color.material_orange));
        colors.add(new ColorProperty(R.string.material_brown, R.color.material_brown));
        colors.add(new ColorProperty(R.string.material_grey, R.color.material_grey));
    }

    public LinkedList<Task> createTasks() {
        int numberOfTasks = difficultyConfiguration.numberOfTasks();
        int numberOfAnswers = difficultyConfiguration.numberOfAnswers();
        LinkedList<Task> tasks = new LinkedList<Task>();
        ArrayList<Integer> combinations = generateCombinations();
        String questionDescription = resources.getString(R.string.task_question_read_and_find_shape);
        for (int i = 0; i < numberOfTasks; i++) {
            ArrayList<Item> answers = new ArrayList<Item>();
            ArrayList<Integer> selectedIndexes = numberGenerator.generateUniqueIntegers(0, combinations.size()-1, numberOfAnswers);

            Integer correctCombination = combinations.get(selectedIndexes.get(0));
            ShapeItem correctItem = createShapeItem(correctCombination, true);
            answers.add(correctItem);

            for (int j = 1; j < selectedIndexes.size(); j++) {
                Integer combination = combinations.get(selectedIndexes.get(j));
                ShapeItem item = createShapeItem(combination, false);
                answers.add(item);
            }

            ColorProperty correctColorProperty = colors.get(correctCombination % 100);
            StringBuilder question = new StringBuilder(resources.getString(correctColorProperty.getNameResId()));
            question.append(" ").append(resources.getString(correctItem.getName()));

            Task task = new Task(questionDescription, question.toString(), answers);
            tasks.add(task);
        }
        return tasks;
    }

    protected ArrayList<Integer> generateCombinations() {
        ArrayList<Integer> combinations = new ArrayList();
        for (int color = 0; color < colors.size(); color++) {
            for (int shape = SHAPE_CIRCLE; shape <= SHAPE_TRIANGLE; shape++){
                combinations.add(shape*100 + color);
            }
        }
        return combinations;
    }

    protected ShapeItem createShapeItem(Integer combination, boolean correct) {
        int shapeIndex = combination / 100;
        int colorIndex= combination % 100;

        ColorProperty colorProperty = colors.get(colorIndex);
        int color = resources.getColor(colorProperty.getCodeResId());
        ShapeItem item;
        switch (shapeIndex) {
            case SHAPE_CIRCLE:
                item = new CircleItem(color, correct);
                break;
            case SHAPE_OVAL:
                item = new OvalItem(color, correct);
                break;
            case SHAPE_TRIANGLE:
                item = new TriangleItem(color, correct);
                break;
            case SHAPE_SQUARE:
            default:
                item = new SquareItem(color, correct);
        }

        return item;
    }

}
