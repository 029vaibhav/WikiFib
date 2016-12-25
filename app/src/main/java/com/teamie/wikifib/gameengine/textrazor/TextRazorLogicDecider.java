package com.teamie.wikifib.gameengine.textrazor;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.bean.MyWord;
import com.teamie.wikifib.gameengine.interfaces.LogicDecider;
import com.teamie.wikifib.utils.Constants;
import com.textrazor.annotations.Entity;
import com.textrazor.annotations.Response;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.teamie.wikifib.utils.Constants.BLANK;

/**
 * Created by vaibhav on 22/12/16.
 */

public class TextRazorLogicDecider implements LogicDecider<Response> {

    int first;
    int next;

    @Override
    public GameData getGameData(Response response, String content) {

        GameData gameData = new GameData();
        gameData.setText(content);
//        List<String> strings = TextRazorLevelDecider.getInstance().levelCriteria(Constants.level);
        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(Locale.US);
        sentenceIterator.setText(content);
        first = sentenceIterator.first();
        next = sentenceIterator.next();
//        if (strings.size() == 1) {
//            for (int i = 0; i < 10; i++) {
//                String line = content.substring(first, next);
//                defaultSelection(line, gameData, i, response, sentenceIterator);
//            }
//
//
//        } else if (strings.size() > 1) {

        List<Entity> entities = response.getEntities();
        Set<String> entitySet = new HashSet<>();
        for (Entity entity : entities) {
            entitySet.add(entity.getEntityId());
        }
        int entitiesSelected = 0;
        for (int i = 0; i < 10; i++) {
            String line = content.substring(first, next);
            if (entitiesSelected != Constants.level) {
                String word = containsWord(line, entitySet);
                if (word != null) {
                    fillGameData(line, word, gameData, i);
                    entitySet.remove(word);
                    nextSentenceIterate(sentenceIterator, next);
                    entitiesSelected++;
                } else {
                    defaultSelection(line, gameData, i, response, sentenceIterator);
                }
            } else {
                defaultSelection(line, gameData, i, response, sentenceIterator);
            }
        }

//        }
        return gameData;
    }

    private void defaultSelection(String line, GameData gameData, int i, Response response, BreakIterator sentenceIterator) {
        line = line.trim();
        String word = containsWord(line, Constants.prepAndConjunctions);
        if (word != null) {
            fillGameData(line, word, gameData, i);

        } else {
            BreakIterator wordIterator = BreakIterator.getWordInstance(Locale.US);
            wordIterator.setText(line);
            List<String> words = new ArrayList<>();
            int first1 = wordIterator.first();
            for (int wend = wordIterator.next(); wend != BreakIterator.DONE; first1 = wend, wend = wordIterator.next()) {
                word = line.substring(first1, wend);
                if (word.length() > 1)
                    words.add(word);
            }

            Random r = new Random();
            int selectedWordPos = r.nextInt(words.size());
            word = words.get(selectedWordPos);
            fillGameData(line, word, gameData, i);
        }
        nextSentenceIterate(sentenceIterator, next);

    }

    private void nextSentenceIterate(BreakIterator sentenceIterator, int next) {
        if (sentenceIterator.next() != -1) {
            this.first = next;
            this.next = sentenceIterator.next();
        }
    }

    private void fillGameData(String line, String word, GameData gameData, int index) {

        Pattern pattern = Pattern.compile("\\b" + word + "\\b");
        Matcher matcher = pattern.matcher(line);
        int startPos = 0;
        if (matcher.find(0)) {
            startPos = matcher.start();

        }
        int endPos = startPos + word.length();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(line);
        stringBuilder.replace(startPos, endPos, BLANK);
        gameData.getTextViewHashMap().put(index, stringBuilder.toString());
        gameData.getRemovedWords().put(index, new MyWord(startPos, endPos, word));
        gameData.getEditTextHashMap().put(index, word);


    }


    private String containsWord(String line, Collection<String> strings) {

        for (String s : strings) {
            if (line.contains(" " + s + " ")) {
                return s;
            }
        }
        return null;
    }
}
