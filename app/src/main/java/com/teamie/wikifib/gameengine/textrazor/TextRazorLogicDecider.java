package com.teamie.wikifib.gameengine.textrazor;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.gameengine.interfaces.LogicDecider;
import com.teamie.wikifib.level.TextRazorLevelDecider;
import com.teamie.wikifib.utils.Constants;
import com.textrazor.annotations.Response;
import com.textrazor.annotations.Sentence;
import com.textrazor.annotations.Word;

import java.util.List;
import java.util.Random;

/**
 * Created by vaibhav on 22/12/16.
 */

public class TextRazorLogicDecider implements LogicDecider<Response> {


    @Override
    public GameData getGameData(Response response, String content) {

//        String[] lines = content.split("[.?\n]");
        StringBuilder modifiedString = new StringBuilder();
        modifiedString.append(content.trim());
        List<String> strings = TextRazorLevelDecider.getInstance().levelCriteria(Constants.level);


        int offset = 0;
        if (strings.size() == 1) {

            for (int i = 0; i < 10; i++) {

//                String line = lines[i];
                List<Word> words = response.getSentences().get(i).getWords();
                Random r = new Random();
                int wordIndex = r.nextInt(words.size());
                Word word = words.get(wordIndex);
                modifiedString.replace(word.getStartingPos() + offset, word.getEndingPos() + offset, "_______");
                offset += 7;

            }


        } else if (strings.size() == 2) {

        } else if (strings.size() == 3) {

        }


        List<Sentence> sentences = response.getSentences();
        for (Sentence sentence : sentences) {
//            sentence.
//                    String s = sentence.toString();
//            System.out.println(s);

        }


        return null;
    }
}
