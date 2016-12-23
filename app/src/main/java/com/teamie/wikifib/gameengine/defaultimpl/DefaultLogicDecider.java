package com.teamie.wikifib.gameengine.defaultimpl;

import android.util.Log;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.bean.MyWord;
import com.teamie.wikifib.gameengine.interfaces.LogicDecider;

import java.util.Random;

import static com.teamie.wikifib.utils.Constants.BLANK;

/**
 * Created by vaibhav on 22/12/16.
 */

public class DefaultLogicDecider implements LogicDecider<String> {


    @Override
    public GameData getGameData(String response, String content) {

        GameData gameData = new GameData();
        gameData.setText(content);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(content);
        Log.d("initial: ", stringBuilder.toString());

        String[] lines = content.split("[.?\n]");
        int x = 0, linePos = 0, startPos;

        for (int i = 0; i < 10; i++) {
            lines[i] = lines[i].trim();
            if (lines[i].isEmpty()) {
                continue;
            }
            String[] words = lines[i].split("\\s+");

            Random r = new Random();
            int selectedWordPos = r.nextInt(words.length);

            startPos = stringBuilder.indexOf(words[selectedWordPos], linePos);

            int endPos = startPos + words[selectedWordPos].length();
            gameData.getRemovedWords().put(i, new MyWord(startPos, endPos, words[selectedWordPos]));
            stringBuilder.replace(startPos, endPos, BLANK);
            linePos += lines[i].length() + 1;
        }
        gameData.setText(stringBuilder.toString());
        return gameData;
    }
}
