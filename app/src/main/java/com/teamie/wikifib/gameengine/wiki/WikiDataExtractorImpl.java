package com.teamie.wikifib.gameengine.wiki;

import com.teamie.wikifib.bean.WikiResponse;
import com.teamie.wikifib.gameengine.interfaces.DataExtractor;
import com.teamie.wikifib.network.ApiClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by vaibhav on 20/12/16.
 */

public class WikiDataExtractorImpl implements DataExtractor {


    @Override
    public Response<WikiResponse> getData() {
        final Call<WikiResponse> data = ApiClient.getInstance().getService().getData();
        try {
            return data.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
