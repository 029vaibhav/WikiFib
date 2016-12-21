package com.teamie.wikifib.playscreen;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.bean.WikiResponse;
import com.teamie.wikifib.interfaces.DataExtractor;
import com.teamie.wikifib.interfaces.WordExtractor;

import java.util.List;

import retrofit2.Response;

/**
 * Created by vaibhav on 20/12/16.
 */

public class PlayModel {

    private static PlayModel playModel;

    static PlayModel getInstance() {
        if (playModel == null)
            playModel = new PlayModel();
        return playModel;
    }

    WikiResponse getDataFromServer() {
        DataExtractor dataExtractor = DataExtractorFactory.getInstance().getImplementation();
        Response<WikiResponse> data = dataExtractor.getData();
        if (data.isSuccessful()) {
            WikiResponse body = data.body();
            return isDataBig(body) ? body : getDataFromServer();

        }
        return null;
    }

    private boolean isDataBig(WikiResponse wikiResponse) {

        return true;
    }

    List<GameData> getGameData(String s) {


        WordExtractor
    }


}
