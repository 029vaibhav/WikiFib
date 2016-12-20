package com.teamie.wikifib.playscreen;

import com.teamie.wikifib.bean.WikiResponse;
import com.teamie.wikifib.interfaces.DataExtractor;

import retrofit2.Response;

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

    public WikiResponse getDataFromServer() {
        DataExtractor dataExtractor = new DataExtractorImpl();
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
    

}
