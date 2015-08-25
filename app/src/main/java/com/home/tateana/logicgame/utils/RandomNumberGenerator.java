package com.home.tateana.logicgame.utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tateana on 08-Jul-15.
 */
public class RandomNumberGenerator {

    private Random generator = new Random();


    public Integer generateInteger(int max) {
       return generator.nextInt(max);
    }

    public Integer generateInteger(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }

        if(min == max) {
            return min;
        }

        return generator.nextInt(max - min + 1) + min;
    }

    public ArrayList<Integer> generateUniqueIntegers(int min, int max, int count) {
        if((max - min + 1) < count) {
            throw new RuntimeException("Possible range from" + min + " to " + max + " is shorter then requested count " + count);
        }
        ArrayList<Integer> possibleIntegers = new ArrayList();
        for (int i = min; i <= max; i++) {
            possibleIntegers.add(i);
        }
        return generateUniqueIntegers(possibleIntegers, count);
    }



    public ArrayList<Integer> generateUniqueIntegers(int min, int max, int count, int exception) {
        ArrayList<Integer> possibleIntegers = new ArrayList();
        for (int i = min; i <= max; i++) {
            if(i == exception) {
                continue;
            }
            possibleIntegers.add(i);
        }
        return generateUniqueIntegers(possibleIntegers, count);
    }

    private ArrayList<Integer> generateUniqueIntegers(ArrayList<Integer> possibleIntegers, int count) {
        ArrayList<Integer> selectedIntegers = new ArrayList();
        int indexToTake;
        for (int i = 0; i < count; i++) {
            indexToTake = generator.nextInt(possibleIntegers.size());
            selectedIntegers.add(possibleIntegers.get(indexToTake));
            possibleIntegers.remove(indexToTake);
        }
        return selectedIntegers;
    }
}
