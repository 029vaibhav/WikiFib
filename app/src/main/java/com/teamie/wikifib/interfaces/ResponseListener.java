package com.teamie.wikifib.interfaces;

import retrofit2.Response;

/**
 * Created by vaibhav on 20/12/16.
 */

public interface ResponseListener<T> {

    void onFailure();

    void onSuccess(Response<T> t);
}
