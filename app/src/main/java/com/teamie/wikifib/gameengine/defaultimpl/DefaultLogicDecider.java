package com.teamie.wikifib.gameengine.defaultimpl;

import android.util.Log;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.bean.MyWord;
import com.teamie.wikifib.gameengine.interfaces.LogicDecider;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.teamie.wikifib.utils.Constants.BLANK;

/**
 * Created by vaibhav on 22/12/16.
 */

public class DefaultLogicDecider implements LogicDecider<String> {


    @Override
    public GameData getGameData(String response, String content) {

        GameData gameData = new GameData();
        gameData.setText(content);

        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(Locale.US);
        BreakIterator wordIterator = BreakIterator.getWordInstance(Locale.US);
        sentenceIterator.setText(content);
        int startPos = 0;
        int first = sentenceIterator.first();
        int next = sentenceIterator.next();
        for (int i = 0; i < 10; i++) {
            String line = content.substring(first, next);
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }

            List<String> words = new ArrayList<>();
            wordIterator.setText(line);
            int first1 = wordIterator.first();
            for (int wend = wordIterator.next(); wend != BreakIterator.DONE; first1 = wend, wend = wordIterator.next()) {
                String word = line.substring(first1, wend);
                if (word.length() > 1)
                    words.add(word);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(line);
            Log.d("individual lines : ", stringBuilder.toString());
            Random r = new Random();
            int selectedWordPos = r.nextInt(words.size());
            String word = words.get(selectedWordPos);

            Pattern pattern = Pattern.compile("\\b" + word + "\\b");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find(0))
                startPos = matcher.start();
            int endPos = startPos + word.length();
            stringBuilder.replace(startPos, endPos, BLANK);
            gameData.getTextViewHashMap().put(i, stringBuilder.toString());
            gameData.getRemovedWords().put(i, new MyWord(startPos, endPos, word));
            gameData.getEditTextHashMap().put(i, word);

            if (sentenceIterator.next() != -1) {
                first = next;
                next = sentenceIterator.next();
            }

        }
        return gameData;
    }


}
