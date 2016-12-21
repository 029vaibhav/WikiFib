package com.teamie.wikifib.level;

import com.textrazor.annotations.Relation;
import com.textrazor.annotations.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaibhav on 21/12/16.
 */

public class TextRazorLevelDecider implements LevelDecider {

    public static TextRazorLevelDecider decider;
    public static String WORD = "words";
    public static String ENTITIES = "entities";
    public static String RELATIONS = "relations";


    public static TextRazorLevelDecider getInstance() {

        if (decider == null) {
            decider = new TextRazorLevelDecider();
        }
        return decider;
    }

    @Override
    public List<String> levelCriteria(int level) {

        List<String> strings = new ArrayList<>();
        if (level < 5) {
            strings.add(WORD);
        } else if (level < 10) {
            strings.add(WORD);
            strings.add(ENTITIES);
        } else if (level < 10) {
            strings.add(WORD);
            strings.add(ENTITIES);
            strings.add(RELATIONS);
        }
        return strings;
    }


    @Override
    public List<String> wordsAsFib(Object... params) {

        Response response = (Response) params[0];
        List<String> strings = (List<String>) params[0];


        List<String> words = new ArrayList<>();
        int size = strings.size();
        if (size == 3) {

            List<Relation> relations = response.getRelations();

        } else if (size == 2) {

        } else if (size == 1) {

        }
        return words;
    }
}
