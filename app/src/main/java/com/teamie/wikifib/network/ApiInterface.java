package com.teamie.wikifib.network;

import com.teamie.wikifib.bean.WikiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vaibhav on 20/12/16.
 */
public interface ApiInterface {

    @GET("?format=json&action=query&generator=random&grnnamespace=0&prop=extracts&explaintext=rvprop=title&grnlimit=1")
    Call<WikiResponse> getData();


}