package com.teamie.wikifib.network;

import android.content.Context;
import android.os.AsyncTask;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.interfaces.AsyncListener;
import com.teamie.wikifib.playscreen.PlayPresenter;

/**
 * Created by vaibhav on 25/12/16.
 */

public class DataFromServerAsync extends AsyncTask<Void, Void, GameData> {

    AsyncListener<GameData> asyncListener;
    Context context;

    public DataFromServerAsync(AsyncListener asyncListener, Context context) {

        this.asyncListener = asyncListener;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        asyncListener.onPreExecute();
    }

    @Override
    protected GameData doInBackground(Void... voids) {
        return asyncListener.doInBackground();
    }

    @Override
    protected void onPostExecute(GameData gameData) {
        super.onPostExecute(gameData);
        asyncListener.onPostExecute(gameData);
    }
}
