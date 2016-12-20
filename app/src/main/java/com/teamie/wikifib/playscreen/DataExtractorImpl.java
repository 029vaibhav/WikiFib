package com.teamie.wikifib.playscreen;

import com.teamie.wikifib.bean.WikiResponse;
import com.teamie.wikifib.interfaces.DataExtractor;
import com.teamie.wikifib.network.ApiClient;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by vaibhav on 20/12/16.
 */

public class DataExtractorImpl implements DataExtractor {


    @Override
    public Response<WikiResponse> getData() {
        final Call<WikiResponse> data = ApiClient.getInstance().getService().getData();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable callable = new Callable() {
            @Override
            public Response<WikiResponse> call() throws Exception {
                return data.execute();
            }
        };

        Future submit = executorService.submit(callable);
        try {
            return (Response<WikiResponse>) submit.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

//        data.enqueue(new Callback<WikiResponse>() {
//            @Override
//            public void onResponse(Call<WikiResponse> call, Response<WikiResponse> response) {
//                callback.onSuccess(response);
//            }
//
//            @Override
//            public void onFailure(Call<WikiResponse> call, Throwable t) {
//                callback.onFailure();
//            }
//        });
        return null;

    }
}
