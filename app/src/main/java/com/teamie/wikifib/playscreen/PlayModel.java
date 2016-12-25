package com.teamie.wikifib.playscreen;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.bean.MyWord;
import com.teamie.wikifib.bean.WikiResponse;
import com.teamie.wikifib.gameengine.factories.DataExtractorFactory;
import com.teamie.wikifib.gameengine.interfaces.DataExtractor;
import com.teamie.wikifib.utils.Constants;
import com.teamie.wikifib.utils.Utilities;

import java.text.BreakIterator;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;

import static com.teamie.wikifib.utils.Constants.LEVEL_KEY;

/**
 * Created by vaibhav on 20/12/16.
 */

public class PlayModel {

    private static PlayModel playModel;

    public static PlayModel getInstance() {
        if (playModel == null)
            playModel = new PlayModel();
        return playModel;
    }

    String getDataFromServer() {
        DataExtractor dataExtractor = DataExtractorFactory.getInstance().getImplementation();
        Response<WikiResponse> data = dataExtractor.getData();
        if (data != null)
            if (data.isSuccessful()) {
                String body = data.body().getQuery().getPages().entrySet().iterator().next().getValue().getExtract()
                        .replaceAll("==\\s[A-za-z1-9\\s]+\\s==", "").trim().replace("\n", "").replace("=", "");
                return isDataBig(body) ? body : getDataFromServer();


            }
        return null;
    }

    private boolean isDataBig(String wikiResponse) {

        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(Locale.US);
        sentenceIterator.setText(wikiResponse);
        int i = 0;
        while (sentenceIterator.next() != -1) {
            i++;
            sentenceIterator.next();
            if (i == 10)
                break;
        }

        return i == 10;
    }

    List<GameData> getGameData(String s) {


        return null;
    }

    public int getLevel() {
        return Utilities.getInstance().getFromSharedPreference(LEVEL_KEY);
    }

    public void setLevel(int count) {
        if (count == 10) {
            Constants.level++;
            Utilities.getInstance().saveInSharedPreference(LEVEL_KEY, Constants.level);
        }

    }


    public void validate(GameData gameData) {

        String content = "";
        int count = 0;

        for (int i = 0; i < 10; i++) {

            String s = gameData.getTextViewHashMap().get(i);
            MyWord userSelectedWord = gameData.getUserSelectedWords().get(i);
            String correctWord = gameData.getEditTextHashMap().get(i);

            if (correctWord.equals(userSelectedWord.getText()))
                count++;


        }


    }
}
