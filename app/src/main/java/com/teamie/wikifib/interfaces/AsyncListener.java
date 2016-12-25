package com.teamie.wikifib.interfaces;

/**
 * Created by vaibhav on 25/12/16.
 */

public interface AsyncListener<T> {

    void onPreExecute();

    void onPostExecute(T gameData);

    T doInBackground();
}
